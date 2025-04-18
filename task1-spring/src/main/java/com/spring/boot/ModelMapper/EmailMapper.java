package com.spring.boot.ModelMapper;

import com.spring.boot.dto.EmailDto;
import com.spring.boot.model.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmailMapper {

    EmailMapper EMAIL_MAPPER = Mappers.getMapper(EmailMapper.class);
    @Mapping(source = "employee.id", target = "employeeId")
    EmailDto toEmailDto(Email email);

    @Mapping(source = "employeeId", target = "employee.id")
    Email fromEmailDto(EmailDto emailDto);

    List<EmailDto> toEmailDtos(List<Email> emails);
    List<Email> fromEmailDtos(List<EmailDto> emailDtos);
}
