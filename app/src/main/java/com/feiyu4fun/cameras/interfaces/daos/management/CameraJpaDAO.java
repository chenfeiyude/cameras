package com.feiyu4fun.cameras.interfaces.daos.management;

import com.feiyu4fun.cameras.dtos.management.CameraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cameraJpaDAO")
public interface CameraJpaDAO extends JpaRepository<CameraDTO, Long> {
	
}
