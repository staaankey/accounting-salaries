import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function Users() {
    const [users, setUsers] = useState([]);

    useEffect(()=> {
        loadUsers();
    }, [])

    const loadUsers = async()=> {
        const result =await axios.get("http://localhost:8080/findAllUsers");
        setUsers(result.data);
        console.log(result.data);
    }

  return (
    <div className='container'>
        <div className='py-4'>
        <table class="table border shadow">
            <thead>
                <tr>
                <th scope="col">#</th>
                <th scope="col">Login</th>
                <th scope="col">Password</th>
                </tr>
            </thead>
            <tbody>

                {
                    users.map((user, index)=>(
                        <tr>
                        <th scope="row" key={index}>{index+1}</th>
                        <td>{user.login}</td>
                        <td>{user.password}</td>
                        <td>
                            <button className='btn btn-primary mx-2'>View</button>
                            <button className='btn btn-outline mx-2'>Edit</button>
                            <button className='btn btn-danger mx-2'>Delete</button>
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
