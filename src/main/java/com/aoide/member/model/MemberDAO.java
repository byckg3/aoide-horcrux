package com.aoide.member.model;

import java.util.List;
import java.util.Optional;

public interface MemberDAO 
{
	public int create( MemberVO valueObject );
	public int update( MemberVO newValueObject );
	public int delete( String account );
	public Optional< MemberVO > findByAccount( String account );
	public List< MemberVO > getAll();
}
