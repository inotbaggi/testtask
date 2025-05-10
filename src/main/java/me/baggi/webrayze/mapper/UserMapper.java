package me.baggi.webrayze.mapper;

import me.baggi.webrayze.dto.UserDTO;
import me.baggi.webrayze.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
}
