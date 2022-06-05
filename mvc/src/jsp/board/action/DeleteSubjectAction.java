package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.board.model.BoardDAO;

public class DeleteSubjectAction implements Service {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		BoardDAO dao = BoardDAO.getInstance();
		
		String isLoginUser = (String) request.getSession().getAttribute("loginID");
		String pass = request.getParameter("ckpw");
		String maker = request.getParameter("maker");
		int num = Integer.parseInt(request.getParameter("num"));
		
		if(isLoginUser==null || !isLoginUser.equals(maker)) {
			forward.setRedirect(true);
			forward.setNextPath("/Error.do");
		}else{
			if(dao.isDelete(pass,num)) {
				dao.delete(num);
				forward.setRedirect(true);
				forward.setNextPath("/board/list.do");
			}else {
				forward.setRedirect(true);
				forward.setNextPath("/Error.do");
			}
		}
		
		return forward;
	}
}	
