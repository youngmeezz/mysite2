package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.mvc.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		HttpSession session = request.getSession(true);
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		//여기에 UserNo도 받아와야 하는지 궁금 -> write.jsp에 userNo에 대한 내용이 없으니까 안 넣어도 됨
		
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setUserNo(authUser.getNo());
		
		new BoardDao().insert(boardVo);
	
		response.sendRedirect(request.getContextPath() + "/board");
		
	}
}

