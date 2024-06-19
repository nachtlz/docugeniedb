package app.docugeniedb.controller;

import app.docugeniedb.dto.ChatDTO;
import app.docugeniedb.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/chats")
public class ChatController {

    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatDTO> createChat(@RequestBody ChatDTO chatDTO) {
        ChatDTO savedChatDTO = chatService.createChat(chatDTO);
        return new ResponseEntity<>(savedChatDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ChatDTO> getChatById(@PathVariable("id") Long chatId) {
        ChatDTO savedChatDTO = chatService.getChatById(chatId);
        return ResponseEntity.ok(savedChatDTO);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<ChatDTO>> getChatsByPersonId(@PathVariable("id") Long personId) {
        List<ChatDTO> chatDTOS = chatService.getChatsByPersonId(personId);
        return ResponseEntity.ok(chatDTOS);
    }

    @GetMapping
    public ResponseEntity<List<ChatDTO>> getAllChats() {
        List<ChatDTO> chatDTOS = chatService.getAllChats();
        return ResponseEntity.ok(chatDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<ChatDTO> updateChat(@PathVariable("id") Long chatId, @RequestBody ChatDTO chatDTO) {
        ChatDTO updateChatDTO = chatService.updateChat(chatId, chatDTO);
        return ResponseEntity.ok(updateChatDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Long chatId) {
        chatService.deleteChat(chatId);
        return ResponseEntity.ok("Chat deleted successfully!");
    }

    @PostMapping("{idChat}/file")
    public ResponseEntity<String> addFilesToChat(@PathVariable("idChat") Long chatId, @RequestParam List<Long> fileIds) {
        for (Long fileId : fileIds) {
            chatService.addFileToChat(chatId, fileId);
        }
        return ResponseEntity.ok("Files added to chat successfully!");
    }

    @DeleteMapping("{idChat}/file")
    public ResponseEntity<String> deleteFilesFromChat(@PathVariable("idChat") Long chatId, @RequestParam List<Long> fileIds) {
        for (Long fileId : fileIds) {
            chatService.deleteFileToChat(chatId, fileId);
        }
        return ResponseEntity.ok("Files deleted from chat successfully!");
    }
}
