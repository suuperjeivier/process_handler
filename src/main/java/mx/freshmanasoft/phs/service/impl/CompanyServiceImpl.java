package mx.freshmanasoft.phs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.Company;
import mx.freshmanasoft.phs.repository.CompanyRepository;
import mx.freshmanasoft.phs.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private CompanyRepository repository;
	
	@Override
	public Iterable<Company> fetch() {
		return repository.findByStatus(1);
	}

	@Override
	public Company post(Company entity) {

		return repository.save(entity);
	}

	@Override
	public Company put(Company entity) {
		
		return repository.save(entity);
	}

	@Override
	public Company delete(Company entity) {
		return repository.save(entity);
	}

}
