import { Toast } from 'primereact/toast';
import React, { useRef, useState } from 'react';
import { TextField, Button } from '@material-ui/core';
import './../../../App.css';
import sendapi from '../../../components/handler';
import useToken from '../../../useToken';

export default function RegisterPerson({  }){

  const[name, setName] = useState('');
  const { token, setToken } = useToken();
  const toast = useRef(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    
    if (name.trim() === '' ) {
        fillInFields();
        document.getElementById('name').focus();
        return false;
    } else {
      let path = 'persons';
      const body = {
          'name': name,
      }

      sendapi(path, token, 'json', body)
              .then((json) => {
                  let severity = "warn"
                  let summary = "Registration Failure"
                  let detail = "Please check person's name"
                  let success = json.id !== undefined
                  if (success){
                      severity = "info"
                      summary = `Person ${json.id} register created`
                      detail = ''
                  } else {
                      severity = "warn"
                      summary = "Failure While Registering Person"
                      // detail = `${json.breadCrumbId} - Contact System Administrator`
                      detail = `${json.status} - Contact System Administrator`
                  }

                  const msgResp = {
                      severity: severity,
                      summary: summary,
                      detail: detail,
                      closable: false,
                      life: 10000,
                  }

                  toast.current.show(msgResp);
                  
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
      <h1>Person Registration</h1>
    
      <form onSubmit={handleSubmit} className="form">
        <TextField 
          id='name' 
          name='name' 
          value={name}
          onChange={(e) => setName(e.target.value)}
          autoFocus
          label="Name" 
          required />
        <Button 
          variant="contained" 
          color="primary" 
          type="submit">
          Register
        </Button>
        <Toast ref={toast} />
      </form>
    </>
  );
};

// Login.propTypes = {
//   setToken: PropTypes.func.isRequired
// }
