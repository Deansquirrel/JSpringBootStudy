package com.yuansong.study.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yuansong.study.repository.UserRepository;
import com.yuansong.study.repository.dao.Role;
import com.yuansong.study.repository.dao.User;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("acc is not exists");
		}
		return new UserDetails() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<SimpleGrantedAuthority> authorities = new ArrayList<>();
				List<Role> roleList = userRepository.getUserRoleById(user.getId());
				for(Role role : roleList) {
					authorities.add(new SimpleGrantedAuthority(role.getName()));
				}
				return authorities;
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getUsername();
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return !user.getLocked();
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return user.getEnabled();
			}
			
		};
	}

}
