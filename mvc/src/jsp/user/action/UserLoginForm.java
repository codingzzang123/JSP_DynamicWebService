package jsp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;

public class UserLoginForm implements Service {
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		return "/WEB-INF/user/Login.jsp";
	}
}
