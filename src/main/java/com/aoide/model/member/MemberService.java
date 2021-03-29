package com.aoide.model.member;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import com.aoide.util.DataGenerator;
import com.aoide.util.PasswordEncoder;

public class MemberService
{
	private MemberDAO dao;

	public MemberService( MemberDAO dao )
	{
		this.dao = dao;
	}
	
	public Member createMemberAccount( Member vo )
	{
		String salt = DataGenerator.generateBase64RandomString( 20 );
		String saltedPassword = PasswordEncoder.encode( vo.getPassword(), salt );

		vo.setSalt( salt );
		vo.setPassword( saltedPassword );
		dao.save( vo );

		return vo;
	}

	public boolean verify( String account, String password )
	{
		Optional< Member > optionalMember = findMemberProfile( account );
		if ( optionalMember.isPresent() )
		{
			Member memberProfile = optionalMember.get();
			String saltedPassword = PasswordEncoder.encode( password, memberProfile.getSalt() );

			return saltedPassword.equals( memberProfile.getPassword() );
		}
		return false;
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

	private byte[] getSalt()
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[ 16 ];

        random.nextBytes(salt);

        return salt;
    }
}
