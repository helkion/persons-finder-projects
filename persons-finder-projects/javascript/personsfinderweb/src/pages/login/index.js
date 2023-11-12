import { Toast } from 'primereact/toast';
import React, { useRef, useState } from 'react';
import { TextField, Button } from '@material-ui/core';
import PropTypes from 'prop-types';
import './../../App.css';
import sendapi from '../../components/handler';

export default function Login({ setToken }){

  const[username, setUsername] = useState('');
  const[password, setPassword] = useState('');
  const toast = useRef(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    
    if (username.trim() === '' 
      || password.trim() === '') {
        fillInFields();
        document.getElementById('username').focus();
        return false;
    } else {
      let path = 'login';
      console.log(`${username} : ${password}`)
      const cred = window.btoa(
          `${username}:${password}`)

      sendapi(path, cred, 'json', null)
        .then(async (resp) => {
            // console.log(json.breadCrumbId)
            let severity = "warn"
            let summary = "Login failure"
            let detail = "Please check your username and password"
            console.log(resp)
            console.log('here')
            console.log(resp.resul)
            if (resp.result){
                console.log('logged in')
                setToken(cred)
                window.location.reload(false)
                
            } else {
                
                severity = "warn"
                summary = "Failure while logining in"
                detail = 'Contact System Administrator'

            }

            const msgError = {
                severity: severity,
                summary: summary,
                detail: detail,
                life: 3000
            }

            toast.current.show(msgError);
        })
    }
  };

  const fillInFields = () => {
    toast.current.show(
        {severity: 'error',
         summary: 'Error',
          detail: 'Please fill in all fields!'});
  }

  return (
    <>
      <h1>System Access</h1>
    
      <form onSubmit={handleSubmit} className="form">
        <TextField 
          id='username' 
          name='username' 
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          autoFocus
          label="User name" 
          required />
        <TextField 
          label="Password" 
          type='password' 
          id='password' 
          name='password' 
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required/>
        <Button 
          variant="contained" 
          color="primary" 
          type="submit">
          Log in
        </Button>
        <Toast ref={toast} />
      </form>
    </>
  );
};

Login.propTypes = {
  setToken: PropTypes.func.isRequired
}
