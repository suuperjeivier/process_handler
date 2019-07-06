app.controller('logsCtrl', function($state, logsService, $filter, $scope, $timeout){
	const self = this;
	
	self.logs         = [];
	// Pager
	self.viewby       = 10;
	self.currentPage  = 1;
	self.itemsPerPage = self.viewby;
	self.maxSize      = 5;
	
	self.get = () => {
		logsService.get().then( data => {
			self.logs = data;
			self.paginarLogs();
			console.log('Self data: ', data);
		}, error => {
			 alertify.alert('Exito', 'Error al obtener los logs', function(){ alertify.error('Ok'); });
		});
	};
	
	self.filterSearch = () => {
		self.filterLog = self.filterLog? self.filterLog:'';
		self.logsLength = $filter('filter')(self.logs, self.filterLog);
		
		let array = $filter('filter')(self.logs, self.filterLog);
		if(array != null && array.length) {
			self.paginarLogsFilter(array);
		} else {
			alertify.alert('Error', 'Sin datos para la consulta con los datos proporcionados', function(){ alertify.error('No encontrado'); });
		}
	};
	
	self.paginarLogsFilter = array => {
		if(array.length > 10) {
			self.numPages = Math.ceil(array.length / self.itemsPerPage);
			
			$scope.$watch('lCtrl.currentPage + lCtrl.itemsPerPage', function() {
				let begin = ((self.currentPage -1) * self.itemsPerPage),
					end = begin + self.itemsPerPage;
				self.filteredLogs = self.logs.slice(begin, end);
			});
		} else {
			self.filteredLogs = angular.copy(self.bankActions);
		}
	};
	
	self.paginarLogs = () => {
		if(self.logs.length > 10) {
			self.numPages = Math.ceil(self.logs.length / self.itemsPerPage);
			$scope.$watch('lCtrl.currentPage + lCtrl.itemsPerPage', function(){
				let begin = ((self.currentPage - 1) * self.itemsPerPage),
					end = begin + self.itemsPerPage;
				self.filteredLogs = self.logs.slice(begin, end);
			});
		} else {
			self.filteredLogs = angular.copy(self.bankActions)
		}
	};
	
	self.setPage = pageNo => {
		self.currentPage = pageNo;
	};
	
	self.pageChanged = () => {
		console.log('Page change to: ' + self.currentPage);
	};
	
	self.setItemsPerPage = num => {
		self.itemsPerPage = num;
		self.currentPage = 1;
	};
	
	self.setDataShow = (log) => {
		self.log = log;
	};
	
	const initController = () => {
		self.get();
	};
	
	angular.element(document).ready(function (){
		initController();
	});
});