app.controller('bankActionsCtrl', function ($scope, $filter, $state, $stateParams, banksService, bankActionService, storageService, batchService, ratesAPIService, bankAccountService) {
	const self = this;
	self.bankActions = [];
	self.bankAction = null;
	self.tcInical = {};
	self.tcFinal = {};
	self.account = null;
	self.subAccountType = 0;
	//pager
	self.viewby = 10;	
	self.currentPage = 1;
	self.itemsPerPage = self.viewby;
	self.maxSize = 5; //Number of pager buttons to show
	self.maxDate = new Date();

	//end pager
	self.newBankAction = () => {

		self.bankAction = {
				status: 1
		}
	};

	self.cancel = () => {
		self.bankAction = null;
	};

	self.getBankAccounts = () => {
		bankAccountService.get().then( data => {
			self.bankAccounts = data;
		}, error => {

		});
	};

	self.getBanks = () => {
		banksService.get().then(data => {
			self.banks = data;
		}, error => {
			console.log('Error al obtener los bancos');
		});
	};

	self.get = () => {
		if(self.account){
			bankActionService.getByAccountId(self.account.id).then(data => {
				if(self.subAccountType > 0){
					self.bankActions = $filter('filter')(data, {subAccountType: self.subAccountType});
				}else{
					self.bankActions = data;
				}				
				self.paginarAcciones();
			}, error => {
				alertify.error('Error');
				console.log('Error al obtener las acciones bancaras', error);
			});
		}else{
			bankActionService.getAllGrouped().then(data => {
				console.log(data);
				self.bankActions = data;
				self.paginarAcciones();
			}, error => {
				alertify.error('Error');
				console.log('Error al obtener las acciones bancaras', error);
			});
		}

	};
//	pager

	self.paginarAcciones = function(){
		if(self.bankActions.length > 10){
			self.numPages = Math.ceil(self.bankActions.length / self.itemsPerPage);		
			$scope.$watch('bactCtrl.currentPage + bactCtrl.itemsPerPage', function() {
				var begin = ((self.currentPage - 1) * self.itemsPerPage) , end = begin + self.itemsPerPage;
				console.log("begin: " + begin+" end: " +end)
				self.filteredActions = self.bankActions.slice(begin, end);
			});
		}else{
			self.filteredActions = angular.copy(self.bankActions);
		}

	}
	self.paginarAccionesFilter = function(array){
		if(array.length > 10){
			self.numPages = Math.ceil(array.length / self.itemsPerPage);		
			$scope.$watch('bactCtrl.currentPage + bactCtrl.itemsPerPage', function() {
				var begin = ((self.currentPage - 1) * self.itemsPerPage)
				, end = begin + self.itemsPerPage;
				self.filteredActions = array.slice(begin, end);
			});
		}else{
			self.filteredActions = angular.copy(array);
		}

	}

	self.filterActions = () =>{
		let array = $filter('filter')(self.bankActions, {cusip: self.filterActionNumberCusip, isinSerie: self.filterActionNumberIsin, secId: self.filterActionNumberSecId});
		if(array != null && array.length){
			self.paginarAccionesFilter(array);
		}else{
			alertify.alert('Error', 'Sin datos para la consulta con los datos proporcionados', function(){ alertify.error('No encontrado'); });
		}

	};


	self.setPage = function (pageNo) {
		self.currentPage = pageNo;
	};

	self.pageChanged = function() {
		console.log('Page changed to: ' + self.currentPage);
	};

	self.setItemsPerPage = function(num) {
		self.itemsPerPage = num;
		self.currentPage = 1; //reset to first page
	}
	//end pager

	self.post = () => {
		if(self.account){
			self.bankAction.account = self.account;
			self.bankAction.subAccountType = self.subAccountType;
		}		
		console.log('informacion de la accion: ',self.bankAction);
		bankActionService.post(self.bankAction).then(data => {
			self.bankAction = null;
			alertify.alert('Exito', 'Registro exitoso', function(){ alertify.success('Ok'); });
			self.get();
		}, error => {
			alertify.error('Error');
			console.log('Error al registrar la accion bancaria', error);
		});
	};

	self.update = bankAction => {
		console.log('bankAction: ',bankAction);
		//bankAction.fechaInicio = new Date(bankAction.fechaInicio);		
		bankAction.fechaFinalReal = new Date(bankAction.fechaFinalReal);
		bankAction.fechaDeAdquisicion = new Date(bankAction.fechaDeAdquisicion);
		bankAction.fechaFinal =  $filter('date')(bankAction.fechaFinalReal, 'dd/MM/yyyy');
		if(bankAction.valuation){
			bankAction.valuation.fechaFinalDelPeriodo = new Date(bankAction.valuation.fechaFinalDelPeriodo);
			bankAction.valuation.utilidadPerdidaPorValuacion = bankAction.valuation.valuacionDolaresAlFinal - bankAction.valuation.valuacionDolaresPeriodoAnterior;
		}	
		if(bankAction.sell){
			bankAction.sell.fechaVenta = new Date(bankAction.sell.fechaVenta);
		}	
		
		self.bankAction = bankAction;
		self.fetchForRates(self.bankAction);
		self.selectedBank = self.bankAction.account.bank;
	};

	self.submitForm = isValid => {
		console.log('Valid Form');
		if (isValid) {
			self.addUpdate();
		}
	};

	self.addUpdate = () => {

		if(self.bankAction.titulos && typeof self.bankAction.titulos === 'string') self.bankAction.titulos = parseFloat(self.bankAction.titulos);
		if(self.bankAction.unitCost && typeof self.bankAction.unitCost === 'string') self.bankAction.unitCost = parseFloat(self.bankAction.unitCost);
		if(self.bankAction.tcInicial && typeof self.bankAction.tcInicial === 'string') self.bankAction.tcInicial = parseFloat(self.bankAction.tcInicial);
		if(self.bankAction.tcFinal && typeof self.bankAction.tcFinal === 'string') self.bankAction.tcFinal = parseFloat(self.bankAction.tcFinal);
		if(self.bankAction.marketPrice && typeof self.bankAction.marketPrice === 'string') self.bankAction.marketPrice = parseFloat(self.bankAction.marketPrice);
		if(self.bankAction.valorActualRegistradoManualmente && typeof self.bankAction.valorActualRegistradoManualmente === 'string') self.bankAction.valorActualRegistradoManualmente = parseFloat(self.bankAction.valorActualRegistradoManualmente);
		if(self.bankAction.saldoInicial && typeof self.bankAction.saldoInicial === 'string') self.bankAction.saldoInicial = parseFloat(self.bankAction.saldoInicial);
		
		//valuacion
		if(self.bankAction.valuation.marketPrice && typeof self.bankAction.valuation.marketPrice === 'string') self.bankAction.valuation.marketPrice = parseFloat(self.bankAction.valuation.marketPrice);
		if(self.bankAction.valuation.valorDeMercado && typeof self.bankAction.valuation.valorDeMercado === 'string') self.bankAction.valuation.valorDeMercado = parseFloat(self.bankAction.valuation.valorDeMercado);
		if(self.bankAction.valuation.plusvMinusvMensual && typeof self.bankAction.valuation.plusvMinusvMensual === 'string') 
			self.bankAction.valuation.plusvMinusvMensual = parseFloat(self.bankAction.valuation.plusvMinusvMensual);
		if(self.bankAction.valuation.plusvMinusvAcumulado && typeof self.bankAction.valuation.plusvMinusvAcumulado === 'string') 
			self.bankAction.valuation.plusvMinusvAcumulado = parseFloat(self.bankAction.valuation.plusvMinusvAcumulado);
		if(self.bankAction.valuation.intDevMensual && typeof self.bankAction.valuation.intDevMensual === 'string') 
			self.bankAction.valuation.intDevMensual = parseFloat(self.bankAction.valuation.intDevMensual);
		if(self.bankAction.valuation.intDevAcumulado && typeof self.bankAction.valuation.intDevAcumulado === 'string') 
			self.bankAction.valuation.intDevAcumulado = parseFloat(self.bankAction.valuation.intDevAcumulado);
		if(self.bankAction.valuation.tcInicial && typeof self.bankAction.valuation.tcInicial === 'string') 
			self.bankAction.valuation.tcInicial = parseFloat(self.bankAction.valuation.tcInicial);
		if(self.bankAction.valuation.tcFinal && typeof self.bankAction.valuation.tcFinal === 'string') 
			self.bankAction.valuation.tcFinal = parseFloat(self.bankAction.valuation.tcFinal);
		if(self.bankAction.valuation.valuacionDolaresPeriodoAnterior && typeof self.bankAction.valuation.valuacionDolaresPeriodoAnterior === 'string') 
			self.bankAction.valuation.valuacionDolaresPeriodoAnterior = parseFloat(self.bankAction.valuation.valuacionDolaresPeriodoAnterior);
		if(self.bankAction.valuation.valuacionDolaresAlFinal && typeof self.bankAction.valuation.valuacionDolaresAlFinal === 'string') 
			self.bankAction.valuation.valuacionDolaresAlFinal = parseFloat(self.bankAction.valuation.valuacionDolaresAlFinal);
		if(self.bankAction.valuation.utilidadPerdidaPorValuacion && typeof self.bankAction.valuation.utilidadPerdidaPorValuacion === 'string') 
			self.bankAction.valuation.utilidadPerdidaPorValuacion = parseFloat(self.bankAction.valuation.utilidadPerdidaPorValuacion);
		
		//venta
		if(self.bankAction.sell.saldoInicial && typeof self.bankAction.sell.saldoInicial === 'string') 
			self.bankAction.sell.saldoInicial = parseFloat(self.bankAction.sell.saldoInicial);
		if(self.bankAction.sell.tcInicial && typeof self.bankAction.sell.tcInicial === 'string') 
			self.bankAction.sell.tcInicial = parseFloat(self.bankAction.sell.tcInicial);
		if(self.bankAction.sell.tcFinal && typeof self.bankAction.sell.tcFinal === 'string') 
			self.bankAction.sell.tcFinal = parseFloat(self.bankAction.sell.tcFinal);
		
		
		if (self.bankAction.id) {
			self.put();
		} else {
			self.post();
		}
	};

	self.put = () => {
		bankActionService.post(self.bankAction).then(data => {
			alertify.alert('Exito', 'Actualización exitosa', function(){ alertify.success('Ok'); });
			self.bankAction = null;
			self.get();
		}, error => {
			alertify.error('Error');
			console.log('Error al actualizar la accion bancaria', error);
		});
	};

	self.del = bankAction => {
		bankAction.status = 0;
		bankActionService.del(bankAction).then(data => {
			alertify.alert('Exito', 'Eliminación exitosa', function(){ alertify.success('Ok'); });
			bankAction = null;
			self.get();
		}, error => {
			alertify.error('Error');
			console.log("Error al eliminar el la accion bancaria", error);
		});
	};

	self.addDocument =()=>{
		var file = self.myFile;
		console.log('file is ' );
		console.dir(file);
		self.processing = true;
		if(self.account){			
			storageService.post(file).then(resp =>{
				console.log(resp);
				if(resp.message == "FILE_UPLODED"){
					batchService.postSubAccount(resp.newFileName, self.account.id, self.subAccountType).then(resp =>{
						console.log(resp);
						$('#exampleModal').modal('toggle');
						alertify.alert('Exito', 'correcto, archivo procesado!', function(){ alertify.success('Ok'); });
						self.get();
						self.processing = false;
					},error =>{
						console.error("error");
						self.processing = false;
					});
				}else{
					alertify.success('Error');
					console.error("error en mensaje de carga");
					self.processing = false;
				}
			},error =>{
				alertify.success('Error');
				console.error("error de procesado en carga");
				self.processing = false;
			});
		}else{
			alertify.alert('Error', 'No permitido, debe iniciar con un numero de cuenta!', function(){ alertify.error('Detenido'); });
			self.processing = false;
		}
		//send your binary data via $http or $resource or do anything else with it

	};


	/**
	 * let keys = Object.keys(resp.rates);
			let firstKey = keys[0];
			let lastKey = keys[Object.keys(resp.rates).length-1];
			console.log(firstKey);
			console.log(lastKey);
	 */
	self.fetchForRates = (bac) =>{
		let dateInicio =  new Date(bac.fechaInicioReal);
		let fechaInicio =  dateInicio.getFullYear() > 1969 ? $filter('date')(bac.fechaInicioReal, 'yyyy-MM-dd') : $filter('date')(bac.fechaDeAdquisicion, 'yyyy-MM-dd');
		let fechaFin = $filter('date')(bac.fechaFinalReal, 'yyyy-MM-dd');
		console.log("inicio:", fechaInicio);
		console.log("fin:", fechaFin);
		bac.fechaInicioReal = new Date(bac.fechaInicioReal);
		bac.fechaFinalReal = new Date(bac.fechaFinalReal);


		ratesAPIService.fecthOnPeriod(""+fechaInicio, ""+fechaFin).then(resp =>{

			let keys = Object.keys(resp.rates);
			for(var i in keys) {				
				if(keys[i] == fechaInicio){
					self.tcInicial = resp.rates[keys[i]];
					console.warn("encontrado inicial!");
				}
				if(keys[i] == fechaFin){
					console.warn("encontrado fechaFin!");
					self.tcFinal = resp.rates[keys[i]];
				}	   
			}			
			console.log(self.tcInical);
			console.log(self.tcFinal);
		},error =>{
			console.error("error");
		});
	};

	self.cancelbankAction = () =>{
		self.bankAction = null;
	};

	self.filterSearch = () => {
		self.filteredActionsLength = 
			$filter('filter')(self.filteredActions, 
					{cusip: self.filterActionNumberCusip,
				isinSerie: self.filterActionNumberIsin, 
				secId: self.filterActionNumberSecId});
	};

	self.formatDate = (date)=>{
		var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();

		if (month.length < 2) month = '0' + month;
		if (day.length < 2) day = '0' + day;

		return [year, month, day].join('-');
	};

	self.stateGoTo = (stateTrans, obj) =>{
		$state.go(stateTrans, obj, {location: false, inherit: false});
	};

	self.getHistory = (ba) =>{
		self.actionToGetHistory = ba;
		self.fetchForRates(ba);

		bankActionService.getActionHistory(ba.id).then(data => {	

			self.actionsHistory = data;
			if(self.actionsHistory.slice(-1)){
				let lastItem = self.actionsHistory.slice(-1).pop()
				self.valorMercado = lastItem.titulos * lastItem.marketPrice;
				self.valorACosto = lastItem.unitCost * lastItem.titulos;
				setTimeout(() => {
					self.valuacionDlsAlInicio = lastItem.dlsAlInicio * (lastItem.tcInicial ? lastItem.tcInicial : self.tcInicial.MXN);
					self.valuacionDlsAlFinal = lastItem.dlsAlInicio * (lastItem.tcFinal ? lastItem.tcFinal : self.tcFinal.MXN);
					self.utilidadPerdidaPorValuacion = lastItem.utilidadPerdidaPorValuacion = self.valuacionDlsAlFinal - self.valuacionDlsAlInicio;//1
					self.plusMinusV = self.valorMercado - self.valorACosto;//2

					self.valorDeInstrumentoDeInversionPorTipoDeCambio = lastItem.titulos * self.valorDelDolarActual;
					self.valorActualRegistradoManualmente = lastItem.valorActualRegistradoManualmente;
				}, 1000);

			}


		}, error => {
			console.log('Error al obtener el historial de la accion bancaria', error);
		});

	};

	self.getCurrentExchangeRate = ()=>{
		ratesAPIService.fetchCurrentRate().then(resp =>{
			self.valorDelDolarActual = resp.rates.MXN;
		},error =>{
			console.error("error");
		});
	};

	function onlyNumbersWithDot(e) {           
		var charCode;
		if (e.keyCode > 0) {
			charCode = e.which || e.keyCode;
		}
		else if (typeof (e.charCode) != "undefined") {
			charCode = e.which || e.keyCode;
		}
		if (charCode == 46)
			return true
			if (charCode > 31 && (charCode < 48 || charCode > 57))
				return false;
		return true;
	}

	self.validateAccount =()=>{
		if(self.bankAction.account.isInversionEnDolares 
				|| self.bankAction.account.isInversionEnPesos
				|| self.bankAction.account.isEfectivoEnPesos
				||self.bankAction.account.isEfectivoEnDolares){

		}else{
			alertify.alert('Error', 'La Cuenta de Inversión seleccionada, no contiene sub cuentas!', function(){ alertify.error('Detenido'); });
		}
	};

	self.complete=function(string){
		var output=[];
		angular.forEach(self.instList,function(inst){
			if(inst){
				if(inst.toLowerCase().indexOf(string.toLowerCase())>=0){
					output.push(inst);
				}
			}
			
		});
		self.filterInst=output;
	};
	
	self.fillTextbox=function(string){
		self.bankAction.instrumento=string;
		self.filterInst=null;
	};
	
	self.restaValuaciones = () => {
		self.bankAction.valuation.utilidadPerdidaPorValuacion = self.bankAction.valuation.valuacionDolaresAlFinal - self.bankAction.valuation.valuacionDolaresPeriodoAnterior;
	};

	self.getInst = ()=>{
		bankActionService.getInstGruped().then(data => {			
			self.instList = data;			
		}, error => {
			alertify.error('Error');
			console.log('Error al obtener las acciones bancarias inst', error);
		});
		
	};
	const initController = () => {
		console.log("params:", $stateParams);
		if($stateParams.account){
			self.account = $stateParams.account;
			if($stateParams.subAccount){
				self.subAccountType = $stateParams.subAccount;
			}else{
				self.subAccountType = 0;
			}			
			console.log(self.account);
			self.get();
			//self.getByCompany(self.company);
		}else{
			//self.company = null;
			self.account = null;
			self.subAccountType = 0;
			self.get();			
		}
		self.getCurrentExchangeRate();
		self.getBanks();
		self.getBankAccounts();
		self.getInst();
	};
	angular.element(document).ready(function () {
		initController();
	});
});
app.directive('fileModel', ['$parse', function ($parse) {
	return {
		restrict: 'A',
		link: function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function(){
				scope.$apply(function(){
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
}]);

app.directive('uiCurrency', function ($filter, $parse) {
	return {
		require: 'ngModel',
		restrict: 'A',
		link: function (scope, element, attrs, ngModel) {

			function parse(viewValue, noRender) {
				if (!viewValue)
					return viewValue;

				// strips all non digits leaving periods.
				var clean = viewValue.toString().replace(/[^0-9.]+/g, '').replace(/\.{2,}/, '.');

				// case for users entering multiple periods throughout the number
				var dotSplit = clean.split('.');
				if (dotSplit.length > 2) {
					clean = dotSplit[0] + '.' + dotSplit[1].slice(0, 5);
				} else if (dotSplit.length == 2) {
					clean = dotSplit[0] + '.' + dotSplit[1].slice(0, 5);
				}

				if (!noRender)
					ngModel.$render();
				return clean;
			}

			ngModel.$parsers.unshift(parse);

			ngModel.$render = function() {
				console.log('viewValue', ngModel.$viewValue);
				console.log('modelValue', ngModel.$modelValue);
				var clean = parse(ngModel.$viewValue, true);
				if (!clean)
					return;

				var currencyValue,
				dotSplit = clean.split('.');

				// todo: refactor, this is ugly
				if (clean[clean.length-1] === '.') {
					currencyValue = '$' + $filter('number')(parseFloat(clean)) + '.';

				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 1) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 1);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 2) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 2);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 3) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 3);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 4) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 4);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 5) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 5);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 6) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 6);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 7) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 7);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 8) {
					currencyValue = '$' + $filter('number')(parseFloat(clean), 8);
				} else {
					currencyValue = '$' + $filter('number')(parseFloat(clean));
				}

				element.val(currencyValue);
			};

		}
	};
})

