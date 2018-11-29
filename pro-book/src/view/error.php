<?php
function render_template(int $errorCode, string $errorMessage) {
  return <<<HTML

<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="favicon.ico" type="image/x-icon" />
  <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
  <link rel='stylesheet' href='src/view/static/css/common.css'>
  <link rel='stylesheet' href='src/view/static/css/main.css'>
  <link rel='stylesheet' href='src/view/static/css/error.css'>
  <script type='module' src='src/view/static/js/error.js'></script>
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <meta name="google-signin-client_id" content="320134199127-rqu56mi4kr6h0ekkbrejr00agenerb3p.apps.googleusercontent.com">
  <link rel="stylesheet" href="src/view/static/css/fonts.css" type='text/css'>
  <title>Not Found</title>
</head>
<body>
	<div class='main-page-container'>
    <div class='main-header-container'>
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
    </div>
    <div class='main-content-container'>
      <div class='main-content-padding'></div>
      <p class='error-code'>{$errorCode}</p>
      <h3 class='error-message'>{$errorMessage}</h3>
    </div>
  </div>
  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>

HTML;
}