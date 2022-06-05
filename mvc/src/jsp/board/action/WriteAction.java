package jsp.board.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.*;
import jsp.board.model.BoardDAO;

public class WriteAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String savaPath = "/fileUpload/board";
		String prefix = "C:\\JavaS\\jspwork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvc";
		String uploadFilePath = prefix + savaPath;
		int size = 10*1024*1024*10;
		
		
		ActionForward forward = new ActionForward();
		BoardDAO dao = BoardDAO.getInstance();
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()); 
			
			String subject = multi.getParameter("sub");
			String pass = multi.getParameter("pass");
			String maker = multi.getParameter("maker");
			String content = multi.getParameter("con");
			String filename = multi.getFilesystemName("file");
			
	
			if(filename == null) {
				dao.createBoard(subject, content, maker, pass);
				forward.setRedirect(true);
				forward.setNextPath("/board/list.do");
			}else { 
				dao.createBoardPic(subject, content, maker, filename, pass);
				forward.setRedirect(true);
				forward.setNextPath("/board/list.do");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
		
	}
}
