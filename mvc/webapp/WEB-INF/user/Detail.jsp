<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<% request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Detail</title>
</head>
<body>
	<jsp:include page="/temp.jsp"></jsp:include>
	<c:if test="${loginID eq null }"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	<c:if test="${loginID ne ${u.id }"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	
<div class="container mt-5 mb-5" style="font-size: 20px; font-weight: bold; width: 60%;">
        <h1><b>프로필</b></h1>
		<div class="text-center mt-5">
		            <img src="http://localhost:8080/mvc/fileUpload/user/${u.code }" class="rounded-circle" width="250px" height="250px">
        </div>
        

        <div class="mb-3 mt-5">
            <label class="form-label">ID</label>
            <input type="text" value="${u.id }" disabled class="form-control" style="font-weight: bold;" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" style="font-weight: bold;" value="${u.name }" disabled class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label class="form-label">AGE</label>
            <input type="text" style="font-weight: bold;" value="${u.age }" disabled class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label class="form-label">COMMENT</label>
            <textarea class="form-control" disabled style="font-weight: bold; height: 100px;">${u.comment }</textarea>
        </div>

        <div class="text-end">
            <a href="${pageContext.request.contextPath }/user/Profile/Update.do?userName=${loginID }" class="btn btn-dark">정보수정</a>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                계정삭제
            </button>
        </div>
    </div>


    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><b>계정 삭제 알림</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form method="post" action="#">
                    <div class="modal-body">
                        <h5>정말로 삭제하시겠습니까?</h5>
                        <b>패스워드 확인</b> <input type="password" name="ckpw" class="form-control mt-2">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button class="btn btn-danger" style="font-weight: bold; font-family:monospace;">계정삭제</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>