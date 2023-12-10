(function () {
    let views = {
        '#/books': {
            file: 'static/templates/all-books.html',
            model: booksHandler
        },
        '#/users/login': {
            file: 'static/templates/login.html',
            model: loginHandler
        },
        '#/users/register': {
            file: 'static/templates/register.html',
            model: registerHandler
        },
		"#/home": {
            file: 'static/templates/home.html',
			model: {}
		},
    }

function onHashChange() {


	let view = views[window.location.hash];

    function loadViewAndModel() {

        fetch(view.file)
            .then((resp) => resp.text())
            .then((data) => {
                let section =  document.getElementById('main-section');
                section.innerHTML = data;
				if (typeof view.model == "function") {
					view.model();
				}
            })
            .catch((err) => {
                console.error(err);
            });
    }

	loadViewAndModel();

}

window.onhashchange = onHashChange;
window.location.href = "#/home";

onHashChange();
    
})();




