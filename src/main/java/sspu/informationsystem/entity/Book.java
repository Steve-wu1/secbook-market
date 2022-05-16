package sspu.informationsystem.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private Integer bookId;

    private String bName;

    private Double bPrice;

    private String bDetail;

    private String bPhoto;

    private Integer bStoreId;

    private static final long serialVersionUID = 1L;
}