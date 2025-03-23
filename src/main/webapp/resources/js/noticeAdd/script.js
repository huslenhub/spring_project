document.getElementById("buttonSubmit").addEventListener("click", function() {
    const formData = {
        memID: document.getElementById("memID").value,
        title: document.getElementById("title").value,
        content: document.getElementById("content").value,
        writer: document.getElementById("writer").value,
    }


//index 파일에서 만든 메타 csrf tag 2개 js 로 가져온다
    const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
    const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

    fetch("/menu/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify(formData)
    }).then(response => {
        if (!response.ok) {
            throw new Error("공지사항 작성 실패");
        }
        return response.text();
    }).then(data  => {
        console.log("Success");
        window.location.href = "/";
    }).catch(error => {
        console.log("에러 발생", error);
    })
});