const fetchData = async () => {
    try {
        const response = await fetch("localhost:8080/api/users/{id}")
        const user = await response.json();

        // Получение данных user
        document.getElementById('user-id').innerText = user.id
        document.getElementById('user-name').innerText = user.name
        document.getElementById('user-age').innerText = user.age
        document.getElementById('user-password').innerText = user.password

        const rolesElement = document.getElementById('user-roles')
        rolesElement.innerHTML = ''
        user.roles.forEach(role => {
            const roleElement = document.createElement('div')
            roleElement.innerText = role.name
            rolesElement.appendChild(roleElement)
        })
    } catch (error) {
        console.log('Error fetching data:', error)
    }
}

fetchData().then( () => {
    console.log('Data fetched successfully')
}).catch( error => {
    console.log('Error fetching data', error)
})