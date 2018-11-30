<?php
function render_template(string $username) {
  return <<<HTML

<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="favicon.ico" type="image/x-icon" />
  <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
  <link rel='stylesheet' href='src/view/static/css/common.css'>
  <link rel='stylesheet' href='src/view/static/css/main.css'>
  <link rel='stylesheet' href='src/view/static/css/about.css'>
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <meta name="google-signin-client_id" content="320134199127-rqu56mi4kr6h0ekkbrejr00agenerb3p.apps.googleusercontent.com">
  <script type='module' src='src/view/static/js/main.js'></script>
  <link rel="stylesheet" href="src/view/static/css/fonts.css" type='text/css'>
  <title>Browse</title>
</head>
<body>
	<div class='main-page-container'>
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
        <div id='browseTab' class='main-menu-tab'>
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
      <div class='about-title-container'>
        <h1 class='about-title'>About Us</h1>
      </div>
      <div class='about-content-container'>
        <div class='about-content-column-container'>
          <div class='about-content-picture-container'>
            <img class='profile-picture' src='src/model/profile/manasye.png' alt='Manasye Shousen Bukit'>
          </div>
          <div class='about-content-name-container'>
            <h3 class='name'>Manasye Shousen Bukit</h3>
            <h3 class='title'>Front End Master</h3>
          </div>
          <div class='about-content-detail-container'>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_email.svg'>
              </div>
              <div class='about-content'>
                <a href='mailto:manasyebukit@gmail.com'><p>manasyebukit@gmail.com</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_github.svg'>
              </div>
              <div class='about-content'>
                <a href='https://github.com/manasye'><p>manasye</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_linkedin.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.linkedin.com/in/manasye-shousen-bukit-833313156/'><p>Manasye Shousen Bukit</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_instagram.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.instagram.com/manasyebukit/'><p>@manasyebukit</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_facebook.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.facebook.com/manase.sekosen'><p>Manase Sekosen</p></a>
              </div>
            </div>
          </div>
        </div>
        <div class='about-content-column-container'>
          <div class='about-content-picture-container'>
            <img class='profile-picture' src='src/model/profile/abram.jpg' alt='Abram Situmorang'>
          </div>
          <div class='about-content-name-container'>
            <h3 class='name'>Abram Situmorang</h3>
            <h3 class='title'>Node JS Master</h3>
          </div>
          <div class='about-content-detail-container'>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_email.svg'>
              </div>
              <div class='about-content'>
                <a href='mailto:abram.perdanaputra@gmail.com'><p>abram.perdanaputra@gmail.com</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_github.svg'>
              </div>
              <div class='about-content'>
                <a href='https://github.com/abrampers'><p>abrampers</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_linkedin.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.linkedin.com/in/abrampers/'><p>Abram Situmorang</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_instagram.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.instagram.com/abrampers/'><p>@abrampers</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_facebook.svg'>
              </div>
              <div class='about-content'>
                  <a href='https://www.facebook.com/abram.perdanaputra'><p>Abram Perdanaputra</p></a>
              </div>
            </div>
          </div>
        </div>
        <div class='about-content-column-container'>
          <div class='about-content-picture-container'>
            <img class='profile-picture' src='src/model/profile/izzan.jpeg' alt='Ahmad Izzan'>
          </div>
          <div class='about-content-name-container'>
            <h3 class='name'>Ahmad Izzan</h3>
            <h3 class='title'>Java Master</h3>
          </div>
          <div class='about-content-detail-container'>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_email.svg'>
              </div>
              <div class='about-content'>
                <a href='mailto:aahmadizzan@gmail.com'><p>aahmadizzan@gmail.com</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_github.svg'>
              </div>
              <div class='about-content'>
                <a href='https://github.com/ahmadizzan'><p>ahmadizzan</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_linkedin.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.linkedin.com/in/ahmadizzan/'><p>Ahmad Izzan</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_instagram.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.instagram.com/izzaaann/'><p>@izzaaann</p></a>
              </div>
            </div>
            <div class='about-content-row-container'>
              <div class='about-content-icon-container'>
                <img class='icon' src='src/view/static/img/icon_facebook.svg'>
              </div>
              <div class='about-content'>
                <a href='https://www.facebook.com/ahmad.izzan.5'><p>Ahmad Izzan</p></a>
              </div>
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