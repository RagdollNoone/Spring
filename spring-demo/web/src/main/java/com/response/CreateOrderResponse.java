package com.response;

import com.IResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class CreateOrderResponse
implements IResponse, Serializable {
    private static final long serialVersionUID = -1825167930596408273L;

    private String orderNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
