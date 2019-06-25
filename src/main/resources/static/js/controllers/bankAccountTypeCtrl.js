app.controller('bankAccountTypeCtrl', function ($state, bankAccountTypeService, $filter) {
    const self = this;
    self.bankAccountType = null;
    self.bankAccountTypes = [];

    self.newBankAccountType = () => {
        self.bankAccountType = {
            status: 1
        };
    };

    self.cancel = () => {
        self.bankAccountType = null;
    };
    
   
    self.get = () => {
    	bankAccountTypeService.get().then(data => {
            console.log('bancos: ', data);
            self.bankAccountTypes = data;
        }, error => {
            console.log('Error al obtener las empresas', error);
        });
    };

  

    self.submitForm = isValid => {
        console.log('Valid Form');
        if (isValid) {
            self.addUpdate();
        }else {
        	self.validClass = {};
        	self.validClass.name = 'valid';        	
        	
        	if(!self.bankAccountType.name){
        		self.validClass.name = 'invalid';
        	} else if(self.bankAccountType.name.trim().length === 0){
        		self.validClass.name = 'invalid';
        	}
        }
    };

    self.addUpdate = () => {
        if (self.bankAccountType.id) {
            self.put();
        } else {
            self.post();
        }
    };
    self.post = () => {
    	bankAccountTypeService.post(self.bankAccountType).then(data => {
    		self.bankAccountType = null;
            self.get();
            alertify.alert('Exito', 'Tipo de cuenta registrada exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            console.log('Error al registrar El tipo de cuenta bancaria', error);
        });
    };
    
    self.update = bank => {
        console.log('bank: ',bank);
        self.bankAccountType = bank;
    };
    
    self.put = () => {
    	bankAccountTypeService.post(self.bankAccountType).then(data => {
            self.bankAccountType = null;
            self.get();
            alertify.alert('Exito', 'Tipo de cuenta actualizada exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            console.log('Error al actualizar el banco', error);
        });
    };
    
    self.confirmDelete = bankAccountType => {
    	alertify.confirm('Confirmar eliminación', '¿Está seguro?', function(){ 
    		self.del(bankAccountType);
    		}
        , function(){ alertify.error('Cancelado')});
    };
    
    self.del = bankAccountType => {
    	bankAccountType.status = 0;
    	bankAccountTypeService.del(bankAccountType).then(data => {
        	alertify.success('Eliminada')
            self.get();
        }, error => {
            console.log("Error al eliminar el banco", error);
        });
    };
    
    const initController = () => {
        self.get();
    };

    angular.element(document).ready(function () {
        initController();
    });
});