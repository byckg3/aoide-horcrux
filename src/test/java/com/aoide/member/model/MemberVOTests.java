package com.aoide.member.model;

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

public class MemberVOTests
{
    private static Validator validator;
    private Set< ConstraintViolation< MemberVO > > constraintViolations;
    private MemberVO member;

    @BeforeAll
    public static void suiteSetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @BeforeEach
    void setUp() {
        member = new MemberVO();
        member.setAccount( "test001" );
        member.setPassword( "password" );
        member.setName( "Alex" );
        member.setEmail( "alex@gmail.com" );
    }

//     @Test
//     public void test_validMemberInfo()
//     {
        
//     }

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
        for ( ConstraintViolation< MemberVO > violation : constraintViolations ) {
            System.out.println( violation.getMessage() ); 
        }
    }
}
