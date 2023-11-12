import { Toast } from 'primereact/toast';
import React, { useRef, useState } from 'react';
import { TextField, Button } from '@material-ui/core';
import './../../../App.css';
import sendapi from '../../../components/handler';
import useToken from '../../../useToken';
import JsonTable from '../../../components/jsontable';

export default function FindAround({  }){

  const[referenceId, setReferenceId] = useState('');
  const[radiusInKm, setRadiusInKm] = useState('');
  const { token, setToken } = useToken();
  const toast = useRef(null);
  const[jsonData, setJsonData] = useState(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    
    if (referenceId.trim() === '' 
      || radiusInKm.trim() === '') {
        fillInFields();
        document.getElementById('referenceId').focus();
        return false;
    } else {
      let path = `locations/findaround/${referenceId}`;
      path += `?radiusInKm=${radiusInKm}`;
      sendapi(path, token, 'json', null)
              .then((json) => {
                  let severity = "warn"
                  let summary = "Location Find Around Failure"
                  let detail = "Please check location information"
                  let success = json !== undefined
                  if (success){
                      severity = "info"
                      summary = `Person ${referenceId}'s location set`
                      detail = ''//''JSON.stringify(json)
                      setJsonData(json)
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
      <h1>Find Around</h1>
    
      <form onSubmit={handleSubmit} className="form">
        <TextField 
          id='referenceId' 
          name='referenceId' 
          value={referenceId}
          onChange={(e) => setReferenceId(e.target.value)}
          autoFocus
          label="Person's Id" 
          required />
        <TextField 
          id='radiusInKm' 
          name='radiusInKm' 
          value={radiusInKm}
          onChange={(e) => setRadiusInKm(e.target.value)}
          label="Radius (Km)" 
          required />
        <Button 
          variant="contained" 
          color="primary" 
          type="submit">
          Find
        </Button>
        <Toast ref={toast} />
        {jsonData ? <JsonTable data={jsonData} /> : '...'}
      </form>
    </>
  );
};
