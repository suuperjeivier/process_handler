app.controller('bankActionsCtrl', function (bankAccountService) {
    const self = this;
    self.bankActions = [];
    self.bankAction = null;

    self.newBankAction = () => {
        self.bankAction = {
            status: 1
        }
    };

    self.cancel = () => {
        self.bankAction = null;
    };

    self.get = () => {
        bankAccountService.get().then(data => {
            self.bankAccounts = data;
        }, error => {
            console.log('Error al obtener las cuentas bancarias', error);
        });
    };

    self.post = () => {
        bankAccountService.post(self.bankAccount).then(data => {
            self.bankAccount = null;
            alert("registro exitoso");
            self.getBankAccounts();
        }, error => {
            console.log('Error al registrar la cuenta bancaria', error);
        });
    };
    
    self.update = bankAction => {
        console.log('bankAction: ',bankAction);
        self.bankAction = bankAccount;
    };

    self.submitForm = isValid => {
        console.log('Valid Form');
        if (isValid) {
            self.addUpdate();
        }
    };

    self.addUpdate = () => {
        if (self.bankAction.id) {
            self.put();
        } else {
            self.post();
        }
    };
    
    self.put = () => {
        bankAccountService.post(self.bankAction).then(data => {
            alert("Actualización exitosa");
            self.bankAction = null;
            self.get();
        }, error => {
            console.log('Error al actualizar la cuenta bancaría', error);
        });
    };

    self.del = bankAction => {
        bankAccount.status = 0;
        bankAccountService.del(bankAccount).then(data => {
            alert("Eliminación exitosa");
            self.getBankAccounts();
        }, error => {
            console.log("Error al eliminar el archivo");
        });
    };

    const initController = () => {
        self.get();
    };

    angular.element(document).ready(function () {
        initController();
    });
});