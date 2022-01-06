import React, { useState } from 'react'
import ListStation from './ListStation'
import ParkingSlot from './ParkingSlot';
import SearchForm from './SearchForm'

function StationScreen(props) {
    /*LẤY TỪ ÔNG ĐẠT */
    // const vehicle = {
    //     'id':1,
    //     'parkingSlotId': 1,
    //     'type': 1,
    //     'lisensePlate': "abcd",
    //     'battery':1,
    //     'maxTime': 1,
    //     'status': 1
    // };
    const {vehicle} = props.location;
    const type = vehicle.type;
    const [toggle, setToggle] = useState(false);
    const [stationId, setStationId] = useState();
    const handleOnClick = (stationId) => {
        setToggle(prev => !prev);
        setStationId(stationId);
    }

    const togglePopup = () => {
        setToggle(prev => !prev);

    }

    return (
        <div>
            <h2>List Station</h2>
            {/* <SearchForm /> */}
            <ListStation handleOnClick={handleOnClick}/>
            {toggle && <ParkingSlot type={type} stationId={stationId} vehicle={vehicle} handleOnClose={() => togglePopup()}/>}

        </div>
    )
}

export default StationScreen
