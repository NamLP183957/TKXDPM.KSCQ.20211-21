import axios from 'axios'
import React, { useEffect, useState } from 'react'
import {getListStation} from '../../../services/ViewStation'
function ListStation(props) {

    const [stations, setStations] = useState([]);

    useEffect(() => {
        (async () => {
            //const resp = await axios.get("http://localhost:8081/api/stations/");
            const resp = await getListStation();
            console.log(resp);
            setStations(resp.data);
        })()
    }, [])
    
    const handleOnClick = (id) => {
        if (props.handleOnClick) {
            props.handleOnClick(id)
        }
    }

    return (
        <div className='w-100 d-flex flex-column align-items-center text-start overflow-auto'>
            {stations.map((station, index) => {
                var {address, area, name} = station;
                return( 
                    <div className='w-50' key={index} onClick={() => handleOnClick(station.id)}>
                        <div className="card mb-3">
                            <div className="card-header bg-warning fw-bold">
                                 <span className='me-3'>{index + 1}.</span> {name} 
                            </div>
                            <div className="card-body">
                                <p><span className='fw-bold'>Adrress:</span> {address}</p>
                                <span className='fw-bold'>Area:</span> {area}
                            </div>
                        </div>
                    </div>
                )
            })}
        </div>
    )
}

export default ListStation
