package kr.co.itcen.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.mysite.dao.GuestBookDao;
import kr.co.itcen.mysite.vo.GuestBookVo;

import kr.co.itcen.web.WebUtils;

public class GuestBookServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setCharacterEncoding("utf-8");
			
			String action = request.getParameter("a");
			
			if("add".equals(action)) {
				String writer = request.getParameter("writer");
				String password = request.getParameter("password");
				String text = request.getParameter("message");
				
				GuestBookVo vo = new GuestBookVo();
				vo.setWriter(writer);
				vo.setPassword(password);
				vo.setText(text);

				new GuestBookDao().insert(vo);

				response.sendRedirect(request.getContextPath() + "/gb");
			}else if("deleteform".equals(action)) {
				
			}else if("delete".equals(action)) {
				
			}else {
				WebUtils.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
