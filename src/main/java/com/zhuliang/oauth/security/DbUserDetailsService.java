package com.zhuliang.oauth.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zhuliang.oauth.entity.Role;
import com.zhuliang.oauth.entity.User;
import com.zhuliang.oauth.service.RoleService;
import com.zhuliang.oauth.service.UserService;



@Component
public class DbUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.selectByAccount(username);
		if (user == null) {
			throw new UsernameNotFoundException("账号不存在");
		}
		List<Role> roles = roleService.selectRolesByUserId(user.getId());
		return new UserDetail(user, roles);
	}

}
