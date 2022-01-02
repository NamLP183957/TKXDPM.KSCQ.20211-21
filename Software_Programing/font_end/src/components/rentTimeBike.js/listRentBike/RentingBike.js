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
    return (
        <Card style={{width: '18rem'}} className='card'>
            <Card.Body>
                <Card.Title>Xe đang thuê</Card.Title>
                <div>Biển số: {bike.licensePlate}</div>
                <div>Loại xe: {BIKE_TYPE[bike.type]}</div>
                <div>Thời gian bắt đầu thuê</div>
                <div>Thời gian thuê xe: {time}</div>
                <div>Số tiền phải trả: </div>
                <br />
                <Button variant='warning'>Tạm dừng</Button>
                <span />
                <Button variant="danger" onClick={() => handleOnClick()}>Trả xe</Button>
            </Card.Body>
        </Card>
    )
}

export default RentingBike
