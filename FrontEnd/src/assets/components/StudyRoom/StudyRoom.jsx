import React, { useState, useEffect } from 'react';
import './StudyRoom.css'
const StudyRoom = (props) => {
  const [studyDuration, setStudyDuration] = useState(props.timer.timer); // Study duration in minutes
  const [breakDuration, setBreakDuration] = useState(props.timer.break_timer); // Break duration in minutes
  const [timeLeft, setTimeLeft] = useState(studyDuration * 60); // Initial time left in seconds
  const [timerRunning, setTimerRunning] = useState(false);
  const [isStudySession, setIsStudySession] = useState(true); // Initial session is study session
  // const [totalTimerRunning, setTotalTimerRunning]=useState(false);
  const [timeStudied, setTimeStudied]=useState(0)
  const [playSong,toggleSong] = useState(null)

  useEffect(()=>{
    toggleSong(new Audio(`https://firebasestorage.googleapis.com/v0/b/pomodorowebsite-cab09.appspot.com/o/${props.song.url}?alt=media&token=${props.song.url}`))
  },[])

  useEffect(() => {

    async function updateUser(){
      let res = await fetch(`http://localhost:8080/api/updateTimer/${props.timer.timer}`,{
        method:"GET",
        headers:{"Authorization":`Bearer ${props.user.token}`}
      }
      ).then(async(res)=>{return await res.json()})

      console.log(res)
    }



    const intervalId = setInterval(() => {
      if (timerRunning) {
        const nextIsStudySession = !isStudySession;
        setTimeLeft(prevTimeLeft => {
          
          if (prevTimeLeft <= 0) {
            // Toggle session
            
            setIsStudySession(nextIsStudySession);
        
            // Calculate time left based on the session
            const nextTimeLeft = nextIsStudySession ? studyDuration * 60 : breakDuration * 60;
            // Trigger beep sound
            playBeep();
            if(!nextIsStudySession){
              console.log("hi")
                updateUser()
            }

            return nextTimeLeft;
          }
          return prevTimeLeft - 1;
        });
        setTimeStudied(prevTimeStudied => {
          if (isStudySession) {
            return prevTimeStudied+1;
          }
          return prevTimeStudied;
        });
      }
    }, 1000);

    return () => clearInterval(intervalId);
  }, [timerRunning, breakDuration, studyDuration, isStudySession]);

  const startTimer = () => {
    setTimerRunning(true);
    playSong.play()
  };

  const stopTimer = () => {
    setTimerRunning(false);
    playSong.pause()
  };
  const playBeep = () => {
    const audio = new Audio('https://firebasestorage.googleapis.com/v0/b/pomodorowebsite-cab09.appspot.com/o/ring1.mp3?alt=media&token=490078a4-174f-4385-be97-57378360cf1b');
    audio.play();
  };
  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
  };

  return (
    
    <div className="study-room-container">
      <div className="study-room">
        <h1 className="title">{isStudySession? "Pomodoro Timer":"Break Timer"}</h1>
        <div className="timer">{formatTime(timeLeft)}</div>
        <div className="buttons">
          {timerRunning ? (
            <button onClick={stopTimer}>Pause Timer</button>
          ) : (
            <button onClick={startTimer}>Start Timer</button>
          )}
        <div className="total-time-studied">
           Total Time Studied: {formatTime(timeStudied)}
         </div>
       </div>
        </div>
      </div>
      
    // </div>
  );
};

export default StudyRoom;



// import React, { useState, useEffect } from 'react';
// import './StudyRoom.css';

// const StudyRoom = () => {
//   const [studyDuration, setStudyDuration] = useState(0.1); // Study duration in minutes
//   const [breakDuration, setBreakDuration] = useState(0.05); // Break duration in minutes
//   const [timeLeft, setTimeLeft] = useState(studyDuration * 60); // Initial time left in seconds
//   const [timerRunning, setTimerRunning] = useState(false);
//   const [isStudySession, setIsStudySession] = useState(true); // Initial session is study session
//   const [totalTimeStudied, setTotalTimeStudied] = useState(0); // Total time studied
//   const [partialTimeStudied, setPartialTimeStudied] = useState(0); // Partial time studied during current session

//   useEffect(() => {
//     const intervalId = setInterval(() => {
//       if (timerRunning) {
//         setTimeLeft((prevTimeLeft) => {
//           const nextIsStudySession = !isStudySession;
//           if (prevTimeLeft <= 0) {
//             // Toggle session
//             // nextIsStudySession = !isStudySession;
//             setIsStudySession(nextIsStudySession);

//             // Calculate time left based on the session
//             const nextTimeLeft = nextIsStudySession ? studyDuration * 60 : breakDuration * 60;

//             // Update total time studied
//             if (nextIsStudySession) {
//               setTotalTimeStudied((prevTotal) => prevTotal + studyDuration);
//             } else {
//               setPartialTimeStudied(0); // Reset partial time studied
//             }

//             // Trigger beep sound
//             playBeep();
//             return nextTimeLeft;
//           }

//           // if (nextIsStudySession) {
//           //   setPartialTimeStudied((prevPartial) => prevPartial + 1);
//           // }

