package com.empresa.marco.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.marco.repository.GenericRepository;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	GenericRepository repository;
}
