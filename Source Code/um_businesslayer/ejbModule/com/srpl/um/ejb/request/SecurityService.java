package com.srpl.um.ejb.request;

import java.util.List;

import javax.ejb.Remote;

import com.srpl.um.ejb.entity.GroupPermission;

@Remote
public interface SecurityService {

	public List<GroupPermission> getUserServicesByUserId(Long userId);
}
