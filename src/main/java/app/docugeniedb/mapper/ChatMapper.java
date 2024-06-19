package app.docugeniedb.mapper;

import app.docugeniedb.dto.ChatDTO;
import app.docugeniedb.dto.FileDTO;
import app.docugeniedb.dto.MessageDTO;
import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.entity.Chat;
import app.docugeniedb.entity.File;
import app.docugeniedb.entity.Message;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ChatMapper {

    public static ChatDTO mapToChatDTO(Chat chat) {
        List<MessageDTO> messageDTOS = chat.getMessages().stream().map(MessageMapper::mapToMessageDTO).toList();
        Set<FileDTO> fileDTOSSet = chat.getFiles().stream().map(FileMapper::mapToFileDTO).collect(Collectors.toSet());
        return new ChatDTO(
                chat.getId(),
                chat.getName(),
                chat.getDescription(),
                chat.getCreationDate().toString(),
                chat.getMode(),
                chat.getHeader(),
                chat.getColor(),
                chat.getSenderColor(),
                chat.getReceiverColor(),
                chat.getBackgroundColor(),
                chat.getExternalColor(),
                chat.getOrientation(),
                messageDTOS,
                chat.getPerson().getId(),
                fileDTOSSet
        );
    }

    public static Chat mapToChat(ChatDTO chatDTO) {
        List<Message> messageList = Optional.ofNullable(chatDTO)
                .map(ChatDTO::getMessages)
                .orElse(Collections.emptyList())
                .stream()
                .map(MessageMapper::mapToMessage)
                .toList();
        Set<File> fileSet = Optional.ofNullable(chatDTO)
                .map(ChatDTO::getFiles)
                .orElse(Collections.emptySet())
                .stream()
                .map(FileMapper::mapToFile)
                .collect(Collectors.toSet());
        return new Chat(
                chatDTO.getChat_id(),
                chatDTO.getName(),
                chatDTO.getDescription(),
                Timestamp.valueOf(chatDTO.getCreation_date()),
                chatDTO.getMode(),
                chatDTO.getHeader(),
                chatDTO.getColor(),
                chatDTO.getSender_color(),
                chatDTO.getReceiver_color(),
                chatDTO.getBackground_color(),
                chatDTO.getExternal_color(),
                chatDTO.getOrientation(),
                messageList,
                null,
                fileSet
        );
    }
}
