package com.feiyu4fun.cameras.controllers.management;

import com.feiyu4fun.cameras.dtos.management.CameraDTO;
import com.feiyu4fun.cameras.enums.Brands;
import com.feiyu4fun.cameras.exceptions.AuthenticationException;
import com.feiyu4fun.cameras.interfaces.daos.management.CameraJpaDAO;
import com.google.inject.internal.util.ImmutableMap;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CamerasControllerTests {
    @MockBean
    private CameraJpaDAO cameraJpaDAO;

    @Autowired
    private CamerasController camerasController;

    private List<CameraDTO> cameraDTOS;

//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();

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
    public void getCamerasByBrandTest() {
        Map<String, String> headers = ImmutableMap.of("username", "feiyu", "token", "test");
        List<CameraDTO> foundCameras = camerasController.getCamerasByBrand(headers, cameraDTOS.get(0).getBrand());

        assertNotNull(foundCameras);
        assertEquals(foundCameras.size(), cameraDTOS.size());
        assertEquals(foundCameras.get(0).getBrand(), cameraDTOS.get(0).getBrand());
        assertEquals(foundCameras.get(0).getModel(), cameraDTOS.get(0).getModel());

        foundCameras = camerasController.getCamerasByBrand(headers, null);
        assertEquals(foundCameras.size(), 0);

        foundCameras = camerasController.getCamerasByBrand(headers, "");
        assertEquals(foundCameras.size(), 0);

        foundCameras = camerasController.getCamerasByBrand(headers, Brands.CANON.toString());
        assertEquals(foundCameras.size(), 0);
    }

    @Test(expected = AuthenticationException.class)
    public void getCamerasByBrandTest_Exception() {
//        expectedException.expectMessage("You are not allowed to use this internal api");
//        expectedException.expectCause(org.hamcrest.Matchers.any(AuthenticationException.class));
//        assertThatExceptionOfType(AuthenticationException.class)
//                .isThrownBy(() -> camerasController.getCamerasByBrand(ImmutableMap.of(), cameraDTOS.get(0).getBrand()));
        camerasController.getCamerasByBrand(ImmutableMap.of(), cameraDTOS.get(0).getBrand());
    }
}
