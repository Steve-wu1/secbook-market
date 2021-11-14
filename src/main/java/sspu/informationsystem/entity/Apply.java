package sspu.informationsystem.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Apply implements Serializable {
    private Integer applyId;

    private Integer aType;

    private Integer aState;

    private Date aDate;

    private Integer aId;

    private Integer dealId;

    private static final long serialVersionUID = 1L;
}