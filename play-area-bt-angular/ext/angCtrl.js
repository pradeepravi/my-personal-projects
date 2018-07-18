
var myapp = angular.module("myApp", []);

myapp.controller('indexCtrl',function($scope,$document,braintreeService ){
  braintreeService.init();
  $scope.change = function() {
      console.log("********** hostedFieldsGlobal *****");
      var nonce = braintreeService.asynchTokenizeHostedFields().then(function (respond) {
          if(respond){
            console.log('YAY GOT NONCE - >'+respond);
            alert('YAY GOT NONCE - >'+respond);
          }
      });
  };
}
);
