window.onload = function(){
    checkUser();
};

function checkUser(){
    const isLogin = localStorage.getItem("isLogin");
    const role = localStorage.getItem("role");
    if(isLogin !== "true"){
        location.href = "/login.html";
        return;
    }

    const mainTitle = document.getElementById("mainTitle");
    const loginUser = document.getElementById("loginUser");
    const adminInputBox = document.getElementById("adminInputBox");
    const adminMenu = document.getElementById("adminMenu");
    const roleLogo = document.getElementById("roleLogo");
    const roleBadge = document.getElementById("roleBadge");
    const mainBody = document.getElementById("mainBody");
    const adminSearchMenu = document.getElementById("adminSearchMenu");

    if(role === "admin"){
        if(mainTitle !== null){
            mainTitle.textContent = "학생 관리 시스템 (관리자)";
        }
        if(loginUser !== null){
            loginUser.textContent = "관리자";
        }
        if(roleLogo !== null){
            roleLogo.src = "/images/admin-logo.png";
        }
        if(adminInputBox !== null){
            adminInputBox.style.display = "block";
        }
        if(adminSearchMenu !== null){
            adminSearchMenu.style.display = "flex";
        }
        if(adminMenu !== null){
            adminMenu.style.display = "block";
        }
        if(roleBadge !== null){
            roleBadge.textContent = "ADMIN";
        }
        if(mainBody !== null){
            mainBody.className = "admin-theme";
        }
    }else if(role === "user"){
        if(mainTitle !== null){
            mainTitle.textContent = "학생 관리 시스템";
        }
        if(loginUser !== null){
            loginUser.textContent = "일반 사용자";
        }
        if(roleLogo !== null){
            roleLogo.src = "/images/user-logo.png";
        }
        if(adminInputBox !== null){
            adminInputBox.style.display = "none";
        }
        if(adminSearchMenu !== null){
            adminSearchMenu.style.display = "none";
        }
        if(adminMenu !== null){
            adminMenu.style.display = "none";
        }
        if(roleBadge !== null){
            roleBadge.textContent = "USER";
        }
        if(mainBody !== null){
            mainBody.className = "user-theme";
        }
    }

    if(adminMenu !== null){
        console.log("adminMenu display =", adminMenu.style.display);
    }
}

function logout(){
    localStorage.removeItem("isLogin");
    localStorage.removeItem("role");
    location.href="/login.html";
}

function showResult(data){
    if(typeof data === "object"){
        document.getElementById("result").textContent =
            JSON.stringify(data, null, 2);
    } else {
        document.getElementById("result").textContent = data;
    }
}

function getAllStudents(){
    fetch('/students')
        .then(response => response.json())
        .then(data => { showResult(data); }
    );
}

function getStudentByID(){
    const id = document.getElementById("id").value;
    if(id === ""){
        alert("학생번호를 입력하세요.");
        return;
    }

    fetch('/students/search/' + encodeURIComponent(id))
        .then(response => response.text())
        .then(data => {
            try{
                showResult(JSON.parse(data));
            } catch{
                showResult(data);
            }
        }
    );
}

function getStudentByName(){
    const name = document.getElementById("name").value;
    if(name === ""){
        alert("이름을 입력하세요.");
        return;
    }

    fetch('/students/search/name?name=' + encodeURIComponent(name))
        .then(response => response.text())
        .then(data => {
            try{
                showResult(JSON.parse(data));
            } catch{
                showResult(data);
            }
        }
    );
}

function getStudentByDepartment(){
    const department = document.getElementById("department").value;
    if(department === ""){
        alert("학과를 입력하세요.");
        return;
    }

    fetch('/students/search?department='+encodeURIComponent(department))
        .then(response => response.json())
        .then(data => {
            showResult(data);
        }
    );
}

function addStudent(){
    const id = document.getElementById("id").value;
    const name = document.getElementById("name").value;
    const department = document.getElementById("department").value;

    if(id === "" || name === "" || department === ""){
        alert("학생번호, 이름, 학과를 모두 입력하세요.");
        return;
    }

    const url = '/students/new'+'?id='+id+'&name='+encodeURIComponent(name)+'&department='+encodeURIComponent(department);

    fetch(url, {
        method: 'POST'
    })
        .then(response => response.text())
        .then(data => {
            try{
                showResult(JSON.parse(data));
            } catch{
                showResult(data);
            }
        }
    );
}

function updateStudent(){
    const id = document.getElementById("id").value;
    const name = document.getElementById("name").value;
    const department = document.getElementById("department").value;

    if(id === "" || name === "" || department === ""){
        alert("학생번호, 이름, 학과를 모두 입력하세요.");
        return;
    }

    const url = '/students/'+id+'?name='+encodeURIComponent(name)+'&department='+encodeURIComponent(department);

    fetch(url, {
        method: 'PUT'
    })
        .then(response => response.text())
        .then(data => {
            try{
                showResult(JSON.parse(data));
            } catch{
                showResult(data);
            }
        }
    );
}

function deleteStudent(){
    const id = document.getElementById("id").value;
    if(id === ""){
        alert("삭제할 학생번호를 입력하세요.");
        return;
    }

    fetch('/students/'+id,{method: 'DELETE'})
        .then(response => response.text())
        .then(data => {
            showResult(data);
        }
    );
}

// 결과를 텍스트 파일로 저장
function saveResultFile(){
    const result = document.getElementById("result").textContent;
    if(result.trim() === ""){
        alert("저장할 결과가 없습니다.");
        return;
    }
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day = String(now.getDate()).padStart(2, "0");
    const hour = String(now.getHours()).padStart(2, "0");
    const minute = String(now.getMinutes()).padStart(2, "0");
    const second = String(now.getSeconds()).padStart(2, "0");

    const fileName = "studentsInf_"+year+month+day+"_"+hour+minute+second+".json";
    const blob = new Blob([result], { type: "application/json;charset=utf-8" });
    const link = document.createElement("a");
    link.href=URL.createObjectURL(blob);
    link.download = fileName;
    link.click()
}

function getStudentGrade(){
    const name = document.getElementById("name").value.trim();
    const department = document.getElementById("department").value.trim();

    if(name === "" && department === ""){
        alert("이름과 학과를 모두 입력하세요");
        return;
    }

    fetch('/students/search/grade/' + encodeURIComponent(name) + '?department=' + encodeURIComponent(department))
        .then(response => response.text())
        .then(data => {
            try{
                showResult(JSON.parse(data));
            } catch{
                showResult(data);
            }
        }
    );

}

function getStudentCount(){
    fetch('/students/count')
        .then(response => response.text())
        .then(data => {
            showResult("전체 학생 수 : "+data+"명");
        });
}

function getDepartmentCount(){
    const department = document.getElementById("department").value;
    if(department.trim() === ""){
        alert("학과를 입력하세요.");
        return;
    }
    fetch('/students/count?department?department='+encodeURIComponent(department))
        .then(response => response.text())
        .then(data => {
            showResult(department + " 학생 수 : " + data + "명");
        }
    );
}