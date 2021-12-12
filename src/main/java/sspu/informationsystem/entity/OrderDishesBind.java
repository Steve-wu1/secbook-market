package sspu.informationsystem.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class OrderDishesBind implements Serializable {
    private Integer orderDishesId;

    private Integer oId;

    private Integer dId;

    private Integer dNumber;

    private static final long serialVersionUID = 1L;
}