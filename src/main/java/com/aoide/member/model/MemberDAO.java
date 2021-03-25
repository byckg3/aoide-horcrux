package com.aoide.member.model;

import java.util.List;
import java.util.Optional;

public interface MemberDAO 
{
	public void save( Member valueObject );
	public int update( Member newValueObject );
	public int delete( Long id );
	public Optional< Member > findByAccount( String account );
	public List< Member > getAll();
}