//           return prevTimeLeft - 1;
//         });
//       }
//     }, 1000);

//     return () => clearInterval(intervalId);
//   }, [timerRunning, breakDuration, studyDuration, isStudySession,totalTimeStudied,partialTimeStudied]);

//   const startTimer = () => {
//     setTimerRunning(true);
//   };

//   const stopTimer = () => {
//     setTimerRunning(false);
//     setPartialTimeStudied(0); // Reset partial time studied on stop
//   };

//   const playBeep = () => {
//     const audio = new Audio('https://firebasestorage.googleapis.com/v0/b/pomodorowebsite-cab09.appspot.com/o/ring1.mp3?alt=media&token=490078a4-174f-4385-be97-57378360cf1b');
//     audio.play();
//   };

//   const formatTime = (seconds) => {
//     const minutes = Math.floor(seconds / 60);
//     const remainingSeconds = seconds % 60;
//     return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
//   };

//   return (
//     <div className="study-room-container">
//       <div className="study-room">
//         <h1 className="title">Pomodoro Timer</h1>
//         <div className="timer">{formatTime(timeLeft)}</div>
//         <div className="buttons">
//           {timerRunning ? (
//             <button onClick={stopTimer}>Pause Timer</button>
//           ) : (
//             <button onClick={startTimer}>Start Timer</button>
//           )}
//         </div>
//         <div className="total-time-studied">
//           Total Time Studied: {formatTime(totalTimeStudied * 60 + partialTimeStudied)}
//         </div>
//       </div>
//     </div>
//   );
// };

// export default StudyRoom;

// import React, { useState, useEffect } from 'react';
// import './StudyRoom.css';

// const StudyRoom = () => {
//   const [studyDuration, setStudyDuration] = useState(0.1); // Study duration in minutes
//   const [breakDuration, setBreakDuration] = useState(0.05); // Break duration in minutes
//   const [timeLeft, setTimeLeft] = useState(studyDuration * 60); // Initial time left in seconds
//   const [timerRunning, setTimerRunning] = useState(false);
//   const [totalRunning, setTotalRunning] = useState(false);
//   const [timeStudied, setTimeStudied] = useState(studyDuration * 60);
//   const [partialTimeStudied, setPartialTimeStudied] = useState(0); // Partial time studied during current session

//   const [isStudySession, setIsStudySession] = useState(true); // Initial session is study session
//   const [totalTimeStudied, setTotalTimeStudied] = useState(0); // Total time studied
//   const [startTime, setStartTime] = useState(null); // Start time of the current session

//   useEffect(() => {
//     let intervalId;

//     const updateTimeLeft = () => {
//       const elapsedTime = 0;
//       const newTimeLeft = isStudySession
//         ? Math.max(0, studyDuration * 60 - elapsedTime / 1000)
//         : Math.max(0, breakDuration * 60 - elapsedTime / 1000);
//       setTimeLeft(newTimeLeft);
//     };

//     const updateTotalTimeStudied = () => {
//       if (isStudySession) {
//         setTotalTimeStudied((prevTotal) => prevTotal + (Date.now() - startTime) / 1000);
//       }
//     };

//     const startTimer = () => {
//       setTimerRunning(true);
//       setStartTime(Date.now());
//       intervalId = setInterval(updateTimeLeft, 100);
//       updateTotalTimeStudied();
//     };

//     const stopTimer = () => {
//       setTimerRunning(false);
//       clearInterval(intervalId);
//       updateTotalTimeStudied();
//     };

//     const toggleSession = () => {
//       setIsStudySession((prev) => !prev);
//       setTimeLeft(isStudySession ? breakDuration * 60 : studyDuration * 60);
//       setStartTime(Date.now());
//       playBeep();
//     };

//     const playBeep = () => {
//       const audio = new Audio('https://firebasestorage.googleapis.com/v0/b/pomodorowebsite-cab09.appspot.com/o/ring1.mp3?alt=media&token=490078a4-174f-4385-be97-57378360cf1b');
//       audio.play();
//     };


//     if (timerRunning) {
//       startTimer();
//     } else {
//       stopTimer();
//     }

//     if (timeLeft <= 0) {
//       toggleSession();
//     }

//     return () => clearInterval(intervalId);
//   }, [timerRunning, breakDuration, studyDuration, isStudySession, timeLeft]);

//   const formatTime = (seconds) => {
//     const minutes = Math.floor(seconds / 60);
//     const remainingSeconds = seconds % 60;
//     return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
//   };

//   return (
    
//     <div className="study-room-container">
//       <div className="study-room">
//         <h1 className="title">Pomodoro Timer</h1>
//         <div className="timer">{formatTime(timeLeft)}</div>
//         <div className="buttons">
//           {timerRunning ? (
//             <button onClick={() => setTimerRunning(false)}>Pause Timer</button>
//           ) : (
//             <button onClick={() => setTimerRunning(true)}>Start Timer</button>
//           )}
//         </div>
//         <div className="total-time-studied">
//           Total Time Studied: {formatTime(totalTimeStudied)}
//         </div>
//       </div>
//     </div>
//   );
// };

// export default StudyRoom;