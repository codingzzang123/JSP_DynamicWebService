package jsp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.user.model.UserDAO;
import jsp.user.model.UserVO;

public class UserDetailUpdate implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		String id = request.getParameter("userName");
		UserDAO dao = UserDAO.getInstance();
		UserVO vo=null;
		vo = dao.select(id);
		request.setAttribute("u", vo);
		forward.setRedirect(false);
		forward.setNextPath("/WEB-INF/user/DetailUpdate.jsp");
		return forward;
	}
}
