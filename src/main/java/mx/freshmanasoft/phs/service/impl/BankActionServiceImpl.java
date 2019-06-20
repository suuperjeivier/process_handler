package mx.freshmanasoft.phs.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.BankAction;
import mx.freshmanasoft.phs.repository.BankActionRepository;
import mx.freshmanasoft.phs.service.BankActionService;

@Service("bankActionService")
public class BankActionServiceImpl implements BankActionService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BankActionServiceImpl.class);

	@Autowired
	private BankActionRepository repository;

	@Override
	public Iterable<BankAction> fetch() {
		LOGGER.debug("getting actions!");
		return repository.findAll();
	}

	@Override
	public BankAction post(BankAction entity) {
		return repository.save(entity);
	}

	@Override
	public BankAction put(BankAction entity) {
		return repository.save(entity);
	}

	@Override
	public BankAction delete(BankAction entity) {
		repository.delete(entity);
		entity.setId(null);
		return entity;
	}

	@Override
	public Iterable<BankAction> fetch(Long accountId) {
		return repository.findByAccountId(accountId);
	}

	@Override
	public Iterable<BankAction> fetchHistory(Long actionId) {
		List<BankAction> byCusip = new ArrayList<>();
		List<BankAction> byIsinSerie = new ArrayList<>();
		List<BankAction> bySecId = new ArrayList<>();
		List<BankAction> lbac = null;
	
		Optional<BankAction> bac = repository.findById(actionId);
		if(bac.isPresent()) {
			BankAction action = bac.get();
			String cusip = action.getCusip();
			String isinSerie = action.getIsinSerie();
			String secId = action.getSecId();


			if(cusip != null) {
				byCusip = repository.findByCusipAndIsinSerieNotNullAndSecIdNotNull(cusip);
			}else {
				if(isinSerie != null) {
					byIsinSerie = repository.findByIsinSerieAndCusipNotNullAndSecIdNotNull(isinSerie);
				}else if(secId != null) {
					bySecId = repository.findBySecIdAndCusipNotNullAndIsinSerieNotNull(secId);
				}else {
					LOGGER.debug("no params provided for this query");
				}
				
			}
			lbac = new ArrayList<BankAction>();
			lbac.addAll(byCusip);
			lbac.addAll(byIsinSerie);
			lbac.addAll(bySecId);


		
		}

		return lbac;
	}


}
