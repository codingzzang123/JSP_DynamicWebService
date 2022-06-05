<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
<jsp:include page="/temp.jsp"></jsp:include>
<div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-5">
            <form action="${pageContext.request.contextPath }/user/JoinAction.do" method="post" name="JoinForm" enctype="multipart/form-data">
                <div class="container mt-5 mb-5" style="width: 60%; font-weight: bold; font-size: 20px;">
                    <img class="mb-4" src="/mvc/img/user/Login.png" alt="logo" width="100" height="100">
                    <h1><b>회원가입</b></h1>
                    <div class="mb-3 mt-5">
                        <label for="exampleInputEmail1" class="form-label">ID</label>
                        <input type="text" name="id" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                    </div>
                    <div class="mt-4 text-end">
                    	<input type="button" value="ID중복체크" onclick="#" class="btn btn-danger" style="font-weight: bold;"/>                    
                    </div>	
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">PASSWORD</label>
                        <input type="password" name="pw" class="form-control" id="exampleInputPassword1" required>
                    </div>
                    
                    <div class="mb-3 mt-3">
                        <label for="exampleInputPassword1" class="form-label">NAME</label>
                        <input type="text" name="name" class="form-control" id="exampleInputPassword1" required>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">AGE</label>
                        <input type="number" name="age" class="form-control" id="exampleInputPassword1" required>
                    </div>
                    
                    
                    
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">COMMENT</label>
                        <textarea class="form-control" name="comment" ></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">USERPIC</label>
                        <input type="file" class="form-control" name="upic" id="exampleInputPassword1">
                    </div>
                    <div class="mt-4 text-end">
                    	<input type="button" value="회원가입" onclick="inputCheck()" class="btn btn-dark" style="font-weight: bold;"/>                    
                    </div>
                </div>
            </form>
        </div>
    </div>
	<script>
	    function inputCheck(){
	    if(document.JoinForm.id.value==""){
	        alert("아이디를 입력해 주세요.");
	        document.regForm.id.focus();
	        return;
	    }
	    if(document.JoinForm.pw.value==""){
	        alert("비밀번호를 입력해 주세요.");
	        document.regForm.pw.focus();
	        return;
	    }
	    if(document.JoinForm.age.value==""){
	        alert("나이를 입력해 주세요.");
	        document.regForm.age.focus();
	        return;
	    }
	    if(document.JoinForm.name.value==""){
	        alert("이름를 입력해 주세요.");
	        document.regForm.name.focus();
	        return;
	    }
	    if(document.JoinForm.comment.value==""){
	        alert("소개를 입력해 주세요.");
	        document.regForm.comment.focus();
	        return;
	    }
	    document.JoinForm.submit();
	}
    </script>
</body>
</html>