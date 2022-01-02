import React from 'react'
import TransactionForm from './TransactionForm';
import {DEPOSIT_TYPE} from '../../../variables/ConstantCommon'
function TransactionScreen(props) {
    const {state} = props.location;

    return (
        <div>
            <h1>Thông tin thẻ thanh toán</h1>
            <h2>Tiền đặt cọc: {DEPOSIT_TYPE[state.type]} vnd</h2>
            <TransactionForm bike={state}/>
        </div>
    )
}

export default TransactionScreen
