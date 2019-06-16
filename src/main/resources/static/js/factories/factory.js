'use stritc';
app.factory('factory', function ($http, $q) {
    return {
        get: function (url) {
            return $http({
                url: '/' + url,
                method: "GET"
            }).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(response);
            });
        },
        get: function (url, params) {
            return $http({
                url: '/' + url,
                method: "GET",
                headers: {
                    'Content-Type': 'application/json'
                },
                params: params
            }).then(function (response) {
                return response.data
            }, function (response) {
                return $q.reject(response);
            });
        },
        getExternal: function (url, params) {
            return $http({
                url: url,
                method: "GET",
                headers: {
                    'Content-Type': 'application/json'
                },
                params: params
            }).then(function (response) {
                return response.data
            }, function (response) {
                return $q.reject(response);
            });
        },
        post: function (url, _data) {
            return $http({
                url: '/' + url,
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: _data
            }).then(function (response) {
                return response.data
            }, function (response) {
                return $q.reject(response);
            });
        },
        postFile: function (url, _data) {
            return $http({
                url: '/' + url,
                method: "POST",
                headers: {
                    'Content-Type': undefined
                },
                data: _data
            }).then(function (response) {
                return response.data
            }, function (response) {
                return $q.reject(response);
            });
        },
        put: function (url, _data) {
            return $http({
                url: '/' + url,
                method: "PUT",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: _data
            }).then(function (response) {
                return response.data
            }, function (response) {
                return $q.reject(response);
            });
        },
        del: function (url, _data) {
            return $http({
                url: '/' + url,
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: _data
            }).then(function (response) {
                return response.data
            }, function (response) {
                return $q.reject(response);
            });
        }
    }
});