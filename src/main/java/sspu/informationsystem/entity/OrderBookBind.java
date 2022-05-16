package sspu.informationsystem.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderBookBind implements Serializable {
    private Integer orderBookId;

    private Integer oId;

    private Integer bId;

    private Integer bNumber;

    private static final long serialVersionUID = 1L;
}