import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddDepartment() {
  let navigate = useNavigate();

  const [department, setDepartment] = useState({
    parentId: "",
    name: "",
  });

  const {parentId, name} = department;

  const onInputChange = (e) => {
    setDepartment({ ...department, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/save", department);
    navigate("/departments");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Додати відділ</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Ідентифікатор головного підрозділу
              </label>
              <input
                type={"number" }
                pattern="[0-9]*"
                className="form-control"
                placeholder="Задайте ідентифікатор підрозділу"
                name="parentId"
                value={parentId}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Назва
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Задайте назву"
                name="name"
                value={name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/departments">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}