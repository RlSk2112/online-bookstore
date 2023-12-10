function loginHandler() {
    const url = "/api/bookstore/users/login"
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
			.then(() => {
				window.location.href = "#/books"
			})
            .catch((err) =>
                console.error(err)
            );
    }
}
