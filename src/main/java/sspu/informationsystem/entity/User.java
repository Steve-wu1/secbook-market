package sspu.informationsystem.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {
    private Integer userId;

    private String uName;

    private Integer uSex;

    private String uPhone;

    private String uAccount;

    private String uPassword;

    private static final long serialVersionUID = 1L;
}