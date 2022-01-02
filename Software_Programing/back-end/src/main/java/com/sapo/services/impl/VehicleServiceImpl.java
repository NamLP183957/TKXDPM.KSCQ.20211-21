package com.sapo.services.impl;

import com.sapo.common.ConstantVariableCommon;
import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.entities.Invoice;
import com.sapo.entities.ParkingSlot;
import com.sapo.entities.Vehicle;
import com.sapo.repositories.InvoiceRepository;
import com.sapo.repositories.ParkingSlotRepository;
import com.sapo.repositories.StationRepository;
import com.sapo.repositories.VehicleRepository;
import com.sapo.services.VehicleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class.toString());

    private final VehicleRepository vehicleRepository;
    private final ParkingSlotRepository parkingSlotRepository;
    private final StationRepository stationRepository;
    private final InvoiceRepository invoiceRepository;


    //lấy 1 list vehicle trong bãi xe
    @Override
    public List<Vehicle> getListVehicleInStation(int stationId) {
        List<ParkingSlot> parkingSlots = parkingSlotRepository.findByStationId(stationId);
        List<Vehicle> vehicles = new ArrayList<>();
        parkingSlots.forEach(parkingSlot -> {
            Vehicle vehicle = vehicleRepository.findVehicleByParkingSlotId(parkingSlot.getId());
            vehicles.add(vehicle);
        });
        return vehicles;
    }

    //Hàm tìm Vehicle bằng id
    @Override
    public Vehicle findVehicleById(int id) {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);
        return vehicle;
    }

    @Override
    public List<Vehicle> getListVehicleInRentTime() {
        List<Vehicle> rentVehicles = vehicleRepository.findVehicleByStatus(ConstantVariableCommon.RENTED_VEHICLE_STATUS);
        rentVehicles.addAll(vehicleRepository.findVehicleByStatus(ConstantVariableCommon.RENTED_DAY_VEHICLE_STATUS));
        return rentVehicles;
    }

    @Override
    public VehicleDTOResponse findVehicleInRentTimeById(int id) {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);
        VehicleDTOResponse vehicleDTOResponse = new VehicleDTOResponse();
        vehicleDTOResponse.setId(vehicle.getId());
        vehicleDTOResponse.setType(vehicle.getType());
        vehicleDTOResponse.setLicensePlate(vehicle.getLicensePlate());
        vehicleDTOResponse.setBattery(vehicle.getBattery());
        vehicleDTOResponse.setMaxTime(vehicle.getMaxTime());
        vehicleDTOResponse.setStatus(vehicle.getStatus());
        Invoice invoice = invoiceRepository.findInvoiceByVehicleIdAndStatus(vehicle.getId(), 1);
        long totalRentTime = new Date().getTime() - invoice.getStartTime();
        vehicleDTOResponse.setTimeRented(totalRentTime);
        double fee = 0;
        if (totalRentTime <= 600) {
            fee = 0;
        }
        else {
            if (totalRentTime <= 1800) {
                fee = 10000;
            }
            else {
                fee = 10 + (totalRentTime - 1800)/900 * 3000;
                if (((totalRentTime - 1800) % 900) > 0) {
                    fee = fee + 3000;
                }
            }
        }
        if (vehicle.getType() != 1) fee = fee * 1.5;
        vehicleDTOResponse.setFee(fee);
        return vehicleDTOResponse;
    }

    @Override
    public VehicleDTOResponse findNotRentVehicleByParkingSlot(int parkingSlotId) {
        Vehicle vehicle = vehicleRepository.findVehicleByParkingSlotIdAndStatus(parkingSlotId, ConstantVariableCommon.NOT_RENT_VEHICLE_STATUS);
        return new VehicleDTOResponse(vehicle);
    }

    @Override
    public boolean updateVehicleStatusAndParkingSlot(Integer vehicleId, Integer status, Integer parkingSlotId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        if (vehicle == null) {
            return false;
        } else {
            vehicle.setStatus(status);
            vehicle.setParkingSlotId(parkingSlotId);
            vehicleRepository.save(vehicle);
            return true;
        }
    }

    //Hàm lưu Vehicle bằng VehicleRepository
    private void saveVehicleRepository(Vehicle vehicle) {
        try {
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            LOGGER.error("ERROR: {} | Save vehicle", vehicle);
        }
    }

}
