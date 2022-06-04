package jsp.user.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.*;
import jsp.user.model.UserDAO;

public class UserLoginAction implements Service {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String isLogin = (String)request.getSession().getAttribute("loginID");
		String remember = request.getParameter("remember"); //아이디 기억하기 버튼
		
		ActionForward forward = new ActionForward();
		if(isLogin != null) {
			forward.setRedirect(true);
			forward.setNextPath("/Main.do");
			return forward;
		}
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		boolean flag = dao.isMember(id, pw);
		if(flag==false) {
			forward.setRedirect(true);
			forward.setNextPath("/Error.do");
			return forward;
		}else{
			if(remember == null) { //기억하기 버튼 클릭 안했을때 (기존의 쿠키 있음 지워야함)
				Cookie[] co = request.getCookies();
				if(co != null) { //기존의 쿠키가 있을때 
					for(Cookie c:co) {
						if((c.getName()).equals("remember")){ //그중 remember쿠키만 지움
							Cookie cookie = new Cookie("remember",id);
							cookie.setMaxAge(0);
							cookie.setPath("/");
							response.addCookie(cookie);
						}
					}
				}	
			}else if(remember.equals("remember")) {
				Cookie cookie = new Cookie("remember",id);
				cookie.setMaxAge(60*10);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			request.getSession().setAttribute("loginID",id);
			forward.setRedirect(true);
			forward.setNextPath("/Main.do");
			return forward;
		}
	}
}
