app.service('storageService', function($q, factory){
    const self = this;
    const path = 'storage';
    self.post = file => {
    	let fd = new FormData();		
		fd.append('file',file);
        return $q((resolve, reject) => {
            factory.postFile(path,fd).then(data => {
                resolve(data);
            }, error => {
                reject(error);
            });
        });
    };

   
});