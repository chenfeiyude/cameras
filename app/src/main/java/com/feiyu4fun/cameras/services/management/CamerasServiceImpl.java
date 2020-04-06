package com.feiyu4fun.cameras.services.management;

import com.feiyu4fun.cameras.dtos.management.CameraDTO;
import com.feiyu4fun.cameras.interfaces.daos.management.CameraJpaDAO;
import com.feiyu4fun.cameras.interfaces.management.CamerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("camerasService")
public class CamerasServiceImpl implements CamerasService {
    @Autowired
    private CameraJpaDAO cameraJpaDAO;

    @Override
    public List<CameraDTO> getCamerasByBrand(String brand) {
        return cameraJpaDAO.findAllByBrand(brand);
    }
}
