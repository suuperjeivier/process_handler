app.controller('bankAccountsCtrl', function ($state, $stateParams, bankAccountService) {
    const self = this;
    self.bankAccounts = [];
    self.bankAccount = null;
    self.newBankAccount = () => {
        self.bankAccount = {
            banco: '',
            tipoCuenta: '',
            sucursal: '',
            noCliente: 0,
            clabe: '',
            cuenta: '',
            noTargeta: '',
            swif: '',
            moneda: '',
            contacto: '',
            telefonoContacto: '',
            cardexActivo: false,
            predeterminado: false,
            status: 1
        }
        return self.bankAccount;
    };

    self.cancelBankAccount = () => {
        self.bankAccount = null;
    };

    self.update = bankAccount => {
        console.log('cuenta: ',bankAccount);
        self.bankAccount = bankAccount;
    };

    self.submitForm = isValid => {
        console.log('Valid Form');
        if (isValid) {
            self.addUpdate();
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