package sspu.informationsystem.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer userId;

    private String uName;

    private Integer uSex;

    private String uPhone;

    private String uAccount;

    private String uPassword;

    private String uAddress;

    private static final long serialVersionUID = 1L;
}