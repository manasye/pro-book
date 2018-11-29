import $$ from './lib/jQowi.js';

$$('#titleContainer').onmouseenter = () => {
    $$('#titleBackground').classList.add('hover');
    $$('#titleText').classList.add('hover');
};

$$('#titleContainer').onmouseleave = () => {
    $$('#titleBackground').classList.remove('hover');
    $$('#titleText').classList.remove('hover');
};

$$('#titleContainer').onclick = () => {
    window.location = '/';
};

$$('#logoutButtonContainer').onmouseenter = () => {
    $$('#logoutButton').classList.add('hover');
    $$('#logoutButtonIcon').classList.add('hover');
};

$$('#logoutButtonContainer').onmouseleave = () => {
    $$('#logoutButton').classList.remove('hover');
    $$('#logoutButtonIcon').classList.remove('hover');
};

$$('.main-menu-tab').forEach(element => {
    element.onmouseenter = () => {
        element.classList.add('hover');
    };
    element.onmouseleave = () => {
        element.classList.remove('hover');
    };
});

$$('#browseTab').onclick = () => {
    window.location = '/browse';
};

$$('#historyTab').onclick = () => {
    window.location = '/history';
};

$$('#profileTab').onclick = () => {
    window.location = '/profile';
};

// $$('#logoutButton').onclick = () => {
//     // var auth2 = gapi.auth2.getAuthInstance();
//     // console.log(auth2);
//     // auth2.signOut().then(function() {
//     //     console.log('User signed out.');
//     // });
//     console.log('clicked');
//     document.location.href =
//         'https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost/application-name/logoutUser';
// };
