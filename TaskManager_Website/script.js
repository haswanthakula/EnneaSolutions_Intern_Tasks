function add_butt() {
  const inputField = document.getElementById("inp");
  const taskText = inputField.value.trim();

  if (taskText === "") {
    alert("Enter a task");
    return;
  }

  const taskContainer = document.createElement("div");
  taskContainer.className = "task-container";

  const checkbox = document.createElement("input");
  checkbox.type = "checkbox";
  checkbox.className = "task-checkbox";
  checkbox.addEventListener("change", function () {
    if (checkbox.checked) {
      taskTextElement.classList.add("completed");
    } else {
      taskTextElement.classList.remove("completed");
    }
  });

  const tasks = document.querySelectorAll(".task-text");
  for (const task of tasks) {
    if (task.textContent === taskText) {
      alert("Task already exists!");
      return;
    }
  }

  const taskTextElement = document.createElement("span");
  taskTextElement.className = "task-text";
  taskTextElement.textContent = taskText;

  const deleteButton = document.createElement("button");
  deleteButton.className = "delete-button";
  deleteButton.innerHTML = '<i class="bi bi-trash"></i>';
  deleteButton.addEventListener("click", function () {
    taskContainer.remove();
  });

  taskContainer.appendChild(checkbox);
  taskContainer.appendChild(taskTextElement);
  taskContainer.appendChild(deleteButton);

  document.getElementById("html_div").appendChild(taskContainer);

  inputField.value = "";
}
