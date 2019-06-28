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
		List<BankAction> byCusipAndSecIdAndIsin = new ArrayList<>();
		List<BankAction> byCusipAndSecIdOnly = new ArrayList<>();		
		List<BankAction> byCusipAndIsinOnly = new ArrayList<>();
		List<BankAction> bySecIdAndIsinOnly = new ArrayList<>();
		List<BankAction> byAccountIdNoCusipAndNoSecIdAndNoIsin = new ArrayList<>();
		List<BankAction> byCusipOnly = new ArrayList<>();
		List<BankAction> byIsinSerieOnly = new ArrayList<>();
		List<BankAction> bySecIdOnly = new ArrayList<>();
		List<BankAction> lbac = null;

		Optional<BankAction> bac = repository.findById(actionId);
		if(bac.isPresent()) {
			lbac = new ArrayList<BankAction>();
			BankAction action = bac.get();
			String cusip = action.getCusip() != null ? action.getCusip().trim() : null;
			String isinSerie = action.getIsinSerie() != null ? action.getIsinSerie().trim() : null;
			String secId = action.getSecId() != null ? action.getSecId().trim() : null;
			LOGGER.info(action.toString());
			//buscar por cusip por isin y por sec identifier
			//primero los 3 juntos
			if(cusip != null && !cusip.isEmpty() && secId != null && !secId.isEmpty() && isinSerie != null && !isinSerie.isEmpty()) {
				byCusipAndSecIdAndIsin = repository.findByCusipAndSecIdAndIsinSerieOrderByFechaInicioReal(cusip, secId, isinSerie);
				if(byCusipAndSecIdAndIsin != null && !byCusipAndSecIdAndIsin.isEmpty()) {
					lbac.addAll(byCusipAndSecIdAndIsin);
				}else {
					LOGGER.debug("nothing found with the params provided for this query [byCusipAndSecIdAndIsin] " + cusip +"|"+ secId + "|" + isinSerie);
				}
			}else {
				LOGGER.debug("no params provided for this query [byCusipAndSecIdAndIsin]");
			}
			//luego los 3 pero uno c/u a la vez osea cusip con sec id o cusip con isin pero el otro null
			if(cusip != null && !cusip.isEmpty() && secId != null && !secId.isEmpty() && (isinSerie == null || isinSerie.isEmpty())) {
				byCusipAndSecIdOnly = repository.findByCusipAndSecIdAndIsinSerieIsNullOrderByFechaInicioReal(cusip, secId);
				if(byCusipAndSecIdOnly != null && !byCusipAndSecIdOnly.isEmpty()) {
					lbac.addAll(byCusipAndSecIdOnly);
				}else {
					LOGGER.debug("nothing found with the params provided for this query [CUSIP, secId]");
				}
			}else {
				LOGGER.debug("no params provided for this query [CUSIP, secId]");
			}
			if(cusip != null && !cusip.isEmpty() && isinSerie != null && !isinSerie.isEmpty() && (secId == null || secId.isEmpty())) {
				byCusipAndIsinOnly = repository.findByCusipAndIsinSerieAndSecIdIsNullOrderByFechaInicioReal(cusip, isinSerie);
				if(byCusipAndIsinOnly != null && !byCusipAndIsinOnly.isEmpty()) {
					lbac.addAll(byCusipAndIsinOnly);
				}else {
					LOGGER.debug("nothing found with the params provided for this query [CUSIP, isinSerie]");
				}
			}else {
				LOGGER.debug("no params provided for this query [CUSIP, isinSerie]");
			}
			
			if(isinSerie != null && !isinSerie.isEmpty() && secId != null && !secId.isEmpty() && (cusip == null || cusip.isEmpty())) {
				bySecIdAndIsinOnly = repository.findByIsinSerieAndSecIdAndCusipIsNullOrderByFechaInicioReal(isinSerie, secId);
				if(bySecIdAndIsinOnly != null && !bySecIdAndIsinOnly.isEmpty()) {
					lbac.addAll(bySecIdAndIsinOnly);
				}else {
					LOGGER.debug("nothing found with the params provided for this query [isinSerie, secId]");
				}
			}else {
				LOGGER.debug("no params provided for this query [isinSerie, secId]");
			}

			//luego 1 c/u a la vez pero lo demas null osea solo cusip con los otros 2 null, solo sec id con los otros 2 null y solo isin con los otros 2 null

			if(cusip != null && !cusip.isEmpty() && (isinSerie == null || isinSerie.isEmpty()) && (secId == null || secId.isEmpty())) {
				byCusipOnly = repository.findByCusipAndIsinSerieIsNullAndSecIdIsNullOrderByFechaInicioReal(cusip);
				if(byCusipOnly != null && !byCusipOnly.isEmpty()) {
					lbac.addAll(byCusipOnly);
				}else {
					LOGGER.debug("nothing found with the params provided for this query [CUSIP]");
				}
			}else {				
				LOGGER.debug("no params provided for this query [CUSIP]");
			}
			if(isinSerie != null && !isinSerie.isEmpty() && (cusip == null || cusip.isEmpty()) && (secId == null || secId.isEmpty())) {
				byIsinSerieOnly = repository.findByIsinSerieAndCusipIsNullAndSecIdIsNullOrderByFechaInicioReal(isinSerie);
				if(byIsinSerieOnly != null && !byIsinSerieOnly.isEmpty()) {
					lbac.addAll(byIsinSerieOnly);
				}else {
					LOGGER.debug("nothing found with the params provided for this query [isinSerie]");	
				}
			}else {
				LOGGER.debug("no params provided for this query [isinSerie]");
			}
			if(secId != null && !secId.isEmpty() && (cusip == null || cusip.isEmpty()) && (isinSerie == null || isinSerie.isEmpty())) {
				bySecIdOnly = repository.findBySecIdAndCusipIsNullAndIsinSerieIsNullOrderByFechaInicioReal(secId);
				if(bySecIdOnly != null && !bySecIdOnly.isEmpty()) {
					lbac.addAll(bySecIdOnly);
				}else {
					LOGGER.debug("nothing found with the params provided for this query [secId]");
				}
			}else {
				LOGGER.debug("no params provided for this query [secId]");
			}
			if((secId == null || secId.isEmpty()) && (cusip == null || cusip.isEmpty()) && (isinSerie == null || isinSerie.isEmpty())){
				if(action.getAccount() != null && action.getAccount().getId() != null) {
					byAccountIdNoCusipAndNoSecIdAndNoIsin = (List<BankAction>) repository.findAllByAccountIdAndCusipIsNullAndIsinSerieIsNullAndSecIdIsNullOrderByFechaInicioReal(action.getAccount().getId());
					if(byAccountIdNoCusipAndNoSecIdAndNoIsin != null && !byAccountIdNoCusipAndNoSecIdAndNoIsin.isEmpty()) {
						lbac.addAll(byAccountIdNoCusipAndNoSecIdAndNoIsin);
					}else {
						LOGGER.debug("nothing found with the params provided for this query [byAccountIdNoCusipAndNoSecIdAndNoIsin]");
					}
				}else {
					LOGGER.debug("Account param was not provided for this query [byAccountIdNoCusipAndNoSecIdAndNoIsin]");
				}				
			}else {
				LOGGER.debug("params provided for this query [byAccountIdNoCusipAndNoSecIdAndNoIsin]");
			}
		}else {
			LOGGER.debug("action no present!");
		}		

		return lbac;
	}


}
