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
	public @ResponseBody Iterable<BankAction> getAllBankActions(){
		return service.fetch();
	}
	
	@GetMapping("grouped")
	public @ResponseBody Iterable<BankAction> getAllGrouped(){
		return service.fetchGrouped();
	}
	
	@GetMapping("inst/grouped")
	public @ResponseBody Iterable<String> getInstGrouped(){
		return service.fetchInstGrouped();
	}
	
	@GetMapping("history/id")
	public @ResponseBody Iterable<BankAction> getActionHistory(@RequestParam(name="actionId") final Long actionId){
		return service.fetchHistory(actionId);
	}
	
	@GetMapping("history/cusip/isinSerie/secId")
	public @ResponseBody Iterable<BankAction> getActionHistoryH(
			@RequestParam(name="cusip") final String cusip,
			@RequestParam(name="isinSerie") final String isinSerie,
			@RequestParam(name="secId") final String secId){
		return service.fetchHistoryH(cusip,cusip,secId);
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
