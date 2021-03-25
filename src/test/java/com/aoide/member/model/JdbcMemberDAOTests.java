package com.aoide.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


class JdbcMemberDAOTests extends AbstractMemberDAOTests
{
    private String testDbUrl;
    private String userName;
    private String password;

    @Override
    void configureDAO()
    {
        String currentDir = System.getProperty( "user.dir" );
        testDbUrl = "jdbc:h2:tcp://localhost/" + currentDir + "/test";
        userName = "admin";
        password = "admin";
        
        memberDao = new JdbcMemberDAO() {
            @Override
            protected Connection getConnection()  throws SQLException
            {
                return DriverManager.getConnection( testDbUrl, userName, password );
            }
        };
        assumeTrue( ( ( JdbcMemberDAO ) memberDao ).canConnect(), "cannot connect to db..." );
    }
}
