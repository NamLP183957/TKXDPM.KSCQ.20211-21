import axios from "axios"
import {SERVER_PORT} from '../variables/Server'
const RETURNBIKE_API_URL = `http://localhost:${SERVER_PORT}/api/return-bike`

const getParkingSLot = (stationId, type) => {
    return axios.get(RETURNBIKE_API_URL + "/get-parking-slot/" + stationId + "/" + type);
}

const processTransaction = (paymentFormJSON) => {
    return axios.post(`${RETURNBIKE_API_URL}/process-transaction`, paymentFormJSON, {
        headers: {'Content-Type': 'application/json'}
    });
}

export {
    getParkingSLot,
    processTransaction
};