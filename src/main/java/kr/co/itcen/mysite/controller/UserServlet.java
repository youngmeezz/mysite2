package kr.co.itcen.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestBookDao;
import kr.co.itcen.mysite.vo.GuestBookVo;
import kr.co.itcen.web.WebUtils;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		if("joinform".equals(action)) {
		
			WebUtils.forward(request, response,"/WEB-INF/views/user/joinform.jsp");
		}
		else if("guestbook".equals(action)) {
			
			/*index(list)*/
			List<GuestBookVo> list = new GuestBookDao().getList();
			request.setAttribute("list", list);
			WebUtils.forward(request, response,"/WEB-INF/views/guestbook/list.jsp");
		}
		else if("insert".equals(action)) {
			String writer = request.getParameter("name");
			String password = request.getParameter("pass");
			String text = request.getParameter("content");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setWriter(writer);
			vo.setPassword(password);
			vo.setText(text);

			new GuestBookDao().insert(vo);

			response.sendRedirect(request.getContextPath() + "/user?a=guestbook");
		}else if("deleteform".equals(action)) {
			
			WebUtils.forward(request, response,"/WEB-INF/views/guestbook/deleteform.jsp");
			
			
		}else if("delete".equals(action)) {
	
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			
			Long no_ = Long.parseLong(no);
			
			new GuestBookDao().set_delete(no, password);
			response.sendRedirect(request.getContextPath() + "/user?a=guestbook");
		}else {
			WebUtils.forward(request, response, request.getContextPath());
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
