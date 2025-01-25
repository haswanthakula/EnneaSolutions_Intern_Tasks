import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  const [tasks, setTasks] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadTasks();
  }, []);

  const loadTasks = async () => {
    const result = await axios.get("http://localhost:8080/tasks");
    setTasks(result.data);
  };

  const deleteTask = async (id) => {
    await axios.delete(`http://localhost:8080/task/${id}`);
    loadTasks();
  };

  const toggleComplete = async (task) => {
    try {
      const updatedTask = {
        ...task,
        completed: !task.completed
      };
      await axios.put(`http://localhost:8080/task/${task.id}`, updatedTask);
      loadTasks();
    } catch (error) {
      console.error("Error updating task completion:", error);
    }
  };

  return (
    <div className="container">
      <div className="py-4">
        {tasks.length === 0 ? (
          <div className="text-center">
            <h2>No Tasks Available</h2>
            <p>Click 'Add Task' to create your first task!</p>
            <Link to="/addtask" className="btn btn-primary mt-3">
              Add Task
            </Link>
          </div>
        ) : (
          <table className="table border shadow">
            <thead>
              <tr>
                <th scope="col">S.N</th>
                <th scope="col">Task Name</th>
                <th scope="col">Description</th>
                <th scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              {tasks.map((task, index) => (
                <tr key={task.id}>
                  <th scope="row">{index + 1}</th>
                  <td>{task.taskName}</td>
                  <td>{task.description}</td>
                  <td>
                    <div className="d-flex align-items-center justify-content-center gap-2">
                      <button
                        className={`btn btn-sm ${task.completed ? 'btn-success' : 'btn-warning'}`}
                        onClick={() => toggleComplete(task)}
                        style={{ minWidth: '90px' }}
                      >
                        {task.completed ? 'Completed' : 'Incomplete'}
                      </button>
                      <Link
                        className="btn btn-primary btn-sm"
                        to={`/edittask/${task.id}`}
                      >
                        Edit
                      </Link>
                      <button
                        className="btn btn-danger btn-sm"
                        onClick={() => deleteTask(task.id)}
                      >
                        Delete
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}
