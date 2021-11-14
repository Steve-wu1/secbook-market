package sspu.informationsystem.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Order implements Serializable {
    private Integer orderId;

    private Date oTime;

    private Integer oState;

    private Date oRequireTime;

    private Integer oUserId;

    private Integer oRank;

    private String oComment;

    private static final long serialVersionUID = 1L;
}