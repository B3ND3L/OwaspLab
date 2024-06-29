let currentUser = null;

function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost/api/login', true);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            currentUser = JSON.parse(xhr.responseText);
            document.getElementById('loginPage').style.display = 'none';
            document.getElementById('profilePage').style.display = 'block';
            document.getElementById('messagesPage').style.display = 'block';
            document.getElementById('userNameDisplay').textContent = currentUser.name;
            getUserPoints(currentUser.id);
            displayMessages();
        } else if (xhr.readyState === 4) {
            alert('Invalid username or password');
        }
    };

    xhr.send(JSON.stringify({ name: username, password: password }));
}

function logout() {
    currentUser = null;
    document.getElementById('loginPage').style.display = 'block';
    document.getElementById('profilePage').style.display = 'none';
    document.getElementById('messagesPage').style.display = 'none';
}

function getUserPoints(userId) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `http://localhost/api/user/${userId}/points`, true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const points = JSON.parse(xhr.responseText).points;
            document.getElementById('userPointsDisplay').textContent = points;
        }
    };

    xhr.send();
}

function displayMessages() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `http://localhost/api/user/${currentUser.id}/messages`, true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const messages = JSON.parse(xhr.responseText);
            const messagesList = document.getElementById('messagesList');
            messagesList.innerHTML = '';

            messages.forEach(m => {
                const fromUser = m.from === currentUser.id ? 'You' : `User ${m.from}`;
                const toUser = m.to === currentUser.id ? 'You' : `User ${m.to}`;
                const messageElement = document.createElement('div');
                messageElement.className = 'message';
                messageElement.textContent = `${fromUser} to ${toUser}: ${m.message}`;
                messagesList.appendChild(messageElement);
            });
        }
    };

    xhr.send();
}

function sendMessage(toUserId, message) {
    const xhr = new XMLHttpRequest();
    xhr.open('PUT', `http://localhost/api/user/${currentUser.id}/message`, true);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            displayMessages();
        }
    };

    xhr.send(JSON.stringify({ to: toUserId, message: message }));
}

function addPoints(userId, points) {
    const xhr = new XMLHttpRequest();
    xhr.open('PUT', `http://localhost/api/user/${userId}/addPoints/${points}`, true);

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            getUserPoints(userId);
        }
    };

    xhr.send();
}
