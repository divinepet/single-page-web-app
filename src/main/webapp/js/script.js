const form  = document.getElementsByTagName('form')[0];
const email = document.getElementById('email');
const firstName = document.getElementById('first');
const lastName = document.getElementById('last');
const phone = document.getElementById('phone');


const emailRegExp = /^([a-z0-9_.+-]+)@([\da-z.-]+)\.([a-z.]{2,6})$/;
const nameRegExp = /^[a-zA-Z0-9_-]{2,16}$/;
const phoneRegExp = /^[ 0-9]+$/;

function addEvent(element, event, callback) {
    let previousEventCallBack = element["on"+event];
    element["on"+event] = function (e) {
        let output = callback(e);

        if (output === false) return false;

        if (typeof previousEventCallBack === 'function') {
            output = previousEventCallBack(e);
            if(output === false) return false;
        }
    }
}

addEvent(window, "load", function () {
    // является ли поле пустым
    const emailTest = email.value.length === 0 || emailRegExp.test(email.value);
    const firstTest = firstName.value.length === 0 || nameRegExp.test(firstName.value);
    const lastTest = lastName.value.length === 0 || nameRegExp.test(lastName.value);
    const phoneTest = phone.value.length === 0 || phoneRegExp.test(phone.value);

    email.className = emailTest ? "valid" : "invalid";
    firstName.className = firstTest ? "valid" : "invalid";
    lastName.className = lastTest ? "valid" : "invalid";
    phone.className = phoneTest ? "valid" : "invalid";
});

// при вводе пользователем значения в поле
addEvent(lastName, "input", function () {
    const test = lastName.value.length === 0 || nameRegExp.test(lastName.value);
    lastName.className = (test) ? "valid" : "invalid";
});
addEvent(email, "input", function () {
    const test = email.value.length === 0 || emailRegExp.test(email.value);
    email.className = (test) ? "valid" : "invalid";
});
addEvent(firstName, "input", function () {
    const test = firstName.value.length === 0 || nameRegExp.test(firstName.value);
    firstName.className = (test) ? "valid" : "invalid";
});
addEvent(phone, "input", function () {
    const test = phone.value.length === 0 || phoneRegExp.test(phone.value);
    phone.className = (test) ? "valid" : "invalid";
});


// попытка отправить данные
addEvent(form, "submit", function () {
    const emailTest = email.value.length === 0 || emailRegExp.test(email.value);
    const firstTest = firstName.value.length === 0 || nameRegExp.test(firstName.value);
    const lastTest = lastName.value.length === 0 || nameRegExp.test(lastName.value);
    const phoneTest = phone.value.length === 0 || phoneRegExp.test(phone.value);


    if (!emailTest) {
        email.className = "invalid";
        return false;
    } else {
        email.className = "valid";
    }

    if (!firstTest) {
        firstName.className = "invalid";
        return false;
    } else {
        firstName.className = "valid";
    }

    if (!lastTest) {
        lastName.className = "invalid";
        return false;
    } else {
        lastName.className = "valid";
    }

    if (!phoneTest) {
        phone.className = "invalid";
        return false;
    } else {
        phone.className = "valid";
    }
});