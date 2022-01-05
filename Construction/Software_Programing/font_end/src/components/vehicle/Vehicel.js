import React, { useEffect, useState } from "react";
import { Button, Card } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import VehicleService from "../../services/VehicleService";
import "./css/Vehicle.css";
import { BIKE_TYPE } from "../../variables/ConstantCommon";
function Vehicle(props) {
  const history = useHistory();
  React.useEffect(() => {
    // Specify how to clean up after this effect:
    return function cleanup() {
      // to stop the warning of calling setTl of unmounted component
      var id = window.setTimeout(null, 0);
      while (id--) {
        window.clearTimeout(id);
      }
    };
  });

  const [vehicles, setVehicles] = useState([]);

  const { stationId } = props.location;
  useEffect(() => {
    async function fetchvehicleList() {
      try {
        VehicleService.getListVehicleInStation(stationId)
          .then((res) => {
            const data = res.data;
            setVehicles(
              data.map((vehicle) => {
                console.log(vehicle);
                return {
                  select: false,
                  parkingSlotId: vehicle.parkingSlotId,
                  type: vehicle.type,
                  licensePlate: vehicle.licensePlate,
                  battery: vehicle.battery,
                  maxTime: vehicle.maxTime,
                  status: vehicle.status,
                };
              })
            );
            // setIsLoaded(true);
          })
          .catch(function (error) {
            console.log("ERROR: " + error.response.data.status);
          });
      } catch (error) {
        console.log("Failed to fetch vehicle list: ", error.message);
        // setError(error);
      }
    }
    fetchvehicleList();
  }, []);

  const handleOnClick = () => {
    history.push({
      pathname: "/rent-bike/",
    });
  };
  if (vehicles.length === 0) {
    return (
      <div style={{ fontSize: "25px", color: "green" }}>
        Không có xe nào trong bãi
      </div>
    );
  }
  return (
    <div>
      {vehicles.map((vehicle) => (
        <Card style={{ width: "18rem" }} className="card">
          <Card.Body>
            <Card.Title>Vị trí: {vehicle.parkingSlotId}</Card.Title>
            <div>Biển số: {vehicle.licensePlate}</div>
            <div>Loại xe: {BIKE_TYPE[vehicle.type]}</div>
            {vehicle.type === 2 || vehicle.type === 4 ? <div>Thời lượng pin: {vehicle.battery} %</div> : null}
            {vehicle.type === 2 || vehicle.type === 4 ? <div>Thời gian sử dụng tối đa: {vehicle.maxTime} phút</div> : null}
            <br />
            <Button variant="danger" onClick={() => handleOnClick()}>
              Thuê xe
            </Button>
          </Card.Body>
        </Card>
      ))}
    </div>
  );
}

export default Vehicle;
