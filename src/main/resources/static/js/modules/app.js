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
		params: {account: null},
		controller: 'bankActionsCtrl',
		controllerAs: 'bactCtrl'
	})
	.state('bussiness',{
		url: '/bussiness',
		templateUrl:'views/company.html',
		controller: 'companyCtrl',
		controllerAs: 'cCtrl'
	})
	.state('banks',{
		url: '/banks',
		templateUrl:'views/banks.html',
		controller: 'banksCtrl',
		controllerAs: 'bCtrl'
	});
}]);
////on every request, authenticate user first
//angular.element(document).ready(() => {
//	window._keycloak = Keycloak('keycloak/keycloak.json');
//
//	window._keycloak.init({
//		onLoad: 'login-required'
//	})
//	.success((authenticated) => {
//		if(authenticated) {
//			window._keycloak.loadUserProfile().success(function(profile){
//				angular.bootstrap(document, ['account-demo']); // manually bootstrap Angular
//			});
//		}
//		else {
//			window.location.reload();
//		}
//	})
//	.error(function () {
//		window.location.reload();
//	});
//});
//
////use bearer token when calling backend
//app.config(['$httpProvider', function($httpProvider) {
//	var isExpired = window._keycloak.isTokenExpired();
//	var token = window._keycloak.token;
//	
//	if (sExpired) {
//		window._keycloak.updateToken(5)
//		.success(function() {
//			$httpProvider.defaults.headers.common['Authorization'] = 'BEARER ' + token;
//		})
//		.error(function() {
//			console.error('Failed to refresh token');
//		});
//	}
//		
//	$httpProvider.defaults.headers.common['Authorization'] = 'BEARER ' + token;
//}]);