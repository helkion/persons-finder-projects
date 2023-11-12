import { general, properties } from '../../assets/properties.js';

export default function sendapi(path, token, respType = 'json', data = null){    

    console.log(`[${respType}] = ${data?.num}`)

    const options = {
        method: data? 'POST' : 'GET',
        body: data? JSON.stringify(data) : null,
        headers: {
            'Authorization': `Basic ${token?.replaceAll('"', '')}` , 
            'Content-Type': 'application/json',
            'Accept': '*/*'
        }
    }
    let url = properties.API_ADDR + path;
    
    console.log(url)
    return fetch(url, options)
        .then((response) => {
            console.log(response)
            if (respType === 'json')
                return response.json();
            else if (respType === 'blob')
                return response.blob();
        })
        .then((resp) => {
            if (respType === 'json')
                console.log(resp)
            return resp
        }) 
}