package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;

public class WriteForm implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setNextPath("/WEB-INF/board/create.jsp");
		return forward;
	}
}
