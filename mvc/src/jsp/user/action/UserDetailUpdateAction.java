package jsp.user.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.*;
import jsp.user.model.UserDAO;

public class UserDetailUpdateAction implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = request.getParameter("userName");
		String path = "";
		request.setCharacterEncoding("UTF-8");
		
		String savaPath = "/fileUpload/user";
		String prefix = "C:\\JavaS\\jspwork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvc";
		String uploadFilePath = prefix + savaPath;
		
		int size = 10*1024*1024;
		String name="";
		String age= "";
		String comment = "";
		String filename = "";
		String pw = "";
		
		ActionForward forward = new ActionForward();
		UserDAO dao = UserDAO.getInstance();
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()); 
			name = multi.getParameter("name");
			age = multi.getParameter("age");
			comment = multi.getParameter("comment");
			pw = multi.getParameter("pw");
			filename = multi.getFilesystemName("upic");
			
			if(!dao.isMember(id, pw)) {
				forward.setRedirect(true);
				forward.setNextPath("/Error.do");
				return forward;
			}else {
				if(filename == null) {
					dao.updateUser(id, name, age, comment);
					path ="/user/Profile.do?userName="+id;
				}else {
					String fe = "";
					File file = new File(uploadFilePath+"/"+filename);
					
					int i = filename.lastIndexOf('.');
					if (i > 0) {
					    fe = filename.substring(i);
					}
					//기존 유저 삭제
					String deleteFileName = multi.getParameter("origin");
					if( !(deleteFileName.equals("0.png"))){
						File f = new File(uploadFilePath+"/"+deleteFileName);
						f.delete();
					}
					
					
					String code = System.currentTimeMillis()+"_user"+fe;
					File newFile = new File(uploadFilePath+"/"+code);
					file.renameTo(newFile); //이름 바꿔줌
					
					dao.updateUserPic(id, name, age, comment, filename, code);
					path = "/user/Profile.do?userName="+id;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		forward.setRedirect(true);
		forward.setNextPath(path);
		return forward;
	}
}
