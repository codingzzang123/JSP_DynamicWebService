package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.board.model.*;

public class ModifyForm implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("contentNum"));
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO detail = dao.select(num);
		
		request.setAttribute("detail", detail);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setNextPath("/WEB-INF/board/update.jsp");
		return forward;
	}
}
