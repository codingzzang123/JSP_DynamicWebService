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
	<c:choose>
		<c:when test="${loginID eq null }">
			<div class="p-5 mb-4 bg-light rounded-3 text-center">
		        <div class="container-fluid py-5">
		            <h1 class="display-5 fw-bold"> 💬 Hosun Page </h1>
		            <p class="fs-4">Login is Essential ⛔</p>
		            <a href="${pageContext.request.contextPath }/user/Login.do"><button class="btn btn-dark btn-lg" type="button" style="font-weight: bold;">LOGIN</button></a>
		        </div>
		    </div>
		</c:when>
		<c:when test="${loginID ne null }">
			<div class="p-5 mb-4 bg-light rounded-3 text-center">
		        <div class="container-fluid py-5">
		            <h1 class="display-5 fw-bold"> 💬 Hosun Page </h1>
		            <p class="fs-4"> ✋Welcome ${loginID } </p>
		            <img src="http://localhost:8080/mvc/fileUpload/user/${imgcode }" width="400px" height="300px" class="rounded-circle">
		        </div>
	    	</div>	
		</c:when>
	</c:choose>
</body>
</html>