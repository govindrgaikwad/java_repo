package com.empresa.marco.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.marco.repository.GenericRepository;

@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {

	private static Logger logger = Logger.getLogger(ProjectServiceImpl.class);

	@Autowired
	GenericRepository repository;
}
