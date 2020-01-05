package com.zhuliang.oauth.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.zhuliang.oauth.entity.Role;
import com.zhuliang.oauth.entity.User;


/**
 * 
 * @author zhuliang
 *
 * @date 2017年12月20日
 */
public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;
	private List<Role> roles;

	public UserDetail(User user, List<Role> roles) {
		this.user = user;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles == null || roles.size() < 1) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("");
		}
		StringBuilder commaBuilder = new StringBuilder();
		for (Role role : roles) {
			commaBuilder.append(role.getName()).append(",");
		}
		String authorities = commaBuilder.substring(0, commaBuilder.length() - 1);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getAccount();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
    public int hashCode() {
        return getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserDetail) {
            return ((UserDetail) obj).getUsername().endsWith(getUsername());
        }
        return false;
    }
}