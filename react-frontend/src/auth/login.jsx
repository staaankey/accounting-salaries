import {  useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
function Login() {
   
    const [login, setlogin] = useState("");
    const [password, setPassword] = useState("");

    async function log(event) {
        event.preventDefault();
        try {
          await axios.post("http://localhost:8080/login", {
            login: login,
            password: password,
            }).then((res) => 
            {
             console.log(res.data);
             
             if (res.data.message == "login not exits") 
             {
               alert("login not exits");
             } 
             else if(res.data.message == "Login Success")
             { 
                
                throw console.error();
             } 
              else 
             { 
                alert("Incorrect login and Password not match");
             }
          }, fail => {
           console.error(fail); // Error!
    });
        }
 
         catch (err) {
          alert(err);
        }
      
      }
    return (
       <div>
            <div class="container">
            <div class="row">
                <h2>Login</h2>
             <hr/>
             </div>
             <div class="row">
             <div class="col-sm-6">
 
            <form>
        <div class="form-group">
          <label>login</label>
          <input type="login"  class="form-control" id="login" placeholder="Enter Name"
          
          value={login}
          onChange={(event) => {
            setlogin(event.target.value);
          }}
          
          />
        </div>
        <div class="form-group">
            <label>password</label>
            <input type="password"  class="form-control" id="password" placeholder="Enter Fee"
            
            value={password}
            onChange={(event) => {
              setPassword(event.target.value);
            }}
            
            />
          </div>
                  <button type="submit" class="btn btn-primary" onClick={login}>Login</button>
              </form>
            </div>
            </div>
            </div>
     </div>
    );
  }
  
  export default Login;