package com.empresa.marco.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.marco.repository.GenericRepository;

@Service("MethodService")
public class MethodServiceImpl implements MethodService {

	private static Logger logger = Logger.getLogger(MethodServiceImpl.class);

	@Autowired
	GenericRepository repository;
}
