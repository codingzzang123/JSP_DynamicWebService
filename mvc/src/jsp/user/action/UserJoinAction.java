package jsp.user.action;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.*;
import jsp.user.model.UserDAO;

public class UserJoinAction implements Service {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		UserDAO dao = UserDAO.getInstance();
		String path = ""; //리턴 해줄변수
		request.setCharacterEncoding("UTF-8");
		String savaPath = "/fileUpload/user";
		String prefix = "C:\\JavaS\\jspwork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvc";
		String uploadFilePath = prefix + savaPath;
		System.out.println("경로 = "+uploadFilePath);
		
		int size = 10*1024*1024;
		String id="";
		String name="";
		String pw = "";
		String age= "";
		String comment = "";
		String filename = "";
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()); 
			id = multi.getParameter("user_id");
			name = multi.getParameter("name");
			pw = multi.getParameter("pw");
			age = multi.getParameter("age");
			comment = multi.getParameter("comment");
			filename = multi.getFilesystemName("upic");
			
			if(filename == null) {
				dao.insert(id, name, pw, age, comment);
				path ="/user/Login.do";
			}
			else {
				String fe = "";
				File file = new File(uploadFilePath+"/"+filename);
				
				int i = filename.lastIndexOf('.');
				if (i > 0) {
				    fe = filename.substring(i);
				}
				String code = System.currentTimeMillis()+"_user"+fe;
				File newFile = new File(uploadFilePath+"/"+code);
				file.renameTo(newFile); //이름 바꿔줌
				
				dao.insert(id, name, pw, age, comment, filename, code);
				path ="/user/Login.do";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setNextPath(path);
		forward.setRedirect(true);
		return forward;
	}
}
