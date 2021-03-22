package com.aoide.member.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import java.sql.Timestamp;
import javax.sql.DataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.aoide.member.model.MemberService;
import com.aoide.member.model.Member;


@WebServlet( "/login" )
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		LoginForm form = new LoginForm();
		form.setAccount( request.getParameter( "account" ).trim() );
		form.setPassword( request.getParameter( "password" ).trim() );

		MemberService memberService = ( MemberService ) getServletContext().getAttribute( "memberService" );
		Validator validator = ( Validator ) getServletContext().getAttribute( "formValidator" );

		Set< ConstraintViolation< LoginForm > > constraintViolations = validator.validate( form );

		if ( constraintViolations.isEmpty() && canLogin( form, memberService ) )
		{
			Member memberProfile = memberService.findMemberProfile( form.getAccount() ).get();
			
			request.getSession().setAttribute( "member", memberProfile );
			//response.sendRedirect( "WEB-INF/welcome.jsp" );
			request.getRequestDispatcher( "WEB-INF/welcome.jsp" ).forward( request, response );
			System.out.println( "Login successfully" );
			return;
		}
		else
		{
			printErrors( constraintViolations );
			request.setAttribute( "errorMsg", "invalid account or password" );
			request.getRequestDispatcher( "login.html" ).forward( request, response );
		}
	}

	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doGet( request, response );
	}

	private boolean canLogin( LoginForm form, MemberService memberService )
	{
		Optional< Member > optionalMember = memberService.findMemberProfile( form.getAccount() );
		if ( optionalMember.isPresent() )
		{
			Member memberProfile = optionalMember.get();
			return memberProfile.getPassword().equals( form.getPassword() );
		}
		return false;
	}

	private void printErrors( Set< ConstraintViolation< LoginForm > > constraintViolations )
    {
        for ( ConstraintViolation< LoginForm > violation : constraintViolations ) {
            System.out.println( violation.getMessage() ); 
        }
    }
}
