package com.aoide.model.member;

class HibernateMemberDAOTests extends AbstractMemberDAOTests
{
    @Override
    void configureDAO()
    {
        memberDao = new HibernateMemberDAO( "test_hibernate.cfg.xml" );
    }
}
