package mx.freshmanasoft.phs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;
import mx.freshmanasoft.phs.service.bankaccount.BankAccountService;


@RestController
@RequestMapping(path="bank-accounts")
public class BankAccountController {
	
	@Autowired
	private BankAccountService service;
	
	@GetMapping
	public @ResponseBody Iterable<BankAccount> getAllBankAccounts(){
		return service.fetch();
	}
	
	@GetMapping("company/id")
	public @ResponseBody Iterable<BankAccount> getByCompany(@RequestParam(name="companyId") final Long companyId){
		return service.fetch(companyId);
	}
	
	@PostMapping
	public BankAccount post(@RequestBody final BankAccount entity) {
		return service.post(entity);
	}
	
	@PutMapping
	public BankAccount put(@RequestBody final BankAccount entity) {
		return service.put(entity);
	}
	
	@DeleteMapping 
	BankAccount delete(@RequestBody final BankAccount entity) {
		return service.delete(entity);
	}
}
