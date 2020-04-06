package com.feiyu4fun.cameras.interfaces.management;

import com.feiyu4fun.cameras.dtos.management.CameraDTO;

import java.util.List;

public interface CamerasService {
    List<CameraDTO> getCamerasByBrand(String brand);
}
