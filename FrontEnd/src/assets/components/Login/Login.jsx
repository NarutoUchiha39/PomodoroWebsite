import { jwtDecode } from 'jwt-decode';
import {Navigate,redirect,useNavigate} from 'react-router-dom'
import './Login.css'
function Login(props) {

  let navigate = useNavigate()

  async function handleSubmit(event){

    event.preventDefault()
    const email = event.target.user.value;
    const password = event.target.pass.value;
    
    const res = await fetch("http://localhost:8080/login",
    {
      method:"POST",
      headers:{
        'Content-Type': 'application/json'
      },
      body:JSON.stringify({
        "email":email,
        "password":password
      })
    }).then(async(res)=>{return await res.json()})
    if(res.status == "FORBIDDEN"){
      alert("User Does not Exists or wrong password !!")
    }else{
      const decoded = jwtDecode(res.token)

      let new_user = {}
      new_user['token'] = res.token
      new_user['email'] = decoded.email
      new_user['mobile_number'] = decoded.mobileNumber
      new_user['userName'] = decoded.userName
      new_user['role'] = decoded.Roles
      new_user['iat'] = decoded.iat
      new_user['expiration'] = decoded.exp

      props.setUser(new_user)
      return navigate("/")
    }
    


  }

  return (
    <>
            {
              props.user.token
            }
            <form className="login-wrap" onSubmit={handleSubmit}>
                <div className="login-html">
                  <input id="tab-1" type="radio" name="tab" className="sign-in" /><label htmlFor="tab-1" className="tab">Sign In</label>
                  <input id="tab-2" type="radio" name="tab" className="sign-up"/><label htmlFor="tab-2" className="tab"></label>
                  <div className="login-form">
                    <div className="sign-in-htm">
                      <div className="group">
                        <label htmlFor="user" className="label">Email</label>
                        <input id="user" type="text" className="input"/>
                      </div>
                      <div className="group">
                        <label htmlFor="pass" className="label">Password</label>
                        <input id="pass" type="password" className="input" data-type="password"/>
                      </div>
                      <div className="group">
                        <input id="check" type="checkbox" className="check" />
                        <label htmlFor="check"><span className="icon"></span> Keep me Signed in</label>
                      </div>
                      <div className="group">
                        <input type="submit" className="button" value="Sign In"/>
                      </div>
                      <div className="hr"></div>
                      <div className="foot-lnk">
                        <a href="#forgot">Forgot Password?</a>
                      </div>
                    </div>
                
                </div>
              </div>
          </form>

  </>
  )
}

export default Login