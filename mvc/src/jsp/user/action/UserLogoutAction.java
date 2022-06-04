package jsp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;

public class UserLogoutAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setNextPath("/Main.do");
		request.getSession().invalidate();
		return forward;
	}
}
