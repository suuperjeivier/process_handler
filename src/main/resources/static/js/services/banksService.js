app.service('banksService', function($q, factory){
    const self = this;
    const path = 'bank';

     self.get = () => {
        return $q((resolve, reject) => {
            factory.get(path).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.post = bank => {
        return $q((resolve, reject) => {
            factory.post(path,bank).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.put = bank => {
        return $q((resolve, reject) => {
            factory.put(path,bank).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

    self.del = bank => {
        return $q((resolve, reject) => {
            factory.del(path,bank).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };
});