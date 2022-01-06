import React from 'react'
import { Table } from 'react-bootstrap';
import {BIKE_TYPE} from "../../../variables/ConstantCommon"
function TransactionResultScreen(props) {
    const {card, amount, bike} = props.location;
    console.log("bike: ", bike);
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
                        <th>Mã thẻ</th>
                        <td>{card.cardCode}</td>
                    </tr>
                    Thông tin xe:
                    <tr>
                        <th>Biển số ze</th>
                        <td>{bike.liencePlate}</td>
                    </tr>
                    <tr>
                        <th>Loại xe</th>
                        <td>{BIKE_TYPE[bike.type]}</td>
                    </tr>
                    {(bike.type === 3 || bike.type === 4) && 
                        <tr>
                            <th>Thời lượng pin</th>
                            <td>{bike.battery}</td>
                        </tr>
                    }
                    <tr>
                        <th>Tiền đặt cọc</th>
                        <td>{amount} VND</td>
                    </tr>
                </tbody>
            </Table>
        </div>
    )
}

export default TransactionResultScreen
