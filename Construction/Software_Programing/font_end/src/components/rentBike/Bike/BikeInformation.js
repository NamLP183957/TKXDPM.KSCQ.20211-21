import React from 'react'
import "bootstrap/dist/css/bootstrap.min.css"
import { DEPOSIT_TYPE, BIKE_TYPE } from '../../../variables/ConstantCommon'
import Table from '../../table/Table';
import TableRow from '../../table/TableRow';
import TableHeader from '../../table/TableHeader';
import TableData from '../../table/TableData';
function BikeInformation(props) {
    const { bike } = props;
    const { id, battery, licensePlate, maxTime, parkingSlotId, status, type } = bike;

    return (
        <div style={{ width: "50%", marginTop: '5%' }}>

            <h3>Thông tin xe</h3>

            <Table>
                <TableRow>
                    <TableHeader>Biển số xe</TableHeader>
                    <TableData>{licensePlate}</TableData>
                </TableRow>
                <TableRow>
                    <TableHeader>Loại xe</TableHeader>
                    <TableData>{BIKE_TYPE[type]}</TableData>
                </TableRow>
                {(type === 2 || type === 4) &&
                    <TableRow>
                        <TableHeader>Thời lượng pin</TableHeader>
                        <TableData>{battery}</TableData>
                    </TableRow>
                }
                <TableRow>
                    <TableHeader>Phí đặt cọc</TableHeader>
                    <TableData>{DEPOSIT_TYPE[type]}  VND</TableData>
                </TableRow>
            </Table>

        </div>
    )
}

export default BikeInformation
