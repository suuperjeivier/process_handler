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
//pager
	
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
		let fechaInicio = $filter('date')(bac.fechaInicioReal, 'yyyy-MM-dd');
		let fechaFin = $filter('date')(bac.fechaFinalReal, 'yyyy-MM-dd');
		console.log("inicio:", fechaInicio);
		console.log("fin:", fechaFin);
		bac.fechaInicioReal = new Date(bac.fechaInicioReal);
		bac.fechaFinalReal = new Date(bac.fechaFinalReal);


		ratesAPIService.fecthOnPeriod(""+fechaInicio, ""+fechaFin).then(resp =>{

			let keys = Object.keys(resp.rates);
			for(var i in keys) {				
				if(keys[i] == fechaInicio){
					self.tcInical = resp.rates[keys[i]];
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
		self.valorMercado = ba.titulos * ba.marketPrice;
		self.valorACosto = ba.unitCost * ba.titulos;
		self.valuacionDlsAlInicio = ba.dlsAlInicio * ba.tcInicial;
		self.valuacionDlsAlFinal = ba.dlsAlInicio * ba.tcFinal;
		self.utilidadPerdidaPorValuacion = ba.utilidadPerdidaPorValuacion = self.valuacionDlsAlFinal - self.valuacionDlsAlInicio;//1
		self.plusMinusV = self.valorMercado - self.valorACosto;//2
		
		bankActionService.getActionHistory(ba.id).then(data => {			
			self.actionsHistory = data;			
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
