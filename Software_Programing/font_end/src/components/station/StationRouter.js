import React from 'react'
import { Route, Switch } from 'react-router-dom'
import Vehicle from '../vehicle/Vehicel'
import ListStationScreen from './ListStationScreen'

function StationRouter() {
    return (
        <div>
            <Switch>
                <Route 
                    path="/station/"
                    component={ListStationScreen}
                    exact
                />
                <Route
                    path="/station/vehicle/:id"
                    component={Vehicle}
                    exact
                />
            </Switch>
        </div>
    )
}

export default StationRouter
