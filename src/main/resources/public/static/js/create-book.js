import loadHandler from "./books";
import searchedId from "./books"

function createHandler(event) {
    if (event) {
        event.preventDefault();
    }

    const BASE_URL = '/api/bookstore';

    let form = document.getElementById('form');

    const newDomElements = {
        formTitle: form.children[0],
        titleInput: form.children[2],
        authorInput: form.children[4],
        submitButton: form.children[5],
    }

    newDomElements.submitButton.addEventListener('click', createHandler);

    if (newDomElements.authorInput.value === '' || newDomElements.titleInput.value === '') {
        return;
    }

    let currentTitle = newDomElements.titleInput.value;
    let currentAuthor = newDomElements.authorInput.value;

    let payload = JSON.stringify({
        title: currentTitle,
        author: currentAuthor
    });

    let requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: payload
    };

    fetch(BASE_URL, requestOptions)
        .then(() => {
            loadHandler(event);
            newDomElements.authorInput.value = '';
            newDomElements.titleInput.value = '';
        })
        .catch((err) => {
            console.error(err);
        });

    function editHandler(event) {

        if (event) {
            event.preventDefault();
        }

        let searchedTr = this.parentNode.parentNode;

        searchedId = searchedTr.id;
        newDomElements.formTitle.textContent = 'Edit FORM';


        let tds = Array.from(searchedTr.children);
        let firstTd = tds[1];
        let secondTd = tds[2];


        newDomElements.titleInput.value = firstTd.textContent;
        newDomElements.authorInput.value = secondTd.textContent;


        allDomElements.form.children[5].remove();

        let saveButton = document.createElement('button');
        saveButton.textContent = 'Save';
        saveButton.addEventListener('click', saveHandler);

        allDomElements.form.appendChild(saveButton);

        editButton.addEventListener('click', editHandler);
        deleteButton.addEventListener('click', deleteHandler);
    }

    function saveHandler(event) {
        if (event) {
            event.preventDefault();
        }

        let payload = JSON.stringify({
            title: newDomElements.titleInput.value,
            author: newDomElements.authorInput.value,
            id: searchedId
        });

        let requestOptions = {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: payload
        };

        fetch(`${BASE_URL}/${searchedId}`, requestOptions)
            .then(() => {
                loadHandler();
                newDomElements.titleInput.value = '';
                newDomElements.authorInput.value = '';
            })
            .catch((err) => {
                console.error(err);
            });


        this.remove();
        let submitButton = document.createElement('button');
        submitButton.textContent = 'Submit';
        submitButton.addEventListener('click', createHandler);
        allDomElements.form.appendChild(submitButton);

    }

    function deleteHandler(event) {

        if (event) {
            event.preventDefault();
        }

        let searchedTr = this.parentNode.parentNode;
        let searchedId = searchedTr.id;

        let requestOptions = {
            method: "DELETE",
        };


        fetch(`${BASE_URL}/${searchedId}`, requestOptions)
            .then(() => {
                let arr = Array.from(Object.values(allDomElements.tbody.querySelectorAll('tr')));
                if (arr.length === 1) {
                    page--;
                    if (page <= 0) {
                        page = 1;
                    }
                }
                loadHandler();
            })
            .catch((err) => {
                console.error(err);
            });

    }
}