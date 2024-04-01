package app.docugeniedb.service;

import app.docugeniedb.dto.ChatDTO;

import java.util.List;

public interface ChatService {

    ChatDTO createChat(ChatDTO chatDTO);

    ChatDTO getChatById(Long chatId);

    List<ChatDTO> getChatsByPersonId(Long personId);

    List<ChatDTO> getAllChats();

    ChatDTO updateChat(Long chatId, ChatDTO updatedChat);

    void deleteChat(Long chatId);

    void addFileToChat(Long chatId, Long fileId);

    void deleteFileToChat(Long chatId, Long fileId);
}
