package com.aoide.member.model;

import java.util.List;
import java.util.Optional;

public class MemberService
{
	private AoideDAO< Member > dao;

	public MemberService( AoideDAO< Member > dao )
	{
		this.dao = dao;
	}
	
	public Member createMemberAccount( Member vo )
	{
		dao.create( vo );
		return vo;
	}
	
	public int updateMemberProfile( Member vo ) {
		return dao.update( vo );
	}
	
	public int deleteMemberProfile( Long id ) {
		return dao.delete( id );
	}
	
	public Optional< Member > findMemberProfile( Long id )
	{
		return dao.findByPrimaryKey( id );
	}
	
	public List< Member > getAllMemberProfiles()
	{
		return dao.getAll();
	}
}
