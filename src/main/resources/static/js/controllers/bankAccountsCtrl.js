app.controller('bankAccountsCtrl', function(bankAccountService){
    const self = this;
    
    self.bankAccounts   = [];
    self.bankAccount    = null;

    self.newBankAccount = () => {
       self.bankAccount = {
           banco:'',
           tipoCuenta: '',
           sucursal: '',
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

    self.submitForm = isValid => {
        console.log('Valid Form');
        if(isValid){
            self.addUpdate();
        }
    };

    self.addUpdate = () => {
        if(self.bankAccount.id){
            self.put();
        } else {
            self.post();
        }
    }

    self.getBankAccounts = () => {
        bankAccountService.get().then(data => {
            self.bankAccounts = data;
        }, error=>{
            console.log('Error al obtener las cuentas bancarias',error);
        });
    };

    self.post = () =>{
        bankAccountService.post(self.bankAccount).then(data => {
            self.bankAccount = null;
            alert("registro exitoso");
        }, error=>{
            console.log('Error al registrar la cuenta bancaria',error);
        });
    };

    self.put = () => {
        bankAccountService.post(self.bankAccount).then(data => {
            alert("Actualización exitosa");
            self.bankAccount = null;
        }, error=>{
            console.log('Error al actualizar la cuenta bancaría',error);
        });
    };


    const initController = () =>{
        self.getBankAccounts();
    };

    angular.element(document).ready(function (){
		initController();
	});
});