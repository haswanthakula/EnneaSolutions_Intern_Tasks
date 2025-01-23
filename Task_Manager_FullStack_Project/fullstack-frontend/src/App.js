import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddTask from "./users/AddTask";
import EditTask from "./users/EditTask";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/addtask" element={<AddTask />} />
          <Route exact path="/edittask/:id" element={<EditTask />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
