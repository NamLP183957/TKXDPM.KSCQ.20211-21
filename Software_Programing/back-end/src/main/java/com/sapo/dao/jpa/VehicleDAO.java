package com.sapo.dao.jpa;


import com.sapo.entities.Vehicle;
import com.sapo.services.impl.VehicleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository(value = "VehicleDAO")
@Transactional(rollbackOn = Exception.class)
public class VehicleDAO {
  @PersistenceContext
  private EntityManager entityManager;
  private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class.toString());

    //Hàm tìm Vehicle bắng keyword
    public List<Vehicle> findAllVehicleByKeyword(String keyword){
        String sql = "SELECT * FROM vehicles where 1=1 ";
        if(keyword != null && keyword.length() !=0 ){
            sql = sql + "AND  LCASE(CONCAT(vehicles.license_plate, ' ', vehicles.type)) LIKE LCASE('%" + keyword + "%')";
        }
        Query query = entityManager.createNativeQuery(sql, Vehicle.class);
        return  query.getResultList();
    }

    //hàm tìm vehicle bằng Biển số
    public Vehicle findVehicleByLicensePlate(String licensePlate){
        String sql = "SELECT * FROM vehicles where license_plate = '"+ licensePlate +"'";
        Query query = entityManager.createNativeQuery(sql, Vehicle.class);
        return (Vehicle) query.getSingleResult();
    }

  // Hàm tìm Vehicle bằng id
  public Vehicle findVehicleById(int id){
    String sql = "SELECT * FROM vehicles where id = "+ id;
    Query query = entityManager.createNativeQuery(sql, Vehicle.class);
    return (Vehicle) query.getSingleResult();
  }

}
