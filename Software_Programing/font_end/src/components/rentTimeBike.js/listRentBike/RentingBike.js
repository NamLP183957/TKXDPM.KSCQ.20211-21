import React, { useEffect, useState } from 'react'
import { Button, Card } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';
import {BIKE_TYPE} from '../../../variables/ConstantCommon'
function RentingBike(props) {
    const history = useHistory();
    const {bike} = props;
    const [time, setTime] = useState(150);
    console.log(bike);
    // useEffect(() => {
    //     const timerId = setInterval(() => {setTime(prev => prev + 1)}, 1000);
    //     return () => {
    //         clearInterval(timerId);
    //     }
    // }, [])
    const handleOnClick = () => {
        console.log("redirect")
        history.push({
            pathname: '/renting-return-bike/list-station',
            vehicle: bike
        })
    }

    const toLocaleDateString= timestamp => {
        const date = new Date(timestamp);
        const hour = Number(date.getHours()) > 9 ? date.getHours() : `0${date.getHours()}`;
        const min = Number(date.getMinutes()) > 9 ? date.getMinutes() : `0${date.getMinutes()}`;
        return `${hour}:${min}`;
    };

    return (
        <Card style={{width: '18rem'}} className='card'>
            <Card.Body>
                <Card.Title>Xe đang thuê</Card.Title>
                <div>Biển số: {bike.licensePlate}</div>
                <div>Loại xe: {BIKE_TYPE[bike.type]}</div>
                <div>Thời gian bắt đầu thuê xe: {toLocaleDateString(bike.startTime)}</div>
                <div>Thời gian đã thuê xe: {toLocaleDateString(bike.timeRented)}</div>
                <div>Số tiền phải trả: {bike.fee}</div>
                <div>Thời lượng pin (dành cho xe điện): {bike.battery}</div>
                <div>Thời gian sử dụng tối đa (dành cho xe điện): {bike.maxTime}</div>
                <br />
                <Button variant='warning'>Tạm dừng</Button>
                <span />
                <Button variant="danger" onClick={() => handleOnClick()}>Trả xe</Button>
            </Card.Body>
        </Card>
    )
}

export default RentingBike
