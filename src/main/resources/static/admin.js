let table = []
let currentUser = '';
let deleteModal = new bootstrap.Modal(document.getElementById('deleteModal') );
let editModal = new bootstrap.Modal(document.getElementById('editModal') );

function getAllUsers() {
    fetch('/api/admin')
        .then(response => response.json() )
        .then(data => {
            if (data.length > 0) {
                table = data;
            } else {
                table = [];
            }
            showUsers(table)
            console.log(data)
        })
        .catch(error => {
        console.error(error)
    })
}

getAllUsers();

function showUsers(table) {
    let temp = '';

    for (let user of table) {
        temp += `
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.roles.map(role => role.name).join(" ")}</td>
                <td>${user.password}</td>
                <td><a onclick='showEditModal(${user.id})' class="btn btn-sm btn-primary" id="edit">Edit</a></td>
                <td><a onclick='showDeleteModal(${user.id})' class="btn btn-sm btn-danger" id="delete">Delete</a></td>
            </tr>
        `;
    }

    document.getElementById('allUsersBody').innerHTML = temp;
}

fetch('/api/admin/current')
    .then(response => response.json())
    .then(data => {
        currentUser = data
        console.log(data)
        document.getElementById('headAdminName').innerText = currentUser.name
        document.getElementById('headAdminRoles').innerText = currentUser.roles
            .map(role => role.name).join(' ')
        showUser(currentUser)
    }).catch(error => {
        console.error('Пользователь с таким id не был найден' + error)
})

function showUser(user) {
    const roles = user.roles.map(role => role.name).join(" ");

    document.getElementById('oneUserBody').innerHTML = `
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${roles}</td>
            <td>${user.password}</td>
        </tr>
    `;
}

function roleUsers(event) {
    let roles = [];
    let select = document.querySelector(event);
    for (let i = 0; i < select.options.length; i++) {
        if (select.options[i].selected) {
            roles.push({
                id: select.options[i].value,
                name: select.options[i].value === '1' ? 'ROLE_USER' : 'ROLE_ADMIN'
            })
        }
    }
    return roles;
}

document.getElementById('newUser').addEventListener('submit', newUser);

function newUser(form) {
    form.preventDefault();

    let newUser = new FormData(form.target);
    let user = {
        id: null,
        name: newUser.get('name'),
        age: newUser.get('age'),
        roles: roleUsers('#roles'),
        password: newUser.get('password')
    };

    fetch('api/admin', {
        method: 'POST',
        body: JSON.stringify(user),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(() => {
            getAllUsers();
            form.target.reset();
        });

    const triggerE1 = document.querySelector('#tableButton');
    bootstrap.Tab.getInstance(triggerE1).show();
}

function showDeleteModal(id) {
    document.getElementById('closeDeleteModal').setAttribute('onclick', () => {
        deleteModal.hide();
        document.getElementById('deleteUser').reset();
    });

    let request = new Request("/api/admin/" + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    fetch(request).then(response => response.json()).then(deleteUser => {

        document.getElementById('idDel').setAttribute('value', deleteUser.id);
        document.getElementById('nameDel').setAttribute('value', deleteUser.name);
        document.getElementById('ageDel').setAttribute('value', deleteUser.age);

        document.getElementById('passwordDel').setAttribute('value', deleteUser.password);

        let roles = deleteUser.roles;
        if (roles.includes("USER") ) {
            document.getElementById('rolesDel1').setAttribute('selected', 'true');
        }
        if (roles.includes("ADMIN") ) {
            document.getElementById('rolesDel2').setAttribute('selected', 'true');
        }

        deleteModal.show();
    });

    let isDelete = false;
    document.getElementById('deleteUser').addEventListener('submit', event => {
        event.preventDefault();
        if (!isDelete) {
            isDelete = true;
            let request = new Request("/api/admin/" + id, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            fetch(request).then(() => {
                getAllUsers();
            });
            document.getElementById('deleteUser').reset();
        }

        deleteModal.hide();
    });
}

function showEditModal(id) {
    document.getElementById('closeEditModal').setAttribute('onclick', () => {
        document.getElementById('editUser').reset();
        editModal.hide();
    })

    let request = new Request("/api/admin/" + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    });

    fetch(request).then(response => response.json() ).then(editUser => {

        document.getElementById('editId').setAttribute('value', editUser.id);
        document.getElementById('editName').setAttribute('value', editUser.name);
        document.getElementById('editAge').setAttribute('value', editUser.age);
        document.getElementById('editPassword').setAttribute('value', editUser.password);

        let roles = editUser.roles;
        if (roles.includes("USER") ) {
            document.getElementById('rolesEdit1').setAttribute('selected', 'true');
        }
        if (roles.includes("ADMIN") ) {
            document.getElementById('rolesEdit2').setAttribute('selected', 'true');
        }

        editModal.show();
    });

    document.getElementById('editUser').addEventListener('submit', editUser );

}

function editUser(event) {
    event.preventDefault();

    let editUser = new FormData(event.target);
    let user = {
        id: editUser.get('id'),
        name: editUser.get('name'),
        age: editUser.get('age'),
        roles: roleUsers('#editRoles'),
        password: editUser.get('password')
    };

    fetch('api/admin', {
        method: 'PUT',
        body: JSON.stringify(user),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then( () => {
            event.target.reset();
            getAllUsers();
            editModal.hide();
        })

}


