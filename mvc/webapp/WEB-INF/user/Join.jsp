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
                    
                    
                    <div class="mb-3 mt-5 form-group">
                        <label for="user_id" class="form-label">아이디</label>
                        <input type="text" id="user_id" name="user_id" class="form-control" placeholder="ID" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                    </div>
                    <div class="check_font" id="id_check"><!-- 경고문 들어가는곳  --></div>
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
                    	<input type="button" value="회원가입" id="reg_submit" onclick="inputCheck()" class="btn btn-dark" style="font-weight: bold;"/>                    
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
	    
	   $("#user_id").blur(function(){
		  var user_id =$('#user_id').val();
		  $.ajax({
			  url : '${pageContext.request.contextPath}/user/idCheck.do?userId='+ user_id,
			  type : 'get',
			  success : function(data){
				  console.log("1 = 중복o / 0 = 중복x : "+ data);
				  
				  if (data == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#id_check").text("사용중인 아이디입니다 :p");
						$("#id_check").css("color", "red");
						$("#reg_submit").attr("disabled", true);
					} else {
						
						if(idJ.test(user_id)){
							// 0 : 아이디 길이 / 문자열 검사
							$("#id_check").text("");
							$("#reg_submit").attr("disabled", false);
				
						} else if(user_id == ""){
							
							$('#id_check').text('아이디를 입력해주세요 :)');
							$('#id_check').css('color', 'red');
							$("#reg_submit").attr("disabled", true);				
							
						} else {
							
							$('#id_check').text("아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
							$('#id_check').css('color', 'red');
							$("#reg_submit").attr("disabled", true);
						}
						
					}
				}, error : function() {
						console.log("실패");
				}
			});
		});
    </script>
</body>
</html>