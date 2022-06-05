<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="PATH" value="C:\\JavaS\\jspwork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvc/fileUpload/board"/>
<!DOCTYPE html>
<html>
<head>
	<style>
	#reply{
		font-size:18px;  border:1px solid #ccc;
		border-radius:10px;
		margin-bottom:5px;
	}
	#reReply2{
		font-size:16px;  border:1px solid #ccc;
		border-radius:5px;
		margin-bottom:5px;
		background-color:#dee2e6;
	}</style>
<meta charset="UTF-8">
<title>Board List Detail</title>
</head>
<body>
	<jsp:include page="/temp.jsp"></jsp:include>
	<%-- 화면 출력 --%>
	<div class="container mt-5 mb-5">
		<div style="margin-top:50px;">
			<h1><a href="${pageContext.request.contextPath }/board/list.do?pageNum=${pageNum }">Board💬 </a></h1>
			<hr><br> <%--hr 굵기 조금 키우고 색 파란색으로 바꿔야함 --%>
		</div>
        <h2><b>${detail.subject }</b></h2>
        <div class="row" style="margin-top:20px;">
        	<div class="col-lg-4">
                    written by <b>${detail.maker }</b>  |   
                    <b>${detail.pubdate }</b>
            </div>
            <div class="col-lg-2"></div>
            <div class="col-lg-2"></div>
            <div class="col-lg-4 text-end" style="margin-rigth:10px;">
                   	조회수<b>${detail.clicks }</b>  |   
                   	댓글<b>${detail.replys }</b>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-8">
               <textarea class="form-control" style="height: 400px; resize:none;" readonly>${detail.content }</textarea>
            </div>
            <div class="col-lg-4" style="margin:auto">
            	<c:choose>
					<c:when test="${code ne '0.png' }">
						<div class="text-center mt-5">
				            <img src="http://localhost:8080/mvc/fileUpload/user/${code }" class="rounded-circle" width="250px" height="250px">
				        </div>
					</c:when>
					<c:when test="${code eq '0.png' }">
						<div class="text-center mt-5">
				            <img src="http://localhost:8080/mvc/fileUpload/user/0.png" class="rounded-circle" width="250px" height="250px">
				        </div>
					</c:when>
				</c:choose>	
            </div>
        </div>
        
        <div class="row">
        	<div class="col-lg-8">
        		<div style= "border: 1px solid gray; margin-top:40px;">
        			<div style="margin-left:20px; margin-top:10px;">
	        			<p>원본 첨부파일</p>
	        			<c:choose>
	        				<c:when test="${detail.upload eq 'no-uploadFile' }">
	        					<p><a href="#">Upload File is Empty ..</a></p>
	        				</c:when>
	        				<c:when test="${detail.upload ne 'no-uploadFile' }">
	        					<c:choose>
	        						<c:when test="${loginID eq null }">
	        							<p><a href="${pageContext.request.contextPath }/Error.do">${detail.upload }  ..[Download]</a></p>
	        						</c:when>
	        						<c:otherwise>
	        							<p><a href="${path }" download="${detail.upload }">${detail.upload }  ..[Download]</a></p>
	        						</c:otherwise>
	        					</c:choose>
	        				</c:when>
	        			</c:choose>
        			</div>	
        		</div>
        	</div>
        	<div class="col-lg-4" style="margin:auto">
		        <%-- login user만 수정,삭제 버튼 클릭  --%>
		        <div class="text-end mt-5">
		            <a href="${pageContext.request.contextPath }/board/list.do" class="btn btn-dark btn-lg">BACK</a>
		            <c:choose>
		            	<c:when test="${loginID ne null }">
		            		<c:choose>
		            			<c:when test="${loginID eq detail.maker }">
		            				<a href="${pageContext.request.contextPath }/board/modify.do?contentNum=${detail.num}"><button type="button" class="btn btn-primary btn-lg" >
		                				MOD</button></a>
		            				
		            				<button type="button" class="btn btn-danger btn-lg" data-bs-toggle="modal" data-bs-target="#DelSub">DEL</button>
		            			</c:when>
		            			<c:when test="${loginID ne detail.maker }">
		            				<a href="#"><button type="button" class="btn btn-primary btn-lg" disabled>
		                				MOD</button></a>
				                	<button type="button" class="btn btn-danger btn-lg" disabled>
				                		DEL</button>
		            			</c:when>
		           			</c:choose>
		            	</c:when>
		            	<c:when test="${loginID eq null }">
							<a href="#"><button type="button" class="btn btn-primary btn-lg" disabled>
		                		MOD</button></a>
		                	<button type="button" class="btn btn-danger btn-lg" disabled>
		                		DEL</button>
		            	</c:when>
		            </c:choose>
		        </div>
			</div>
		</div>
        
        <%-- 댓글 출력 --%>
		<div class="row mt-5" >
			<div class="col-lg-8">
				전체 댓글<b> ${detail.replys } </b>개
			</div>
			<div class="col-lg-1"></div>
			<div class="col-lg-1">본문보기</div>
			<div class="col-lg-1">댓글닫기</div>
			<div class="col-lg-1">새로고침</div>
		</div>
		<hr style="background-color:blue; height:3px;">
		
		<%-- 댓글 출력 --%>
		<c:set var="i" value="1"/>
		<c:if test="${replys ne null }">
			<c:forEach var="re" items="${replys }">
				<div class="row" id="reply">
					<div class="col-lg-1">${re.maker }</div>
					<div class="col-lg-8" onclick="maketest(${i})">${re.content } <%--이 onclick이 댓글에 대한 답글? 입력하는거임  (js)--%>
					</div>
					<div class="col-lg-2 text-end">${re.pubdate }</div>
					<c:choose>
						<c:when test="${loginID eq null }"><div class="col-lg-1 text-end"><img src="http://localhost:8080/mvc/fileUpload/board/x.png" width="15" height="15" style="margin-bottom:5px;" /></div></c:when>
						<c:when test="${re.maker ne loginID }"><div class="col-lg-1 text-end"><img src="http://localhost:8080/mvc/fileUpload/board/x.png" width="15" height="15" style="margin-bottom:5px;" /></div></c:when>
						<c:when test="${re.maker eq loginID }"><div class="col-lg-1 text-end"><a href="${pageContext.request.contextPath }/board/deleteReplyAction.do?order=${re.order }&maker=${re.maker }&num=${detail.num }&pageNum=${pageNum }"><img src="http://localhost:8080/mvc/fileUpload/board/x.png" width="15" height="15" style="margin-bottom:5px;" /></a></div></c:when>
					</c:choose>
				</div>
				<h3>
					<%-- 댓글에 대한 답변 출력 --%>
					<c:if test="${reReplys ne null }">
						<c:forEach var="r" items="${reReplys }">
							<c:if test="${r.ref eq re.order }">
								<div class="row" id="reReply2" style="margin-left:50px;">
									<div class="col-lg-1">┖ </div>
									<div class="col-lg-1">${r.maker }</div>
									<div class="col-lg-7">${r.content }</div>
									<div class="col-lg-2 text-end">${r.pubdate }</div>
									<c:choose>
										<c:when test="${loginID eq null }"><div class="col-lg-1 text-end"><img src="http://localhost:8080/mvc/fileUpload/board/x.png" width="10" height="10" style="margin-bottom:5px;" /></div></c:when>
										<c:when test="${r.maker ne loginID }"><div class="col-lg-1 text-end"><a href="${pageContext.request.contextPath }/Error.do"><img src="http://localhost:8080/mvc/fileUpload/board/x.png" width="10" height="10" style="margin-bottom:5px;" /></a></div></c:when>
										<c:when test="${r.maker eq loginID }"><div class="col-lg-1 text-end"><a href="${pageContext.request.contextPath }/board/deleteReReplyAction.do?step=${r.step }&maker=${r.maker }&num=${detail.num }&pageNum=${pageNum }"><img src="http://localhost:8080/mvc/fileUpload/board/x.png" width="10" height="10" style="margin-bottom:5px;" /></a></div></c:when>
									</c:choose>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
				</h3>
				<form action="${pageContext.request.contextPath }/board/createReReplyAction.do">
					<input type="hidden" name="num" value="${re.num }">
					<input type="hidden" name="order" value="${re.order }"><%-- 댓글은 차별화를 두어야함 (댓글이라면 REF값을 ORDER이랑 동일하게 설정  DEFAULT값은 0임 )--%>
					<input type="hidden" name="maker" value="${loginID }"> <%-- 따라서 REF가 0이면 부모 댓글이라고 생각하면 됨 --%>
					<input type="hidden" name="pageNum" value="${pageNum }">
					<div class="row" id="here${i }" style="margin-left:50px;"> <%-- ORDER은 부모댓글의 시퀀스, STEP은 자식 댓글의 시퀀스 --%>
						<%-- 여기에 input창이 들어감  변수는 "con2" --%>
					</div>
				</form>
				<c:set var="i" value="${i+1 }"/>
			</c:forEach>
        </c:if>
        
        <hr style="background-color:blue; height:1px;">
        <%-- 댓글 입력  --%>
        <form method="post" action="${pageContext.request.contextPath }/board/createReplyAction.do">
          <div class="row">
              <c:choose>
              	<c:when test="${loginID ne null }"> 
	              		<div class="col-lg-10">
	              			<textarea class="form-control" style="resize:none;" name="con" placeholder="${detail.replys+1 }번째 댓글을 달아주세요🗨"></textarea>
	              		</div>
	              		<div class="col-lg-2">
	              			<input type="hidden" name="num" value="${detail.num }">
	              			<input type="hidden" name="maker" value="${loginID }">
	              			<input type="hidden" name="pageNum" value="${pageNum }">
	              			<button type="submit" class="btn btn-dark" style="margin-top:15px;">확인</button>
	            		</div>
              	</c:when>
              	<c:when test="${loginID eq null }">
              		<div class="col-lg-10">
	              		<a href="${pageContext.request.contextPath }/user/Login.do"><textarea class="form-control" style="resize:none;" disabled placeholder="로그인이 필요한 기능입니다"></textarea></a>
	              	</div>
              		<div class="col-lg-2">
              			<button class="btn btn-dark" disabled style="margin-top:15px;">확인</button>
            		</div>
              	</c:when>
              </c:choose>
          </div>
        </form> 
	
        </div>
	
   		<script>
   		var arr = [];
   	
   		for(var i=0; i<100; i++){
   			arr.push(0);
   		}
   		function maketest(i){
   			var el = "here"+i;
			const div = document.getElementById(el);
   			if(arr[i]>0){
   				if(div.style.display === 'none')  {
					    div.style.display = 'block';
			    }else {
					    div.style.display = 'none';
			    }
   			}
   			
   			if(arr[i]==0){
   				d = document.getElementById(el)
	       		n = document.createElement("div")
		        n.setAttribute('class', 'mt-1')
		        n.innerHTML ='<div style="display: inline-block; margin-right:10px;">┖ </div><div class="col-lg-8" style="display: inline-block"><input type="text" name="con2" class="form-control" placeholder="답글을 입력해주세요🗨"></div><div class="col-lg-3" style="display: inline-block"><button class="btn btn-dark" type="submit" style="margin-left:10px">확인</button></div>'
	       		d.appendChild(n)
		        arr[i]=1;
   			}
   		}
   		</script>
   		<div class="modal fade" id="DelSub" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><b>게시글 삭제 알림</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form method="post" action="${pageContext.request.contextPath }/board/deleteSubjectAction.do">
                    <div class="modal-body">
                        <h5>정말로 삭제하시겠습니까?</h5>
                        <b>패스워드 확인</b> 
                        <input type="password" name="ckpw" class="form-control mt-2">
                        <input type="hidden" name="num" value="${detail.num }">
                        <input type="hidden" name="maker" value="${detail.maker }">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button class="btn btn-danger" style="font-weight: bold; font-family:monospace;">삭제</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>