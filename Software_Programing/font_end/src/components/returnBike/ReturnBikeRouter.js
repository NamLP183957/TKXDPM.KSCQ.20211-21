import React from 'react'
import { Route, Switch } from 'react-router-dom'
import RentTimeScreen from '../rentTimeBike/RentTimeScreen'
import StationScreen from './station/StationScreen'
import TransactionResult from './station/TransactionResult'

function ReturnBikeRouter() {
  return (
    <div>
      <Switch>
        <Route
          path="/renting-return-bike/"
          component={RentTimeScreen}
          exact
        />
        <Route
          path="/renting-return-bike/list-station"
          component={StationScreen}
        />
        <Route
          path="/renting-return-bike/transaction-result"
          component={TransactionResult}
        />
      </Switch>
    </div>
  )
}

export default ReturnBikeRouter
