import React from 'react'
import { Link } from 'react-router-dom';

const TransactionResult = (props) => {
    const {amount, createdAt, card, transactionContent} = props.location.state;
    return ( 
        <div className='d-flex justify-content-center'>
            <div className="text-start w-50 border p-3 bg-light shadow rounded">
                <h2 className='text-success'>Payment Successful !</h2>
                <table className='w-75'>
                    <tbody>
                        <tr>
                            <th>Owner:</th>
                            <td>{card.owner}</td>
                        </tr>
                        <tr>
                            <th>Card code:</th>
                            <td>{card.cardCode}</td>
                        </tr>
                        <tr>
                            <th>Content:</th>
                            <td>{transactionContent}</td>
                        </tr>
                        <tr>
                            <th>Amount:</th>
                            <td>{amount} đồng</td>
                        </tr>
                        <tr>
                            <th>Created at:</th>
                            <td>{createdAt}</td>
                        </tr>
                    </tbody>
                </table>
                <div className='d-flex justify-content-end'>
                    <Link to="/" className='btn btn-success'> Back Home</Link>
                </div>
            </div>
        </div>
     );
}
 
export default TransactionResult;