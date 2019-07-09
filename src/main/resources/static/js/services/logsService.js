app.service('logsService', function($q, factory){
	const self = this;
	const path = 'log';
	
	self.get = () => {
		return $q((resolve, reject) => {
			factory.get(path).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
	
	self.getDiccionary = () => {
		return $q((resolve, reject) => {
			factory.get("json/diccionary.json").then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
});