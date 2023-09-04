import React, { useEffect, useState } from 'react'
import axios from 'axios'
import Nav from 'react-bootstrap/Nav';


export default function Users() {
    const [users, setUsers] = useState([]);


    const deleteUser=async(id)=>{
        await axios.delete(`http://localhost:8080/deleteUser?userId=${id}`);
        loadUsers()
    }

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
        <table className="table border shadow">
            <thead>
                <tr>
                <th scope="col">Ідентифікатор</th>
                <th scope="col">Логін</th>
                <th scope="col">Пароль</th>
                <ul class="btn btn-primary mx-2">
                    <Nav.Link href="/adduser">Додати користувача</Nav.Link>
                </ul>
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
                            <button className='btn btn-primary mx-2'>Переглянути</button>
                            <button className='btn btn-outline mx-2'>Редагувати</button>
                            <button className='btn btn-danger mx-2' onClick={()=> deleteUser(user.id)}>Видалити</button>
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
