app.controller('bankActionsCtrl', function (bankActionService, bankAccountService) {
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
        bankActionService.get().then(data => {
            self.bankActions = data;
        }, error => {
            console.log('Error al obtener las acciones bancaras', error);
        });
    };

    self.post = () => {
        console.log('informacion de la accion: ',self.bankAction);
        bankActionService.post(self.bankAction).then(data => {
            self.bankAction = null;
            alert("registro exitoso");
            self.get();
        }, error => {
            console.log('Error al registrar la accion bancaria', error);
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
        bankActionService.post(self.bankAction).then(data => {
            alert("Actualización exitosa");
            self.bankAction = null;
            self.get();
        }, error => {
            console.log('Error al actualizar la accion bancaria', error);
        });
    };

    self.del = bankAction => {
    	bankAction.status = 0;
        bankActionService.del(bankAction).then(data => {
            alert("Eliminación exitosa");
            bankAction = null;
            self.get();
        }, error => {
            console.log("Error al eliminar el la accion bancaria", error);
        });
    };

    const initController = () => {
        self.get();
    };

    angular.element(document).ready(function () {
        initController();
    });
});