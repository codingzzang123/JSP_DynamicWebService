<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/temp.jsp"></jsp:include>
	<c:if test="${loginID eq null }"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	
	<div class="container mt-5 mb-5" style="font-size: 20px; font-weight: bold; width: 80%;">
		<h1><b>게시글 작성</b></h1>
		<form method="post" enctype="multipart/form-data" name="createForm" action="${pageContext.request.contextPath }/board/writeInsert.do">
		    <div class="mb-3 mt-5">
		        <label class="form-label">SUBJECT</label>
		        <input type="text" class="form-control" name="sub">
		    </div>
		    <div class="mb-3">
                 <label class="form-label">PASSWORD</label>
                 <input type="password" class="form-control" name="pass" value="1234">
            </div>
		    <div class="mb-3">
		        <label class="form-label">WRITER</label>
		        <input type="text" class="form-control" value="${loginID }" disabled>
		    	<input type="hidden" name="maker" value="${loginID }">
		    </div>
		    <div class="mb-3">
		        <label class="form-label">CONTENT</label>
		        <textarea class="form-control" style="height: 200px;" name="con"></textarea>
	        </div>
	        
	        <div class="mb-3">
                 <label class="form-label">UploadFile</label>
                 <input type="file" class="form-control" name="file" >
            </div>
            
            <div class="text-end mt-3">
            		<input type ="button" class="btn btn-dark" value="생성" onclick="inputCheck()">
	        </div>
	    </form>
	</div>
	<script>
		function inputCheck(){
		    if(document.createForm.sub.value==""){
		        alert("글 제목을 입력해 주세요.");
		        document.regForm.sub.focus();
		        return;
		    }
		    if(document.createForm.pass.value==""){
		        alert("비밀번호를 입력해 주세요.");
		        document.regForm.pass.focus();
		        return;
		    }
		    if(document.createForm.con.value==""){
		        alert("내용을 입력해 주세요.");
		        document.regForm.con.focus();
		        return;
		    }
		    document.createForm.submit();
		}
	</script>
</body>
</html>