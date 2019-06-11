app.service('companyService', function($q, factory){
    const self = this;
    const path = 'company';

     self.get = () => {
        return $q((resolve, reject) => {
            factory.get(path).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.post = company => {
        return $q((resolve, reject) => {
            factory.post(path,company).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.put = company => {
        return $q((resolve, reject) => {
            factory.put(path,company).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.del = company => {
        return $q((resolve, reject) => {
            factory.del(path,company).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };
});