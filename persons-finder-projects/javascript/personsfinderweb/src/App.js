// Page.js
import React from 'react';
import Login from './pages/login';
import './App.css';
import useToken from './useToken';
import MainMenu from './components/menu';
import Routes from './routes';

function App (){
  const { token, setToken } = useToken();

  const content = token? 
    <>
      <Routes/>
    </>
    : <>
        <Login setToken={setToken}/>
      </>

  return (
    <div className="page">
      <MainMenu />
      <div className="content">
        {content}      
      </div>
    </div>
  );
}

export default App;
