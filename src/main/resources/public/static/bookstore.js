function View3Model() {
    this.run = function () {
        loadBooks();
    }

    function loadBooks() {

        fetch("./books.json")
            .then(resp => resp.json())
            .then()
            .catch((err) => {
                console.error(err);
            });
    }
}

let views = {
    "#/api/bookstore/users/login": {
        file: "./",
        model: {
            run: function () {

            }
        }
    },
    "#/api/bookstore/users/register": {
        file: "./view1.html",
        model: {
            run: function () {
                console.log("hello I'm number 1");
            }
        }
    },
    "#/api/bookstore/books/all": {
        file: "./view2.html",
        model: {
            run: function () {
                document.getElementById("secret").innerHTML = "my secret is out";
            }
        }
    },
    "#/api/bookstore/authors/all": {
        file: "./view3.html",
        model: new View3Model()
    },
    "#/api/bookstore/books/edit": {
        file: "./view3.html",
        model: new View3Model()
    },
    "#/api/bookstore/authors/add": {
        file: "./view3.html",
        model: new View3Model()
    },
    "#/api/bookstore/books/add": {
        file: "./view3.html",
        model: new View3Model()
    }
}

function Application() {
    this.start = function () {
        const router = new Router();
        router.start();
        console.log("I've loaded some script");
    };
}

function Router() {
    this.start = function () {
        window.onhashchange = function () {
            let view = views[window.location.hash];
            let html = fetch(view.file).then(r => {
                r.text().then(t => {
                    document.getElementById("content").innerHTML = t;
                    view.model.run();
                });
            });
        };
    }
}

const app = new Application();
app.start();
