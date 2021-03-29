package com.aoide.model.member;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTests
{
    private static Validator validator;
    private Set< ConstraintViolation< Member > > constraintViolations;
    private Member member;

    @BeforeAll
    public static void suiteSetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @BeforeEach
    void setUp() {
        member = new Member();
        member.setAccount( "test001" );
        member.setPassword( "password" );
        member.setName( "Alex" );
        member.setEmail( "alex@gmail.com" );
    }

    @Test
    public void test_createMemberAccount()
    {
        
    }

//     @Test
//     public void test_accountCannotBeNull()
//     {
        
        
//     }

//    @Test
//     public void test_email()
//     {
        
//     }

    private void printError()
    {
        for ( ConstraintViolation< Member > violation : constraintViolations ) {
            System.out.println( violation.getMessage() ); 
        }
    }
}
