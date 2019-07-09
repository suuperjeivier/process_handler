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
			
		}, error => {
			 alertify.alert('Exito', 'Error al obtener los logs', function(){ alertify.error('Ok'); });
		});
	};
	
	self.getDiccionary = () => {
		logsService.getDiccionary().then( data => {
			self.diccionary = data;
			
		}, error => {
			 alertify.alert('Exito', 'Error al obtener el diccionario', function(){ alertify.error('Ok'); });
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
			self.filteredLogs = angular.copy(self.logs);
		}
	};
	
	self.paginarLogs = () => {
		if(self.logs.length > 10) {
			self.numPages = Math.ceil(self.logs.length / self.itemsPerPage);
			$scope.$watch('lCtrl.currentPage + lCtrl.itemsPerPage', function(){
				let begin = ((self.currentPage - 1) * self.itemsPerPage),
					end = begin + self.itemsPerPage;
				self.filteredLogs = self.logs.slice(begin, end);
				console.log('Self data: ', self.filteredLogs);
			});
		} else {
			self.filteredLogs = angular.copy(self.logs)
		}
	};
	
	self.setPage = pageNo => {
		self.currentPage = pageNo;
	};
	
	self.setItemsPerPage = num => {
		self.itemsPerPage = num;
		self.currentPage = 1;
	};
	
	self.setDataShow = (log) => {
		self.log = log;
		self.log.data = self.log.data.split(','); 
	};
	
	
	self.findKeyword = keyword => {
		let key = self.diccionary.find(function(element){
			return element.method == keyword;
		});
		
		return key.diccionary;
	};
	
	const initController = () => {
		self.getDiccionary();
		self.get();
	};
	
	angular.element(document).ready(function (){
		initController();
	});
});