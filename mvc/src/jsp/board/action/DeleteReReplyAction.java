package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.board.model.BoardDAO;
import jsp.board.model.ReplyDAO;

public class DeleteReReplyAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		ReplyDAO rdao = ReplyDAO.getInstance();
		BoardDAO bdao = BoardDAO.getInstance();
		
		String isLoginUser = (String) request.getSession().getAttribute("loginID");
		String maker = request.getParameter("maker");
		int step = Integer.parseInt(request.getParameter("step"));
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		if(isLoginUser == null || !(isLoginUser.equals(maker))) {
			forward.setRedirect(true);
			forward.setNextPath("/Error.do");
		}else {
			rdao.deleteRe(step);
			bdao.replysPlus(num);
			forward.setRedirect(true);
			forward.setNextPath("/board/list/view.do?num="+num+"&pageNum="+pageNum);
		}
		
		return forward;
	}
}
