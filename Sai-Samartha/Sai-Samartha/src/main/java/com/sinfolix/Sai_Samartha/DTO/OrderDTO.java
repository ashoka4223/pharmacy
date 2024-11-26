package com.sinfolix.Sai_Samartha.DTO;

import lombok.Data;
import java.time.LocalDate;
@Data
public class OrderDTO {

    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private LocalDate orderDate;
    private LocalDate modified_time;
    private int status;
    private boolean isPrescriptionBased;
    private boolean reviewed;

}
