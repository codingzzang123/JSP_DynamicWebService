package jsp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.user.model.UserDAO;

public class UserIdCheck implements Service {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		String userId = request.getParameter("userId");
		
		UserDAO dao = UserDAO.getInstance();
		dao.idCheck(userId);
			
		forward.setRedirect(false);
		forward.setNextPath("");
		return forward;
	}
}
