app.service('ratesAPIService', function($q, factory){
    const self = this;
    const path = 'https://api.exchangeratesapi.io/';
    self.fecthOnPeriod = (start, end) => {    	
    	let data = {
    			start_at:start,
    			end_at: end,
    			symbols: "USD,GBP,AUD,MXN,EUR",
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

   
});