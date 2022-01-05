import axios from "axios";
import {SERVER_PORT} from '../variables/Server'
const VEHICLE_API_URL = `http://localhost:${SERVER_PORT}/api/vehicles/`

const getListVehicleInStation = (stationId) => {
    return axios.get(VEHICLE_API_URL + "/list/" + stationId);
};

const findVehicleById = (id) => {
    return axios.get(VEHICLE_API_URL + "/" + id);
};

export default {
    getListVehicleInStation,
    findVehicleById
};