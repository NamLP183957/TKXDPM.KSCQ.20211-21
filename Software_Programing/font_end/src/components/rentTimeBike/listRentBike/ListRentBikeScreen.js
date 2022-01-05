import axios from 'axios'
import './index.css'
import React, { useEffect, useState } from 'react'
import ListRentingBike from './ListRentingBike';
import { SERVER_PORT } from '../../../variables/Server';



function ListRentBikeScreen() {
    const [error, setError] = useState();
    const [listBike, setListBike] = useState([]);
    useEffect(() => {
            (async() => {
            try {
                const resp = await axios.get("http://localhost:8080/api/vehicles/list-in-rent-time");
                console.log(resp);
                setListBike(resp.data);
            } catch (error) {
                setError("Không thể tải dữ liệu")
            }})();

        setInterval(
            () => {
            (async() => {
            try {
                const resp = await axios.get(`http://localhost:${SERVER_PORT}/api/vehicles/list-in-rent-time`);
                setListBike(resp.data);
            } catch (error) {
                setError("Không thể tải dữ liệu")
            }
        })() }
        , 1000);

    }, [])



    return (
        <div>
            <h1>Danh sách xe đang thuê</h1>
            {error && <div>{error}</div>}
            {listBike.length === 0 && <div>Không có xe nào đang thuê</div>}
            {listBike.length > 0 && <ListRentingBike listBike={listBike}/>}
        </div>
    )
}

export default ListRentBikeScreen
