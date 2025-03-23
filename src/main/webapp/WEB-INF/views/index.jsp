<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>카네스블랙 카페</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>

<%@include file="common/header.jsp" %>

    <div id="container">
        <div id="menuAdmin">
            <h2 id="menuAdminH2">공지사항</h2>
            <!--jstl을 사용하여 세션 변수 셋팅-->
            <!--매니저일때 수정 버튼 보이기-->
            <c:if test="${MANAGER == true}">
                <button type="button" onclick="location.href=`${pageContext.request.contextPath}/noticeAddPage`">edit</button>
            </c:if>
            <div id="menuList">

            </div>
        </div>
    </div>
<%@include file="common/footer.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</body>
</html>
