package com.tripMate.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ImageDTO {
    private int id;

    private String url;

    private String alt;

}
