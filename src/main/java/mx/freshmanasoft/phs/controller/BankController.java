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

import mx.freshmanasoft.phs.entity.Bank;
import mx.freshmanasoft.phs.service.BankService;

@RestController
@RequestMapping(path="bank")
public class BankController {
	
	@Autowired
	private BankService service;
	
	@GetMapping
	public @ResponseBody Iterable<Bank> getAllBank(){
		return service.fetch();
	}
	
	@PostMapping
	public Bank post(@RequestBody final Bank entity) {
		return service.post(entity);
	}
	
	@PutMapping
	public Bank put(@RequestBody final Bank entity) {
		return service.put(entity);
	}
	
	@DeleteMapping
	public Bank delete(@RequestBody final Bank entity) {
		return service.delete(entity);
	}
}
