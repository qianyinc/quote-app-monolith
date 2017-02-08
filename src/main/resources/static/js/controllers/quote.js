'use strict'

angular.module('app.controllers', [])

.controller('RandomQuoteCtrl', function($scope, QuoteService) {
	QuoteService.random()
		.$promise.then(function(quote) {
			$scope.quote = quote;
		});
})
.controller('SaveQuoteCtrl', function($scope, $state, QuoteService) {
    
    $scope.saveQuote = function() {
        QuoteService.save(
            $scope.quote,
            function(response) {
                $state.go("authorList", {});
            },
            function(err) {
                console.log(err);
            }
        );
    };
})
.controller('AuthorListCtrl', function($scope, AuthorService) {
	AuthorService.list()
    .$promise.then(function(authors) {
	    $scope.authors = authors;
    });
	/*
	$scope.authors = [ {
		"id" : 1,
		"name" : "Douglas Adams"
	}, {
		"id" : 2,
		"name" : "Gautama Buddha"
	}, {
		"id" : 3,
		"name" : "Albert Einstein"
	} ];
	*/
})
.controller('QuoteListCtrl', function($scope,  $stateParams, QuoteService) {
	QuoteService.by({name: $stateParams.name})
	    .$promise.then(function(quotes) {
		    $scope.quotes = quotes;
	    });
	/*
	$scope.quotes = [ {
		"id" : 1,
		"text" : "The world is a thing of utter inordinate complexity and richness and strangeness that is absolutely awesome",
		"source" : "https://en.wikiquote.org/wiki/Douglas_Adams",
		"author" : {
			"id" : 1,
			"name" : 
		}
	} ];
	*/
});