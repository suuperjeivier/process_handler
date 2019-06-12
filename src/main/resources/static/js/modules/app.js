const app = angular.module('processHandlerApp',['ui.router']);

// router
app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider,$urlRouterProvider){
	
	//$urlRouterProvider.otherwise('/bank-accounts');
	
	$stateProvider
		.state('home',{
			url: '/',
			templateUrl:'panel.html',
			controller: 'homeCtrl',
			controllerAs: 'hCtrl'
		})
		.state('bank-accounts',{
			url: '/bank-accounts',
			templateUrl:'views/bankAccounts.html',
			params: {company: null},
			controller: 'bankAccountsCtrl',
			controllerAs: 'baCtrl'
		})
		.state('bank-actions',{
			url: '/bank-actions',
			templateUrl:'views/bankActions.html',
			params: {account: null},
			controller: 'bankActionsCtrl',
			controllerAs: 'bactCtrl'
		})
		.state('bussiness',{
			url: '/bussiness',
			templateUrl:'views/company.html',
			controller: 'companyCtrl',
			controllerAs: 'cCtrl'
		});
}]);