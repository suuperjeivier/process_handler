app.service('bankActionService', function($q, factory){
	const self = this;
	const path = 'bank-actions';

	self.get = () => {
		return $q((resolve, reject) => {
			factory.get(path).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
	
	self.getAllGrouped = () => {
		return $q((resolve, reject) => {
			factory.get(path+ '/grouped').then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
	
	self.getInstGruped = () =>{
		return $q((resolve, reject) => {
			factory.get(path+ '/inst/grouped').then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
	
	self.getByAccountId = (accountId) => {
		let data = {accountId: accountId};
		return $q((resolve, reject) => {
			factory.get(path+'/account/id', data).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
	
	self.getActionHistory = (actionId) => {
		let data = {actionId: actionId};
		return $q((resolve, reject) => {
			factory.get(path+'/history/id', data).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
	
	self.getActionHistoryH = (data) => {
		return $q((resolve, reject) => {
			factory.get(path+'/history/cusip/isinSerie/secId', data).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};

	self.post = bankAction => {
		return $q((resolve, reject) => {
			factory.post(path,bankAction).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};

	self.put = bankAction => {
		return $q((resolve, reject) => {
			factory.put(path,bankAction).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};

	self.del = bankAction => {
		return $q((resolve, reject) => {
			factory.del(path,bankAction).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};
});