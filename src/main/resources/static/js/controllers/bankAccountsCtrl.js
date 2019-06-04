app.controller('bankAccountsCtrl', function(){
    const self = this;
    
    self.newBankAccount = () => {
       self.bankAccount = {
           banco:'',
           tipoCuenta: '',
           noCliente:0,
           clabe: '',
           cuenta:'',
           noTargeta:'',
           swif:'',
           moneda: '',
           contacto: '',
           telefonoContacto:'',
           cardexActivo: false,
           predeterminado: false,
           status: 1
       }
    };

    self.cancelBankAccount = () => {
        self.bankAccount = null;
    };

    const initController = () =>{

    };

    angular.element(document).ready(function (){
		initController();
	});
});