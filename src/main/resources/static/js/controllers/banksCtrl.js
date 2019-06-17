app.controller('banksCtrl', function ($state, banksService) {
    const self = this;
    self.bank = null;
    self.banks = [];

    self.newBank = () => {
        self.bank = {
            status: 1
        }
    };

    self.cancel = () => {
        self.bank = null;
    };

    self.get = () => {
        banksService.get().then(data => {
            console.log('bancos: ', data);
            self.banks = data;
        }, error => {
            console.log('Error al obtener las empresas', error);
        });
    };

    self.post = () => {
        console.log('informacion de la empresa: ',self.company);
        banksService.post(self.bank).then(data => {
            self.bank = null;
            self.get();
            alertify.alert('Exito', 'Banco registrado exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            console.log('Error al registrar la accion bancaria', error);
        });
    };
    
    self.update = bank => {
        console.log('bank: ',bank);
        self.bank = bank;
    };

    self.submitForm = isValid => {
        console.log('Valid Form');
        if (isValid) {
            self.addUpdate();
        }else {
        	self.validClass = {};
        	self.validClass.name = 'valid';
        	self.validClass.friendlyAccount = 'valid';
        	self.validClass.rfc = 'valid';
        	
        	if(!self.bank.name){
        		self.validClass.name = 'invalid';
        	}	
        }
    };

    self.addUpdate = () => {
        if (self.bank.id) {
            self.put();
        } else {
            self.post();
        }
    };
    
    self.put = () => {
        banksService.post(self.bank).then(data => {
            self.bank = null;
            self.get();
            alertify.alert('Exito', 'Banco actualizado exitosamente', function(){ alertify.success('Ok'); });
        }, error => {
            console.log('Error al actualizar el banco', error);
        });
    };
    
    self.confirmDelete = bank => {
    	alertify.confirm('Confirmar eliminación', '¿Está seguro?', function(){ 
    		self.del(bank);
    		}
        , function(){ alertify.error('Cancelado')});
    };
    
    self.del = bank => {
        bank.status = 0;
        banksService.del(bank).then(data => {
        	alertify.success('Eliminado')
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