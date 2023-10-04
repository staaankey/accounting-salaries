import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewDepartment() {
  const [department, setDepartment] = useState({
    id: "",
    parent_id: "",
    name: "",
  });

  useEffect(() => {
    loadDepartment();
  }, []);

  const loadDepartment = async () => {
    const result = await axios.get(`http://localhost:8080/findById?departmentId=0`); //${id}
    setDepartment(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Інформація про підрозділ</h2>

          <div className="card">
            <div className="card-header">
              <tr>
              Ідентифікатор підрозділу: {department.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Назва: </b>
                  {department.name}
                </li>
                <li className="list-group-item">
                  <b>Співробітники:</b>
                  {department.parent_id}
                </li>
              </ul>
              </tr>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}