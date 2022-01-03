import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Table } from "react-bootstrap";
import PopupBody from "../popup/PopupBody";
import PopupDialog from "../popup/PopupDialog";
import PopupFooter from "../popup/PopupFooter";
import PopupTitle from "../popup/PopupTitle";
import { GrCaretNext } from "react-icons/gr";
import { getDetailStation } from "../../services/ViewStation";
function StationDetail(props) {
  const { station } = props;
  const [stationDetail, setStationDetail] = useState(station);
  useEffect(() => {
    (async () => {
      try {
        //const resp = await axios.get("http://localhost:8080/api/stations/" + station.id);
        const resp = await getDetailStation(station.id);
        if (resp.status === 200) {
          console.log(resp.data);
          setStationDetail(resp.data);
        } else {
          console.log("error");
        }
      } catch (error) {
        console.log(error);
      }
    })();
  }, []);

  const handleOnClick = (stationId) => {
    history.push({
      pathname: `/admin/employees/update-employee/${stationId}`,
      stationId: stationId,
    });
  };
  return (
    <PopupDialog handleOnClose={() => props.handleOnClose()}>
      <PopupTitle>Chi tiết bãi đậu xe</PopupTitle>
      <PopupBody>
        <div>Tên bãi xe: {station.name}</div>
        <div>Địa chỉ: {station.address}</div>
        <div>Số lượng xe trong bãi: {stationDetail.numOfCurrBike}</div>
        <div>
          Số lượng ô trống xe đạp thường: {stationDetail.numOfBlankBike}
        </div>
        <div>
          Số lượng ô trống xe đạp điện: {stationDetail.numOfBlankElectricBike}
        </div>
        <div>
          Số lượng ô trống xe đạp đôi: {stationDetail.numOfBlankTwinBike}
        </div>
        <div>
          Số lượng ô trống xe đạp đôi điện:{" "}
          {stationDetail.numOfBlankElectricTwinBike}
        </div>
      </PopupBody>
      <PopupFooter>
        <Button variant="success" onClick={() => handleOnClick(station.id)}>
          Xem xe <GrCaretNext />
        </Button>
      </PopupFooter>
    </PopupDialog>
  );
}

export default StationDetail;
