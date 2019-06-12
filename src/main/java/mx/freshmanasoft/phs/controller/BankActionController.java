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

import mx.freshmanasoft.phs.entity.BankAction;
import mx.freshmanasoft.phs.service.BankActionService;


@RestController
@RequestMapping(path="bank-actions")
public class BankActionController {
	
	@Autowired
	private BankActionService service;
	
	@GetMapping
	public @ResponseBody Iterable<BankAction> getAllBankAccounts(){
		return service.fetch();
	}
	
	@GetMapping("account/id")
	public @ResponseBody Iterable<BankAction> getByAccountId(@RequestParam(name="accountId") final Long accountId){
		return service.fetch(accountId);
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
