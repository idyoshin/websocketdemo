(function() {
    'use strict';
    /* globals SockJS, Stomp */

    angular
        .module('websocketdemoApp')
        .component("longProcessView", longProcessView);

    var longProcessView = {
        templateUrl: 'app/modules/longprocess/longprocess.view.component.html',
        bindings: {
            processId: '<'
        },
        controller: ['$rootScope', 'LongProcessNotificationService',
            function ($rootScope, LongProcessNotificationService) {
                var ctrl = this;

                ctrl.status = {};

                ctrl.$onInit = function () {
                    LongProcessNotificationService.subscribe(
                        ctrl.processId,
                        function (data) {
                            var response = angular.fromJson(data.body);
                            ctrl.status = response;
                            // force the UI to be updated
                            $rootScope.$apply();
                        });
                }
            }]
    };
})();
