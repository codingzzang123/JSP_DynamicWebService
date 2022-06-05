package jsp.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.*;
import jsp.board.model.BoardDAO;

public class ModifyAction implements Service {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		ActionForward forward = new ActionForward();
		BoardDAO dao = BoardDAO.getInstance();
		
		String isLoginUser = (String) request.getSession().getAttribute("loginID");
		if(isLoginUser==null) {
			forward.setRedirect(true);
			forward.setNextPath("/Error.do");
		}
		
		request.setCharacterEncoding("UTF-8");
		String savaPath = "/fileUpload/board";
		String prefix = "C:\\JavaS\\jspwork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvc";
		String uploadFilePath = prefix + savaPath;
		int size = 10*1024*1024*10;
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()); 
			int num = Integer.parseInt(multi.getParameter("num"));
			String subject = multi.getParameter("sub");
			String pass = multi.getParameter("pass");
			String content = multi.getParameter("con");
			String maker = multi.getParameter("maker");
			String filename = multi.getFilesystemName("file");
			
			if(!isLoginUser.equals(maker)) {
				forward.setRedirect(true);
				forward.setNextPath("/Error.do");
			}
			
			if(!dao.getSubjectPass(num, pass)) {
				forward.setRedirect(true);
				forward.setNextPath("/Error.do");
				return forward;
			}else {
				if(filename == null) {
					dao.update(subject, content, num);
					forward.setRedirect(true);
					forward.setNextPath("/board/list/view.do?num="+num);
				}else { //파일이 있을때
					String deleteFileName = multi.getParameter("origin");
					
					if( !(deleteFileName.equals("no-uploadFile"))){ //이전에 업로드한 파일이 있다면 삭제,
						File f = new File(uploadFilePath+"/"+deleteFileName);
						f.delete();
					}
					
					dao.updateFile(subject, content, filename, num);
					forward.setRedirect(true);
					forward.setNextPath("/board/list/view.do?num="+num);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
