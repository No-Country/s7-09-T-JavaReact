package com.tripMate.demo.dto;

import com.tripMate.demo.entity.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoryDTO {

    private int id;
    private String title;
    private String icon;
    private Set<TagDTO> tags = new HashSet<>();
}
