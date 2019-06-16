app.controller('bankAccountsCtrl', function ($state, $stateParams, bankAccountService, companyService, banksService,$filter) {
    const self = this;
    self.bankAccounts = [];
    self.bankAccount = null;
    self.newBankAccount = () => {
        self.bankAccount = {
            status: 1,
            date: new Date()
        }
        return self.bankAccount;
    };
    
    self.searchStartDate = new Date();
    self.searchEndDate = new Date();
    
    self.cancelBankAccount = () => {
        self.bankAccount = null;
        self.validClass = null;
    };
    
    self.searchBetweenDates = (startDate, endDate) => {
    	let sendData = {
    			"startDate":  $filter('date')(startDate, "yyyy/MM/dd"),
    			"endDate": $filter('date')(endDate, "yyyy/MM/dd"),
    			"status": 1
    	};
    	
    	bankAccountService.getByBetweenDates(sendData).then(data => {
    		 self.bankAccounts = data;
    		 console.log('datos optenidos de la busqueda: ', data)
    	}, error => {
    		console.log('Error al buscar las cuentas bancarias');
    	});
    	
    };    

    self.update = bankAccount => {
        console.log('cuenta: ',bankAccount);
        bankAccount.date = new Date(bankAccount.date);
        self.bankAccount = bankAccount;
    };

    self.submitForm = isValid => {
        console.log('Valid Form');
        if (isValid) {
            self.addUpdate();
            self.validClass = null;
        }else {
        	self.validClass = {};
        	self.validClass.accountType = 'valid';
        	self.validClass.accountNumber = 'valid';
        	self.validClass.address = 'valid';
        	self.validClass.telephone = 'valid';
        	self.validClass.client = 'valid';
        	self.validClass.portfolio = 'valid';
        	self.validClass.period = 'valid';
        	self.validClass.currency = 'valid';
        	self.validClass.bank = 'valid';
        	self.validClass.company = 'valid';
        	
        	if(!self.bankAccount.accountType){
        		self.validClass.accountType = 'invalid';
        	}else if(self.bankAccount.accountType.trim().length === 0){
        		self.validClass.accountType = 'invalid';
        	}
        	
			if(!self.bankAccount.accountNumber){
				self.validClass.accountNumber = 'invalid'; 	
			} else if(self.bankAccount.accountNumber.trim().length === 0){
        		self.validClass.accountNumber = 'invalid';
        	}
			
			if(!self.bankAccount.address){
				self.validClass.address = 'invalid';
			} else if(self.bankAccount.address.trim().length === 0){
        		self.validClass.address = 'invalid';
        	}
			
			if(!self.bankAccount.telephone){
				self.validClass.telephone = 'invalid';
			} else if(/\D/.test(self.bankAccount.telephone)){
				self.validClass.telephone = 'invalid';
			}
			
			if(!self.bankAccount.client){
				self.validClass.client = 'invalid';
			} else if(self.bankAccount.client.trim().length === 0){
        		self.validClass.client = 'invalid';
        	}
			
			if(!self.bankAccount.portfolio){
				self.validClass.portfolio = 'invalid';
			} else if(self.bankAccount.portfolio.trim().length === 0){
        		self.validClass.portfolio = 'invalid';
        	}
			
			if(!self.bankAccount.period){
				self.validClass.period = 'invalid';
			} else if(self.bankAccount.period.trim().length === 0){
        		self.validClass.portfolio = 'invalid';
        	}
			
			if(!self.bankAccount.currency){
				self.validClass.currency = 'invalid';
			} else if(self.bankAccount.currency.trim().length === 0){
        		self.validClass.currency = 'invalid';
        	}
			
			if(!self.bankAccount.bank){
				self.validClass.bank = 'invalid';
			} 
			if(!self.bankAccount.company){
				self.validClass.company = 'invalid';
			}
        }
    };

    self.addUpdate = () => {
        if (self.bankAccount.id) {
            self.put();
        } else {
            self.post();
        }
    }

    self.get = () => {
    	if(self.company){
    		self.getByCompany(self.company);
    	}else{
    		bankAccountService.get().then(data => {
                self.bankAccounts = data;
                console.log('Datos obtenidos: ', data);
            }, error => {
                console.log('Error al obtener las cuentas bancarias', error);
            });
    	}        
    };
    
    self.getByCompany = (comp) => {
    	console.log('buscando por comapny:', comp);
        bankAccountService.getByCompany(comp).then(data => {
            self.bankAccounts = data;
        }, error => {
            console.log('Error al obtener las cuentas bancarias', error);
        });
    };
    
    self.getCompany = () => {
    	console.log('Buscando por empresa');
    	companyService.get().then(data => {
    		self.business = data;
    	}, error => {
    		console.log('Error al obtener las empresas: ', error);
    	})
    }; 
    
    self.getBanks = () => {
    	banksService.get().then(data => {
    		self.banks = data;
    	}, error => {
    		console.log('Error al obtener los bancos');
    	});
    };
    
    self.post = () => {
    	if(self.company){
    		self.bankAccount.company = self.company;
    	}
        bankAccountService.post(self.bankAccount).then(data => {
        	
            alertify.alert('Exito', 'Cuenta bancaria registrada exitosamente', function(){ alertify.success('Ok'); });
            self.get();
            self.bankAccount = null;
        }, error => {
            console.log('Error al registrar la cuenta bancaria', error);
        });
    };

    self.put = () => {
        bankAccountService.post(self.bankAccount).then(data => {
            
            self.bankAccount = null;
            self.get();
            alertify.alert('Exito', 'Cuenta bancaria actualizada exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            console.log('Error al actualizar la cuenta bancaría', error);
        });
    };
    
    self.confirmDelete = bankAccount => {
    	alertify.confirm('Confirmar eliminación', '¿Está seguro?', function(){ 
    		self.delete(bankAccount);
    		}
        , function(){ alertify.error('Cancelado')});
    };
    
    self.delete = bankAccount => {
        bankAccount.status = 0;
        bankAccountService.del(bankAccount).then(data => {
        	alertify.success('Eliminado') 
            self.get();
        }, error => {
            console.log("Error al eliminar el archivo");
        });
    };
    
    self.stateGoTo = (stateTrans, data) =>{
    	$state.go(stateTrans, {'account': data}, {location: false, inherit: false});
    };

    const initController = () => {
    	self.getCompany();
    	self.getBanks();
        console.log("params:", $stateParams);
        if($stateParams.company){
        	self.company = $stateParams.company;
        	self.getByCompany(self.company);
        }else{
        	self.company = null;
        	self.get();
        }
        
    };

    angular.element(document).ready(function () {
        initController();
    });
});