import React from 'react'
import { Route, Switch } from 'react-router-dom'
import BikeScreen from './Bike/BikeScreen'
import BikeCodeForm from './BikeCode/BikeCodeForm'
import TransactionResultScreen from './Transaction/TransactionResultScreen'
import TransactionScreen from './Transaction/TransactionScreen'
import RentFeeInfo from './RentFeeInfo'
function RentBikeRouter() {
    return (
        <div>
            <Switch>
                <Route
                    path="/rent-bike/"
                    component={RentFeeInfo}
                    exact
                />
                <Route 
                    path="/rent-bike/bike-code"
                    component={BikeCodeForm}
                />

                <Route
                    path="/rent-bike/bike-info"
                    component={BikeScreen}
                />

                <Route 
                    path="/rent-bike/deposit"
                    component={TransactionScreen}
                />

                <Route
                    path="/rent-bike/transaction-result"
                    component={TransactionResultScreen}
                />
            </Switch>
        </div>
    )
}

export default RentBikeRouter
