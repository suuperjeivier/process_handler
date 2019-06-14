app.controller('bankActionsCtrl', function ($stateParams, bankActionService, storageService, batchService) {
	const self = this;
	self.bankActions = [];
	self.bankAction = null;

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
			}, error => {
				console.log('Error al obtener las acciones bancaras', error);
			});
		}else{
			bankActionService.get().then(data => {
				self.bankActions = data;
			}, error => {
				console.log('Error al obtener las acciones bancaras', error);
			});
		}

	};

	self.post = () => {
		self.bankAction.account = self.account;
		console.log('informacion de la accion: ',self.bankAction);
		bankActionService.post(self.bankAction).then(data => {
			self.bankAction = null;
			alert("registro exitoso");
			self.get();
		}, error => {
			console.log('Error al registrar la accion bancaria', error);
		});
	};

	self.update = bankAction => {
		console.log('bankAction: ',bankAction);
		self.bankAction = bankAction;
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
			alert("Actualización exitosa");
			self.bankAction = null;
			self.get();
		}, error => {
			console.log('Error al actualizar la accion bancaria', error);
		});
	};

	self.del = bankAction => {
		bankAction.status = 0;
		bankActionService.del(bankAction).then(data => {
			alert("Eliminación exitosa");
			bankAction = null;
			self.get();
		}, error => {
			console.log("Error al eliminar el la accion bancaria", error);
		});
	};

	self.addDocument =()=>{
		var file = self.myFile;
		console.log('file is ' );
		console.dir(file);

		if(self.account){
			storageService.post(file).then(resp =>{
				console.log(resp);
				if(resp.message == "FILE_UPLODED"){
					batchService.post(resp.newFileName, self.account.id).then(resp =>{
						console.log(resp);
						$('#myModal').modal('toggle')
						alert("correcto, archivo procesado!");
						self.get();
					},error =>{
						console.error("error");
					});
				}else{
					console.error("error en mensaje de carga");
				}
			},error =>{
				console.error("error de procesado en carga");
			});
		}else{
			alert("no permitido, debe iniciar con un numero de cuenta!");
		}
		//send your binary data via $http or $resource or do anything else with it

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