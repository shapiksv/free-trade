package com.trade.free.dto.event;

import com.trade.free.dto.enums.EventUserType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventUserDto {

    private Integer userId;
    private String firstName;
    private String secondName;
    private String email;
    private EventUserType type;
}
