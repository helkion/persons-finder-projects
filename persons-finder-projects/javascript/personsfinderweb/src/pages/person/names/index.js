import { Toast } from 'primereact/toast';
import React, { useRef, useState } from 'react';
import { TextField, Button } from '@material-ui/core';
import './../../../App.css';
import sendapi from '../../../components/handler';
import useToken from '../../../useToken';
import JsonTable from '../../../components/jsontable';

export default function PersonsNames({  }){

  const[ids, setIds] = useState('');
  const { token, setToken } = useToken();
  const toast = useRef(null);
  const[jsonData, setJsonData] = useState(null);

  const handleSubmit = (event) => {
    event.preventDefault();
    
    if (ids.trim() === '') {
        fillInFields();
        document.getElementById('ids').focus();
        return false;
    } else {
      let path = 'persons/names';
      let body = ids
        .split(',')
        .map(Number)
        .map(id => ({ id }));

      sendapi(path, token, 'json', body)
              .then((json) => {
                  let severity = "warn"
                  let summary = "Location Find Around Failure"
                  let detail = "Please check location information"
                  let success = json !== undefined
                  if (success){
                      severity = "info"
                      summary = `Persons ids' [${ids}] names`
                      detail = ''//''JSON.stringify(json)
                      setJsonData(json)
                  } else {
                      severity = "warn"
                      summary = "Failure While Getting Persons' Names"
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
      <h1>Getting Persons' names by Ids</h1>
    
      <form onSubmit={handleSubmit} className="form">
        <TextField 
          id='ids' 
          name='ids' 
          value={ids}
          onChange={(e) => setIds(e.target.value)}
          autoFocus
          label="Person' Ids" 
          required />
        <Button 
          variant="contained" 
          color="primary" 
          type="submit">
          Get
        </Button>
        <Toast ref={toast} />
        {jsonData ? <JsonTable data={jsonData} /> : '...'}
      </form>
    </>
  );
};
