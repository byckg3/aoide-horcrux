package com.aoide.member.model;

class HibernateMemberDAOTests extends AbstractMemberDAOTests
{
    @Override
    void configureDAO()
    {
        memberDao = new HibernateMemberDAO( "test_hibernate.cfg.xml" );
    }
}
