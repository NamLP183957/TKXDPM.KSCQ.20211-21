import axios from "axios"
import {SERVER_PORT} from '../variables/Server'
const RENTBIKE_API_URL = `http://localhost:${SERVER_PORT}/api/rentbike/`

const postBikeCode = (bikeCode) => {
    return axios.post(`${RENTBIKE_API_URL}/process-bike-code`, bikeCode, {
        headers: {"Content-Type": "application/json"}
    });
}

const postDeposit = (paymentForm, vehicleId) => {
    return axios.post(RENTBIKE_API_URL + `/deposit.${vehicleId}`, paymentForm, {
        headers: {"Content-Type": "application/json"}
    });
}

export {
    postBikeCode,
    postDeposit
};