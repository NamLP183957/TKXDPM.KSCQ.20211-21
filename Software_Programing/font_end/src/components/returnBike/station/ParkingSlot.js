import axios from 'axios';
import React, { useEffect, useState, Component } from 'react';
import { Form } from "react-bootstrap";
import PopupBody from '../../popup/PopupBody';
import PopupDialog from '../../popup/PopupDialog';
import PopupTitle from '../../popup/PopupTitle';
import { useHistory } from 'react-router-dom';
import {BIKE_TYPE} from '../../../variables/ConstantCommon'
import {getParkingSLot, processTransaction} from '../../../services/ReturnBikeService'

function ParkingSlot(props) {
    const history = useHistory();
    const {stationId, type, vehicle} = props;
    const [slots, setSlots] = useState([]);
    const [parkingSlot, setParkingSlot] = useState("");

    const card = {
        owner: 'Group 21',
        cardCode: 'kscq1_group21_2021',
        dateExpired: '1125',
        cvvCode: '899'
    };

    useEffect(() => {
        (async() => {
            // const resp = await axios.get("http://localhost:8081/api/return-bike/get-parking-slot/" + stationId + "/" + type);
            const resp = await getParkingSLot(stationId, type);
            console.log(resp);
            setSlots(resp.data);
        })()
    }, [])

    const onChangeSlot = (e) => {
        setParkingSlot(e.target.value);
        console.log(parkingSlot);
    }

    const handleReturnBike = () => {

        const paymentForm = {card, parkingSlotId: parkingSlot, vehicle};
        const paymentFormJSON = JSON.stringify(paymentForm);
        console.log(paymentFormJSON);
        (async (paymentFormJSON) => {
            // const resp = await axios.post("http://localhost:8081/api/return-bike/process-transaction", paymentFormJSON, {
            //     headers: {"Content-Type": "application/json"}
            // });
        const resp = await processTransaction(paymentFormJSON);
            if (resp.status === 200) {
                console.log(resp.data);
                const {card, createdAt, errorCode, transactionContent, transactionId, amount} = resp.data;
                history.push({
                    pathname: "/renting-return-bike/transaction-result",
                    state: {
                        amount: amount,
                        createdAt: createdAt,
                        card: card,
                        transactionContent: transactionContent
                    }
                })
            } else {
                console.log("error transaction");
            }
            
        }) (paymentFormJSON);
    }

    let typeBike = BIKE_TYPE[type];

    return (
        <div>
            <PopupDialog handleOnClose={props.handleOnClose}>
                <PopupTitle>
                    <h4 className='text-titlte'>Select blank slot</h4>
                </PopupTitle>
                <PopupBody>
                    <table className='w-100'>
                        <tbody>
                        <tr className='row'>
                            <td className='col-4'><span>{typeBike}</span></td>
                            <td className='col-4'>
                                {slots.length > 0 
                                ? <select className="form-select" value={parkingSlot} onChange={(e) => onChangeSlot(e)}>
                                    <option value={parkingSlot} selected hidden>{parkingSlot}</option>
                                    {slots.map((slot, index) => {
                                        var {id, stationId, type, status} = slot;
                                        return(<option key={index} value={id}>{id}</option>)
                                    })}
                                </select> 
                                : <span className='text-danger fs-5'>Không có ô trống hợp lệ !</span>
                                }
                            </td>
                            <td className='col-4'>
                                {parkingSlot!="" 
                                ?<button className='btn btn-primary' onClick={() => handleReturnBike()} >Trả xe</button>
                                :<button className='btn btn-primary' disabled>Trả xe</button>}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </PopupBody>
            </PopupDialog>
        </div>
    )
}

export default ParkingSlot
