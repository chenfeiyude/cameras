package com.feiyu4fun.cameras.dtos.management;

import com.feiyu4fun.cameras.dtos.common.BaseDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="camera")
@Data
public class CameraDTO extends BaseDTO {
    private String brand;
    private String model;
}
