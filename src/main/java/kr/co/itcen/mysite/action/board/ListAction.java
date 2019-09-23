package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.dao.UserBoardDao;
import kr.co.itcen.mysite.vo.UserBoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		if(keyword == null) {
			keyword = "";
		}
		/*index(list)*/
		List<UserBoardVo> list = new UserBoardDao().getList(keyword);
		request.setAttribute("list", list);
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");

	}   

}
