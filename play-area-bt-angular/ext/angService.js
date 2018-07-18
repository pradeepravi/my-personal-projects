angular.module("myApp").factory("braintreeService",function($window, $q) {

        /* jshint unused:false */
        'use strict';

    var globalThis = this;
    var globalHostedIns =null;
    var nonce =null;
    return {

      init:function(){
        var hostedInst = null;
        console.log('INITIALIZING HOSTED Fields');
        var authorization = 'eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiJlZTVlN2Y0Y2U2YzA0ZDAxZjljZWQ3MDRjMmIwMjVmZTVlODQ3NTc0ZmFlN2FhZmI2YTU5NzA5N2NmMzY3NzFlfGNyZWF0ZWRfYXQ9MjAxOC0wNC0zMFQwNjoyODo0NC45NzU0NzMwMDQrMDAwMFx1MDAyNm1lcmNoYW50X2lkPXc4MjV3M2o5a2Z5bTQ3ZHhcdTAwMjZwdWJsaWNfa2V5PTl4NDM2NnJxZzdiZ3hoNXMiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvdzgyNXczajlrZnltNDdkeC9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzL3c4MjV3M2o5a2Z5bTQ3ZHgvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vY2xpZW50LWFuYWx5dGljcy5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tL3c4MjV3M2o5a2Z5bTQ3ZHgifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiTWVsYm91cm5lIElUIC0gRG9tYWlueiIsImNsaWVudElkIjoiQWVFektrMm9VMnp2R1NRMGRPbTZkTjM1X2ltZjBraFVfaVhsMTk0VUFSSEIwYXZ2MFQxSGNvemNfX2d3NWQ1OWg0OE5jQkt0eFlsZk9RRXIiLCJwcml2YWN5VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3BwIiwidXNlckFncmVlbWVudFVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS90b3MiLCJiYXNlVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhc3NldHNVcmwiOiJodHRwczovL2NoZWNrb3V0LnBheXBhbC5jb20iLCJkaXJlY3RCYXNlVXJsIjpudWxsLCJhbGxvd0h0dHAiOnRydWUsImVudmlyb25tZW50Tm9OZXR3b3JrIjpmYWxzZSwiZW52aXJvbm1lbnQiOiJvZmZsaW5lIiwidW52ZXR0ZWRNZXJjaGFudCI6ZmFsc2UsImJyYWludHJlZUNsaWVudElkIjoibWFzdGVyY2xpZW50MyIsImJpbGxpbmdBZ3JlZW1lbnRzRW5hYmxlZCI6dHJ1ZSwibWVyY2hhbnRBY2NvdW50SWQiOiJkb21haW56bnpkIiwiY3VycmVuY3lJc29Db2RlIjoiTlpEIn0sIm1lcmNoYW50SWQiOiJ3ODI1dzNqOWtmeW00N2R4IiwidmVubW8iOiJvZmYifQ==';
        braintree.client.create({
          authorization: authorization
        }, function(err, clientInstance) {
          if (err) {
            console.error(err);
            return;
          }
          createHostedFields(clientInstance);
        });

        function createHostedFields(clientInstance) {
          console.log('createHostedFields');
          console.log(clientInstance);
          braintree.hostedFields.create({
            client: clientInstance,
            styles: {
              'input': {
                'font-size': '16px',
                'font-family': 'courier, monospace',
                'font-weight': 'lighter',
                'color': '#ccc'
              },
              ':focus': {
                'color': 'black'
              },
              '.valid': {
                'color': '#8bdda8'
              }
            },
            fields: {
              number: {
                selector: '#card-number',
                placeholder: '4111 1111 1111 1111'
              },
              cvv: {
                selector: '#cvv',
                placeholder: '123'
              },
              expirationDate: {
                selector: '#expiration-date',
                placeholder: 'MM/YYYY'
              },
              postalCode: {
                selector: '#postal-code',
                placeholder: '11111'
              }
            }
          }, function (err, hostedFieldsInstance) {
            console.log('hostedFieldsInstance');
            console.log(hostedFieldsInstance);
            globalThis.globalHostedIns = hostedFieldsInstance;
          }
        );

          };
      },

      tokenizeHostedFields:function(){
        console.log( 'tokenizeHostedFields');
        console.log( globalThis.globalHostedIns);

        return globalThis.globalHostedIns.tokenize(function (tokeniseErr, payload) {
          if (tokeniseErr) {
            console.error("Token Error ***************");
            console.error(tokeniseErr);
          }
          console.log(payload);
          globalThis.payload = payload;
          console.log(globalThis.payload);

          return globalThis.payload;
        }

      );
    },
    asynchTokenizeHostedFields:function(){
      console.log( 'asynchTokenizeHostedFields');
      console.log( globalThis.globalHostedIns);

      //globalThis.globalHostedIns.tokenize(function (tokeniseErr, payload) {
        var deferred = $q.defer();
        if (nonce) {
            deferred.resolve(nonce);
        } else {
          globalThis.globalHostedIns.tokenize(function (tokeniseErr, payload) {
            if (tokeniseErr) {
              console.error("Token Error ***************");
              console.error(tokeniseErr);
              deferred.reject(tokeniseErr);
            }
            console.log(payload);
            nonce = payload.nonce;
            console.log(nonce);
            deferred.resolve(payload.nonce);
          });
            // $http.get(API_URL + 'status').then(function (respond) {
            //     session = respond.data.sessionId;
            //     deferred.resolve(respond.data.sessionId);
            // }, function (error, code) {
            //     deferred.reject(error);
            //     $log.error(error, code);
            // });
        }
        return deferred.promise;
      //}

  //  );
    }


    }
  });
