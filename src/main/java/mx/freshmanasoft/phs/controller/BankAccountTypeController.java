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

import mx.freshmanasoft.phs.entity.BankAccountType;
import mx.freshmanasoft.phs.service.BankAccountTypeService;

@RestController
@RequestMapping(path="bank/account/type")
public class BankAccountTypeController {
	
	@Autowired
	private BankAccountTypeService service;
	
	@GetMapping
	public @ResponseBody Iterable<BankAccountType> getAll(){
		return service.fetch();
	}
	
	@PostMapping
	public BankAccountType post(@RequestBody final BankAccountType entity) {
		return service.post(entity);
	}
	
	@PutMapping
	public BankAccountType put(@RequestBody final BankAccountType entity) {
		return service.put(entity);
	}
	
	@DeleteMapping
	public BankAccountType delete(@RequestBody final BankAccountType entity) {
		return service.delete(entity);
	}
}
