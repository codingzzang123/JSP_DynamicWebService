<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List..</title>
</head>
<body>
	<jsp:include page="/temp.jsp"></jsp:include>
	<div class="container mt-5" style="width:100%">
        <h1><b>Board💬</b></h1>
        <table class="table table-striped table-hover mt-4">
            <thead>
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">Subject</th>
                    <th scope="col">Pubdate</th>
                    <th scope="col">Writer</th>
                    <th scope="col">Clicks</th>
                </tr>
            </thead>
            
            
            <tbody>
            	
                <c:if test="${articleList ne null }">
	            	<c:forEach var="article" items="${articleList }">
	            		<tr>
	                        <th scope="row">${article.num }</th>
	                        <c:choose>
	                        	<c:when test="${article.replys gt 0 }">
	                        		<td><a class="sub" href="${pageContext.request.contextPath }/board/list/view.do?num=${article.num }&pageNum=${curPageNum }" >${article.subject } </a>[${article.replys }]</td>
	                        	</c:when>
	                        	<c:when test="${article.replys eq 0 }">
	                        		<td><a class="sub" href="${pageContext.request.contextPath }/board/list/view.do?num=${article.num }&pageNum=${curPageNum }" >${article.subject } </a></td>
	                        	</c:when>
	                        </c:choose>
	                        <td>${article.pubdate }</td>
	                        <td>${article.maker }</td>
	                        <td>${article.clicks }</td>
	                    </tr>
	            	</c:forEach>
            	</c:if>
            	<c:if test="${articleList eq null }">
            		<tr>
            			<td> ❓ 등록된 게시물이 없습니다. </td>
            			<td></td>
            			<td></td>
            			<td></td>
            			<td></td>
            		</tr>
            	</c:if>
            </tbody>
            
        </table>
        <div class="row mt-2">
        	<div class="col-lg-10"></div>
        	<div class="col-lg-2 text-end">
        		<c:choose>
            		<c:when test="${loginID ne null }">
            			<a href="${pageContext.request.contextPath }/board/write.do"><button type="button" class="btn btn-success btn-lg">
                   		 글쓰기</button></a>
            		</c:when>
            		<c:when test="${loginID eq null }">
            			<button type="button" class="btn btn-success btn-lg" disabled>
                    		글쓰기</button>
            		</c:when>
            	</c:choose>
        	</div>
        </div>
        
           
	      <div class="row">
	        <div class="col-lg-11">
	            <nav aria-label="Page navigation example">
	                <ul class="pagination nav justify-content-center">
	                	<c:choose>
	                		<c:when test="${ (curPageNum > 1) && !empty kwd }">
	                			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${curPageNum-1 }&search=${cate }&keyword=${kwd }">◀</a></li>
	                		</c:when>
	                		<c:when test="${ curPageNum > 1 }">
	                			 <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${curPageNum-1 }">이전</a></li>
	                		</c:when>
	                	</c:choose>
						<c:forEach begin="${blockStartNum }" end="${blockLastNum }" var="i">
							<c:choose>
								<c:when test="${!empty kwd }">
									<c:choose>
										<c:when test="${i eq curPageNum }">
											<li class="page-item active" aria-current="true"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${i }&search=${cate }&keyword=${kwd }">${i }</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${i }&search=${cate }&keyword=${kwd }">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${i eq curPageNum }">
											<li class="page-item active" aria-current="true"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${i }&search=${cate }&keyword=${kwd }">${i }</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${i }&search=${cate }&keyword=${kwd }">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
	                		<c:when test="${ (curPageNum < lastPageNum) && !empty kwd }">
	                			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${curPageNum+1 }&search=${cate }&keyword=${kwd }">▶</a></li>
	                		</c:when>
	                		<c:when test="${ curPageNum < lastPageNum }">
	                			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/board/list.do?pageNum=${curPageNum+1 }">다음</a></li>
	                		</c:when>
	                	</c:choose>
	                </ul>
	            </nav>
	        </div>
	    </div>
        
        <div class="row	mt-5 mb-5">
            <div class="text-center">
                <form action="${pageContext.request.contextPath }/board/list.do" method="post">
                    <select name="search">
                        <option value="sub" selected>제목</option>
                        <option value="wri" selected>글쓴이</option>
                        <option value="con" selected>내용</option>
                        <input name="keyword" type="text" size="50" value=""><button>검색</button>
                    </select> 
                </form>
            </div>
       	</div>
</body>
</html>