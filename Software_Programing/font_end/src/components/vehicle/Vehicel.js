import VehicleService from "../../services/VehicleService";
import "./css/vehicle.css";

function Vehicle(props) {
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

  const stationId = props.stationId;

  useEffect(() => {
    async function fetchvehicleList() {
      try {
        VehicleService.getListVehicleInStation(stationId)
          .then((res) => {
            const data = res.data;
            console.log("data: ", data);
            setVehicles(
              data.map((vehicle) => {
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
            setIsLoaded(true);
          })
          .catch(function (error) {
            console.log("ERROR: " + error.response.data.status);
          });
      } catch (error) {
        console.log("Failed to fetch vehicle list: ", error.message);
        setError(error);
      }
    }
    fetchvehicleList();
  }, [filters]);

  const handleOnClick = (vehicle) => {
    history.push({
      pathname: "/rent-bike",
    });
  };

  return (
    <div>
      {vehicles.map((vehicle) => (
        <Card style={{ width: "18rem" }} className="card">
          <Card.Body>
            <Card.Title>Danh sách xe</Card.Title>
            <div>Vị trí: {vehicle.parkingSlotId}</div>
            <div>Biển số: {vehicle.licensePlate}</div>
            <div>Loại xe: {BIKE_TYPE[vehicle.type]}</div>
            <div>Thời lượng pin (dành cho xe điện): {vehicle.battery}</div>
            <div>
              Thời gian sử dụng tối đa (dành cho xe điện): {vehicle.maxTime}
            </div>
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
