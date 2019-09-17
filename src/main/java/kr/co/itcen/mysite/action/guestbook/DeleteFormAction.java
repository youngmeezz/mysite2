package kr.co.itcen.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class DeleteFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUtils.forward(request, response,"/WEB-INF/views/guestbook/deleteform.jsp");
	}	
	
}
