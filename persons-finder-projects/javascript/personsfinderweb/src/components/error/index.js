import React, { useState } from 'react';
import { ErrorStyles } from './styles';

export default function Error({errorMessage}) {

    // const[errorMessage, setErrorMessage] = useState('');

    return(
        <ErrorStyles>
            {errorMessage}
        </ErrorStyles>
    );
}

// const[errorMessages, setErrorMessages] = useState({});

// export const renderErrorMessage = (name) =>
//         name === errorMessages.name && (
//             <ErrorStyles>
//                 {errorMessages.message}
//             </ErrorStyles>
//         );