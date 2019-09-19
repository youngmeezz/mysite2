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

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		String title = request.getParameter("title");
//		String contents = request.getParameter("content");
//
//		BoardVo vo = new BoardVo();
//		vo.setTitle(title);
//		vo.setContents(contents);
//		
//		new BoardDao().insert(vo);
//	
//		response.sendRedirect(request.getContextPath() + "/board?a=viewform");
//	}
		
		HttpSession session = request.getSession(true);
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String hit = request.getParameter("hit");
		String registerDate = request.getParameter("registerdate");
		//여기에 UserNo도 받아와야 하는지 궁금 -> write.jsp에 userNo에 대한 내용이 없으니까 안 넣어도 됨
		
		BoardVo boardVo = new BoardVo();

		long _no = Long.parseLong(no);
		int _hit = Integer.parseInt(hit);
		
		boardVo.setNo(_no);
		boardVo.setTitle(title);
		boardVo.setContents(contents);
		boardVo.setHit(_hit);
		boardVo.setRegisterDate(registerDate);
		//boardVo.setUserNo(authUser.getNo());
		
		new BoardDao().getList();
	
		response.sendRedirect(request.getContextPath() + "/board");
		
	}

	private Long parseInt(String no) {
		// TODO Auto-generated method stub
		return null;
	}

}
