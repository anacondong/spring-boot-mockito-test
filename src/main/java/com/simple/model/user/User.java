package com.simple.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String status;
}
