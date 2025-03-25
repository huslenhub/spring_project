//DOM 연결 tag 들을 연결

const container = document.getElementById("container");
const menuAdmin = document.getElementById("menuAdmin");
const menuList = document.getElementById("menuList");


const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

//  데이터를 조회할때 사용할 기능 정의
function fetchMenus(){
    fetch("/menu/all").then(response => response.json())
        .then(menus =>{
           menuList.innerHTML = '';
           menus.forEach(menu =>{
               const menuItem = document.createElement('div');
               menuItem.className = 'menu-tiem';
               menuItem.innerHTML = `
               <a href="#" class="menu-link" style="text-decoration: none; color:black;">
                    <h3>${menu.title}</h3>
                    <p>${menu.content}</p>
                    <small>작성자:${menu.writer},작성일:${menu.indate},조회수:${menu.count},</small>
               </a>     
               <br/>
               <br/>
               `

               menuItem.querySelector(".menu-link").addEventListener('click', (event) =>{
                   event.preventDefault();
                   console.log(`event:${event}`);

                   incrementCount(menu.idx).then(()=> window.location.href=`/noticeCheckPage?idx=${menu.idx}`)
               });
                    menuList.appendChild(menuItem);

           })
        })
}

function incrementCount(idx){
    return fetch(`/menu/count/${idx}`,{
        method:'PUT',
        headers:{
            [csrfHeader]: csrfToken
        }
    })
        .then(response => {
            if(!response.ok){
                console.log("데이터가 프런트에서 백으로 안 넘어갔다");
            }
        })
        .catch(error => {
            console.log(`ERROR: ${error}`);
        })
}


window.addEventListener("load",fetchMenus());

