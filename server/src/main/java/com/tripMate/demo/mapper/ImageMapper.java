package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.ImageDTO;
import com.tripMate.demo.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper( ImageMapper.class );

    ImageDTO toImageDTO(Image Image);
    Image toImage(ImageDTO DTO);
    List<ImageDTO> toImagesDTO(List<Image> images);
    List<Image> toImages(List<ImageDTO> imagesDTO);

}