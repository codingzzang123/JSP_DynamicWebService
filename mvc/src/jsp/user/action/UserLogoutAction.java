package jsp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;

public class UserLogoutAction implements Service{
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.getSession().invalidate();
		return "/WEB-INF/user/Main.jsp";
	}
}
