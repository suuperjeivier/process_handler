app.controller('bankActionsCtrl', function ($scope, $filter, $stateParams, bankActionService, storageService, batchService, ratesAPIService) {
	const self = this;
	self.bankActions = [];
	self.bankAction = null;
	self.tcInical = {};
	self.tcFinal = {};
	//pager
	self.viewby = 10;	
	self.currentPage = 4;
	self.itemsPerPage = self.viewby;
	self.maxSize = 5; //Number of pager buttons to show
	//end pager
	self.newBankAction = () => {
		self.bankAction = {
				status: 1
		}
	};

	self.cancel = () => {
		self.bankAction = null;
	};

	self.get = () => {
		if(self.account){
			bankActionService.getByAccountId(self.account.id).then(data => {
				self.bankActions = data;
				self.paginarAcciones();
			}, error => {
				alertify.error('Error');
				console.log('Error al obtener las acciones bancaras', error);
			});
		}else{
			bankActionService.get().then(data => {
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
		self.numPages = Math.ceil(self.bankActions.length / self.itemsPerPage);		
		$scope.$watch('bactCtrl.currentPage + bactCtrl.itemsPerPage', function() {
		    var begin = ((self.currentPage - 1) * self.itemsPerPage)
		    , end = begin + self.itemsPerPage;
		    self.filteredActions = self.bankActions.slice(begin, end);
		  });
	}


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
		self.bankAction.account = self.account;
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
		bankAction.fechaFinal = new Date(bankAction.fechaFinal);
		self.bankAction = bankAction;
		self.fetchForRates(self.bankAction);
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
					batchService.post(resp.newFileName, self.account.id).then(resp =>{
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
            alertify.alert('Error', 'No permitido, debe iniciar con un numero de cuenta!', function(){ alertify.error('Ok'); });
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
		let fechaInicio = $filter('date')(bac.fechaInicio, 'yyyy-MM-dd');
		let fechaFin = $filter('date')(bac.fechaFinal, 'yyyy-MM-dd');
		console.log("inicio:", fechaInicio);
		console.log("fin:", fechaFin);
		bac.fechaInicio = new Date(bac.fechaInicio);
		bac.fechaFinal = new Date(bac.fechaFinal);


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
		bankActionService.getActionHistory(ba.id).then(data => {			
			self.actionsHistory = data;			
		}, error => {
			console.log('Error al obtener el historial de la accion bancaria', error);
		});
		
	};
	

	const initController = () => {
		console.log("params:", $stateParams);
		if($stateParams.account){
			self.account = $stateParams.account;
			console.log(self.account);
			self.get();
			//self.getByCompany(self.company);
		}else{
			//self.company = null;
			self.get();
			self.account = null;
		}
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
