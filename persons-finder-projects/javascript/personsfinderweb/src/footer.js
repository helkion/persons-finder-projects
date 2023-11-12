import React from "react";

export default function Footer (){

    const versao = '0.0.1';//API_VERSAO: 
    const data = '09/11/2023';//API_DATA: 
    return (        
        <footer 
            style = {{background: '#000000', height: '25px', width: '100%',
                padding: 0, bottom: 0, position: 'absolute'}}>
            <p style={{textAlign:'center', color:'#ffffff', transform: 'translateY(-50%)'}}>
                Person's Finder Web - Version {versao} - {data} 
            </p>
            <br/>
        </footer>
    )
}
