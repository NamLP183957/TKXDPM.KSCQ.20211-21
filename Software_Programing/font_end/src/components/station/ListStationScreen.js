import axios from 'axios';
import React, { useState, useEffect } from 'react'
import ListStation from './ListStation';
import StationDetail from './StationDetail';
import {getStationBySearchKey, getListStation} from "../../services/ViewStation"

function ListStationScreen() {
    const [listStation, setListStation] = useState([]);
    const [error, setError] = useState();
    const [toggle, setToggle] = useState(false);
    const [stationDetail, setStationDetail] = useState();
    const [searchKey, setSearchKey] = useState("");

    useEffect(() => {
        (async() => {
            try {
                //const resp = await axios.get("http://localhost:8080/api/stations/");
                const resp = await getListStation();
                if (resp.status === 200) {
                    setListStation(resp.data);
                } else {
                    setError("Không thể tải dữ liệu")
                }
            } catch (error) {
                setError("Không thể tải dữ liệu")
            }
        })();

    }, [])

    const handleToggle = (station) => {
        setToggle(prev => !prev);
        console.log(station);
        setStationDetail(station);
    }

    const handleOnChange = (event) => {
        const key = event.target.value;
        setSearchKey(key);

        if (key !== "") {
            (async() => {
                try {
                    const resp = await getStationBySearchKey(key);
                    if (resp.status === 200) {
                        setListStation(resp.data);
                    }
                } catch (error) {
                    setError("Không thể tải dữ liệu");
                }
                
            })()
        }
    }

    return (
        <div>
            <div className='row'>
                <h3 className='col-xl-8'>Danh sách bãi đậu xe</h3>
                <input 
                    className='col-xl-3 border border-secondary' 
                    value={searchKey}
                    onChange={handleOnChange}    
                    placeholder='Nhập tên bãi để xe...'
                />
            </div>
            {error && <div>{error}</div>}
            {listStation.length === 0 && <div>Không có bãi đậu xe</div>}
            {listStation.length > 0 && <ListStation listStation={listStation} handleDetail={ handleToggle}/>}
            {toggle && <StationDetail station={stationDetail} handleOnClose={() => setToggle(prev => !prev)}/>}
        </div>
    )
}

export default ListStationScreen
