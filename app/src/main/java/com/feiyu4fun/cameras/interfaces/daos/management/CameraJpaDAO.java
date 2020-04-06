package com.feiyu4fun.cameras.interfaces.daos.management;

import com.feiyu4fun.cameras.dtos.management.CameraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cameraJpaDAO")
public interface CameraJpaDAO extends JpaRepository<CameraDTO, Long> {

    List<CameraDTO> findAllByBrand(String brand);

}
