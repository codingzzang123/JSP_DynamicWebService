package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction implements BoardService{
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/WEB-INF/board/list.jsp";
	}
}
