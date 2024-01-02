package com.poscdx.odc.ampro015.domain.entity;

import lombok.Data;
import java.util.List;

@Data
public class Pme00AllRoomResponse {

    private int status;
    private List<Pme00Room> data;
    private String message;
}
