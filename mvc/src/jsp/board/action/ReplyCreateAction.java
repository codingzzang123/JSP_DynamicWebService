package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.board.model.BoardDAO;
import jsp.board.model.ReplyDAO;

public class ReplyCreateAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		BoardDAO dao = BoardDAO.getInstance();
		ReplyDAO rdao = ReplyDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String maker = request.getParameter("maker");
		String content = request.getParameter("con");
		
		rdao.insertReply(num, maker, content); //댓글 insert
		dao.replysPlus(num); //게시판 댓글수 +1
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setNextPath("/board/list/view.do?num="+num+"&pageNum="+pageNum);
		return forward;
	}
}
