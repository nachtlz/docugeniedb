package app.docugeniedb.mapper;

import app.docugeniedb.dto.ChatDTO;
import app.docugeniedb.dto.FileDTO;
import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.entity.Chat;
import app.docugeniedb.entity.File;
import app.docugeniedb.entity.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PersonMapper {

    public static PersonDTO mapToPersonDTO(Person person) {
        List<FileDTO> fileDTOS = person.getFiles().stream().map(FileMapper::mapToFileDTO).toList();
        List<ChatDTO> chatDTOS = person.getChats().stream().map(ChatMapper::mapToChatDTO).toList();
        return new PersonDTO(
                person.getId(),
                person.getUsername(),
                person.getPassword(),
                fileDTOS,
                chatDTOS
        );
    }

    public static Person mapToPerson(PersonDTO personDTO) {
        List<File> fileList = Optional.ofNullable(personDTO)
                .map(PersonDTO::getFiles)
                .orElse(Collections.emptyList())
                .stream()
                .map(FileMapper::mapToFile)
                .toList();
        List<Chat> chatList = Optional.ofNullable(personDTO)
                .map(PersonDTO::getChats)
                .orElse(Collections.emptyList())
                .stream()
                .map(ChatMapper::mapToChat)
                .toList();
        return new Person(
                personDTO.getPerson_id(),
                personDTO.getUsername(),
                personDTO.getPassword(),
                fileList,
                chatList
        );
    }
}
