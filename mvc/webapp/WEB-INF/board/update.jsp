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
	<c:if test="${ detail eq null}"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	<c:if test="${ detail.maker ne loginID}"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	<c:if test="${loginID eq null }"><c:redirect url="/Error.do" context="${pageContext.request.contextPath }"/></c:if>
	 
	 <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/board/modifyAction.do">
        <div class="container mt-5 mb-5" style="font-size: 20px; font-weight: bold; width: 80%;">
            <h1><b>게시글수정</b></h1>
            <div class="mb-3 mt-5">
                <label class="form-label">SUBJECT</label>
                <input type="text" class="form-control" value="${detail.subject }" name="sub">
            </div>
            
            <div class="mb-3">
                 <label class="form-label">PASSWORD</label>
                 <input type="password" class="form-control" name="pass">
            </div>
            
            <div class="mb-3">
                <label class="form-label">WRITER</label>
                <input type="text" class="form-control" disabled value="${detail.maker }">
            </div>
            <div class="mb-3">
                <label class="form-label">CONTENT</label>
                <textarea class="form-control" style="height: 100px;" name="con">${detail.content }</textarea>
            </div>
            
            <div class="mb-3">
                 <label class="form-label">UploadFile  (${detail.upload })</label>
                 <input type="file" class="form-control" name="file" >
            </div>
            
            <div class="text-end mt-3">
                <button class="btn btn-dark">수정하기</button>
            </div> 
            <input type="hidden" name="num" value="${detail.num }">
            <input type="hidden" name="maker" value="${detail.maker }">
            <input type="hidden" name="origin" value="${detail.upload }">
        </div>
    </form>
</body>
</html>