package com.canesblack.spring_project1.mapper;

import com.canesblack.spring_project1.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
//자동으로  @component 와 비슷하게 스피링 컨터에너에 인터페이스 등록
//자바언어 mysql 언어 사이 번역
public interface UserMapper {

    //crud 에 crate
    @Insert("INSERT INTO backend_spring_project.user(username,password,writer,role)VALUES(#{username},#{password},#{writer},#{role})")
    void insertUser(User user);


    //crud 에 read
    @Select("SELECT username,password,writer,role FROM backend_spring_project.user WHERE username=#{username}")
    User findByUsername(String username);

    @Select("SELECT writer FROM backend_spring_project.user WHERE username=#{username}")
    String findWriter(String username);
//    //crud 에 update
//    @Delete()
//    //crud 에 delete
//    @Update()
}
