package com.tripMate.demo.mapper;

import com.tripMate.demo.dto.ContactDTO;
import com.tripMate.demo.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );

    ContactDTO toContactDTO(Contact contact);
    Contact toContact(ContactDTO DTO);
    List<ContactDTO> toContactsDTO(List<Contact> contact);
    List<Contact> toContacts(List<ContactDTO> contactDTO);

}
