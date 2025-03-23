package com.canesblack.spring_project1.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    //spring security 사용하고자 할때 이렇게 한다.
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        //해킹 기법으로 보호조치를 하는 방법, 자바스크립트로 cookie 접근 막음(withHttpOnlytrue) 나중에 js에 csrf기능 넣을 것이다.
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))//특정 도메인으로 데이터 주고 받을 수 있도록 설정하는것
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))//세션 설정 필요시 생성
                .authorizeHttpRequests(authz ->authz.requestMatchers("/","/loginPage","/logout","/noticeCheckPage","/registerPage","/menu/all")
                .permitAll()
                .requestMatchers(HttpMethod.POST,"/login","/registerPage").permitAll()
                .requestMatchers(HttpMethod.POST,"/register").permitAll()
                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/WEB-INF/**").permitAll()
                .requestMatchers("/noticeAddPage","/noticeModifyPage").hasAnyAuthority("ADMIN","MANAGER")
                .requestMatchers(HttpMethod.POST,"/menu/add").hasAnyAuthority("ADMIN","MANAGER")
                .requestMatchers(HttpMethod.POST,"/menu/update").hasAnyAuthority("ADMIN","MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/menu/delete").hasAnyAuthority("ADMIN","MANAGER")
                .anyRequest().authenticated()
        )//이 주소들에 누구나 접근 가능



        .formLogin(
                login -> login.loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .failureHandler(authenticationFailureHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .permitAll()
        )
        .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")//로그아웃시 이동
                .invalidateHttpSession(true)//세션무효화
                .deleteCookies("JSESSIONID")//쿠키삭제
                .permitAll()
        );
        return http.build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/loginPage?error=true");
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();
                boolean isManager = authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ADMIN")||
                        grantedAuthority.getAuthority().equals("MANAGER"));
                if (isManager) {
                    session.setAttribute("MANAGER", true);
                }
                //세션에 로그인 아이디 저장
                session.setAttribute("username", authentication.getName());
                //세션에 로그인 상태 저장
                session.setAttribute("isAuthenticated", true);
                response.sendRedirect(request.getContextPath() + "/");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
//password 를 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:8080"));//8080안에서 데이터 주고 받을 수 있게 해준 것
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));//이렇게 해줌
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
