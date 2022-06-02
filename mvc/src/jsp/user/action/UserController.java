package jsp.user.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//넘어온 명령을 추출하는 과정
		String requestURI = request.getRequestURI();
		String path = request.getContextPath();
		String command = requestURI.substring(path.length()+1);
		ActionForward forward = null;
		Action action = null;
		System.out.println(requestURI);
		System.out.println(path);
		System.out.println(command);
		//보여줄 화면 url
		String form = "/WEB-INF/user/";
		try {
			if(command.contentEquals("Main.do")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("/WEB-INF/Main.jsp");
			}else if(command.equals("Login.do")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"Login.jsp");
			}else if(command.equals("Join.do")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"Join.jsp");
			}
			
			if(forward != null) {
				if(forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				}else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getNextPath());
					dispatcher.forward(request, response);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
