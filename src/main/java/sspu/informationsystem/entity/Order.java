package sspu.informationsystem.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Order implements Serializable {
    private Integer orderId;

    private Date oTime;

    private Integer oState;

    private String oStateName;

    private Date oRequireTime;

    private Integer oUserId;

    private String oNmae;

    private String oPhone;

    private Integer oRank;

    private String oComment;

    private Integer oPrice;

    private static final long serialVersionUID = 1L;
}