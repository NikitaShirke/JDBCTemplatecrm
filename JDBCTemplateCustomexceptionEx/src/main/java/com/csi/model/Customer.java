package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private int custId;
    private String custName;
    private String custAddress;
    private long custContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy",timezone = "Asia/Kolkata")
    private Date custDOB;
    private String custEmail;
    private String custPassword;
}
