app.controller('companyCtrl', function ($state, companyService) {
    const self = this;
    self.company = null;
    self.companys = [];

    self.newCompany = () => {
        self.company = {
            status: 1
        }
    };

    self.cancel = () => {
        self.company = null;
    };

    self.get = () => {
        companyService.get().then(data => {
            console.log('Empreas: ', data);
            self.companys = data;
        }, error => {
            console.log('Error al obtener las empresas', error);
        });
    };

    self.post = () => {
        console.log('informacion de la empresa: ',self.company);
        companyService.post(self.company).then(data => {
            self.company = null;
            alert("registro exitoso");
            self.get();
        }, error => {
            console.log('Error al registrar la accion bancaria', error);
        });
    };
    
    self.update = company => {
        console.log('bankAction: ',company);
        self.company = company;
    };

    self.submitForm = isValid => {
        console.log('Valid Form');
        if (isValid) {
            self.addUpdate();
        }else {
        	self.validClass = {};
        	self.validClass.name = 'valid';
        	self.validClass.friendlyAccount = 'valid';
        	self.validClass.number = 'valid';
        	
        	if(!self.company.name){
        		self.validClass.name = 'invalid';
        		console.log('incorrecto');
        	}
        	
			if(!self.company.accountNumber){
				self.validClass.friendlyAccount = 'invalid'; 	
				console.log('incorrecto');
			}
			
			if(!self.company.number){
				self.validClass.number = 'invalid';
				console.log('incorrecto');
			}
        }
    };

    self.addUpdate = () => {
        if (self.company.id) {
            self.put();
        } else {
            self.post();
        }
    };
    
    self.put = () => {
        companyService.post(self.company).then(data => {
            alert("Actualización exitosa");
            self.company = null;
            self.get();
        }, error => {
            console.log('Error al actualizar la accion bancaria', error);
        });
    };

    self.del = company => {
        company.status = 0;
        companyService.del(company).then(data => {
            alert("Eliminación exitosa");
            self.get();
        }, error => {
            console.log("Error al eliminar el la accion bancaria", error);
        });
    };
    
    self.addBankAccount = company => {
    	$state.go('bank-accounts', {'company': company}, {location: false, inherit: false});
    };

    const initController = () => {
        self.get();
    };

    angular.element(document).ready(function () {
        initController();
    });
});