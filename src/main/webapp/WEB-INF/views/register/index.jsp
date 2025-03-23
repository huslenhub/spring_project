<%--
  Created by IntelliJ IDEA.
  User: imac24
  Date: 25. 3. 19.
  Time: 오후 4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/register/style.css">

</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div id="register-container-wrapper">
    <div class="register-container">
        <h2>Sign Up</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="input-group">
                <label for="writer">Writer:</label>
                <input type="text" id="writer" name="writer" required>
            </div>

            <!--csrf 적용 필요-->


            <div class="input-group">
                <button type="submit" class="register-button">Sign Up</button>
            </div>

            <div class="login-link">
                <a href="${pageContext.request.contextPath}/loginPage">already has account</a>
            </div>

        </form>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>

</body>
</html>
