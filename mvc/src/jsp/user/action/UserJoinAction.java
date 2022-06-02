package jsp.user.action;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.Service;
import jsp.user.model.UserDAO;

public class UserJoinAction implements Service {
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		UserDAO dao = UserDAO.getInstance();
		String result = ""; //리턴 해줄변수
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
			id = multi.getParameter("id");
			name = multi.getParameter("name");
			pw = multi.getParameter("pw");
			age = multi.getParameter("age");
			comment = multi.getParameter("comment");
			filename = multi.getFilesystemName("upic");
			
			if(filename == null) {
				dao.insert(id, name, pw, age, comment);
				result =  "/WEB-INF/user/Login.jsp";
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
				result =  "/WEB-INF/user/Login.jsp";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result; 
	}
	
}
