function loginHandler() {
    const url = "/api/bookstore/authenticate"
    let username = document.getElementById('username');
    let password = document.getElementById('password');
    let loginButton = document.getElementById('login-button');
    loginButton.addEventListener('click', logUser);

    function logUser() {
        let payload = JSON.stringify({
            username: username.value,
            password: password.value
        });

        let reqOptions = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: payload
        }

        fetch(url, reqOptions)
			.then((resp) => {
                if (!checkResponseIsOk(resp)) {
                    let elements = document.querySelector('#login-error');
                    for (const element of elements) {
                        element.style.display = 'inline-block';
                    }
                }
				window.location.href = "#/books"
			})
            .catch((err) =>
                console.error(err)
            );
    }
}

function checkResponseIsOk(response) {
    return response.status >= 200 && response.status < 300;
}