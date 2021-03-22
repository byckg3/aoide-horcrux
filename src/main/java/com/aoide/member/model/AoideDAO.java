package com.aoide.member.model;

import java.util.List;
import java.util.Optional;

public interface AoideDAO< T >
{
    public int create( T valueObject );
    public int update( T newValueObject );
    public int delete( Object pk );
    public Optional< T > find( Object key );
    public List< T > getAll();
}
