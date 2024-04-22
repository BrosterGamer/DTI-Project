from flask import Flask, jsonify, request

app = Flask(_name_)

tasks = [
    {'id': 1, 'title': 'Task 1', 'description': 'Description for Task 1', 'completed': False},
    {'id': 2, 'title': 'Task 2', 'description': 'Description for Task 2', 'completed': True}
]

# Route to get all tasks
@app.route('/tasks', methods=['GET'])
def get_tasks():
    return jsonify({'tasks': tasks})

# Route to create a new task
@app.route('/tasks', methods=['POST'])
def create_task():
    new_task = {
        'id': len(tasks) + 1,
        'title': request.json['title'],
        'description': request.json.get('description', ''),
        'completed': False
    }
    tasks.append(new_task)
    return jsonify(new_task), 201

# Route to update an existing task
@app.route('/tasks/<int:task_id>', methods=['PUT'])
def update_task(task_id):
    task = next((task for task in tasks if task['id'] == task_id), None)
    if task:
        task.update(request.json)
        return jsonify(task)
    return jsonify({'error': 'Task not found'}), 404

# Route to delete a task
@app.route('/tasks/<int:task_id>', methods=['DELETE'])
def delete_task(task_id):
    global tasks
    tasks = [task for task in tasks if task['id'] != task_id]
    return jsonify({'message': 'Task deleted successfully'})

if _name_ == '_main_':
    app.run(debug=True)