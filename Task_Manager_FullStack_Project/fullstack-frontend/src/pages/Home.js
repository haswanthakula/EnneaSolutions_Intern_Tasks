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
                    <Link
                      className="btn btn-primary mx-2"
                      to={`/edittask/${task.id}`}
                    >
                      Edit
                    </Link>
                    <button
                      className="btn btn-danger mx-2"
                      onClick={() => deleteTask(task.id)}
                    >
                      Delete
                    </button>
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
