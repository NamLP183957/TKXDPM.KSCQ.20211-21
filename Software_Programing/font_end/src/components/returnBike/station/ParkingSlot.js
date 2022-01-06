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
    // switch(type){
    //     case 1:
    //         typeBike = "Xe đạp";
    //         break;
    //     case 2:
    //         typeBike = "Xe đạp đơn điện";
    //         break;
    //     case 3:
    //         typeBike = "Xe đạp đôi";
    //         break;
    //     case 4:
    //         typeBike = "Xe đạp đôi điện";
    //         break;
    //     default:
    //         break;
    // }
    return (
        <div>
            <PopupDialog handleOnClose={props.handleOnClose}>
                <PopupTitle>
                    <h4 className='text-titlte'>Select blank slot</h4>
                </PopupTitle>
                <PopupBody>
                    <table className='w-100'>
                        <tbody>
                        <tr>
                            <td><span>{typeBike}</span></td>
                            <td>
                                {slots.length > 0 
                                ? <select className="form-select" value={parkingSlot} onChange={(e) => onChangeSlot(e)}>
                                    <option value={parkingSlot} selected hidden>{parkingSlot}</option>
                                    {slots.map((slot, index) => {
                                        var {id, stationId, type, status} = slot;
                                        return(<option key={index} value={id}>{id}</option>)
                                    })}
                                </select> 
                                : <span className='text-danger'>Not Avaible Parking Slot !</span>
                                }
                            </td>
                            <td>
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
