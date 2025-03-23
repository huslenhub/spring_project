//DOM 연결 tag 들을 연결

const container = document.getElementById("container");
const menuAdmin = document.getElementById("menuAdmin");
const menuList = document.getElementById("menuList");

//csrf token header 가져오기
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
const csrfHeader = document.querySelector("meta[name='_csrfHeader']").getAttribute("content");

