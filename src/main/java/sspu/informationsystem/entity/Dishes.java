package sspu.informationsystem.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class Dishes implements Serializable {
    private Integer dishesId;

    private String dName;

    private Double dPrice;

    private String dDetails;

    private String dPhoto;

    private Integer dStoreId;

    private static final long serialVersionUID = 1L;
}