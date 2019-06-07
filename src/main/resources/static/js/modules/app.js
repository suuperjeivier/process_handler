const app = angular.module('processHandlerApp',['ui.router']);

// router
app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider,$urlRouterProvider){
	
	//$urlRouterProvider.otherwise('/bank-accounts');
	
	$stateProvider
		.state('home',{
			url: '/',
			templateUrl:'',
			controller: 'homeCtrl',
			controllerAs: 'hCtrl'
		})
		.state('bank-accounts',{
			url: '/bank-accounts',
			templateUrl:'views/bankAccounts.html',
			controller: 'bankAccountsCtrl',
			controllerAs: 'baCtrl'
		})
		.state('bank-actions',{
			url: '/bank-actions',
			templateUrl:'views/bankActions.html',
			controller: 'bankActionsCtrl',
			controllerAs: 'bactCtrl'
		});
}]);