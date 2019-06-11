package mx.freshmanasoft.phs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.freshmanasoft.phs.entity.Company;
import mx.freshmanasoft.phs.service.CompanyService;

@RestController
@RequestMapping(path="company")
public class CompanyController {
	
	@Autowired
	private CompanyService service;
	
	@GetMapping
	public @ResponseBody Iterable<Company> getAllCompanies(){
		return service.fetch();
	}
	
	@PostMapping
	public Company post(@RequestBody final Company entity) {
		return service.post(entity);
	}
	
	@PutMapping
	public Company put(@RequestBody final Company entity) {
		return service.post(entity);
	}
	
	@DeleteMapping
	public Company delete(@RequestBody final Company entity) {
		return service.post(entity);
	}
}
