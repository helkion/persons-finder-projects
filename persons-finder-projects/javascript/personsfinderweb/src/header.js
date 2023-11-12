import React from 'react';
import HeaderLogo from './assets/img/header_20231031.jpg';
import ImageHeader from './assets/img/bg_top.gif';

var headerLogo = {
    width: "80%",
    height: "75px",
    cabecalho: "url(" + HeaderLogo +")"
};

var lateralHeaderLogo = {
    width: "10%",
    height: "75px",
    cabecalho: "url(" + ImageHeader +")"
};

export default function Header(){
    return (
        <div id='headerdiv'>
            <header className="App-header">  
                <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"></link>                       
                <img alt='' src={ImageHeader} style={ lateralHeaderLogo }/>
                <img alt='' src={HeaderLogo} style={ headerLogo }/>
                <img alt='' src={ImageHeader} style={ lateralHeaderLogo }/>
            </header>
        </div>
    )
}