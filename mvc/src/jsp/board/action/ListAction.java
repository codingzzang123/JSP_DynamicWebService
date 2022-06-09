package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import controller.*;
import jsp.board.model.*;

public class ListAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		Paging p = new Paging();
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardVO> articleList = null;
		
		String cate = request.getParameter("search");
		String kwd = request.getParameter("keyword");
		System.out.println("카테고리 : "+cate+"\t키워드: "+kwd);
		String pageNum = request.getParameter("pageNum");
	    if(pageNum == null){
	    	pageNum = "1";
	    }
	    int PageSize = 15;
	    int curPageNum = Integer.parseInt(pageNum);
	    int lastPageNum;
	    int start = (curPageNum-1) * PageSize + 1; 
    	int end = curPageNum * PageSize;
	    p.makeBlock(curPageNum);
	    
	    if(kwd==null||kwd.equals("")||kwd.length()==0) {
	    	p.makeLastPageNum();
	    	lastPageNum = p.getLastPageNum();
	    	articleList = dao.getArticles(start, end);
	    }else {
	    	p.makeLastPageNum(cate, kwd);
	    	lastPageNum = p.getLastPageNum();
	    	if(cate.equals("sub")) {
	    		articleList = dao.searchSubject(kwd,start,end);
			}else if(cate.equals("wri")) {
				articleList = dao.searchMaker(kwd,start,end);
			}else if(cate.equals("con")) {
				articleList = dao.searchContent(kwd,start,end);
			}
	    }
	    Integer blockStartNum = p.getBlockStartNum();
	    Integer blockLastNum = p.getBlockLastNum();
	    Integer pageBlock = p.getPageBlock();
		request.setAttribute("curPageNum", curPageNum);
		request.setAttribute("blockStartNum", blockStartNum);
		request.setAttribute("blockLastNum", blockLastNum);
		request.setAttribute("lastPageNum", lastPageNum);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("articleList",articleList);
		request.setAttribute("cate",cate);
		request.setAttribute("kwd",kwd);
	
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setNextPath("/WEB-INF/board/list.jsp");
		return forward;
		
		
		
	}
}
