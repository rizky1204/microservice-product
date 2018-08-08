package system.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CreateProductVO {

    private String productName;
    private String productCode;
    private String merk;
    private BigInteger stock;
}
