package jsp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.user.model.*;
public class Main implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String isUser = (String)request.getSession().getAttribute("loginID");
		if(isUser != null) {
			UserDAO dao = UserDAO.getInstance();
			
			request.setAttribute("imgcode", dao.getPicCode(isUser));
		}
		
		ActionForward forward = new ActionForward();
		forward.setNextPath("/WEB-INF/user/Main.jsp");
		forward.setRedirect(false);
		return forward;
	}	
}
