package jsp.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import controller.*;
import jsp.board.model.*;
import jsp.user.model.*;

public class ListViewForm implements Service{
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num")); //글 번호(기본키)
		
		BoardDAO bdao = BoardDAO.getInstance();
		ReplyDAO rdao = ReplyDAO.getInstance();
		UserDAO udao = UserDAO.getInstance();
		
		bdao.clicks(num); //조회수 1증가!
		BoardVO detail = bdao.select(num); //보여줄 글에 대한 정보 detail
		
		String code = udao.getPicCode(detail.getMaker()); //글 작성자의 사진 코드
		
		List<ReplyVO> replys = rdao.getReplys(num); //글 번호에대한 댓글을 가져옴
		List<ReplyVO> reReplys = rdao.reReplys(num); //댓글에대한 답글을 가져옴
		
		request.setAttribute("detail", detail);
		request.setAttribute("code", code);
		request.setAttribute("replys", replys);
		request.setAttribute("reReplys", reReplys);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setNextPath("/WEB-INF/board/listView.jsp");
		return forward;
	}
}
