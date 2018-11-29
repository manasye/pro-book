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
                    if (!res.success) {
                        swal('ERROR', res.message, 'error');
                    } else {
                        swal('SUCCESS', res.message, 'success');
                    }
                }
            });
        },
        allowOutsideClick: () => !swal.isLoading()
    });
};

$$('#purchaseMessagePopupCloseButton').onclick = () => {
    $$('#purchaseMessageBackground').classList.remove('visible');
    $$('#purchaseMessagePopup').classList.remove('visible');
    setTimeout(() => {
        $$('#purchaseMessageBackground').style.zIndex = -1;
        $$('#purchaseMessagePopup').style.zIndex = -1;
    }, 250);
};
