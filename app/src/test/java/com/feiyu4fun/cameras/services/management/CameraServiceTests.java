package com.feiyu4fun.cameras.services.management;

import com.feiyu4fun.cameras.dtos.management.CameraDTO;
import com.feiyu4fun.cameras.enums.Brands;
import com.feiyu4fun.cameras.interfaces.daos.management.CameraJpaDAO;
import com.feiyu4fun.cameras.interfaces.management.CamerasService;
import org.apache.http.auth.AuthenticationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CameraServiceTests {
    @MockBean
    private CameraJpaDAO cameraJpaDAO;

    @Autowired
    private CamerasService camerasService;

    private List<CameraDTO> cameraDTOS;

    @Before
    public void setUp() throws Exception {
        cameraDTOS = new ArrayList<>();
        CameraDTO cameraDTO = new CameraDTO();
        cameraDTO.setBrand(Brands.PENTAX.toString());
        cameraDTO.setModel("KX");
        cameraDTOS.add(cameraDTO);
        Mockito.when(cameraJpaDAO.findAllByBrand(cameraDTO.getBrand())).thenReturn(cameraDTOS);

    }

    @Test
    public void getCamerasByBrandTest() throws AuthenticationException {
        List<CameraDTO> foundCameras = camerasService.getCamerasByBrand(cameraDTOS.get(0).getBrand());
        assertNotNull(foundCameras);
        assertEquals(foundCameras.size(), cameraDTOS.size());
        assertEquals(foundCameras.get(0).getBrand(), cameraDTOS.get(0).getBrand());
        assertEquals(foundCameras.get(0).getModel(), cameraDTOS.get(0).getModel());
    }
}
