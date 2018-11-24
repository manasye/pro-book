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
      <script type='module' src='src/view/static/js/main.js'></script>
      <script type='module' src='src/view/static/js/browse.js'></script>
      <script src="//code.angularjs.org/1.3.0-rc.1/angular.min.js"></script>
      <script src="src/view/static/js/ang-controller.js"></script>
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
               <form id='browseForm' class='browse-form' action='/search' method='GET'>
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
               <!-- <div class='search-title-container'>
                  <h1 class='search-title'>Maximo's Result</h1>
                  <div class='search-result-count-container add-background'>
                    <h4 class='search-result-count'>Found 2 Result(s)</h4>
                  </div>
                  </div> -->
               <div class='search-result-container' ng-repeat="book in books">
                  <div class="search-book-container">
                     <div class="search-book-content-container">
                        <div class="search-book-image-container">
                           <img class="search-book-image" src="src/model/books/1.jpg/">
                        </div>
                        <div class="search-book-text-container">
                           <h4 class="book-title">The Communist Manifesto</h4>
                           <h4 class="book-author">Karl Marx - 0.0 / 5.0 (0 vote)</h4>
                           <p class="book-description">The Communist Manifesto is divided into a preamble and four sections, the last of these a short conclusion.</p>
                        </div>
                     </div>
                     <div class="search-detail-button-container">
                        <form id="bookDetail-0" action="/book" method="get">
                           <input hidden="" name="id" value="1">
                        </form>
                        <button class="search-detail-button" type="submit" form="bookDetail-0">
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
