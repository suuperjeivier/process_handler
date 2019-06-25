app.service('bankAccountTypeService', function ($q, factory) {
    const self = this;
    const path = 'bank/account/type';
    self.get = () => {
        return $q((resolve, reject) => {
            factory.get(path).then(data => {
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