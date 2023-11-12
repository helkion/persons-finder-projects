import { Toast } from 'primereact/toast';
import React, { useRef, useState } from 'react';
import { TextField, Button } from '@material-ui/core';
import './../../../App.css';
import sendapi from '../../../components/handler';
import useToken from '../../../useToken';

export default function SetLocation({  }){

  const[referenceId, setReferenceId] = useState('');
  const[latitude, setLatitude] = useState('');
  const[longitude, setLongitude] = useState('');
  const { token, setToken } = useToken();
  const toast = useRef(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    
    if (referenceId.trim() === '' 
      || latitude.trim() === ''
      || longitude.trim() === '') {
        fillInFields();
        document.getElementById('referenceId').focus();
        return false;
    } else {
      let path = 'locations/setlocation';
      const body = {
          'referenceId': referenceId,
          'latitude': latitude,
          'longitude': longitude,
      }

      sendapi(path, token, 'json', body)
              .then((json) => {
                  let severity = "warn"
                  let summary = "Location Setting Failure"
                  let detail = "Please check location information"
                  let success = json.id !== undefined
                  if (success){
                      severity = "info"
                      summary = `Person ${json.id}'s location set`
                      detail = JSON.stringify(json)
                  } else {
                      severity = "warn"
                      summary = "Failure While Setting Location"
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
      <h1>Set Location</h1>
    
      <form onSubmit={handleSubmit} className="form">
        <TextField 
          id='referenceId' 
          name='referenceId' 
          value={referenceId}
          onChange={(e) => setReferenceId(e.target.value)}
          autoFocus
          label="Person Id" 
          required />
        <TextField 
          id='latitude' 
          name='latitude' 
          value={latitude}
          onChange={(e) => setLatitude(e.target.value)}
          label="Latitude" 
          required />
        <TextField 
          id='longitude' 
          name='longitude' 
          value={longitude}
          onChange={(e) => setLongitude(e.target.value)}
          label="Longitude" 
          required />
        <Button 
          variant="contained" 
          color="primary" 
          type="submit">
          Set
        </Button>
        <Toast ref={toast} />
      </form>
    </>
  );
};
