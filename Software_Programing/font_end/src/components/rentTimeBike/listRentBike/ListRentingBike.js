import React from 'react'
import RentingBike from './RentingBike';

function ListRentingBike(props) {
    const {listBike} = props;
    return (
        <div>
            {listBike.map((bike, index) => (
                <RentingBike key={index} bike={bike}/>
            ))}
        </div>
    )
}

export default ListRentingBike
