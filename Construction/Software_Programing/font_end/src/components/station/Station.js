import React, { useState } from 'react'
import { Button } from 'react-bootstrap';
import PopupDialog from '../popup/PopupDialog';
import StationDetail from './StationDetail';
import {BiCommentDetail} from 'react-icons/bi'
function Station(props) {
    const { station, index } = props;

    const handleOnClick = (station) => {

    }

    return (
        <tr>
            <td>{index + 1}</td>
            <td>{station.name}</td>
            <td>{station.address}</td>
            <td><Button variant='success' onClick={() => props.handleDetail(station)}>Chi tiết <BiCommentDetail /></Button></td>
        </tr>
    )
}

export default Station
