<?php
function render_template(string $username) {
  return <<<HTML

<!DOCTYPE html>
<html ng-app="browseApp">
   <head>
      <link rel="icon" href="favicon.ico" type="image/x-icon" />
      <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
      <link rel='stylesheet' href='src/view/static/css/common.css'>
      <link rel='stylesheet' href='src/view/static/css/main.css'>
      <link rel='stylesheet' href='src/view/static/css/browse.css'>
      <link rel='stylesheet' href='src/view/static/css/search.css'>
      <link rel='stylesheet' href='src/view/static/css/grid.css'>
      <script type='module' src='src/view/static/js/main.js'></script>
      <script type='module' src='src/view/static/js/browse.js'></script>
      <script src="//code.angularjs.org/1.3.0-rc.1/angular.min.js"></script>
      <script src="src/view/static/js/ang-controller.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
      <link rel="stylesheet" href="src/view/static/css/fonts.css" type='text/css'>
      <title>Pro-Book</title>
   </head>
   <body>
      <div id='inputValidationMessageContainer' class='main-input-validation-message-container'>
         <p id='inputValidationMessage' class='main-input-validation-message'></p>
      </div>
      <div class='main-page-container' ng-controller="mainController">
         <div class='main-header-container'>
            <div class='main-header-top-container'>
               <div id='titleContainer' class='main-title-container'>
                  <div class='main-title-zstack'>
                     <h1 id='titleShadow' class='main-title-shadow'>PRO-BOOK</h1>
                  </div>
                  <div class='main-title-zstack'>
                     <h1 id='titleBackground' class='main-title-background'>PRO-BOOK</h1>
                  </div>
                  <div class='main-title-zstack'>
                     <h1 id='titleText' class='main-title-text'><span class='main-title-text-first'>PRO</span>-BOOK</h1>
                  </div>
               </div>
               <div class='main-misc-container'>
                  <div class='main-greeting-container'>
                     <h5>Hi, {$username}!</h5>
                  </div>
                  <div id='logoutButtonContainer' class='main-logout-button-container'>
                     <form id='logoutForm' action='/logout' method='get'></form>
                     <button id="logoutButton" class='main-logout-button' type='submit' form='logoutForm'>
                        <div id="logoutButtonIcon" class='main-logout-button-icon'></div>
                     </button>
                  </div>
               </div>
            </div>
            <div class='main-header-bottom-container'>
               <div id='browseTab' class='main-menu-tab tab-selected'>
                  <h3>Browse</h3>
               </div>
               <div id='historyTab' class='main-menu-tab tab-mid'>
                  <h3>History</h3>
               </div>
               <div id='profileTab' class='main-menu-tab'>
                  <h3>Profile</h3>
               </div>
            </div>
         </div>
         <div class='main-content-container'>
            <div class='browse-content-container'>
               <div class='browse-title-container'>
                  <h1 class='browse-title'>Search B<a class='o-button' href='/about'>o</a><a class='o-button' href='/about'>o</a>ks</h1>
               </div>
               <form id='browseForm' class='browse-form'>
                  <input id='queryField' type='text' name='title' placeholder='Input search terms...' autofocus 
                     ng-model="searchInput">
               </form>
               <div class='browse-submit-container'>
                  <button id='submitButton' type='button' form='browseForm' disabled  ng-click="searchBook()">
                     <div id='submitButtonInner' class='browse-submit-inner'>
                        SEARCH
                     </div>
                  </button>
               </div>
            </div>
            <div class="loader" ng-if="isLoading">Loading...</div>
            <div class='search-content-container'>
               <div class='search-title-container'>
                  <div class='search-result-count-container add-background'>
                    <h4 class='search-result-count' ng-if="searched">
                      Found {{books.length || 0}} Result(s)</h4>
                  </div>
                </div>
               <div class='search-result-container' ng-repeat="book in books" ng-if="searched">
                  <div class="search-book-container">
                     <div class="search-book-content-container">
                        <div class="search-book-image-container">
                           <img class="search-book-image" src={{book.imageUrl}}>
                        </div>
                        <div class="search-book-text-container">
                            <div class="row">
                              <div class="col-1-of-2">
                                <h4 class="book-title">{{book.title}}</h4>
                              </div>
                              <div class="col-1-of-2">
                                <h4 class="book-price" ng-class="{'book-price-red': book.price === -1}">
                                  {{ book.price === -1 ? 'Not For Sale' : 'Rp ' + book.price }}</h4>
                              </div>
                            </div>
                           <h4 class="book-author">{{book.author}} - 0.0 / 5.0 (0 vote)</h4>
                           <p class="book-description">{{book.description.length > 300 ? 
                           book.description.substring(0, 300) + '...' : book.description}}</p>
                        </div>
                     </div>
                     <div class="search-detail-button-container">
                        <form id="bookDetail-{{book.id}}" action="/book" method="get">
                           <input hidden="" name="id" value={{book.id}}>
                        </form>
                        <button class="search-detail-button" type="submit" form="bookDetail-{{book.id}}">
                           <div class="search-detail-button-inner">
                              DETAILS
                           </div>
                        </button>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>

HTML;
}