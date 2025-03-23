
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>new post</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/noticeAdd/style.css"/>

  </head>
  <body>
  <jsp:include page="../common/header.jsp"/>

  <form id="menuForm">
    <div id="container">
      <div id="menuAdmin">
        <h2 id="menuAdminH2">공지사항 작성</h2>
        <br>
        <label for="memID">회원아이디</label>
        <input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${username}" readonly>
        <br>
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목" maxlength="20">
        <br>
        <label for="content">내용</label>
        <input type="text" id="content" name="content" placeholder="내용" maxlength="200">
        <br>
        <label for="writer">작성자</label>
        <input type="text" id="writer" name="writer" placeholder="작성자" maxlength="200" value="${writer}" readonly>
        <br>

        <input type="hidden" id="indate" name="indate">



        <button type="button" id="buttonSubmit">확인</button>


      </div>
    </div>
  </form>

  <jsp:include page="../common/footer.jsp"/>

  <script src="${pageContext.request.contextPath}/resources/js/noticeAdd/script.js"></script>
  </body>
</html>
