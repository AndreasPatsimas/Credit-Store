package gr.pwc.assignment.services.impl;

import gr.pwc.assignment.domain.StaffMember;
import gr.pwc.assignment.dto.authenticate.UserDetailsDTO;
import gr.pwc.assignment.repositories.StaffMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final StaffMemberRepository staffMemberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("Load staffMember process [username: {}]", username);

		Optional<StaffMember> staffMember = staffMemberRepository.findByUserName(username);

		if(staffMember.isPresent())
			return new UserDetailsDTO(staffMember.get());
		else
			throw new UsernameNotFoundException("Not found: " + username);
	}

}
