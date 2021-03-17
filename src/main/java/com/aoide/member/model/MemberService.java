package com.aoide.member.model;

import java.util.List;
import java.util.Optional;

public class MemberService
{
	private MemberDAO dao;

	public MemberService( MemberDAO dao )
	{
		this.dao = dao;
	}
	
	public MemberVO createMemberAccount( MemberVO vo )
	{
		dao.create( vo );
		return vo;
	}
	
	public int updateMemberProfile( MemberVO vo ) {
		return dao.update( vo );
	}
	
	public int deleteMemberAccount( String account ) {
		return dao.delete( account );
	}
	
	public Optional< MemberVO > findMemberProfile( String account )
	{
		return dao.findByAccount( account );
	}
	
	public List< MemberVO > getAllMemberProfiles()
	{
		return dao.getAll();
	}

	public static void main( String[] args ) 
	{
		MemberVO m = new MemberVO();
		
		m.setAccount( "smallBirdBeauty" );
		m.setPassword( "28825252" );
		m.setName( "小鳥美麗" );
		m.setEmail( "fattyCutty@outlook.com" );
		
		MemberService ms = new MemberService( new JdbcMemberDAO() );
		
		System.out.println( ms.createMemberAccount( m ) );
		System.out.println( ms.updateMemberProfile( m ) );
		// System.out.println( ms.findMemberProfile( "whiteBirdBeauty" ) );

		
		System.out.println( ms.getAllMemberProfiles() );
		System.out.println( ms.deleteMemberAccount( "smallBirdBeauty" ) );
	}

}
