app.controller('bankAccountsCtrl', function ($state, $stateParams, bankAccountService, bankAccountTypeService, companyService, banksService,$filter, storageService) {
    
	const self        = this;
    self.bankAccounts = [];
    self.bankAccountsAux = [];
    self.bankAccount  = null;
    self.bank         = null;
    self.maxDate      = new Date();
    self.maxDate2      = new Date();
    
    self.newBankAccount = () => {
        self.bankAccount = {
            status: 1,
            company: self.company,
            date: new Date()
        }
        return self.bankAccount;
    };
    
    self.newBank = () => {
        self.bank = {
            status: 1
        }
    };
    
    self.fetchBankAccountTypes = ()=>{
    	bankAccountTypeService.get().then(data => {
            self.bankAccountTypes = data;            
        }, error => {
            console.log('Error al obtener las cuentas bancarias', error);
        });
    };
    
    self.searchStartDate = new Date();
    self.searchEndDate = new Date();
    
    self.cancelBankAccount = () => {
        self.bankAccount = null;
        self.validClass = null;
    };
    
    self.cancelBank = () => {
        self.bank = null;
    };
    
    self.searchBetweenDates = (startDate, endDate) => {
    	let sendData = {
    			"startDate":  $filter('date')(startDate, "yyyy/MM/dd"),
    			"endDate": $filter('date')(endDate, "yyyy/MM/dd"),
    			"status": 1
    	};
    	
    	bankAccountService.getByBetweenDates(sendData).then(data => {
    		 self.bankAccounts = data;
    		 self.bankAccountsLength = data;
    		 self.filterAccountNumber = '';
    		 self.isSearch = true;
    	}, error => {
    		console.log('Error al buscar las cuentas bancarias');
    	});
    	
    };    
    
    self.cancelSearch = () => {
    	self.get();
    	self.isSearch = false;
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
        	self.validClass.date = 'valid';
        	
        	if(!self.bankAccount.date){
        		self.validClass.accountType = 'invalid';
        	}
        	
        	if(!self.bankAccount.accountType){
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
                self.bankAccountsAux = angular.copy(self.bankAccounts);
                self.bankAccountsLength = data;
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
            self.bankAccountsAux = angular.copy(self.bankAccounts);
        }, error => {
            console.log('Error al obtener las cuentas bancarias', error);
        });
    };
    
    self.getByBank = (bank) => {
    	console.log('buscando por bank:', bank);
        bankAccountService.getByBank(bank).then(data => {
            self.bankAccounts = data;
            self.bankAccountsAux = angular.copy(self.bankAccounts);
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
            alertify.alert('Error', 'Ha ocurrido un error al realizar el registro', function(){ alertify.error('Error'); });
        });
    };
    
    self.postBank = () => {
    	console.log('data send: ', self.bank)
        banksService.post(self.bank).then(data => {
            self.bank = null;
            self.getBanks();
            self.bankAccount.bank = data;
            $('#modal-new-bank').modal('hide')
            alertify.alert('Exito', 'Banco registrado exitosamente', function(){ alertify.success('Ok'); });
            
        }, error => {
            alertify.alert('Error', 'Ha ocurrido un error al realizar el registro', function(){ alertify.error('Error'); });
            console.log('Error al registrar la accion bancaria', error);
        });
    };

    self.put = () => {
        bankAccountService.post(self.bankAccount).then(data => {
            
            self.bankAccount = null;
            self.get();
            alertify.alert('Exito', 'Cuenta bancaria actualizada exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            alertify.alert('Error', 'Ha ocurrido un error al realizar la actualización', function(){ alertify.error('Error'); });
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
            alertify.alert('Error', 'Ha ocurrido un error al eliminar el registro', function(){ alertify.error('Error'); });
            console.log("Error al eliminar la cuenta: ", error);
        });
    };
    
    self.stateGoToSubAccount = (stateTrans, data, subAccountType) =>{
    	$state.go(stateTrans, {'account': data, 'subAccount': subAccountType}, {location: false, inherit: false});
    };
    self.stateGoTo = (stateTrans, data) =>{
    	$state.go(stateTrans, {'account': data}, {location: false, inherit: false});
    };
    
    self.submitFormBank = isValid => {
        console.log('Valid Form bank');
        if (isValid) {
            self.postBank();
            self.validClass = null
        }else {
        	
        	self.validClass               = {};
        	self.validClass.name          = 'valid';
        	self.validClass.agent         = 'valid';
        	self.validClass.agentPhone    = 'valid';
        	self.validClass.addressBank   = 'valid';
        	self.validClass.telephoneBank = 'valid';
        	
        	
        	if(!self.bank.name){
        		self.validClass.name = 'invalid';
        	} else if(self.bank.name.trim().length === 0){
        		self.validClass.name = 'invalid';
        	}
        	
        	if(!self.bank.agent){
        		self.validClass.agent = 'invalid';
        	} else if(self.bank.agent.trim().length === 0){
        		self.validClass.agent = 'invalid';
        	}	
        	
        	if(!self.bank.agentPhone){
        		self.validClass.agentPhone = 'invalid';
        	} else if(/\D/.test(self.bank.agentPhone)){
        		self.validClass.agentPhone = 'invalid';
        	}	
        	
        	if(!self.bank.address){
        		self.validClass.addressBank = 'invalid';
        	} else if(self.bank.address.trim().length === 0){
        		self.validClass.addressBank = 'invalid';
        	}
        	
        	if(!self.bank.telephone){
        		self.validClass.telephoneBank = 'invalid';
        	} else if(/\D/.test(self.bank.telephone)){
        		self.validClass.telephoneBank = 'invalid';
        	}	
        }
    };
    
    self.addDocument = () => {
    	let file = self.myFile;
    	self.processing = true;
    	if(self.bankParam) {
    		storageService.post(file).then(resp => {
    			console.log(resp);
    			if(resp.message === "FILE_UPLODED"){
    				
    			} else {
    				console.error("Error en mensaje de carga");
    				self.processing = false;
    			}
    		}, error => {
    			console.error("Error de procesado de carga");
    			self.processing = false;
    		});
    	}
    	
    };
    
    self.filterSearch = () => {
    	if(self.filterAccountNumber){
    		let array = $filter('filter')(self.bankAccounts, self.filterAccountNumber);
    		if(array != null && array.length){
    			self.bankAccounts = array;
    		}else{
    			 alertify.alert('Error', 'Sin datos para la consulta con los datos proporcionados', function(){ alertify.error('No encontrado'); });
    		}
    	}else{
    		self.bankAccounts = self.bankAccountsAux;
    	}
    	
    };
    
    const initController = () => {
    	self.fetchBankAccountTypes();
    	self.getCompany();
    	self.getBanks();
        console.log("params:", $stateParams);
        if($stateParams.company){
        	self.company = $stateParams.company;
        	self.getByCompany(self.company);
        }else if($stateParams.bank){
        	self.bankParam = $stateParams.bank;
        	self.getByBank(self.bank);
        } else{
        	self.company = null;
        	self.get();
        }
        
    };

    angular.element(document).ready(function () {
        initController();
    });
});