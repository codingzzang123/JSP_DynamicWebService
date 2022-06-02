package jsp.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import jsp.user.model.UserDAO;

public class UserLoginAction implements Service {
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String isLogin = (String)request.getSession().getAttribute("loginID");
		if(isLogin != null) {
			return "/WEB-INF/user/Main.jsp";
		}
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		boolean flag = dao.isMember(id, pw);
		System.out.println("flag 값 = "+flag);
		if(flag==false) {
			return "/WEB-INF/user/test.jsp";
		}else{
			request.getSession().setAttribute("loginID",id);
			return "/WEB-INF/user/Main.jsp";
		}
	}
}
