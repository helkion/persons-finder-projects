import { Toast } from 'primereact/toast';
import React, { useRef, useState } from 'react';
import { Buttonn } from '../../components/button/styles';
import { InputStyles } from '../../components/input/styles';
import { FormStyle, LoginWrapperStyle } from '../../styles';
import PropTypes from 'prop-types';
import { ButtonContainer, InputContainer } from './styles';
import sendapi from '../../components/handler';

export default function Login({ setToken, setErrorMessage }){

    const[username, setUsername] = useState('');
    const[password, setPassword] = useState('');
    // const[errorMessages, setErrorMessages] = useState({});
    // const[errorMessage, setErrorMessage] = useState();
    const toast = useRef(null); 

    const errors = {
        uname: "invalid username",
        pass: "invalid password"
    };

    function handleLogin(){ 
        if (document.getElementById('username').value.length === 0
            || document.getElementById('password').value.length === 0){            
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
                        if (resp.ok){
                            console.log('logged in')
                            const json = await resp.json();
                            console.log(json);
                            setToken(cred)
                            window.location.reload(false)
                            
                        } else {
                            
                            severity = "warn"
                            summary = "Failure while logining in"
                            detail = 'Contact System Administrator'

                        }

                        const msgErro = {
                            severity: severity,
                            summary: summary,
                            detail: detail,
                            life: 3000
                        }

                        // setErrorMessage(detail)
                        toast.current.show(msgErro);
                    })
        }
    }

    const fillInFields = () => {
        toast.current.show(
            {severity: 'error',
             summary: 'Error',
              detail: 'Please fill in all fields!'});
    }

    return(
        <LoginWrapperStyle>
            <h1>System Access</h1>
            <FormStyle>
                <InputContainer>
                    <label>
                        Username
                    </label>
                    <InputStyles 
                        width='85px' 
                        type='text' 
                        id='username' 
                        name='username' 
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        autoFocus
                        required/>
                </InputContainer>
                <InputContainer>
                    <label>
                        Password
                    </label>
                    <InputStyles 
                        width='85px' 
                        type='password' 
                        id='password' 
                        name='password' 
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required/>
                </InputContainer>
                {/* <Erro errorMessage={errorMessage} /> */}
                <ButtonContainer>
                    <Buttonn 
                        margin= '0.5em 1em' 
                        onClick={handleLogin}>
                            Log in
                    </Buttonn>
                </ButtonContainer>
                <Toast ref={toast} />
            </FormStyle>
        </LoginWrapperStyle>
    )
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired
  }