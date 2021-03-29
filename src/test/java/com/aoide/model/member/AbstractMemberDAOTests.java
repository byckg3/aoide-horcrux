package com.aoide.model.member;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.aoide.util.DataGenerator;

@TestInstance( Lifecycle.PER_CLASS ) // a new test instance will be created once per test class
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
abstract class AbstractMemberDAOTests
{
    // JUnit creates a new instance of each test class before executing each test method
    // This "per-method" test instance lifecycle is the default behavior in JUnit Jupiter
    static MemberDAO memberDao;
    static Member testVO;

    abstract void configureDAO();

    void prepareTestVO()
    {
        testVO = new Member();
		testVO.setAccount( "test_" + DataGenerator.generateTimestamp() );
		testVO.setPassword( "abcdefgh" );
		testVO.setName( "Curry" );
		testVO.setEmail( "curry02@email.com" );
    }

    @BeforeAll
    void suiteSetUp()
    {
        configureDAO();
        prepareTestVO();
    }

    @Test
    @Order( 1 )
    void test_save()
    {
        assertDoesNotThrow( () -> memberDao.save( testVO ) );
        assertNotNull( testVO.getId() );
    }

    @Test
    @Order( 2 )
    void test_update()
    {
        String newName = "Nancy";
        testVO.setName( newName );
        
        assertEquals( 1, memberDao.update( testVO ) );
    }

    @Test
    @Order( 3 )
    void test_findByAccount()
    {
        Optional< Member > optionalMember = memberDao.findByAccount( testVO.getAccount() );

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
}