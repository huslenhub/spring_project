<%--
  Created by IntelliJ IDEA.
  User: imac24
  Date: 25. 3. 19.
  Time: 오후 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>loginPage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login/style.css">
</head>
<body>


<jsp:include page="../common/header.jsp"/>
    <div id="login-container-wrapper">
        <div id="login_container">
            <h2>login</h2>

            <!--login실패시-->
            <c:if test="${not empty param.error}">
                <p style="color: red;">id or password incorrect</p>
            </c:if>



            <form action="${pageContext.request.contextPath}/login" method="post" >
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="input-group">
                    <label for="username">ID</label>
                    <input type="text" id="username" name="username" required/>
                </div>
                <div class="input-group">
                    <label for="password">password</label>
                    <input type="password" id="password" name="password" required/>
                </div>
                <button type="submit" id="login-button">login</button>
            </form>
            <div id="register-link">
                <!-- a 태그는 페이지 이동, pageContext.request.contextPath  는 최상위 root 경로 아마 localhost:8080-->
                <a href="${pageContext.request.contextPath}/registerPage">sign up</a>
            </div>
        </div>
    </div>

<jsp:include page="../common/footer.jsp"/>


</body>
</html>
