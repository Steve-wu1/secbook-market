package sspu.informationsystem.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Store implements Serializable {
    private Integer storeId;

    private String sName;

    private String sPhone;

    private String sAddress;

    private String sDetails;

    private String sAccount;

    private String sPassword;

    private String sPhoto;

    private static final long serialVersionUID = 1L;
}