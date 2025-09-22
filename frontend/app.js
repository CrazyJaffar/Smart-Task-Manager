const API_URL = 'http://localhost:8080/tasks';

function fetchTasks() {
    fetch(API_URL)
        .then(res => res.json())
        .then(tasks => {
            const tasksDiv = document.getElementById('tasks');
            tasksDiv.innerHTML = '';
            tasks.forEach(task => {
                const div = document.createElement('div');
                div.className = 'task';
                div.innerHTML = `<span class="task-title">${task.title}</span> <span>${task.description}</span>
                    <span class="task-actions">
                        <button onclick="editTask(${task.id}, '${task.title}', '${task.description}', ${task.completed})">Edit</button>
                        <button onclick="deleteTask(${task.id})">Delete</button>
                    </span>`;
                tasksDiv.appendChild(div);
            });
        });
}

function addTask() {
    const title = document.getElementById('task-title').value;
    const desc = document.getElementById('task-desc').value;
    fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, description: desc, completed: false })
    }).then(() => {
        document.getElementById('task-title').value = '';
        document.getElementById('task-desc').value = '';
        fetchTasks();
    });
}

function editTask(id, title, description, completed) {
    const newTitle = prompt('Edit title:', title);
    const newDesc = prompt('Edit description:', description);
    if (newTitle !== null && newDesc !== null) {
        fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: newTitle, description: newDesc, completed })
        }).then(fetchTasks);
    }
}

function deleteTask(id) {
    fetch(`${API_URL}/${id}`, { method: 'DELETE' })
        .then(fetchTasks);
}

document.addEventListener('DOMContentLoaded', fetchTasks);
body { font-family: Arial, sans-serif; background: #f4f4f4; margin: 0; padding: 0; }
h1 { text-align: center; margin-top: 20px; }
#task-form { display: flex; justify-content: center; margin: 20px; gap: 10px; }
#tasks { max-width: 600px; margin: 20px auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
.task { display: flex; justify-content: space-between; align-items: center; padding: 10px 0; border-bottom: 1px solid #eee; }
.task:last-child { border-bottom: none; }
.task-title { font-weight: bold; }
.task-actions button { margin-left: 5px; }

