package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main implements BoardService{
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/WEB-INF/Main.jsp";
	}
}
