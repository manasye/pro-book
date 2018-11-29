import $$ from './lib/jQowi.js';

$$('#orderButton').onclick = () => {
    swal({
        title: 'Submit your token number',
        input: 'text',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        confirmButtonText: 'Submit',
        showLoaderOnConfirm: true,
        preConfirm: code => {
            const urlParams = new URLSearchParams(window.location.search);
            const bookId = urlParams.get('id');

            const data = {
                token: code,
                bookId,
                amount: parseInt($$('#orderQuantitySelector').value, 10)
            };

            return $$.ajax({
                method: 'POST',
                url: `/buy-book`,
                data: JSON.stringify(data),
                callback: response => {
                    let res = JSON.parse(response);
                    console.log(res);
                    // if (res.message !== 'Merchant Secret invalid')
                    //     $$('#qr-code').src = res.qrCode;
                    // else
                    //     $$('#qr-code').src =
                    //         'https://image.flaticon.com/icons/svg/8/8235.svg';
                }
            });
        },
        allowOutsideClick: () => !swal.isLoading()
    }).then(result => {
        // if (result.value) {
        //     swal({
        //         title: `${result.value.login}'s avatar`,
        //         imageUrl: result.value.avatar_url
        //     });
        // }
    });
    // $$('#orderButton').disabled = true;
    // const data = {
    //   'book_id': parseInt($$('#bookIdField').value, 10),
    //   'quantity': parseInt($$('#orderQuantitySelector').value, 10)
    // }
    // $$.ajax({
    //   method: 'POST',
    //   url: '/order',
    //   data: JSON.stringify(data),
    //   callback: (response) => {
    //     response = JSON.parse(response);
    //     $$('#orderButton').disabled = false;
    //     $$('#purchaseMessagePopupText').innerHTML = 'Transaction Number: ' + response.orderNumber;
    //     $$('#purchaseMessageBackground').style.zIndex = 2;
    //     $$('#purchaseMessagePopup').style.zIndex = 2;
    //     setTimeout(() => {
    //       $$('#purchaseMessageBackground').classList.add('visible');
    //       $$('#purchaseMessagePopup').classList.add('visible');
    //     }, 100);
    //   },
    // })
};

$$('#purchaseMessagePopupCloseButton').onclick = () => {
    $$('#purchaseMessageBackground').classList.remove('visible');
    $$('#purchaseMessagePopup').classList.remove('visible');
    setTimeout(() => {
        $$('#purchaseMessageBackground').style.zIndex = -1;
        $$('#purchaseMessagePopup').style.zIndex = -1;
    }, 250);
};
