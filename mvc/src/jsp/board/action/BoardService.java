package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
	 public String excute(HttpServletRequest request, 
	            HttpServletResponse response) throws Throwable;
}
