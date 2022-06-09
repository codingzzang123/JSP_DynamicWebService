<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<% request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/temp.jsp"></jsp:include>
	<c:if test="${loginID eq null }"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	<c:if test="${loginID ne u.id }"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	
	<form method="post" enctype="multipart/form-data" name="updateForm" action="${pageContext.request.contextPath }/user/ProfileAlter.do?userName=${u.id }">
	    <div class="container mt-5 mb-5"  style="font-size: 20px; font-weight: bold; width: 60%;">
	        <h1><b>정보수정</b></h1>
	        <input type="hidden" name="origin" value="${u.code }">
	        <div class="mb-3 mt-5">
	            <label class="form-label">ID</label>
	            <input type="text" value="${u.id }" name="id" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" disabled>
	        </div>
	        <div class="mb-3">
	            <label for="exampleInputPassword1" class="form-label">PASSWORD</label>
	            <input type="password" class="form-control" name="pw" id="exampleInputPassword1">
	        </div>
	        <div class="mb-3">
	            <label class="form-label">NAME</label>
	            <input type="text" class="form-control" name="name" value="${u.name }" aria-describedby="emailHelp">
	        </div>
	        <div class="mb-3">
	            <label class="form-label">AGE</label>
	            <input type="number" class="form-control" name="age" value="${u.age }" aria-describedby="emailHelp">
	        </div>
	        <div class="mb-3">
	            <label class="form-label">COMMENT</label>
	            <textarea class="form-control" name="comment" >${u.comment }</textarea>
	        </div>
	        <div class="mb-3">
	            <label class="form-label">USERPIC (${u.img })</label>
	            <input type="file" class="form-control" id="exampleInputEmail1" name="upic" aria-describedby="emailHelp">
	        </div>
	        <div class="text-end">
	            <a href="${pageContext.request.contextPath }/user/Profile.do?userName=${u.id }"><button type="button" class="btn btn-dark">BACK</button></a>
	            <input type="button" value="UPDATE" onclick="update()" class="btn btn-dark">
	        </div>
	    </div>
	</form>
	<script>
	  function update(){
	    if(document.updateForm.pw.value==""){
	        alert("비밀번호를 입력해 주세요.");
	        document.regForm.pw.focus();
	        return;
	    }
	    document.updateForm.submit();
	}
  </script>
</body>

</html>