package com.aoide.model.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DummyMemberDAO implements MemberDAO
{
    public int saveCalled = 0;
    public int updateCalled = 0;
    public int deleteCalled = 0;
    public int findByAccountCalled = 0;
    public int getAllCalled = 0;

    @Override
	public void save( Member vo ) {
        saveCalled++;
		vo.setId( 1L );
	}

	@Override
	public int update( Member vo )
    {
        updateCalled++;
		return updateCalled;
	}

	@Override
	public int delete( Long id )
    {
        deleteCalled++;
		return deleteCalled;
	}

	@Override
	public Optional< Member > findByAccount( String account )
    {
        findByAccountCalled++;
        Member m = getFakeMember();
        m.setAccount( account );

        return Optional.of( m );
	}

	@Override
	public List< Member > getAll()
    {
        getAllCalled++;
		List< Member > voList = new ArrayList<>();
        voList.add( getFakeMember() );
		
        return voList;
	}

    public Member getFakeMember()
    {
        return new Member();
    }
}
