package sspu.informationsystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private Integer orderId;

    private Date oTime;

    private Integer oState;

    private String oStateName;

    private Date oRequireTime;

    private Integer oUserId;

    private String oName;

    private String oStore;

    private Integer oStoreId;

    private String oPhone;

    private String oContent;

    private Integer oRank;

    private String oComment;

    private Double oPrice;

    private String oUserAddress;

    private String oDelieverName;

    private String oDelieverNum;

    private static final long serialVersionUID = 1L;
}