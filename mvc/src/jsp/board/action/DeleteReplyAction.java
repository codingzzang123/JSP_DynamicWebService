package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.board.model.BoardDAO;
import jsp.board.model.ReplyDAO;

public class DeleteReplyAction implements Service {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		ReplyDAO rdao = ReplyDAO.getInstance();
		BoardDAO bdao = BoardDAO.getInstance();
		
		String isLoginUser = (String) request.getSession().getAttribute("loginID");
		String maker = request.getParameter("maker");
		int order = Integer.parseInt(request.getParameter("order"));
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		if(isLoginUser == null || !(isLoginUser.equals(maker))) {
			forward.setRedirect(true);
			forward.setNextPath("/Error.do");
		}else {
			int loop = rdao.selectDeleteOrder(order);
			rdao.delete(order);
			if(loop!=0) {
				for(int i=0; i<loop; i++) {
					bdao.replysMinus(num);
				}
			}else {
				bdao.replysMinus(num);
			}
			forward.setRedirect(true);
			forward.setNextPath("/board/list/view.do?num="+num+"&pageNum="+pageNum);
		}
		
		return forward;
	}
}
