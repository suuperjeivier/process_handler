const app = angular.module('processHandlerApp',['ui.router', 'ui.bootstrap', 'ngAnimate']);


//router
app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider,$urlRouterProvider){

	$urlRouterProvider.otherwise('/');

	$stateProvider
	.state('home',{
		url: '/',
		templateUrl:'views/charts.html',
		controller: 'homeCtrl',
		controllerAs: 'hCtrl'
	})
	.state('bank-accounts',{
		url: '/bank-accounts',
		templateUrl:'views/bankAccounts.html',
		params: {company: null, bank: null},
		controller: 'bankAccountsCtrl',
		controllerAs: 'baCtrl'
	})
	.state('bank-actions',{
		url: '/bank-actions',
		templateUrl:'views/bankActions.html',
		params: {account: null, subAccount: 0},
		controller: 'bankActionsCtrl',
		controllerAs: 'bactCtrl'
	})
	.state('bussiness',{
		url: '/bussiness',
		templateUrl:'views/company.html',
		controller: 'companyCtrl',
		controllerAs: 'cCtrl'
	})
	.state('bankAccountType',{
		url: '/bank-accounts/type',
		templateUrl:'views/bankAccountType.html',
		controller: 'bankAccountTypeCtrl',
		controllerAs: 'batCtrl'
	})
	.state('banks',{
		url: '/banks',
		templateUrl:'views/banks.html',
		controller: 'banksCtrl',
		controllerAs: 'bCtrl'
	})
	.state('logs',{
		url: '/logs',
		templateUrl:'views/logs.html',
		controller: 'logsCtrl',
		controllerAs: 'lCtrl'
	});
}]);
