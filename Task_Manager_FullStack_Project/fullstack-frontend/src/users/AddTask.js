import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddTask() {
  let navigate = useNavigate();

  const [task, setTask] = useState({
    taskName: "",
    description: "",
  });

  const [tasks, setTasks] = useState([]);
  const [errors, setErrors] = useState({});

  const { taskName, description } = task;

  useEffect(() => {
    loadTasks();
  }, []);

  const loadTasks = async () => {
    try {
      const result = await axios.get("http://localhost:8080/tasks");
      setTasks(result.data);
    } catch (error) {
      console.error("Error loading tasks", error);
    }
  };

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setTask({ ...task, [name]: value });
    
    // Clear specific field error when user starts typing
    if (errors[name]) {
      setErrors({ ...errors, [name]: "" });
    }
  };

  const validateForm = () => {
    const newErrors = {};

    // Check for empty fields
    if (!taskName.trim()) {
      newErrors.taskName = "Task Name is required";
    }

    if (!description.trim()) {
      newErrors.description = "Task Description is required";
    }

    // Check for duplicate task names
    const isDuplicate = tasks.some(
      existingTask => existingTask.taskName.trim().toLowerCase() === taskName.trim().toLowerCase()
    );

    if (isDuplicate) {
      newErrors.taskName = "A task with this name already exists";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    
    // Validate form before submission
    if (validateForm()) {
      try {
        await axios.post("http://localhost:8080/task", task);
        navigate("/");
      } catch (error) {
        console.error("Error adding task", error);
        alert("Failed to add task. Please try again.");
      }
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Add New Task</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="TaskName" className="form-label">
                Task Name
              </label>
              <input
                type="text"
                className={`form-control ${errors.taskName ? 'is-invalid' : ''}`}
                placeholder="Enter task name"
                name="taskName"
                value={taskName}
                onChange={(e) => onInputChange(e)}
              />
              {errors.taskName && (
                <div className="invalid-feedback">{errors.taskName}</div>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="Description" className="form-label">
                Task Description
              </label>
              <textarea
                className={`form-control ${errors.description ? 'is-invalid' : ''}`}
                placeholder="Enter task description"
                name="description"
                value={description}
                onChange={(e) => onInputChange(e)}
              ></textarea>
              {errors.description && (
                <div className="invalid-feedback">{errors.description}</div>
              )}
            </div>
            <div className="text-center">
              <button type="submit" className="btn btn-outline-primary">
                Add Task
              </button>
              <Link className="btn btn-outline-danger mx-2" to="/">
                Cancel
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
