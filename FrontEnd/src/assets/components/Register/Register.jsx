import React from 'react'
import '../Login/Login.css'
import { jwtDecode } from 'jwt-decode'
import {useNavigate} from 'react-router-dom'
function Register(props) {
	const navigate = useNavigate()
	function submitHandler(event){
		event.preventDefault()
		const userName = event.target.user.value
		const pass = event.target.pass.value
		const mobileNumber = event.target.mobile.value
		const Email = event.target.email.value

		let send =JSON.stringify({
			username: userName,
			password: pass,
			email: Email,
			roles: [{role_name:"USER"}],
			mobile: mobileNumber
		  }
		)

		console.log(props.user)
		async function sendData(token){
		
		
			const data = await fetch("http://localhost:8080/register",{
				method:"POST",
				headers:{"Content-Type":"application/json"},
				body:send
			}).then(async(res)=>{return await res.json()})

			let decoded = jwtDecode(data.token)
			let new_user = {}
			new_user['token'] = data.token
			new_user['email'] = Email
			new_user['mobile_number'] = mobileNumber
			new_user['userName'] = userName
			new_user['role'] = decoded.Roles
			new_user['iat'] = decoded.iat
			new_user['expiration'] = decoded.exp
	
			props.setUser(new_user)
			return navigate("/")
		}
		sendData()

		
	}
  return (
    <>
    <form className="login-wrap" onSubmit={submitHandler}>
	<div className="login-html">
		<input id="tab-1" type="radio" name="tab" className="sign-in" checked/><label htmlFor="tab-1" className="tab"></label>
		<input id="tab-2" type="radio" name="tab" className="sign-up"/><label htmlFor="tab-2" className="tab">Sign Up</label>
		<div className="login-form">
			
			<div className="sign-up-htm">
				<div className="group">
					<label htmlFor="user" className="label">Username</label>
					<input id="user" type="text" className="input"/>
				</div>
				<div className="group">
					<label htmlFor="pass" className="label">Password</label>
					<input id="pass" type="password" className="input" data-type="password"/>
				</div>
				<div className="group">
					<label htmlFor="mobile" className="label">Mobile Number</label>
					<input id="mobile" type="number" className="input"/>
				</div>
				<div className="group">
					<label htmlFor="email" className="label">Email Address</label>
					<input id="email" type="email" className="input"/>
				</div>
				<div className="group">
					<input type="submit" className="button" value="Sign Up"/>
				</div>
				<div className="hr"></div>
				<div className="foot-lnk">
          <label htmlFor="tab-1">Already Member</label>
				</div>
			</div>
		</div>
	</div>
</form>
                 

      </>
  )
}

export default Register