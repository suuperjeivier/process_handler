app.controller('logsCtrl', function($state, logsService, $filter){
	const self = this;
	
	self.logs = [];
	
	self.get = () => {
		logsService.get().then( data => {
			self.logs = data;
		}, error => {
			 alertify.alert('Exito', 'Error al obtener los logs', function(){ alertify.error('Ok'); });
		});
	};
	
	self.filterSearch = () => {
		self.filterLog = self.filterLog? self.filterLog:'';
		self.logsLength = $filter('filter')(self.logs, self.filterLog);
	};
	
	const initController = () => {
		self.get();
	};
	
	angular.element(document).ready(function (){
		initController();
	});
});