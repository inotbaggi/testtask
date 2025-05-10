package me.baggi.webrayze.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class SubscriptionDTO {
    private long id;
    private String type;
}
