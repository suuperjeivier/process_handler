app.controller('inicioCtrl', function ($state, companyService) {
    const self = this;
    self.users = [{name: "javier brito pacheco", userName: "jbrito", pass: "test", roles: [{roleName: "user"}, {roleName: "sales"}]}];
    
    const initController = () => {
       //TODO something
    };

    angular.element(document).ready(function () {
        initController();
    });
});