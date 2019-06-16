app.service('batchService', function($q, factory){
    const self = this;
    const path = 'batch';
    self.post = (fileName, accountId) => {
    	let data = new FormData();		
		data.append('accountId', accountId);
        return $q((resolve, reject) => {
            factory.postFile(path+'/file/'+fileName, data).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

   
});