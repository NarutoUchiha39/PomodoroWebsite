import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './assets/components/Login/Login';
import Register from './assets/components/Register/Register';
import Website_Layout from './assets/components/Layout/Website_Layout'; // Import the Home component
import AuthenticatedRoute from './assets/components/AuthenticatedRoutes/AuthenticatedRoute';
import React, { useState } from 'react';
import Home from './assets/components/Home/Home';
import { Layout, Menu, Button, theme } from 'antd';
import UploadSongs from './assets/components/uploadSongs/uploadSongs';
import ListSong from './assets/components/ListSong/ListSong';

import StudyRoom from './assets/components/StudyRoom/StudyRoom';
import EditSongs from './assets/components/EditSongs/EditSongs';
import UserStats from './assets/components/UserStats/UserStats';
import SelectSong from './assets/components/SelectSong/SelectSong';



const { Header, Sider, Content } = Layout;
function App() {
  const [user, setUser] = useState({
    token: null,
    iat: null,
    expiration: null,
    userName: null,
    mobile_number: null,
    email: null,
    role: null
  });

  return (   
          
          <Routes>
            {/* http://localhost:8080/api/updateTimer/23 */}
              <>
                
                <Route element={<Login user={user} setUser={setUser}/>} path="/Login" />
                <Route element={<Register user={user} setUser={setUser}/>} path="/Register" />

                <Route  path="/"
                element={<AuthenticatedRoute loggedIn={user}>
                                <Website_Layout user={user} element={Home}/>
                          </AuthenticatedRoute>
                        }/>

                <Route  path="/UploadSong"
                element={<AuthenticatedRoute loggedIn={user}>
                                <Website_Layout user={user} element={UploadSongs}/>
                          </AuthenticatedRoute>
                        }/>

              <Route  path="/EditSong/:id"
                element={<AuthenticatedRoute loggedIn={user}>
                                <Website_Layout user={user} element={EditSongs}/>
                          </AuthenticatedRoute>
                        }/>


                <Route  path="/ListSongs"
                element={<AuthenticatedRoute loggedIn={user}>
                                <Website_Layout user={user} element={ListSong}/>
                          </AuthenticatedRoute>
                        }/>
                    <Route path="/StudyRoom"
          element={<AuthenticatedRoute loggedIn={user}>
            <Website_Layout user={user} element={StudyRoom} />
          </AuthenticatedRoute>
          } />
                    <Route path="/UserStats"
          element={<AuthenticatedRoute loggedIn={user}>
            <Website_Layout user={user} element={UserStats} />
          </AuthenticatedRoute>
          } />

<Route path="/SetTimer"
          element={<AuthenticatedRoute loggedIn={user}>
            <Website_Layout user={user} element={SelectSong} />
          </AuthenticatedRoute>
          } />
                   
                
              </>

          </Routes>

  );
}

export default App;
