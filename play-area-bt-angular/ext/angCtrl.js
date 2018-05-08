
var myapp = angular.module("myApp", []);

myapp.controller('indexCtrl', function($scope){

  braintree.client.create({
authorization: 'eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiJlZTVlN2Y0Y2U2YzA0ZDAxZjljZWQ3MDRjMmIwMjVmZTVlODQ3NTc0ZmFlN2FhZmI2YTU5NzA5N2NmMzY3NzFlfGNyZWF0ZWRfYXQ9MjAxOC0wNC0zMFQwNjoyODo0NC45NzU0NzMwMDQrMDAwMFx1MDAyNm1lcmNoYW50X2lkPXc4MjV3M2o5a2Z5bTQ3ZHhcdTAwMjZwdWJsaWNfa2V5PTl4NDM2NnJxZzdiZ3hoNXMiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvdzgyNXczajlrZnltNDdkeC9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzL3c4MjV3M2o5a2Z5bTQ3ZHgvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vY2xpZW50LWFuYWx5dGljcy5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tL3c4MjV3M2o5a2Z5bTQ3ZHgifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiTWVsYm91cm5lIElUIC0gRG9tYWlueiIsImNsaWVudElkIjoiQWVFektrMm9VMnp2R1NRMGRPbTZkTjM1X2ltZjBraFVfaVhsMTk0VUFSSEIwYXZ2MFQxSGNvemNfX2d3NWQ1OWg0OE5jQkt0eFlsZk9RRXIiLCJwcml2YWN5VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3BwIiwidXNlckFncmVlbWVudFVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS90b3MiLCJiYXNlVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhc3NldHNVcmwiOiJodHRwczovL2NoZWNrb3V0LnBheXBhbC5jb20iLCJkaXJlY3RCYXNlVXJsIjpudWxsLCJhbGxvd0h0dHAiOnRydWUsImVudmlyb25tZW50Tm9OZXR3b3JrIjpmYWxzZSwiZW52aXJvbm1lbnQiOiJvZmZsaW5lIiwidW52ZXR0ZWRNZXJjaGFudCI6ZmFsc2UsImJyYWludHJlZUNsaWVudElkIjoibWFzdGVyY2xpZW50MyIsImJpbGxpbmdBZ3JlZW1lbnRzRW5hYmxlZCI6dHJ1ZSwibWVyY2hhbnRBY2NvdW50SWQiOiJkb21haW56bnpkIiwiY3VycmVuY3lJc29Db2RlIjoiTlpEIn0sIm1lcmNoYW50SWQiOiJ3ODI1dzNqOWtmeW00N2R4IiwidmVubW8iOiJvZmYifQ=='
}, function (err, clientInstance) {
if (err) {
 console.error(err);
 return;
}

braintree.hostedFields.create({
 client: clientInstance,
 // styles: {
 //   'input': {
 //     'font-size': '14px',
 //     'font-family': 'helvetica, tahoma, calibri, sans-serif',
 //     'color': '#3a3a3a'
 //   },
 //   ':focus': {
 //     'color': 'black'
 //   }
 // },
 fields: {
   number: {
     selector: '#card-number',
     placeholder: '4111 1111 1111 1111'
   },
   cvv: {
     selector: '#cvv',
     placeholder: '123'
   },
   expirationMonth: {
     selector: '#expiration-month',
     placeholder: 'MM'
   },
   expirationYear: {
     selector: '#expiration-year',
     placeholder: 'YY'
   },
   postalCode: {
     selector: '#postal-code',
     placeholder: '90210'
   }
 }
}, function (err, hostedFieldsInstance) {
 if (err) {
   console.error(err);
   return;
 }

 hostedFieldsInstance.on('validityChange', function (event) {
   var field = event.fields[event.emittedBy];

   if (field.isValid) {
     if (event.emittedBy === 'expirationMonth' || event.emittedBy === 'expirationYear') {
       if (!event.fields.expirationMonth.isValid || !event.fields.expirationYear.isValid) {
         return;
       }
     } else if (event.emittedBy === 'number') {
       $('#card-number').next('span').text('');
     }

     // Remove any previously applied error or warning classes
     $(field.container).parents('.form-group').removeClass('has-warning');
     $(field.container).parents('.form-group').removeClass('has-success');
     // Apply styling for a valid field
     $(field.container).parents('.form-group').addClass('has-success');
   } else if (field.isPotentiallyValid) {
     // Remove styling  from potentially valid fields
     $(field.container).parents('.form-group').removeClass('has-warning');
     $(field.container).parents('.form-group').removeClass('has-success');
     if (event.emittedBy === 'number') {
       $('#card-number').next('span').text('');
     }
   } else {
     // Add styling to invalid fields
     $(field.container).parents('.form-group').addClass('has-warning');
     // Add helper text for an invalid card number
     if (event.emittedBy === 'number') {
       $('#card-number').next('span').text('Looks like this card number has an error.');
     }
   }
 });

 hostedFieldsInstance.on('cardTypeChange', function (event) {
   // Handle a field's change, such as a change in validity or credit card type
   if (event.cards.length === 1) {
     $('#card-type').text(event.cards[0].niceType);
   } else {
     $('#card-type').text('Card');
   }
 });

 $('.panel-body').submit(function (event) {
   event.preventDefault();
   hostedFieldsInstance.tokenize(function (err, payload) {
     console.log('About to Tokenize!');

     if (err) {
       console.error(err);
       return;
     }


     // This is where you would submit payload.nonce to your server
     alert('Submit your nonce to your server here!');
   });
 });
});
});

});
