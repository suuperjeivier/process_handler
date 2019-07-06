package mx.freshmanasoft.phs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.Loggin;
import mx.freshmanasoft.phs.repository.LogginRepository;
import mx.freshmanasoft.phs.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService{
	
	@Autowired
	private LogginRepository repository;
	
	@Override
	public Iterable<Loggin> fetch() {
		
		return repository.findAllByOrderByDateDesc();
	}
	
}
