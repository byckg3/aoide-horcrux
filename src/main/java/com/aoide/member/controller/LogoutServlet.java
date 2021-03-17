package com.aoide.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet 
{
    public LogoutServlet() {
        super();
    }

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		if ( session.getAttribute( "memberInfo" ) != null )
		{
			session.removeAttribute( "memberInfo" );
		}
		response.sendRedirect( request.getContextPath() + "/index.jsp" );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
