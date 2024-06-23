import React, { useEffect, useState } from 'react'
import {Row,Col,Divider} from 'antd'
import './Home.css'
const Home = (props) => {
  const [timeStudied,setTimeStudied] = useState({});
  useEffect(()=>{
      async function fetchUserInfo(){
        let res = await fetch(

          "http://localhost:8080/api/getTimeStudied",{
            headers:{"Authorization":`Bearer ${props.user.token}`}
          }
        ).then(async(res)=>{return await res.json()})

        console.log(props)

        setTimeStudied(res);
      }

      fetchUserInfo()
  },[])

  return (
    // <div className="wrapper">
    //         <div className="display">
    //             <div className="user_details">
                    
    //                 <div className="profile" style={{margin:"auto",height:'fit-content',width:'70%',position:'relative',top:'10%'}}>

                    

    //                   <img src='https://media.istockphoto.com/id/1434414228/photo/stern-sad-cat-isolated-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=7AURwT9S7oG_dDkIDHvag4oyy-bAZV7GNZPNBzf-nwE=' style={{width:"70%",height:"20vh",borderRadius:"40%"}}/>
    //                 </div>

    //                 <div className="details" style={{position:"relative",top:"15%",display:'grid',gridTemplateColumns:"1fr 1fr"}}>
                     
    //                    <div className="labels" style={{padding:"20%"}}>
    //                     <div className="item username">Name :</div>
    //                     <div className="item email">Email :</div>
    //                     <div className="item mobileNumber">Mobile: </div>
    //                    </div>

    //                    <div className="values" style={{padding:"20%",paddingLeft:0,textAlign:'left'}}>
    //                       <div className="item username_value">{props.user.userName}</div>
    //                       <div className="item email_value">{props.user.email}</div>
    //                       <div className="item mobile_value">{props.user.mobile_number}</div>
    //                    </div>

    //                 </div>
                      
    //               </div>

    //             <div className='user_stat'>
    //               {timeStudied.time_studied}
    //             </div>

                
    //         </div>

    //   </div>

    <>
    <div className="wrapper">
      <div className="display">
        <Row>
          <Col span={10} style={{height:"70vh"}}>
              <Row gutter={[16, 16]} style={{ height: '50%', display:'flex', justifyContent:'center', alignItems:'center' }}>
                <img src='https://media.istockphoto.com/id/1434414228/photo/stern-sad-cat-isolated-on-white-background.webp?b=1&s=170667a&w=0&k=20&c=7AURwT9S7oG_dDkIDHvag4oyy-bAZV7GNZPNBzf-nwE=' style={{width:"40%",height:"20vh",borderRadius:"40%"}}/>
              </Row>
              
              <Row gutter={[16, 16]} style={{ height: '30%', display:'flex', justifyContent:'center', alignItems:'center',paddingLeft:"20%" }}>

                <Row style={{width:"100%",display:'flex',justifyContent:'flex-start',alignItems:'center'}}>

                  <Row style={{width:"100%"}}>  

                      <Col span={9} className='item'>Username: </Col>
                      <Col span={9} className='item'>{props.user.userName}</Col>

                  </Row>

                </Row>

                <Row style={{width:"100%",display:'flex',justifyContent:'flex-start',alignItems:'center'}}>

                <Row style={{width:"100%"}}>  

                    <Col span={9} className='item'>Email: </Col>
                    <Col span={9} className='item'>{props.user.email}</Col>

                </Row>
               
                </Row>


                <Row style={{width:"100%",display:'flex',justifyContent:'flex-start',alignItems:'center'}}>
                  <Row style={{width:"100%"}}>  
                      <Col span={9} className='item'>Mobile</Col>
                      <Col span={9} className='item'>{props.user.mobile_number}</Col>
                  </Row>
                  </Row>
              </Row>
          </Col>

          <Col span={4}>
            
          </Col>

          
          <Col span={10}>
            
           
          <Row style={{width:"100%",display:'flex',justifyContent:'flex-start',alignItems:'center',top:"15%",position:'relative'}}>
                  <Row style={{width:"100%"}}>  
                      <Col span={9} className='item'>Time Studied: </Col>
                      <Col span={9} className='item'>{timeStudied.time_studied} mins</Col>
                  </Row>
          </Row>
          
           
          </Col>
        </Row>
      </div>
    </div>
    </>

    
  )
}

export default Home