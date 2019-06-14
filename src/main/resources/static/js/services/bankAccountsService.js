app.service('bankAccountService', function ($q, factory) {
    const self = this;
    const path = 'bank-accounts';
    self.get = () => {
        return $q((resolve, reject) => {
            factory.get(path).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };
    
    self.getByBetweenDates = (dates) => {
        return $q((resolve, reject) => {
            factory.get(path+'/between-dates', dates).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };    
    
    self.getByCompany = (comp) => {
    	let data = {'companyId': comp.id};
        return $q((resolve, reject) => {
            factory.get(path+'/company/id', data).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };  
    
    
    self.post = bankAccount => {
        return $q((resolve, reject) => {
            factory.post(path,bankAccount).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.put = bankAccount => {
        return $q((resolve, reject) => {
            factory.put(path,bankAccount).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.del = bankAccount => {
        return $q((resolve, reject) => {
            factory.del(path,bankAccount).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };
});