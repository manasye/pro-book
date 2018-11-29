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
                    if (!res.success) {
                        swal({
                            title:
                                '<h2 style="font-weight: 700; line-height: 50px; font-size: 5rem">ERROR</h2>',
                            type: 'error',
                            html: `<h5 style="margin-bottom: 2vh; text-decoration: none; color:#003366">${
                                res.message
                            }</h5>`,
                            showCloseButton: true,
                            showCancelButton: false,
                            showConfirmButton: false
                        });
                    } else {
                        swal({
                            title:
                                '<h2 style="font-weight: 700; line-height: 50px; font-size: 5rem">SUCCESS</h2>',
                            type: 'success',
                            html: `<h5 style="margin-bottom: 2vh; text-decoration: none; color:#003366">${
                                res.message
                            }</h5>`,
                            showCloseButton: true,
                            showCancelButton: false,
                            showConfirmButton: false
                        });
                    }
                }
            });
        },
        allowOutsideClick: () => !swal.isLoading()
    }).then(() => {});
};

$$('#purchaseMessagePopupCloseButton').onclick = () => {
    $$('#purchaseMessageBackground').classList.remove('visible');
    $$('#purchaseMessagePopup').classList.remove('visible');
    setTimeout(() => {
        $$('#purchaseMessageBackground').style.zIndex = -1;
        $$('#purchaseMessagePopup').style.zIndex = -1;
    }, 250);
};
