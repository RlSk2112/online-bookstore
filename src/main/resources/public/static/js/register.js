function registerHandler() {
    const url = '/api/bookstore/users/register';
    let username = document.getElementById('username');
    let email = document.getElementById('email');
    let password = document.getElementById('password');
    let confirmPassword = document.getElementById('confirmPassword');
    let registerButton = document.getElementById('register-button');
    registerButton.addEventListener('click', regUser)

    function regUser(event) {
        if (event) {
            event.preventDefault();
        }

        let payload = JSON.stringify({
            username: username.value,
            email: email.value,
            password: password.value,
            confirmPassword: confirmPassword.value,
        });

        let reqOptions = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: payload
        }

        fetch(url, reqOptions)
            .then((resp) => resp.text())
            .then((text) => window.location.href = "#/users/login")
            .catch((err) =>
                console.error(err)
            );
    }
}
