package com.seu.edu.bd.cas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ClassLogFillerReq {
    @JsonFormat(pattern = "mm-dd-yyy")
    private Date start;
    @JsonFormat(pattern = "mm-dd-yyy")
    private Date end;
}
