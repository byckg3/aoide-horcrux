package com.aoide.model.member;

import java.util.List;
import java.util.Optional;

public class MemberService
{
	private MemberDAO dao;

	public MemberService( MemberDAO dao )
	{
		this.dao = dao;
	}
	
	public Member createMemberAccount( Member vo )
	{
		dao.save( vo );
		return vo;
	}
	
	public int updateMemberProfile( Member vo ) {
		return dao.update( vo );
	}
	
	public int deleteMemberProfile( Long id ) {
		return dao.delete( id );
	}
	
	public Optional< Member > findMemberProfile( String account )
	{
		return dao.findByAccount( account );
	}
	
	public List< Member > getAllMemberProfiles()
	{
		return dao.getAll();
	}
}
