package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.board.model.BoardDAO;
import jsp.board.model.ReplyDAO;

public class ReReplyCreateAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		BoardDAO dao = BoardDAO.getInstance();
		ReplyDAO rdao = ReplyDAO.getInstance();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int order = Integer.parseInt(request.getParameter("order"));
		String content = request.getParameter("con2");
		
		String maker = request.getParameter("maker");
		dao.replysPlus(num); //댓글수 +1
		rdao.insertReReply(num, maker, content, order, order);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setNextPath("/board/list/view.do?num="+num+"&pageNum="+pageNum);
		return forward;
	}
}
