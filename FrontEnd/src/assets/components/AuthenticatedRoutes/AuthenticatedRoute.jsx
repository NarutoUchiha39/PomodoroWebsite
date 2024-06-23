import React from 'react'
import {Navigate} from 'react-router-dom'
const AuthenticatedRoute = (props) => {
  
  if( !(props.loggedIn.token) ){
    
    return <Navigate to="/Login" replace/>
  }
  return (
    props.children
  )
}

export default AuthenticatedRoute