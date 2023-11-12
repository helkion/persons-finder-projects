import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import RegisterPerson from './pages/person/register';
import SetLocation from './pages/locations/setlocation';
import FindAround from './pages/locations/findaround';
import PersonsNames from './pages/person/names';

const Routes = () => {
    
    return (
        <div>
        
        <BrowserRouter>
            <Switch>
                <Route path='/registerperson' component={RegisterPerson}/>
                <Route path='/setlocation' component={SetLocation}/>
                <Route path='/listpersons' component={PersonsNames}/>
                <Route path='/findaround' component={FindAround}/>
            </Switch>            
        </BrowserRouter>
        </div>
    );
}
export default Routes;