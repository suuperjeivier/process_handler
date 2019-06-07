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

import mx.freshmanasoft.phs.entity.BankAction;
import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;
import mx.freshmanasoft.phs.service.BankActionService;
import mx.freshmanasoft.phs.service.bankaccount.BankAccountService;


@RestController
@RequestMapping(path="bank-actions")
public class BankActionController {
	
	@Autowired
	private BankActionService service;
	
	@GetMapping
	public @ResponseBody Iterable<BankAction> getAllBankAccounts(){
		return service.fetch();
	}
	
	@PostMapping
	public BankAction post(@RequestBody final BankAction entity) {
		return service.post(entity);
	}
	
	@PutMapping
	public BankAction put(@RequestBody final BankAction entity) {
		return service.put(entity);
	}
	
	@DeleteMapping 
	BankAction delete(@RequestBody final BankAction entity) {
		return service.delete(entity);
	}
}
