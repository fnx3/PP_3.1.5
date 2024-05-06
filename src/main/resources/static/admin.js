let table = []
let currentUser = "";

function getAllUsers() {
    fetch("/api/admin")
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
    let temp = "";

    for (let user of table) {
        temp += "<tr>"
        temp += "<td>" + user.id + "</td>"
        temp += "<td>" + user.name + "</td>"
        temp += "<td>" + user.age + "</td>"
        temp += "<td>" + user.roles.map(role => role.name).join(" ") + "</td>"
        temp += "<td>" + user.password + "</td>"
        temp += "<td>" + `<a onclick='showEditModal(${user.id})' class="btn btn-sm btn-primary" id="edit">Edit</a>` + "</td>"
        temp += "<td>" + `<a onclick='showDeleteModal(${user.id})' class="btn btn-sm btn-danger" id="delete">Delete</a>` + "</td>"
        temp += "</tr>"

        document.getElementById('allUsersBody').innerHTML = temp
    }
}

fetch("/api/admin/current")
    .then(response => response.json())
    .then(data => {
        currentUser = data
        console.log(data)
        document.getElementById("headAdminName").innerText = currentUser.name
        document.getElementById("headAdminRoles").innerText = currentUser.roles
            .map(role => role.name).join(" ")
        showUser(currentUser)
    }).catch(error => {
        console.error("Пользователь с таким id не был найден" + error)
})

function showUser(user) {
    let temp = "";

    temp += "<tr>"
    temp += "<td>" + user.id + "</td>"
    temp += "<td>" + user.name + "</td>"
    temp += "<td>" + user.age + "</td>"
    temp += "<td>" + user.roles.map(role => role.name).join(" ") + "</td>"
    temp += "<td>" + user.password + "</td>"
    temp += "</tr>"

    document.getElementById('oneUserBody').innerHTML = temp
}
