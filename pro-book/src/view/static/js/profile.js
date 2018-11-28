import $$ from './lib/jQowi.js';

$$('#editProfileButton').onmouseenter = () => {
    $$('#editProfileButtonIcon').classList.add('hover');
};

$$('#editProfileButton').onmouseleave = () => {
    $$('#editProfileButtonIcon').classList.remove('hover');
};

let data = {
    cardNumber: $$('#card-number-profile').innerHTML
};

$$.ajax({
    method: 'POST',
    url: `/secret`,
    data: JSON.stringify(data),
    callback: response => {
        let res = JSON.parse(response);
        $$('#qr-code').src = res.qrCode;
    }
});
