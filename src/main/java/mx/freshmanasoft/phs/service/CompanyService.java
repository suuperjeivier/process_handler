package mx.freshmanasoft.phs.service;

import mx.freshmanasoft.phs.entity.Company;

public interface CompanyService {
	Iterable<Company> fetch();
	Company post(Company entity);
	Company put(Company entity);
	Company delete(Company entity);
}
