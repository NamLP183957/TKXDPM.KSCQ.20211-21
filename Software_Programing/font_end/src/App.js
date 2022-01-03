import RentBikeScreen from './components/rentBike/RentBikeScreen';
import './App.css';
import {Button} from 'react-bootstrap'
import ReturnBikeScreen from './components/returnBike/ReturnBikeScreen';
import Navbars from './components/navbar/Navbars';
import { Route, Switch } from 'react-router-dom';
import RentTimeScreen from './components/rentTimeBike.js/RentTimeScreen';
import ListStationScreen from './components/station/ListStationScreen';
import logo from './logo.png'
import StationScree from './components/station/StationScreen';
import StationScreen from './components/station/StationScreen';
function App() {
  return (
    <div className="App">
      <Navbars />
      <div className='container'>
      <img src={logo} style={{width: '200px', height: '150px'}}/>
      <Switch>
        <Route
          path="/station/*"
          component={StationScreen}
          exact
        />
        <Route 
          path="/rent-bike/*"
          component={RentBikeScreen}
        />
        <Route
          path="/renting-return-bike/*"
          component={ReturnBikeScreen}
          exact
        />
      </Switch>
      </div>
    </div>
  );
}

export default App;
