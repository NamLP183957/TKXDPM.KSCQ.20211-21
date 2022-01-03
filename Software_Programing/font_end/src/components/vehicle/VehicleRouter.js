import React from 'react'
import { Route, Switch } from 'react-router-dom'
import Vehicle from './Vehicel'
function VehicleRouter() {
    return (
        <div>
            <Switch>
                <Route
                    path="/vehicle/:id"
                    component={Vehicle}
                    exact
                />
            </Switch>
        </div>
    )
}

export default VehicleRouter
