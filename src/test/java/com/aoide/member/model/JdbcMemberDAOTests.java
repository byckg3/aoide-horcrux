package com.aoide.member.model;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class JdbcMemberDAOTests
{
    // JUnit creates a new instance of each test class before executing each test method
    // This "per-method" test instance lifecycle is the default behavior in JUnit Jupiter
    private static JdbcMemberDAO memberDao;
    private static Member testVO;

    private static String currentDir;
    private static String testDbUrl;
    private static String userName;
    private static String password;

    @BeforeAll
    public static void suiteSetUp()
    {
        currentDir = System.getProperty( "user.dir" );
        testDbUrl = "jdbc:h2:tcp://localhost/" + currentDir + "/test";
        userName = "admin";
        password = "admin";
        memberDao = new JdbcMemberDAO( testDbUrl, userName, password );

        assumeTrue( memberDao.canConnect(), "cannot connect to db..." );

        testVO = new Member();
		testVO.setAccount( "test012" );
		testVO.setPassword( "abcdefgh" );
		testVO.setName( "Curry" );
		testVO.setEmail( "curry02@email.com" );
    }

    @Test
    @Order( 1 )
    void test_create()
    {
        assertEquals( 1, memberDao.create( testVO ) );
        assertNotNull( testVO.getId() );
    }

    @Test
    @Order( 2 )
    void test_update()
    {
        String newName = "Nancy";
        testVO.setName( newName );

        assertEquals( 1, memberDao.update( testVO ) );
        System.out.println( testVO );
    }

    @Test
    @Order( 3 )
    void test_findByPrimaryKey()
    {
        Optional< Member > optionalMember = memberDao.findByPrimaryKey( testVO.getId() );

        assertTrue( optionalMember.isPresent() );
        assertEquals( optionalMember.get().getId(), testVO.getId() );
    }

    @Test
    @Order( 4 )
    void test_getAll()
    {
        List< Member > memberList = memberDao.getAll();

        assertTrue( !memberList.isEmpty() );
    }

    @Test
    @Order( 5 )
    void test_delete()
    {
        assertEquals( 1, memberDao.delete( testVO.getId() ) );
    }

    private void printMembers( List< Member > list )
    {
        for ( Member vo : list )
		{
			System.out.println( vo.toString() );
		}
    }
}