app.directive('uiQuantity', function ($filter, $parse) {
	return {
		require: 'ngModel',
		restrict: 'A',
		link: function (scope, element, attrs, ngModel) {

			function parse(viewValue, noRender) {
				if (!viewValue)
					return viewValue;

				// strips all non digits leaving periods.
				var clean = viewValue.toString().replace(/[^0-9.]+/g, '').replace(/\.{2,}/, '.');

				// case for users entering multiple periods throughout the number
				var dotSplit = clean.split('.');
				if (dotSplit.length > 2) {
					clean = dotSplit[0] + '.' + dotSplit[1].slice(0, 2);
				} else if (dotSplit.length == 2) {
					clean = dotSplit[0] + '.' + dotSplit[1].slice(0, 2);
				}

				if (!noRender)
					ngModel.$render();
				return clean;
			}

			ngModel.$parsers.unshift(parse);

			ngModel.$render = function() {
				console.log('viewValue', ngModel.$viewValue);
				console.log('modelValue', ngModel.$modelValue);
				var clean = parse(ngModel.$viewValue, true);
				if (!clean)
					return;

				var currencyValue,
				dotSplit = clean.split('.');

				// todo: refactor, this is ugly
				if (clean[clean.length-1] === '.') {
					currencyValue = '' + $filter('number')(parseFloat(clean)) + '.';

				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 1) {
					currencyValue = '' + $filter('number')(parseFloat(clean), 1);
				} else if (clean.indexOf('.') != -1 && dotSplit[dotSplit.length - 1].length == 1) {
					currencyValue = '' + $filter('number')(parseFloat(clean), 4 );
				} else {
					currencyValue = '' + $filter('number')(parseFloat(clean));
				}

				element.val(currencyValue);
			};

		}
	};
})
