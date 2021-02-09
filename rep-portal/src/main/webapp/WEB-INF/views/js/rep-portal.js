/* eslint no-alert: 0 */

'use strict';

function relatedProductLink(code) {

	var scope = angular.element(document.getElementById('main')).scope();
	scope.$apply(function(){
	scope.relatedProductLink(code);
	});
	//alert(code);
}



function changeWarehouse(code, branch, warehouse) {


	//var socket = new WebSockt('ws://www.baidu.com');//http->ws; https->wss
	//socket.send('hello WebSockt');
	//socket.onmessage = function(event){
	    //var data = event.data;

	    //alert(data);
	//}


	var scope = angular.element(document.getElementById('main')).scope();
	scope.$apply(function(){
	scope.changeWarehouse(code,branch,warehouse);
	});
}




/*var _selected;*/

//
// Here is how to define your module
// has dependent on mobile-angular-ui
//
var app = angular.module('rep-portal', [
	'ngRoute',
	'mobile-angular-ui',
	'ui.bootstrap',
	'ngAnimate', 'ngSanitize',
	// touch/drag feature: this is from 'mobile-angular-ui.gestures.js'.
	// This is intended to provide a flexible, integrated and and
	// easy to use alternative to other 3rd party libs like hammer.js, with the
	// final pourpose to integrate gestures into default ui interactions like
	// opening sidebars, turning switches on/off ..
	'mobile-angular-ui.gestures'
]);
app.run(function($transform,$rootScope) {
	window.$transform = $transform;
    $rootScope.$on('$locationChangeSuccess', function (event, newUrl) {

        var isMobile = {
            Android: function() {
                return navigator.userAgent.match(/Android/i);
            },
            BlackBerry: function() {
                return navigator.userAgent.match(/BlackBerry/i);
            },
            iOS: function() {
                return navigator.userAgent.match(/iPhone|iPad|iPod/i);
            },
            Opera: function() {
                return navigator.userAgent.match(/Opera Mini/i);
            },
            Windows: function() {
                return navigator.userAgent.match(/IEMobile/i);
            },
            any: function() {
                return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
            }
        };

        if( !isMobile.any() )
		{

            document.getElementById('main').classList.add('sidebar-left-in');
		}
        /*	if(document.getElementById('main').classList.contains('sidebar-left-visible')) {
                document.getElementById('main').classList.add('sidebar-left-in');
                console.log('$locationChangeSuccess changed!', newUrl);
            }*/
    });
});


