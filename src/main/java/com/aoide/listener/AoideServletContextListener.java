package com.aoide.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import javax.validation.Validation;
import javax.validation.Validator;

import com.aoide.member.model.JdbcMemberDAO;
import com.aoide.member.model.MemberDAO;
import com.aoide.member.model.MemberService;

@WebListener
public class AoideServletContextListener implements ServletContextListener
{

    @Override
    public void contextInitialized( ServletContextEvent event )
	{ 
    	ServletContext context = event.getServletContext();

		DataSource dataSource = createDataSource();
		MemberDAO memberDao = new JdbcMemberDAO( dataSource );  
    	context.setAttribute( "memberService", new MemberService( memberDao ) );

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		context.setAttribute( "formValidator", validator );
        
        System.out.println( "Context Initialized...");
    }

    private DataSource createDataSource()
    {
    	try
        {
        	Context initContext = new InitialContext();
        	Context envContext = ( Context ) initContext.lookup( "java:/comp/env" );

        	return ( DataSource ) envContext.lookup("jdbc/aoide");
        }
        catch ( NamingException e ) {
        	e.printStackTrace();
        }
		return null;
    }
}
