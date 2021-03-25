package com.aoide.member.model;

import java.util.List;
import java.util.Optional;

import com.aoide.util.HibernateDAOHelper;

public class HibernateMemberDAO implements MemberDAO
{
    private HibernateDAOHelper< Member > daoHelper;

    HibernateMemberDAO( String cfgFilePath )
    {
        daoHelper = new HibernateDAOHelper<>( cfgFilePath );
    }
    
    @Override
    public void save( Member vo )
    {
        daoHelper.persist( vo );
    }

    @Override
	public int update( Member vo )
    {
        String hql = "UPDATE Member SET password = ?1, name = ?2, email = ?3 WHERE account = ?4";
        
        return daoHelper.parameterizedQuery( hql, vo.getPassword(), vo.getName(), vo.getEmail(), vo.getAccount() );
    }

    @Override
	public int delete( Long id )
    {
        String hql = "DELETE FROM Member WHERE id = ?1";

        return daoHelper.parameterizedQuery( hql, id );
    }

    @Override
	public Optional< Member > findByAccount( String account )
    {
        String hql = "FROM Member WHERE account = ?1";
        Member member = daoHelper.list( hql, account ).get( 0 );

        return Optional.ofNullable( member );
    }

    @Override
	public List< Member > getAll()
    {
        String hql = "FROM Member";

        return daoHelper.list( hql );
    }
}
