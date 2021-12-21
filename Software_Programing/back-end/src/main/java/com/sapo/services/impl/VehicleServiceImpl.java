package com.sapo.services.impl;

import com.sapo.dao.jpa.VehicleDAO;
import com.sapo.dto.common.Pagination;
import com.sapo.dto.vehicle.VehicleDTOResponse;
import com.sapo.dto.vehicle.VehiclePaginationDTO;
import com.sapo.entities.Vehicle;
import com.sapo.repositories.VehicleRepository;
import com.sapo.services.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class.toString());

    private final VehicleDAO vehicleDAO;
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleDAO vehicleDAO, VehicleRepository vehicleRepository) {
        this.vehicleDAO = vehicleDAO;
        this.vehicleRepository = vehicleRepository;
    }

    //lấy 1 list vehicle và phân trang
    @Override
    public List<VehicleDTOResponse> getListVehicle(String keyword) {
        List<Vehicle> vehicles = vehicleDAO.findAllVehicleByKeyword(keyword);
        List<VehicleDTOResponse> vehicleDTOS = transferVehicleToVehicleDTOInvoice(vehicles);
        return vehicleDTOS;
    }

    private List<VehicleDTOResponse> transferVehicleToVehicleDTOInvoice(List<Vehicle> vehicles) {
        List<VehicleDTOResponse> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            VehicleDTOResponse vehicleDTO = new VehicleDTOResponse(vehicle.getId(), vehicle.getParkingSlotId(), "chuyển type int sang string", vehicle.getLicensePlate(), "battery", "maxtime", "status");
            vehicleDTOS.add(vehicleDTO);
        }
        return vehicleDTOS;
    }

    //Hàm tìm Vehicle bằng id
    @Override
    public Vehicle findVehicleById(int id) {
        Vehicle vehicle = vehicleRepository.findVehicleById(id);
        return vehicle;
    }

    //Hàm search vehicle
    @Override
    public VehiclePaginationDTO searchVehicle(int page, int limit, String keyword) {
        VehiclePaginationDTO vehiclePaginationDTO = new VehiclePaginationDTO();
        List<Vehicle> vehicles = vehicleDAO.findAllVehicleByKeyword(keyword);
        vehiclePaginationDTO = findAllVehicleDTO(page, limit, vehicles);
        return vehiclePaginationDTO;
    }


    // Chuyển Vehicle sang VehicleDTO
    private List<VehicleDTOResponse> transferVehicleToVehicleDTO(List<Vehicle> vehicles) {
        List<VehicleDTOResponse> vehicleDTOResponses = new ArrayList<>();

        vehicles.forEach(vehicle -> {
            if (vehicle.getLicensePlate() == null) {
                vehicle.setLicensePlate(" ");
            }
            VehicleDTOResponse vehicleDTOResponse = new VehicleDTOResponse(vehicle.getId(), vehicle.getParkingSlotId(), "chuyển type int sang string", vehicle.getLicensePlate(), "battery", "maxtime", "status");
            vehicleDTOResponses.add(vehicleDTOResponse);
        });
        return vehicleDTOResponses;
    }

    // Chuyển Vehicle sang VehicleDTO
    private VehiclePaginationDTO findAllVehicleDTO(int page, int limit, List<Vehicle> vehicles) {
        List<VehicleDTOResponse> vehicleDTOResponses = transferVehicleToVehicleDTO(vehicles);
        VehiclePaginationDTO vehiclePaginationDTO = findAllVehiclePaginationDTO(page, limit, vehicleDTOResponses);
        return vehiclePaginationDTO;
    }

    private VehiclePaginationDTO findAllVehiclePaginationDTO(int page, int limit, List<VehicleDTOResponse> vehicleDTOS) {
        List<VehicleDTOResponse> vehicleDTOList = new ArrayList<VehicleDTOResponse>();
        if ((vehicleDTOS.size() - (page * limit - limit)) > limit) {
            for (int i = page * limit - limit; i < page * limit; i++) {
                vehicleDTOList.add(vehicleDTOS.get(i));
            }
        } else {
            for (int i = page * limit - limit; i < vehicleDTOS.size(); i++) {
                vehicleDTOList.add(vehicleDTOS.get(i));
            }
        }
        Pagination pagination = new Pagination(page, limit, vehicleDTOS.size());
        VehiclePaginationDTO vehiclePaginationDTO = new VehiclePaginationDTO(vehicleDTOList, pagination);
        return vehiclePaginationDTO;
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
