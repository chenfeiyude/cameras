package com.feiyu4fun.cameras.controllers.management;

import com.feiyu4fun.cameras.dtos.management.CameraDTO;
import com.feiyu4fun.cameras.interfaces.management.CamerasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/cameras")
@Validated
@Slf4j
public class CamerasController {

    @Autowired
    private CamerasService camerasService;

    @RequestMapping(method= RequestMethod.GET, value="/get/{brand}")
    public List<CameraDTO> getCamerasByBrand(@RequestHeader Map<String, String> headers,
                                    @PathVariable("brand") String brand) {

        return camerasService.getCamerasByBrand(brand);
    }

}
