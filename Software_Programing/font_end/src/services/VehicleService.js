import axios from "axios";

const VEHICLE_API_URL = "http://localhost:8080/api/vehicles";

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