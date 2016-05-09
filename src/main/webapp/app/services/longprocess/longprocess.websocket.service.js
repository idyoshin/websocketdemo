(function() {
    'use strict';
    /* globals SockJS, Stomp */

    angular
        .module('websocketdemoApp')
        .factory('LongProcessNotificationService', LongProcessNotificationService);

    LongProcessNotificationService.$inject = ['$window', '$q', 'localStorageService'];

    function LongProcessNotificationService($window, $q, localStorageService) {
        var stompClient = null;
        var connected = $q.defer();

        var loc = $window.location;
        var url = loc.protocol + '//' + loc.host + loc.pathname + 'websocket/longprocess/instance';
        var token = localStorageService.get('token');
        if (token && token.expires_at && token.expires_at > new Date().getTime()) {
            url += '?access_token=' + token.access_token;
        } else {
            url += '?access_token=no token';
        }

        /*jshint camelcase: false */
        var socket = new SockJS(url);

        stompClient = Stomp.over(socket);
        var headers = {
            Authorization : 'Bearer ' + token.access_token,
        };
        stompClient.connect(headers, function() {
            connected.resolve('success');
        });


        stompClient.debug = null;

        var service = {
            subscribe: subscribe
        };

        return service;

        function subscribe(forTarget, successHandler) {
            connected.promise.then(function() {
                stompClient.subscribe('/websocket/longprocess/instance/' + forTarget, function(data) {
                    successHandler(data);
                });
            }, null, null);
        }
    }
})();
