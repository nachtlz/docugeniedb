package app.docugeniedb.service.impl;

import app.docugeniedb.dto.ChatDTO;
import app.docugeniedb.dto.FileDTO;
import app.docugeniedb.dto.PersonDTO;
import app.docugeniedb.entity.Chat;
import app.docugeniedb.entity.File;
import app.docugeniedb.exception.ResourceNotFoundException;
import app.docugeniedb.mapper.ChatMapper;
import app.docugeniedb.mapper.FileMapper;
import app.docugeniedb.mapper.PersonMapper;
import app.docugeniedb.repository.ChatRepository;
import app.docugeniedb.service.ChatService;
import app.docugeniedb.service.FileService;
import app.docugeniedb.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    private PersonService personService;

    private FileService fileService;

    @Override
    @Transactional
    public ChatDTO createChat(ChatDTO chatDTO) {
        Chat chat = ChatMapper.mapToChat(chatDTO);
        PersonDTO personDTO = personService.getPersonById(chatDTO.getPerson_id());
        chat.setPerson(PersonMapper.mapToPerson(personDTO));
        Chat savedChat = chatRepository.save(chat);
        return ChatMapper.mapToChatDTO(savedChat);
    }

    @Override
    public ChatDTO getChatById(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Chat does not exist with given id: " + chatId));
        return ChatMapper.mapToChatDTO(chat);
    }

    @Override
    public List<ChatDTO> getChatsByPersonId(Long personId) {
        PersonDTO personDTO = personService.getPersonById(personId);
        List<Chat> chatList = chatRepository.findByPersonId(personId);
        return chatList.stream().map(ChatMapper::mapToChatDTO).toList();
    }

    @Override
    public List<ChatDTO> getAllChats() {
        List<Chat> chatList = chatRepository.findAll();
        return chatList.stream().map(ChatMapper::mapToChatDTO).toList();
    }

    @Override
    @Transactional
    public ChatDTO updateChat(Long chatId, ChatDTO updatedChat) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Chat does not exist with given id: " + chatId));
        PersonDTO personDTO = personService.getPersonById(updatedChat.getPerson_id());
        chat.setName(updatedChat.getName());
        chat.setDescription(updatedChat.getDescription());
        chat.setCreationDate(Timestamp.valueOf(updatedChat.getCreation_date()));
        chat.setMode(updatedChat.getMode());
        chat.setHeader(updatedChat.getHeader());
        chat.setColor(updatedChat.getColor());
        chat.setSenderColor(updatedChat.getSender_color());
        chat.setReceiverColor(updatedChat.getReceiver_color());
        chat.setBackgroundColor(updatedChat.getBackground_color());
        chat.setExternalColor(updatedChat.getExternal_color());
        chat.setOrientation(updatedChat.getOrientation());
        chat.setPerson(PersonMapper.mapToPerson(personDTO));
        Chat savedChat = chatRepository.save(chat);
        return ChatMapper.mapToChatDTO(savedChat);
    }

    @Override
    @Transactional
    public void deleteChat(Long chatId) {
        chatRepository.findById(chatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Chat does not exist with given id: " + chatId));
        chatRepository.deleteById(chatId);
    }

    @Override
    @Transactional
    public void addFileToChat(Long chatId, Long fileId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Chat does not exist with given id: " + chatId));
        FileDTO fileDTO = fileService.getFileById(fileId);
        chat.getFiles().add(FileMapper.mapToFile(fileDTO));
        chatRepository.save(chat);
    }

    @Override
    @Transactional
    public void deleteFileToChat(Long chatId, Long fileId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Chat does not exist with given id: " + chatId));
        FileDTO fileDTO = fileService.getFileById(fileId);
        chat.getFiles().remove(FileMapper.mapToFile(fileDTO));
        chatRepository.save(chat);
    }
}
