import axios from "axios"
import {SERVER_PORT} from '../variables/Server'
const VIEW_STATION_API_URL = `http://localhost:${SERVER_PORT}/api/stations/`

const getListStation = () => {
    return axios.get(`${VIEW_STATION_API_URL}`);
}

const getStationBySearchKey = (searchKey) => {
    return axios.get(`${VIEW_STATION_API_URL}search/${searchKey}`);
}

const getDetailStation = (id) => {
    return axios.get(`${VIEW_STATION_API_URL}${id}`);
}

export {
    getListStation,
    getStationBySearchKey,
    getDetailStation
};