package com.sapo.services.impl;

import com.sapo.common.ConstantVariableCommon;
import com.sapo.dto.parkingSlot.ParkingSlotDTO;
import com.sapo.entities.ParkingSlot;
import com.sapo.entities.Vehicle;
import com.sapo.repositories.ParkingSlotRepository;
import com.sapo.repositories.VehicleRepository;
import com.sapo.services.ParkingSlotService;
import com.sapo.utils.API;
import com.sapo.utils.Configs;
import com.sapo.utils.MyMap;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ParkingSlotServiceImpl implements ParkingSlotService {
    /**
     * Ghi lai hanh dong vao nhat ky
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingSlotServiceImpl.class.toString());
    /**
     * doi tuong parkingSlotRpository
     */
    private ParkingSlotRepository parkingSlotRepository;

    /**
     * Lay ra cho trong de xe trong bai ung voi ma code cua xe
     * @param vehicleCodeInput ma xe nhap vao
     * @return
     */
    @Override
    public ParkingSlotDTO processBikeCode(String vehicleCodeInput) {
        ParkingSlotDTO parkingSlotDTO = new ParkingSlotDTO();
        String url = Configs.GET_VEHICLECODE_URL + vehicleCodeInput;
        LOGGER.info("url: " + url);
        MyMap response = null;

        String vehicleCodeReponse = null;
        try {
            String responseText = API.get(url, Configs.TOKEN);
            response = MyMap.toMyMap(responseText, 0);
            vehicleCodeReponse = (String) response.get("vehicleCode");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (vehicleCodeReponse == null) return parkingSlotDTO;
        ParkingSlot parkingSlot = parkingSlotRepository.findByCode(vehicleCodeReponse);
        if (parkingSlot == null) return parkingSlotDTO;
        else return new ParkingSlotDTO(parkingSlot);
    }

    /**
     * lay ra danh sach cho trong theo loai xe
     * @param stationId id bai xe
     * @param type loai xe
     * @return
     */
    @Override
    public List<ParkingSlotDTO> getBlankSlotByType(Integer stationId, Integer type) {
        List<ParkingSlot> parkingSlots = parkingSlotRepository.findByStationIdAndTypeAndStatus(stationId, type, ConstantVariableCommon.BLANK_SLOT_STATUS);
        List<ParkingSlotDTO> listSlotDto = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingSlots) {
            listSlotDto.add(new ParkingSlotDTO(parkingSlot));
        }
        return listSlotDto;
    }

    /**
     * cap nhat trang thai cho cho trong de xe
     * @param id id cho trong
     * @param status trang thai
     * @return
     */
    @Override
    public boolean updateParkingSLotStatus(Integer id, Integer status) {
        ParkingSlot parkingSlot = parkingSlotRepository.findById(id).orElse(null);

        if (parkingSlot == null) {
            return false;
        } else {
            parkingSlot.setStatus(status);
            parkingSlotRepository.save(parkingSlot);
            return true;
        }
    }

    /**
     * lay ra cho trong trong bai theo id
     * @param id id cho trong
     * @return
     */
    @Override
    public ParkingSlot geParkingSlotById(Integer id) {
        return parkingSlotRepository.findById(id).orElse(null);
    }
}
