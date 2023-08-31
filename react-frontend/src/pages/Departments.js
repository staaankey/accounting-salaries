import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function Department() {
    const [departments, setDepartments] = useState([]);

    useEffect(()=> {
        loadDepartments();
    }, [])

    const loadDepartments = async()=> {
        const result =await axios.get("http://localhost:8080/all");
        setDepartments(result.data);
        console.log(result.data);
    }

    const deleteDepartment=async(id)=>{
        await axios.delete(`http://localhost:8080/delete?departmentId=${id}`);
        loadDepartments()
    }

  return (
    <div className='container'>
        <div className='py-4'>
        <table class="table border shadow">
            <thead>
                <tr>
                <th scope="col">Ідентифікатор</th>
                <th scope="col">Назва</th>
                <th scope="col">Ідентифікатор головного підрозділу</th>
                </tr>
            </thead>
            <tbody>

                {
                    departments.map((departments, index)=>(
                        <tr>
                        <th scope="row" key={index}>{index+1}</th>
                        <td>{departments.name}</td>
                        <td>{departments.parentId}</td>
                        <td>
                            <button className='btn btn-primary mx-2'>View</button>
                            <button className='btn btn-outline mx-2'>Edit</button>
                            <button className='btn btn-danger mx-2' onClick={()=> deleteDepartment(departments.id)}>Delete</button>
                        </td>
                        </tr>
                    ))
                }
                
            </tbody>
            </table>
        </div>
    </div>
  )
}