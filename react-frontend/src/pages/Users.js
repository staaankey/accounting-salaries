import React, { useEffect, useState } from 'react'
import axios from 'axios'

export default function Home() {
    const [users, setUsers] = useState([]);

    useEffect(()=> {
        loadUsers();
    }, [])

    const loadUsers = async()=> {
        const result =await axios.get("http://localhost:8080/find");
        setUsers(result.data);
        console.log(result.data);
    }

  return (
    <div className='container'>
        <div className='py-4'>
        <table class="table border shadow">
            <thead>
                <tr>
                <th scope="col">Ідентифікатор</th>
                <th scope="col">Логін</th>
                <th scope="col">Пароль</th>
                </tr>
            </thead>
            <tbody>

                {
                    users.map((user, index)=>(
                        <tr>
                        <th scope="row" key={index}>{index+1}</th>
                        <td>{user.login}</td>
                        <td>{user.password}</td>
                        </tr>
                    ))
                }
                
            </tbody>
            </table>
        </div>
    </div>
  )
}
