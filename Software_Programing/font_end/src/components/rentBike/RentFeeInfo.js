import React, { useState } from 'react'
import { Button } from 'react-bootstrap';
import { useHistory } from 'react-router-dom'
import BikeCodeForm from './BikeCode/BikeCodeForm';

const titleStyle = {
    fontSize: '20px',
    fontWeight: 'bold'
}
const contentStyle = {
    fontSize: '18px'
}

function RentFeeInfo() {
    const [toggle, setToggle] = useState(false);
    const handleOnClick = () => {
        setToggle(prev => !prev);
    }

    return (
        <div>
            <div style={titleStyle}>Có 4 loại xe</div>
            <div style={contentStyle}>
                <ul>
                    <li>Xe đạp đơn thướng</li>
                    <li>Xe đạp đôi thướng</li>
                    <li>Xe đạp đơn điện</li>
                    <li>Xe đạp đôi điện</li>
                </ul>
            </div>
            <div style={titleStyle}>
                Giá thuê xe: 
            </div>
            <div style={contentStyle}>
                <ul>
                    <li>Khách thuê xe sẽ được miên phí thuê xe nếu trả xe trong vòng 10 phút</li>
                    <li>Nếu khách hàng dùng xe hơn 10 phút, phí thuê xe được tính lũy tiến theo thời gian
                        thuê như sau: <br /> Giá khới điểm 30 phút đầu là 10.000 đồng, cứ 15 phút tiếp theo, Khách
                        sẽ phải trả thêm 3.000 đồng
                    </li>
                    <li>Giá thuê xe của xe đạp đôi thường và xe đơn điện gấp <b>1.5 lần</b> xe đạp đơn thường</li>
                    
                    <li>Giá thuê xe của xe đạp đôi điện <b>2 lần</b> xe đạp đơn thường</li>
                </ul>
            </div>
            <div style={titleStyle}>
                Tiền đặt cọc:
            </div>
            <div style={contentStyle}>
                <ul>
                    <li>Xe đạp đơn thường: <b>400.000 đồng</b></li>
                    <li>Xe đạp đơn điện: <b>700.000 đồng</b></li>
                    <li>Xe đạp đôi thường: <b>550.000 đồng</b></li>
                    <li>Xe đạp đôi điện: <b>800.000 đồng</b></li>
                </ul>
            </div>
            <Button variant='primary' onClick={() => handleOnClick()}>Thuê xe</Button>
            {toggle && <BikeCodeForm handleOnClose={handleOnClick}/>}
        </div>
    )
}

export default RentFeeInfo
