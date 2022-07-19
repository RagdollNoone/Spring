package com.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DemoRequest implements Serializable {
    private static final long serialVersionUID = 8579175038446187064L;

    private String subject;
}
