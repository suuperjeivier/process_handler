package mx.freshmanasoft.phs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.BankAction;

public interface BankActionRepository extends CrudRepository<BankAction, Long>{
	Iterable<BankAction> findByStatus(int status);

	Iterable<BankAction> findByAccountId(Long accountId);

	Iterable<BankAction> findByCusipOrIsinSerieOrSecId(String cusip, String isinSerie, String secId);

	List<BankAction> findByCusipAndIsinSerieNotNullAndSecIdNotNullOrderByFechaInicioRealAndFechaFinalReal(String cusip);

	List<BankAction> findByIsinSerieAndCusipNotNullAndSecIdNotNullOrderByFechaInicioRealAndFechaFinalReal(String isinSerie);

	List<BankAction> findBySecIdAndCusipNotNullAndIsinSerieNotNullOrderByFechaInicioRealAndFechaFinalReal(String isinSerie);

	List<BankAction> findByCusipAndIsinSerieIsNullAndSecIdIsNullOrderByFechaInicioRealAndFechaFinalReal(String cusip);

	List<BankAction> findByIsinSerieAndCusipIsNullAndSecIdIsNullOrderByFechaInicioRealAndFechaFinalReal(String isinSerie);

	List<BankAction> findBySecIdAndCusipIsNullAndIsinSerieIsNullOrderByFechaInicioRealAndFechaFinalReal(String secId);

	List<BankAction> findByCusipAndIsinSerieIsNullAndSecIdOrderByFechaInicioRealAndFechaFinalReal(String cusip, String secId);

	List<BankAction> findByCusipAndIsinSerieAndSecIdIsNullOrderByFechaInicioRealAndFechaFinalReal(String cusip, String secId);

	List<BankAction> findByCusipAndSecIdAndIsinSerieIsNullOrderByFechaInicioRealAndFechaFinalReal(String cusip, String secId);

	List<BankAction> findByIsinSerieAndSecIdAndCusipIsNullOrderByFechaInicioRealAndFechaFinalReal(String isinSerie, String secId);

	List<BankAction> findByCusipAndSecIdAndIsinSerieOrderByFechaInicioRealAndFechaFinalReal(String cusip, String secId, String isinSerie);

}
