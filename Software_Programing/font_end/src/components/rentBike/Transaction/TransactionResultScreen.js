import React from 'react'
import { Table } from 'react-bootstrap';

function TransactionResultScreen(props) {
    const {card, amount, bike} = props.location;
    return (
        <div>
            <h4 style={{color: "green"}}>Giao dịch thành công</h4>
            <Table>
                <tbody>
                    <tr>
                        <th>Chủ thẻ</th>
                        <td>{card.owner}</td>
                    </tr>
                    <tr>
                        <th>Thông tin xe</th>
                        <td>{bike.liencePlate}</td>
                    </tr>
                    <tr>
                        <th>Tiền đặt cọc</th>
                        <td>{amount}</td>
                    </tr>
                </tbody>
            </Table>
        </div>
    )
}

export default TransactionResultScreen
