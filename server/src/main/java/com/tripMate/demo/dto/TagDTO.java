package com.tripMate.demo.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TagDTO {
    private int id;
    private String title;
    private String icon;
}
