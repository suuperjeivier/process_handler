app.controller('companyCtrl', function ($state, companyService, $filter) {
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
            self.get();
            alertify.alert('Exito', 'Empresa registrada exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            console.log('Error al registrar la accion bancaria', error);
        });
    };
    
    self.update = company => {
        console.log('company: ',company);
        self.company = company;
    };

    self.submitForm = isValid => {
        console.log('Valid Form');
        if (isValid) {
            self.addUpdate();
            self.validClass = null;
        }else {
        	self.validClass = {};
        	self.validClass.name = 'valid';
        	self.validClass.friendlyAccount = 'valid';
        	self.validClass.rfc = 'valid';
        	
        	if(!self.company.name){
        		self.validClass.name = 'invalid';
        	} else if(self.company.name.trim().length === 0){
        		self.validClass.name = 'invalid';
        	}
        	
    		if(!self.company.friendlyAccount){
				self.validClass.friendlyAccount = 'invalid';
			} else if(self.company.friendlyAccount.trim().length === 0){
        		self.validClass.friendlyAccount = 'invalid';
        	}
    		
			if(!self.company.rfc){
				self.validClass.rfc = 'invalid'; 	
			} else if(self.company.rfc.trim().length === 0){
        		self.validClass.rfc = 'invalid';
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
            self.company = null;
            self.get();
            alertify.alert('Exito', 'Empresa actualizada exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            console.log('Error al actualizar la accion bancaria', error);
        });
    };
    
    self.confirmDelete = company => {
    	alertify.confirm('Confirmar eliminación', '¿Está seguro?', function(){ 
    		self.del(company);
    		}
        , function(){ alertify.error('Cancelado')});
    };
    
    self.del = company => {
        company.status = 0;
        companyService.del(company).then(data => {
        	alertify.success('Eliminado')
            self.get();
        }, error => {
            console.log("Error al eliminar el la accion bancaria", error);
        });
    };
    
    self.addBankAccount = company => {
    	$state.go('bank-accounts', {'company': company}, {location: false, inherit: false});
    };
    
    self.filterSearch = () => {
    	self.companysLength = $filter('filter')(self.companys, self.filterCompany);
    };

    const initController = () => {
        self.get();
    };

    angular.element(document).ready(function () {
        initController();
    });
});