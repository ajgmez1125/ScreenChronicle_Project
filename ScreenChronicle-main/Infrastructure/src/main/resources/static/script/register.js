document.getElementById('registerForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const roles = formData.getAll('roles');
    const rolesString = roles.join(',');

    const userDto = {
        username: formData.get('username'),
        password: formData.get('password'),
        roles: rolesString
    };

    console.log(JSON.stringify(userDto));

    fetch('api/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userDto),
    })
        .then(response => response.text())
        .then(isRegistered => {
            console.log('Success:', isRegistered);
            const alertBox = document.getElementById('customAlert');
            const alertMessage = document.getElementById('alertMessage');
            alertMessage.textContent = isRegistered;
            if (isRegistered === 'true') {
                alertBox.setAttribute('data-type', 'success');
                alertMessage.textContent = 'User registered successfully, Redirecting to login page...';
                setTimeout(() => {
                    window.location.href = '/login';
                }, 3000);
            } else {
                alertBox.setAttribute('data-type', 'error');
                alertMessage.textContent = 'User registration failed, Please try again...';
                document.getElementById('registerForm').reset();
                setTimeout(() => {
                    alertBox.style.display = 'none';
                }, 3000);
            }
            alertBox.style.display = 'block';
        })
        .catch((error) => {
            alertBox.setAttribute('data-type', 'failure');
            alertMessage.textContent = 'User registration failed, Please try again...';
            document.getElementById('registerForm').reset();
            console.error('Error:', error);
        });
});