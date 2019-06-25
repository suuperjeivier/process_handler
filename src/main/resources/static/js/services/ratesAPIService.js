app.service('ratesAPIService', function($q, factory){
	const self = this;
	const path = 'https://api.exchangeratesapi.io/';
	self.fecthOnPeriod = (start, end) => {    	
		let data = {
				start_at:start,
				end_at: end,
				symbols: "GBP,AUD,MXN,EUR",
				base: 'USD'
		};
		return $q((resolve, reject) => {
			factory.getExternal(path+'history', data).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};

	self.fetchCurrentRate = () => {    	
		//https://api.exchangeratesapi.io/latest?base=USD&symbols=MXN
		let data = {    			
				symbols: "GBP,AUD,MXN,EUR",
				base: 'USD'
		};
		return $q((resolve, reject) => {
			factory.getExternal(path+'latest', data).then(data => {
				resolve(data);
			}, error => {
				reject(error);
			});
		});
	};


});