import React from 'react'
import { Button, Table } from 'react-bootstrap';
import Station from './Station';

function ListStation(props) {
    const {listStation} = props;
    return (
        <Table>
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên bãi xe</th>
                    <th>Địa chỉ</th>
                    <th style={{width: "15%"}}>Chi tiết</th>
                </tr>
            </thead>
            <tbody>
                {listStation.map((station, index) => (
                    <Station key={index} station={station} index={index+1} handleDetail={props.handleDetail}/>
                ))}
            </tbody>
            
        </Table>
    )
}

export default ListStation
