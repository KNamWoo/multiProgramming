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

    const loginUser = document.getElementById("loginUser");
    const adminMenu = document.getElementById("adminMenu");

    if(role === "admin"){
        if(loginUser !== null){
            loginUser.textContent = "관리자";
        }
        if(adminMenu !== null){
            adminMenu.style.display = "block";
        }
    }else if(role === "user"){
        if(loginUser !== null){
            loginUser.textContent = "일반 사용자";
        }
        if(adminMenu !== null){
            adminMenu.style.display = "none";
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