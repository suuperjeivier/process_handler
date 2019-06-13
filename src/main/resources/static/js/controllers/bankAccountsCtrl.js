app.controller('bankAccountsCtrl', function ($state, $stateParams, bankAccountService, companyService) {
    const self = this;
    self.bankAccounts = [];
    self.bankAccount = null;
    self.newBankAccount = () => {
        self.bankAccount = {
            status: 1
        }
        return self.bankAccount;
    };

    self.cancelBankAccount = () => {
        self.bankAccount = null;
        self.validClass = null;
    };

    self.update = bankAccount => {
        console.log('cuenta: ',bankAccount);
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
        		console.log('incorrecto');
        	}
        	
			if(!self.bankAccount.accountNumber){
				self.validClass.accountNumber = 'invalid'; 	
				console.log('incorrecto');
			}
			
			if(!self.bankAccount.address){
				self.validClass.address = 'invalid';
				console.log('incorrecto');
			}
			
			if(!self.bankAccount.telephone){
				self.validClass.telephone = 'invalid';
				console.log('incorrecto');
			}
			
			if(!self.bankAccount.client){
				self.validClass.client = 'invalid';
				console.log('incorrecto');
			}
			
			if(!self.bankAccount.portfolio){
				self.validClass.portfolio = 'invalid';
				console.log('incorrecto');
			}
			
			if(!self.bankAccount.period){
				self.validClass.period = 'invalid';
			}
			
			if(!self.bankAccount.currency){
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
    self.post = () => {
    	if(self.company){
    		self.bankAccount.company = self.company;
    	}
        bankAccountService.post(self.bankAccount).then(data => {
            self.bankAccount = null;
            alert("registro exitoso");
            self.get();
        }, error => {
            console.log('Error al registrar la cuenta bancaria', error);
        });
    };

    self.put = () => {
        bankAccountService.post(self.bankAccount).then(data => {
            alert("Actualización exitosa");
            self.bankAccount = null;
            self.get();
        }, error => {
            console.log('Error al actualizar la cuenta bancaría', error);
        });
    };

    self.delete = bankAccount => {
        bankAccount.status = 0;
        bankAccountService.del(bankAccount).then(data => {
            alert("Eliminación exitosa");
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