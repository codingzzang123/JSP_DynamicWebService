package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import controller.*;
import jsp.board.model.*;

public class ListAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int pageSize = 10; //한 페이지에 보여지는 글 수
		
		String pageNum = request.getParameter("pageNum");
	    if(pageNum == null){
	    	pageNum = "1";
	    }
	    
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    
	    int count = 0;
	    int number = 1;
	    List<BoardVO> articleList = null;
	    BoardDAO dbPro = BoardDAO.getInstance();
	    count = dbPro.getArticleCount(); //전체 글 수
	    
	    if(count > 0){
	        articleList = dbPro.getArticles(startRow, endRow); //수정<3>
	    }
	    number = count - (currentPage-1) * pageSize;
		
		/*
		 *  
		pageContext.setAttribute("articleList",articleList);
	    pageContext.setAttribute("number",number);
	    pageContext.setAttribute("currentPage",currentPage);
	    
	    String user = (String)session.getAttribute("loginID");
	    pageContext.setAttribute("user",user);
		 * 
		 * */
	    
	    
		  int pageBlock = 5;
		  int imsi = count % pageSize == 0 ? 0 : 1;
		  int pageCount = count / pageSize + imsi;
		  int startPage = (int)((currentPage-1) / pageBlock) * pageBlock + 1;
		  int endPage = startPage + pageBlock - 1;
		  request.setAttribute("startPage", startPage);
		  request.setAttribute("pageBlock", pageBlock);
		  request.setAttribute("endPage", endPage);
		  request.setAttribute("pageCount", pageCount);
		  request.setAttribute("count", count);
	    	
		request.setAttribute("articleList",articleList);
		request.setAttribute("number",number);
		request.setAttribute("currentPage",currentPage);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setNextPath("/WEB-INF/board/list.jsp");
		return forward;
	}
}