// You can configure ngRoute as always, but to take advantage of SharedState location
// feature (i.e. close sidebar on backbutton) you should setup 'reloadOnSearch: false'
// in order to avoid unwanted routing.
//
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/productHome', {
		templateUrl : 'productHome.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/productinfo', {
		templateUrl : 'productinfo.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/stockinfo', {
		templateUrl : 'stockinfo.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/imagesInfo', {
		templateUrl : 'imagesInfo.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/pricing', {
		templateUrl : 'pricing.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/similar', {
		templateUrl : 'similar.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/accessories', {
		templateUrl : 'accessories.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/overlay', {
		templateUrl : 'overlay.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/forms', {
		templateUrl : 'forms.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/dropdown', {
		templateUrl : 'dropdown.html',
		reloadOnSearch : false
	});

	$routeProvider.when('/accordion', {
		templateUrl : 'accordion.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/touch', {
		templateUrl : 'touch.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/swipe', {
		templateUrl : 'swipe.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/scroll', {
		templateUrl : 'scroll.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/drag', {
		templateUrl : 'drag.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/drag2', {
		templateUrl : 'drag2.html',
		reloadOnSearch : false
	});
	$routeProvider.when('/carousel', {
		templateUrl : 'carousel.html',
		reloadOnSearch : false
	});
});

//
// `$touch example`
//

app.directive('toucharea', [ '$touch', function($touch) {
	// Runs during compile
	return {
		restrict : 'C',
		link : function($scope, elem) {
			$scope.touch = null;
			$touch.bind(elem, {
				start : function(touch) {
					$scope.containerRect = elem[0].getBoundingClientRect();
					$scope.touch = touch;
					$scope.$apply();
				},

				cancel : function(touch) {
					$scope.touch = touch;
					$scope.$apply();
				},

				move : function(touch) {
					$scope.touch = touch;
					$scope.$apply();
				},

				end : function(touch) {
					$scope.touch = touch;
					$scope.$apply();
				}
			});
		}
	};
} ]);

//
// `$drag` example: drag to dismiss
//
app.directive('dragToDismiss', function($drag, $parse, $timeout) {
	return {
		restrict : 'A',
		compile : function(elem, attrs) {
			var dismissFn = $parse(attrs.dragToDismiss);
			return function(scope, elem) {
				var dismiss = false;

				$drag.bind(elem, {
					transform : $drag.TRANSLATE_RIGHT,
					move : function(drag) {
						if (drag.distanceX >= drag.rect.width / 4) {
							dismiss = true;
							elem.addClass('dismiss');
						} else {
							dismiss = false;
							elem.removeClass('dismiss');
						}
					},
					cancel : function() {
						elem.removeClass('dismiss');
					},
					end : function(drag) {
						if (dismiss) {
							elem.addClass('dismitted');
							$timeout(function() {
								scope.$apply(function() {
									dismissFn(scope);
								});
							}, 300);
						} else {
							drag.reset();
						}
					}
				});
			};
		}
	};
});

//
// Another `$drag` usage example: this is how you could create
// a touch enabled "deck of cards" carousel. See `carousel.html` for markup.
//
app.directive('carousel', function() {
	return {
		restrict : 'C',
		scope : {},
		controller : function() {
			this.itemCount = 0;
			this.activeItem = null;

			this.addItem = function() {
				var newId = this.itemCount++;
				this.activeItem = this.itemCount === 1 ? newId : this.activeItem;
				return newId;
			};

			this.next = function() {
				this.activeItem = this.activeItem || 0;
				this.activeItem = this.activeItem === this.itemCount - 1 ? 0 : this.activeItem + 1;
			};

			this.prev = function() {
				this.activeItem = this.activeItem || 0;
				this.activeItem = this.activeItem === 0 ? this.itemCount - 1 : this.activeItem - 1;
			};
		}
	};
});

app.directive('carouselItem', function($drag) {
	return {
		restrict : 'C',
		require : '^carousel',
		scope : {},
		transclude : true,
		template : '<div class="item"><div ng-transclude></div></div>',
		link : function(scope, elem, attrs, carousel) {
			scope.carousel = carousel;
			var id = carousel.addItem();

			var zIndex = function() {
				var res = 0;
				if (id === carousel.activeItem) {
					res = 2000;
				} else if (carousel.activeItem < id) {
					res = 2000 - (id - carousel.activeItem);
				} else {
					res = 2000 - (carousel.itemCount - 1 - carousel.activeItem + id);
				}
				return res;
			};

			scope.$watch(function() {
				return carousel.activeItem;
			}, function() {
				elem[0].style.zIndex = zIndex();
			});

			$drag.bind(elem, {
				//
				// This is an example of custom transform function
				//
				transform : function(element, transform, touch) {
					//
					// use translate both as basis for the new transform:
					//
					var t = $drag.TRANSLATE_BOTH(element, transform, touch);

					//
					// Add rotation:
					//
					var Dx = touch.distanceX;
					var t0 = touch.startTransform;
					var sign = Dx < 0 ? -1 : 1;
					var angle = sign * Math.min((Math.abs(Dx) / 700) * 30, 30);

					t.rotateZ = angle + (Math.round(t0.rotateZ));

					return t;
				},
				move : function(drag) {
					if (Math.abs(drag.distanceX) >= drag.rect.width / 4) {
						elem.addClass('dismiss');
					} else {
						elem.removeClass('dismiss');
					}
				},
				cancel : function() {
					elem.removeClass('dismiss');
				},
				end : function(drag) {
					elem.removeClass('dismiss');
					if (Math.abs(drag.distanceX) >= drag.rect.width / 4) {
						scope.$apply(function() {
							carousel.next();
						});
					}
					drag.reset();
				}
			});
		}
	};
});

app.directive('dragMe', [ '$drag', function($drag) {
	return {
		controller : function($scope, $element) {
			$drag.bind($element,
				{
					//
					// Here you can see how to limit movement
					// to an element
					//
					transform : $drag.TRANSLATE_INSIDE($element.parent()),
					end : function(drag) {
						// go back to initial position
						drag.reset();
					}
				},
				{ // release touch when movement is outside bounduaries
					sensitiveArea : $element.parent()
				}
			);
		}
	};
} ]);

//rep-portal
/*app.directive('myOnKeyDownCall', function() {
	return function(scope, element, attrs) {
		element.bind("keydown keypress", function(event) {

			scope.$apply(function() {

				var keycode = window.event ? event.keyCode : event.which;

				if (keycode == 13) {
					scope.callSolrService(event.key);
				}

			});
			event.preventDefault();
		});
	};
});*/


//rep-portal
app.filter('htmlContent', [ '$sce', function($sce) {
	return function(input) {
		return $sce.trustAsHtml(input);
	}
} ]);
//
// For this trivial demo we have just a unique MainController
// for everything
//
app.controller('MainController', [ '$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {

	$scope.loadProperties = function() {
		jQuery.i18n.properties({
			name:'celum',
			path:'celum/',
			mode:'map',
			callback: function() {
				$scope.celumurl=  jQuery.i18n.prop('celumurl');
				$scope.userpass = jQuery.i18n.prop('userpass');

			}
		});
	};

	$scope.swiped = function(direction) {
		alert('Swiped ' + direction);
	};

	// User agent displayed in home page
	$scope.userAgent = navigator.userAgent;

	// Needed for the loading screen
	$rootScope.$on('$routeChangeStart', function() {
		$rootScope.loading = true;
	});

	$rootScope.$on('$routeChangeSuccess', function() {
		$rootScope.loading = false;
	});

	// Fake text i used here and there.
	$scope.lorem = 'Besides making it possible to bind different data to the scope inside a directive, using an isolated scope has another effect.' +
		'We can show this by adding another property, vojta, to our scope and trying to access it from within our directive ' +
		' vojta, to our scope and trying to acces.';

	//
	// 'Scroll' screen
	//
	var scrollItems = [];

	for (var i = 1; i <= 100; i++) {
		scrollItems.push('Item ' + i);
	}

	$scope.scrollItems = scrollItems;

	$scope.bottomReached = function() {
		alert('Congrats you scrolled to the end of the list!');
	};

	//
	// Right Sidebar
	//
	$scope.chatUsers = [
		{
			name : 'Carlos  Flowers',
			online : true
		},
		{
			name : 'Byron Taylor',
			online : true
		},
		{
			name : 'Jana  Terry',
			online : true
		},
		{
			name : 'Darryl  Stone',
			online : true
		},
		{
			name : 'Fannie  Carlson',
			online : true
		},
		{
			name : 'Holly Nguyen',
			online : true
		},
		{
			name : 'Bill  Chavez',
			online : true
		}
	];


	$scope.searchProduct = function() {
		alert('You submitted the search');
	};

    $scope.menuclick = function(menu) {
        //var element = angular.element(document.getElementById('menuBtn'));
        //window.location= "#!/" + menu;
    };
	//
	// 'Forms' screen
	//
	$scope.rememberMe = true;
	$scope.email = 'me@example.com';

	$scope.login = function() {
		alert('You submitted the login form');
	};

	//
	// 'Drag' screen
	//
	$scope.notices = [];


	for (var j = 0; j < 10; j++) {
		$scope.notices.push({
			icon : 'envelope',
			message : 'Notice ' + (j + 1)
		});
	}

	$scope.deleteNotice = function(notice) {
		var index = $scope.notices.indexOf(notice);
		if (index > -1) {
			$scope.notices.splice(index, 1);
		}
	}

	////rep-portal

	$scope.baseUrl = 'https://'+ location.hostname + ':' + location.port + '/rep-portal/';

	$scope.oneAtATime = true;

	$scope.message = '';

	$scope.toogle = true;

	$scope.toogled = function($index) {

		if ($index != $scope.previousIndex) {

			$scope.toogle = true;

			$scope.previousIndex = $index;

		} else {

			if ($scope.toogle == true) {

				$scope.toogle = false;
			} else {
				$scope.toogle = true;
			}
		}

	}


	$scope.sortChanged = function() {
		var rootScope = angular.element(document.getElementById('main')).scope();
		$scope.callSolrService(1, rootScope.placement.selected);

		var paginationScope = angular.element(document.getElementById('pagination')).scope();
	    if(paginationScope != null)
			paginationScope.bigCurrentPage = 1;

	};



/*	$scope.selected = undefined;
	$scope.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
	$scope.searchcode = function(value) {
	    if (arguments.length) {
	      _selected = value;
	    } else {
	      return _selected;
	    }
	  };

	  $scope.modelOptions = {
	    debounce: {
	      default: 500,
	      blur: 250
	    },
	    getterSetter: true
	  };
*/

	$scope.placement = {
		    options: [
		      'Relevance',
		      'Name(asc)',
		      'Name(desc)',
		      'Most Recent'
		    ],
		    selected: 'Relevance'
		  };

	$scope.relatedProductLink = function(code) {
		//$scope.searchcode = code;
		$scope.callRestService(code);

	}

	$scope.changeWarehouse = function(code, branch, warehouse) {
		$scope.getStockInformation(code, branch, warehouse);
		$scope.getPolinesInformation(code,branch, warehouse);
	}


	angular.element(window).bind('load', function() {
		//$scope.toogled(3);
		$scope.loadProperties();

	});

}
])
    .controller('PaginationDemoCtrl', function ($scope, $log) {


    	  $scope.setPage = function (pageNo) {
    	    $scope.bigCurrentPage = pageNo;
    	  };

    	  $scope.pageChanged = function() {
    		  var rootScope = angular.element(document.getElementById('main')).scope();
    		  // for page show the previous number when back form product detail page
    		  rootScope.lastBigCurrentPage = $scope.bigCurrentPage;
    		  $scope.callSolrService($scope.bigCurrentPage, $scope.placement.selected);
    	  };

    	  $scope.isShow = true;

    	  $scope.maxSize = 10;
    	  var rootScope = angular.element(document.getElementById('main')).scope();

    	  $scope.bigCurrentPage = rootScope.lastBigCurrentPage;
    	  $scope.bigTotalItems = rootScope.lastBigTotalItems;


    	})
	.controller('TooltipDemoCtrl', function($scope, $sce) {
		$scope.dynamicTooltip = 'Hello, World!';
		$scope.dynamicTooltipText = 'dynamic';
		$scope.htmlTooltip = $sce.trustAsHtml('I\'ve been made <b>bold</b>!');
		$scope.placement = {
			options : [
				'top',
				'top-left',
				'top-right',
				'bottom',
				'bottom-left',
				'bottom-right',
				'left',
				'left-top',
				'left-bottom',
				'right',
				'right-top',
				'right-bottom'
			],
			selected : 'top'
		};
	}) //rep-portal
	.controller('AccordionDemoCtrl', function($scope) {
		$scope.oneAtATime = true;

		$scope.groups = [
			{
				title : 'Dynamic Group Header - 1',
				content : 'Dynamic Group Body - 1'
			},
			{
				title : 'Dynamic Group Header - 2',
				content : 'Dynamic Group Body - 2'
			}
		];

		$scope.items = [ 'Item 1', 'Item 2', 'Item 3' ];

		$scope.addItem = function() {
			var newItemNo = $scope.items.length + 1;
			$scope.items.push('Item ' + newItemNo);
		};

		$scope.status = {
			isCustomHeaderOpen : false,
			isFirstOpen : true,
			isFirstDisabled : false
		};
	}) //rep-portal
	.controller('AlertDemoCtrl', function($scope) {

		$scope.$on('errorMsg', function(e, message) {
			$scope.alerts.push({
				msg : message
			});
		});


		$scope.alerts = [
			/*    { type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.' },
			    { type: 'success', msg: 'Well done! You successfully read this important alert message.' }*/
		];

		$scope.addAlert = function() {
			$scope.alerts.push({
				msg : 'Another alert!'
			});
		};

		$scope.closeAlert = function(index) {
			$scope.alerts.splice(index, 1);
		};
	})
	.controller('ModalDemoCtrl', function($uibModal, $log, $document) {
		var $ctrl = this;
		$ctrl.items = [];

		$ctrl.animationsEnabled = true;

		$ctrl.open = function(size, parentSelector) {
			var parentElem = parentSelector ?
				angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
			var modalInstance = $uibModal.open({
				animation : $ctrl.animationsEnabled,
				ariaLabelledBy : 'modal-title',
				ariaDescribedBy : 'modal-body',
				templateUrl : 'myModalContent.html',
				controller : 'ModalInstanceCtrl',
				controllerAs : '$ctrl',
				size : size,
				appendTo : parentElem,
				resolve : {
					items : function() {
						return $ctrl.items;
					}
				}
			});

			modalInstance.result.then(function(selectedItem) {
				$ctrl.selected = selectedItem;
			}, function() {
				$log.info('Modal dismissed at: ' + new Date());
			});
		};
		/*
		$ctrl.openComponentModal = function () {
		  var modalInstance = $uibModal.open({
		    animation: $ctrl.animationsEnabled,
		    component: 'modalComponent',
		    resolve: {
		      items: function () {
		        return $ctrl.items;
		      }
		    }
		  });

		  modalInstance.result.then(function (selectedItem) {
		    $ctrl.selected = selectedItem;
		  }, function () {
		    $log.info('modal-component dismissed at: ' + new Date());
		  });
		};

		$ctrl.openMultipleModals = function () {
		  $uibModal.open({
		    animation: $ctrl.animationsEnabled,
		    ariaLabelledBy: 'modal-title-bottom',
		    ariaDescribedBy: 'modal-body-bottom',
		    templateUrl: 'stackedModal.html',
		    size: 'sm',
		    controller: function($scope) {
		      $scope.name = 'bottom';
		    }
		  });

		  $uibModal.open({
		    animation: $ctrl.animationsEnabled,
		    ariaLabelledBy: 'modal-title-top',
		    ariaDescribedBy: 'modal-body-top',
		    templateUrl: 'stackedModal.html',
		    size: 'sm',
		    controller: function($scope) {
		      $scope.name = 'top';
		    }
		  });
		};*/

		$ctrl.toggleAnimation = function() {
			$ctrl.animationsEnabled = !$ctrl.animationsEnabled;
		};
	});





//rep-portal
app.directive('searchBar', [ function() {
	return {
		restrict : 'E',

		template : ' <div class="input-group"><input type="text" ng-model="searchcode" ng-model-options="modelOptions" uib-typeahead="suggest for suggest in suggests" class="form-control" placeholder="Product Search ..." uib-tooltip="Enter product to find information" tooltip-placement="top" tooltip-enable="!searchcode" /><span class="input-group-btn"><button class="btn btn-default" ng-click="searchService(1)">search</button></span></div>',


		//'<input type="text" ng-model="searchcode"><button class="btn btn-default" ng-click="callRestService()">search</button>',

		controller : function($scope, $element, $http, $compile) {


			$scope.getProductInformation = function(code) {




				$http.get($scope.baseUrl + 'mdm/products/code/' + code.toUpperCase() + '?warehouse=NSW&branch=400')
				.then(function onSuccess(response) {
					$scope.pproddescmarket = '';
					$scope.productTitle = '';
					$scope.productInfo = '';
					$scope.imagesInfo = '';
					// Handle success
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;
					var obj = angular.fromJson(data);

					    var x2js = new X2JS();
				        var replacement = x2js.xml_str2json(obj.pdrivermain);

				        var replacementHtml = "";
				        var aleterRtm ="";

				        if(replacement.replacementproduct.rtm_replacementproduct != null && replacement.replacementproduct.rtm_replacementproduct != "")
				        {

				        	aleterRtm = "(" + replacement.replacementproduct.rtm_replacementproduct + ")"

				        }

				        var repaceproduct = "";
				        repaceproduct =  replacement.replacementproduct.rtm_replacementproduct;
				        repaceproduct = replacement.replacementproduct.replacementproduct;


				        if(repaceproduct != null && repaceproduct != "")
				        {

				        	replacementHtml = "<span style='background-color:#f4f1cb'>NOTE: This item has been replaced by " + "<a onclick=relatedProductLink('" +  repaceproduct + "')>" + repaceproduct + aleterRtm + "</a> click here</span>";
				        }




						if (obj.pcopyretail == null)
							obj.pcopyretail = '';

					    var prodwarrexclusion = ""

					    if(obj.pprodwarrexclusion != null && obj.pprodwarrexclusion != "")
					    {
					    	prodwarrexclusion = '<b>Specials terms or exclusions:</b>' + obj.pprodwarrexclusion + "<br/>"
					    }

						var pimgmain = '';
						if (obj.pimgmain != '') {
							pimgmain = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgmain + "&format=8" + "'><br/>";
						}

                    	var classsales = obj.pProdclasssales == null? "": obj.pProdclasssales;

						var productapplication = '';
						if(obj.pProductapplication != null && obj.pProductapplication != '')
						{
                            productapplication = '<b>Product Application:</b>' + '<br/>' + obj.pProductapplication  + '<br/><br/>';
                        }

                        var pkeysellingfeatures = '';
						if(obj.pKeysellingfeature1 != null && obj.pKeysellingfeature1 != '')
						{
                            pkeysellingfeatures = pkeysellingfeatures + '<li>' + obj.pKeysellingfeature1 + '</li>';
						}
						if(obj.pKeysellingfeature2 != null && obj.pKeysellingfeature2 != '')
						{
							pkeysellingfeatures = pkeysellingfeatures + '<li>' + obj.pKeysellingfeature2 + '</li>';
						}
						if(obj.pKeysellingfeature3 != null && obj.pKeysellingfeature3 != '')
						{
							pkeysellingfeatures = pkeysellingfeatures + '<li>' + obj.pKeysellingfeature3 + '</li>';
						}
						if(obj.pKeysellingfeature4 != null && obj.pKeysellingfeature4 != '')
						{
							pkeysellingfeatures = pkeysellingfeatures + '<li>' + obj.pKeysellingfeature4 + '</li>';
						}
						if(obj.pKeysellingfeature5 != null && obj.pKeysellingfeature5 != '')
						{
							pkeysellingfeatures = pkeysellingfeatures + '<li>' + obj.pKeysellingfeature5 + '</li>';
						}
						if(pkeysellingfeatures != '')
						{
                            pkeysellingfeatures =  '<b>Key Selling points:</b>' + '<ul>' + pkeysellingfeatures + '</ul>';
						}

                    $scope.productInfo = 'ADN:' +  obj.pprodstatus  + ' | Class:' + classsales + '<BR/>' + '<h2>' + obj.pproddescmarket + '</h2>' + '<b>Code:</b>' + obj.code + '<br/>' + '<b>Barcode Primary:</b>' + obj.pprodbarcodeprimary + '<br/>' + replacementHtml + '<br/><br/>' + obj.pcopyretail + '<br/>'  + '<br/>' + productapplication + pkeysellingfeatures + '<b>Warranty:</b>' + obj.pprodwarrext + ' months' + '<br/>' + prodwarrexclusion + '<br/>' + pimgmain ;

					$scope.pproddescmarket = '<h2>' +  obj.pproddescmarket + '</h2>';

					$scope.productTitle = obj.pproddescmarket;



					var pimgfront = '';
					if (obj.pimgfront != '') {
						pimgfront = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgfront + "&format=8" + "'><br/>";
					}

					var pimgrear = '';
					if (obj.pimgrear != '') {
						pimgrear = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgrear + "&format=8" + "'><br/>";
					}

					var pimglside = '';
					if (obj.pimglside != '') {
						pimglside = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimglside + "&format=8" + "'><br/>";
					}

					var pimgrside = '';
					if (obj.pimgrside != '') {
						pimgrside = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgrside + "&format=8" + "'><br/>";
					}

					var pimgtop = '';
					if (obj.pimgtop != '') {
						pimgtop = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgtop + "&format=8" + "'><br/>";
					}

					var pimginsit = '';
					if (obj.pimginsit != '') {
						pimginsit = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimginsit + "&format=8" + "'><br/>";
					}

					var pimgacc = '';
					if (obj.pimgacc != '') {
						pimgacc = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgacc + "&format=8" + "'><br/>";
					}

					var pimgpkd = '';
					if (obj.pimgpkd != '') {
						pimgpkd = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgpkd + "&format=8" + "'><br/>";
					}

					var pimgclose = '';
					if (obj.pimgclose != '') {
						pimgclose = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgclose + "&format=8" + "'><br/>";
					}

					var pimginuse = '';
					if (obj.pimginuse != '') {
						pimginuse = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimginuse + "&format=8" + "'><br/>";
					}

					var pimgopen = '';
					if (obj.pimgopen != '') {
						pimgopen = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgopen + "&format=8" + "'><br/>";
					}

					var pimgclosed = '';
					if (obj.pimgclosed != '') {
						pimgclosed = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgclosed + "&format=8" + "'><br/>";
					}


					var pimglinediagram = '';
					if (obj.pimglinediagram != '') {
						pimglinediagram = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimglinediagram + "&format=8" + "'><br/>";
					}


					var pimgfeatures = '';
					if (obj.pimgfeatures != '') {
						pimgfeatures = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + obj.pimgfeatures + "&format=8" + "'><br/>";
					}


					$scope.imagesInfo = pimgmain + pimgfront + pimgrear + pimglside + pimgrside + pimgtop + pimginsit + pimgacc + pimgpkd + pimgclose + pimginuse + pimgopen + pimgclosed + pimglinediagram + pimgfeatures;



				}, function onError(response) {
					// Handle error
					$scope.pproddescmarket = '';
					$scope.productInfo = '';
					$scope.imagesInfo = '';
					$scope.productTitle = '';
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;

					var messages = "";
					if (data == null || data == undefined)
						messages = 'Product ' + code.toUpperCase() + ' Information Not Found   \n';

					$scope.message += messages;
				});

			}



			$scope.searchSolr = function(page, sort) {
				//get result item number
				$http.get($scope.baseUrl + 'products/solr/items/'  + $scope.searchcode)
				.then(function onSuccess(response) {

					var paginationScope = angular.element(document.getElementById('pagination')).scope();
					paginationScope.bigTotalItems = response.data;
					paginationScope.isShow = true;
					$scope.lastBigTotalItems = response.data;

				}, function onError(response) {
					var paginationScope = angular.element(document.getElementById('pagination')).scope();
					paginationScope.bigTotalItems = response.data;
					$scope.lastBigTotalItems = response.data;

				});

				//get search result
				$http.get($scope.baseUrl + 'products/solr?'  + 'item=' + $scope.searchcode + '&sort=' + sort + '&page='+page+'&rows='+10)
				.then(function onSuccess(response) {
					$scope.searchResults = "";
					var data = response.data;
					var products = angular.fromJson(data);
					var i = 0;

					for (; i < products.length; i++) {

						var product = products[i];

						var pimgmain = '';
						if (product.imgmain != '') {
							pimgmain = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + product.imgmain + "&format=8" + "'><br/>";
						}

						var productHtml = pimgmain + "Code: " + product.code + "<br>"  + "Primary barcode: " + product.prodbarcodeprimary + "<br>" + "<b>" + "<a onclick=relatedProductLink('" + product.code  + "')>Name: " + product.proddescmarket + "</a>" + "</b><hr />";
						$scope.searchResults += productHtml;
					}

				}, function onError(response) {
					var data = response.data;
				});

				//get more like this
				/*$http.get($scope.baseUrl + 'products/solr/mlt?'  + 'item=' + $scope.searchcode)
				.then(function onSuccess(response) {
					$scope.mltResults = "";
					var data = response.data;
					var products = angular.fromJson(data);
					var i = 0;

					for (; i < products.length; i++) {

						var product = products[i];

						var pimgmain = '';
						if (product.imgmain != '') {
							pimgmain = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + product.imgmain + "&format=8" + "'><br/>";
						}

						var productHtml = pimgmain + "Code: " + product.code + "<br>" + "<b>" + "<a onclick=relatedProductLink('" + product.code  + "')>Name: " + product.proddescmarket + "</a>" + "</b><hr />";
						$scope.mltResults += productHtml;
					}

				}, function onError(response) {
					var data = response.data;
				});*/


			}


			$scope.getRelatedProductInformation = function(code){
				$http.get($scope.baseUrl + 'mdm/relatedProducts/code/' + code.toUpperCase())
				.then(function onSuccess(response) {
					$scope.similarProducts = '';
					$scope.accessories = '';
					// Handle success
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;
					var relatedProducts = angular.fromJson(data);
					var i = 0;
					$scope.similarProducts = '';
					$scope.accessories = '';
					//alert(obj);

					angular.element(document.getElementById('similar-products')).empty();
					angular.element(document.getElementById('similar-accessories')).empty();

					$scope.similarProducts = "";
					$scope.similarAccessories = "";

					for (; i < relatedProducts.length; i++) {

						var related = relatedProducts[i];


						$http.get($scope.baseUrl + 'mdm/product/target/code/' + related.targetcode.toUpperCase() + '?warehouse=NSW&branch=400')
							.then(function onSuccess(response) {
								var data = response.data;
								var status = response.status;
								var statusText = response.statusText;
								var headers = response.headers;
								var config = response.config;

								if (related.relationtype == 'SIMILAR') {


									var  similarProduct = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass + data.pimgmain + "&format=8" + "'>" +
									"<div>" + "<a onclick=relatedProductLink('" + data.code  + "')>Name: " + data.pproddescmarket + "</a>" + "<br/>Code: " + data.code + "<br/></div>";
								    //var temp = $compile(similarProduct)($scope);

								    //$scope.similarProducts += temp.html();
								    //angular.element(document.getElementById('similar-products')).append(temp);

									$scope.similarProducts += similarProduct;

								} else if (related.relationtype == 'ACCESSORIES') {


									var  similarAccessory = "<img class='lazy img-responsive' src='" + $scope.celumurl + $scope.userpass  + data.pimgmain + "&format=8" + "'>" +
									"<div>" + "<a onclick=relatedProductLink('" + data.code  + "')>Name: " + data.pproddescmarket + "</a>" + "<br/>Code: " + data.code + "<br/></div>";
								    //var temp = $compile(similarAccessory)($scope);

									$scope.similarAccessories += similarAccessory;
								    //angular.element(document.getElementById('similar-accessories')).append(temp);


								}

							}, function onError(response) {
								// Handle error
								var data = response.data;
								var status = response.status;
								var statusText = response.statusText;
								var headers = response.headers;
								var config = response.config;

								var messages = "";
								if (data == null || data == undefined)
									if ($scope.message == "") {
										messages = 'Related Product ' + related.targetcode.toUpperCase() + ' Not Found    \n';
									} else {
										messages = ', Related Product ' + related.targetcode.toUpperCase() + ' Not Found    \n';

								}
								$scope.message += messages;


							});



					}

					//$scope.stockInfo = '<h2>' + $scope.pproddescmarket + '</h2>' + '<b>' + obj.stkStkqty + '</b> units on-hand in warehouse <br/>' + '<b>' + obj.stkAllstk + '</b> units allocated to customers<br/>' + '<b>' + stocklevel + '</b> units available<br/>' + '<b>' + totalorder + '</b> units on order<br/>'


				}, function onError(response) {
					// Handle error
					$scope.similarProducts = '';
					$scope.accessories = '';
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;

					var messages = "";
					if (data == null || data == undefined)
						messages = 'Related Products for' + code.toUpperCase() + 'Not Found \n';

					$scope.message += messages;

				});


			}



			$scope.getStockInformation = function(code,branch, warehouse) {

				var nsw, akl, dcrta;
				('" + product.code  + "')
				if(warehouse == "NSW")
				   nsw = "<a style='font-weight:bold;font-size:18px;' onclick=changeWarehouse('" + code  + "','400','NSW')>" + "Sydney Warehouse" + "</a><br/>";
				else
				    nsw = "<a onclick=changeWarehouse('" + code  + "','400','NSW')>" + "Sydney Warehouse" + "</a><br/>";

				if(warehouse == "AKL")
					akl = "<a style='font-weight:bold;font-size:18px;' onclick=changeWarehouse('" + code  + "','200','AKL')>" + "Auckland Warehouse" + "</a><br/>";
				else
					akl = "<a onclick=changeWarehouse('" + code  + "','200','AKL')>" + "Auckland Warehouse" + "</a><br/>";


				if(warehouse == "DCRTA")
					dcrta = "<a style='font-weight:bold;font-size:18px;' onclick=changeWarehouse('" + code  + "','300','DCRTA')>" + "RTM AU Warehouse (Sydney)" + "</a><br/><br/>";
				else
					dcrta = "<a onclick=changeWarehouse('" + code  + "','300','DCRTA')>" + "RTM AU Warehouse (Sydney)" + "</a><br/><br/>";

				$http.get($scope.baseUrl + 'warehouse/stock?' + 'item=' + code.toUpperCase() + '&warehouse='+warehouse+'&branch='+branch)
				.then(function onSuccess(response) {
					$scope.stockInfo = '';
					// Handle success
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;
					var obj = angular.fromJson(data);
					var stocklevel = obj.stkStkqty - obj.stkAllstk - obj.stkUnavqty + obj.stkUnvallqty - obj.stkResqty;

					$scope.stockInfo = nsw + akl + dcrta + '<b>' + obj.stkStkqty + '</b> units on-hand in warehouse <br/>' + '<b>' + obj.stkAllstk + '</b> units allocated to customers<br/>' + '<b>' + stocklevel + '</b> units available<br/>';




				}, function onError(response) {
					// Handle error


					$scope.stockInfo = nsw + akl + dcrta;
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;
					var messages = "";
					if (data == null || data == undefined) {
						if ($scope.message == "") {
							messages = 'Stock Not Found    \n';
						} else {
							messages = ',  Stock Not Found    \n';
						}
					}
					$scope.message += messages;

				});

			}

			$scope.getPolinesInformation = function(code, branch, warehouse){
				$http.get($scope.baseUrl + 'warehouse/polines?' + 'item=' + code.toUpperCase() + '&warehouse='+warehouse+'&branch='+branch)
				.then(function onSuccess(response) {

					$scope.polinesInfo = '';
					$scope.totalorderInfo = '';

					// Handle success
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;



					var obj = angular.fromJson(data);

					var totord = parseInt("0");

					if(obj.length > 0){

						$scope.polinesInfo = "<br><table ><thead><tr><th>PO Number&nbsp;&nbsp; </th><th>Quantity&nbsp;&nbsp; </th><th>ETA</th></tr></thead><tbody>"
					}

					for (var i=0;i<obj.length;i++)
					{
						$scope.polinesInfo +=
							"<tr>" +
							"<th>" + obj[i].pordOrder + "</th>" +
							"<td>" + obj[i].poitQtyord + "</td>" +
							"<td>" + obj[i].poitDuedateString + "</td>" +
							"</tr>";
						totord = totord + parseInt(obj[i].poitQtyord);
					}

					if(obj.length > 0){

						$scope.polinesInfo += "</tbody></table>";
					}

					if(obj.length > 0){
						$scope.totalorderInfo =  '<b>' + totord.toString() + '</b> units on order<br/>';
					}

				}, function onError(response) {
					// Handle error

					$scope.polinesInfo = '';
					$scope.totalorderInfo = '';
					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;
					var messages = "";
					if (data == null || data == undefined)
						messages = '';

					$scope.message += messages;
				});
			}

      $scope.getBundleInformation = function(code) {
        $scope.bundleInfo = "";

        $http.get($scope.baseUrl + 'warehouse/bundle?' + 'item=' + code.toUpperCase())
          .then(function onSuccess(response) {
            var pricingHtml = response.data;

            var bundle = jQuery(pricingHtml);

            var bundleContent = bundle.find("#view6");

            $scope.bundleInfo = bundleContent.html();


          }, function onError(response) {
            $scope.bundleInfo = "";

            var data = response.data;
            var status = response.status;
            var statusText = response.statusText;
            var headers = response.headers;
            var config = response.config;
            var messages = "";
            if (data == null || data == undefined)
              messages = '';

            $scope.message += messages;
          });

      }

			$scope.getPricingInformation = function(code) {

				$scope.pricingInfo = "";

				$http.get($scope.baseUrl + 'warehouse/pricing?' + 'item=' + code.toUpperCase())
				.then(function onSuccess(response) {
					var pricingHtml = response.data;

					var pricing = jQuery(pricingHtml);

					var sonar = pricing.find("div.hySoanar_Container");

					var electus = pricing.find("div.hyTB_Container");

					var jaycar = pricing.find("div.hyPriceJay_Container");

					var rtm = pricing.find("div.hyRTM_Container");

					rtm.find("h2").text("RTM Pricing");

					var arau = pricing.find("div.ARAU_special").parent();
					//var arnz = pricing.find("div.ARNZ_special");
					var ar = "";
					if(arau.length > 0)
						ar = "<h2>AR Special price</h2>" + arau.html();
						//ar = "<h2>AR Special price</h2>" + "<div class='ARAU_special' >" + arau.html() + "</div>" + "<div class='ARAU_special' >" + "<div class='ARNZ_special' >" + arnz.html() + "</div>" ;

					$scope.pricingInfo = ar +  electus.html() + sonar.html()+ "<h2>Jaycar Pricing</h2>" + jaycar.html() + rtm.html();


				}, function onError(response) {
					$scope.pricingInfo = "";

					var data = response.data;
					var status = response.status;
					var statusText = response.statusText;
					var headers = response.headers;
					var config = response.config;
					var messages = "";
					if (data == null || data == undefined)
						messages = '';

					$scope.message += messages;
				});

			}


			$scope.callRestService = function(code) {

				window.location= "#!/productHome";

				if (code == '')
					return;

				$scope.message = '';


				$scope.getProductInformation(code);

				$scope.getRelatedProductInformation(code);

				$scope.getStockInformation(code,'400','NSW');

				$scope.getPricingInformation(code);

        $scope.getBundleInformation(code);

				$scope.getPolinesInformation(code,'400','NSW');



				$scope.limit = "";
				$http.get($scope.baseUrl + 'limit')
				.then(function onSuccess(response) {
					$scope.limit = response.data;


				}, function onError(response) {
					// Handle error

					$scope.limit = "";
				});

				$scope.expire = "";
				$http.get($scope.baseUrl + 'expire')
				.then(function onSuccess(response) {
					$scope.expire = response.data;


				}, function onError(response) {
					// Handle error

					$scope.expire = "";
				});
			}

			$scope.suggestService = function(){
				$scope.suggests = [];
				$http.get($scope.baseUrl + 'products/solr/suggest?item='  + $scope.searchcode)
				.then(function onSuccess(response) {
/*					var json = response.data;

					var parsed = JSON.parse(json);

					var arr = [];

					for(var x in parsed){
					  arr.push(parsed[x]);
					}

					$scope.suggests = arr;*/
					$scope.suggests = response.data;

				}, function onError(response) {


				});
			}


			$scope.searchService = function(page) {


				$scope.callSolrService(page,$scope.placement.selected);

				var paginationScope = angular.element(document.getElementById('pagination')).scope();
			    if(paginationScope != null)
					paginationScope.bigCurrentPage = 1;
			}

			$scope.callSolrService = function(page,sort) {

				$scope.productTitle = '';
				window.location= "#!/";
				$scope.isShow = true;

				if ($scope.searchcode.toUpperCase() == '')
					return;

				$scope.message = '';


				$scope.searchSolr(page, sort);
			}
		},


		link : function(scope, ele, attrs, ctrl) {

			/*  scope.$watch('searchcode', function(newVal, oldVal) {
			    if (newVal) {
			      scope.searchcode = newVal;

			      scope.callRestService();
			    }
			  });*/
			ele[0].onkeyup = function(event) {

				var keycode = window.event ? event.keyCode : event.which;

				if (keycode == 13) {
					scope.searchService(1);
				}
				else{
					scope.suggestService();
				}
				event.preventDefault();
			};
		},
	};
} ]);


//Please note that $uibModalInstance represents a modal window (instance) dependency.
//It is not the same as the $uibModal service used above.

app.controller('ModalInstanceCtrl', function($uibModalInstance, items) {
	var $ctrl = this;
	$ctrl.items = items;
	$ctrl.selected = {
		item : $ctrl.items[0]
	};

	$ctrl.ok = function() {
		$uibModalInstance.close($ctrl.selected.item);
	};

	$ctrl.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});

//Please note that the close and dismiss bindings are from $uibModalInstance.

app.component('modalComponent', {
	templateUrl : 'myModalContent.html',
	bindings : {
		resolve : '<',
		close : '&',
		dismiss : '&'
	},
	controller : function() {
		var $ctrl = this;

		$ctrl.$onInit = function() {
			$ctrl.items = $ctrl.resolve.items;
			$ctrl.selected = {
				item : $ctrl.items[0]
			};
		};

		$ctrl.ok = function() {
			$ctrl.close({
				$value : $ctrl.selected.item
			});
		};

		$ctrl.cancel = function() {
			$ctrl.dismiss({
				$value : 'cancel'
			});
		};
	}
});
