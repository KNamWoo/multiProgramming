function login(){
    const userID = document.getElementById("userID").value;
    const password = document.getElementById("password").value;
    if(userID === "admin" && password==="1234"){
        localStorage.setItem("isLogin", "true");
        localStorage.setItem("role", "admin");
        alert("관리자 권한으로 로그인 완료");
        location.href = "/students.html"
        return;
    }

    if(userID === "user" && password === "1234"){
        localStorage.setItem("isLogin", "true");
        localStorage.setItem("role", "user");
        alert("User로 로그인");
        location.href = "/students.html"
        return;
    }

    document.getElementById("message").textContent = "아이디 또는 비밀번호가 올바르지 않습니다.";
}