import React, { useEffect, useState } from "react";
import { Button, Card } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import { BIKE_TYPE } from "../../../variables/ConstantCommon";
function RentingBike(props) {
  const history = useHistory();
  const { bike } = props;
  const [time, setTime] = useState(150);
  console.log(bike);
  // useEffect(() => {
  //     const timerId = setInterval(() => {setTime(prev => prev + 1)}, 1000);
  //     return () => {
  //         clearInterval(timerId);
  //     }
  // }, [])
  const handleOnClick = () => {
    console.log("redirect");
    history.push({
      pathname: "/renting-return-bike/list-station",
      vehicle: bike,
    });
  };

  const toLocaleDateString = (timestamp) => {
    const date = new Date(timestamp);
    const day = Number(date.getDate()) > 9 ? date.getDate() : `0${date.getDate()}`;
    const hour =
      Number(date.getHours()) > 9 ? date.getHours() : `0${date.getHours()}`;
    const min =
      Number(date.getMinutes()) > 9
        ? date.getMinutes()
        : `0${date.getMinutes()}`;
    return `${day*24 + hour}h ${min}p`;
  };

  const toLocaleDateStringV2= timestamp => {
    const date = new Date(timestamp);
    const months = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
    const year = date.getFullYear();
    const month = months[date.getMonth()];
    const day = Number(date.getDate()) > 9 ? date.getDate() : `0${date.getDate()}`;
    const hour = Number(date.getHours()) > 9 ? date.getHours() : `0${date.getHours()}`;
    const min = Number(date.getMinutes()) > 9 ? date.getMinutes() : `0${date.getMinutes()}`;
    return `${day}-${month}-${year} ${hour}:${min}`;
  };
    // return (
        
    //     <Card style={{width: '18rem'}} className='card'>
    //         <Card.Body>
    //             <Card.Title>Xe đang thuê</Card.Title>
    //             <div>Biển số: {bike.licensePlate}</div>
    //             <div>Loại xe: {BIKE_TYPE[bike.type]}</div>
    //             <div>Thời gian bắt đầu thuê xe: {toLocaleDateString(bike.startTime)}</div>
    //             <div>Thời gian đã thuê xe: {toLocaleDateString(bike.timeRented)}</div>
    //             <div>Số tiền phải trả: {bike.fee}</div>
    //             <div>Thời lượng pin (dành cho xe điện): {bike.battery}</div>
    //             <div>Thời gian sử dụng tối đa (dành cho xe điện): {bike.maxTime}</div>
    //             <br />
    //             <Button variant='warning'>Tạm dừng</Button>
    //             <span />
    //             <Button variant="danger" onClick={() => handleOnClick()}>Trả xe</Button>
    //         </Card.Body>
    //     </Card>
    // )

  return (
    <Card style={{ width: "23rem" }} className="card">
      <Card.Body >
        <Card.Title>Xe đang thuê</Card.Title>
        <div>Biển số: {bike.licensePlate}</div>
        <div>Loại xe: {BIKE_TYPE[bike.type]}</div>
        <div>
          Thời gian bắt đầu thuê xe: {toLocaleDateStringV2(bike.startTime)}
        </div>
        <div>Thời gian đã thuê xe: {toLocaleDateString(bike.timeRented)}</div>
        <div>Số tiền phải trả: {bike.fee}</div>
        {bike.type === 2 || bike.type === 4 ? <div>Thời lượng pin: {bike.battery} %</div> : null}
        {bike.type === 2 || bike.type === 4 ? <div>Thời gian sử dụng tối đa: {bike.maxTime} phút</div> : null}
        <br />
        <Button variant="warning">Tạm dừng</Button>
        <span />
        <Button variant="danger" onClick={() => handleOnClick()}>
          Trả xe
        </Button>
      </Card.Body>
    </Card>
  );
}

export default RentingBike;
