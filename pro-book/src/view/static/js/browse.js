import $$ from './lib/jQowi.js';

const invalidQueryMessage = 'Please input some search terms';

let submitButtonHovered = false;

function htmlToPlaintext(text) {
    return text ? String(text).replace(/<[^>]+>/gm, '') : '';
}

function showInputValidationMessage() {
    $$('#inputValidationMessageContainer').classList.add('visible');
    $$('#inputValidationMessage').classList.add('visible');
}

function hideInputValidationMessage() {
    $$('#inputValidationMessageContainer').classList.remove('visible');
    $$('#inputValidationMessage').classList.remove('visible');
}

function updateInputValidationMessage(message) {
    $$('#inputValidationMessage').innerHTML = message;
}

function validateInput(_) {
    const queryField = $$('#queryField');
    const submitButton = $$('#submitButton');

    if (queryField.value.length == 0) {
        submitButton.disabled = true;
        updateInputValidationMessage(invalidQueryMessage);
        if (submitButtonHovered) showInputValidationMessage();
    } else {
        submitButton.disabled = false;
        hideInputValidationMessage();
    }
}

$$('#queryField').oninput = validateInput;

updateInputValidationMessage(invalidQueryMessage);

$$('#submitButtonInner').onmouseenter = () => {
    if ($$('#submitButton').disabled) {
        showInputValidationMessage();
    }
    submitButtonHovered = true;
    validateInput();
};

$$('#submitButtonInner').onmouseleave = () => {
    hideInputValidationMessage();
    submitButtonHovered = false;
};

$$('#queryField').onkeyup = e => {
    var code = e.keyCode ? e.keyCode : e.which;
    if (code == 13) {
        // Enter keycode
        $$('#submitButtonInner').click();
    }
};
