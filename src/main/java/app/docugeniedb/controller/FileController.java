package app.docugeniedb.controller;

import app.docugeniedb.dto.FileDTO;
import app.docugeniedb.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/files")
public class FileController {

    private FileService fileService;

    @PostMapping
    public ResponseEntity<FileDTO> createFile(@RequestBody FileDTO fileDTO) {
        FileDTO savedFileDTO = fileService.createFile(fileDTO);
        return new ResponseEntity<>(savedFileDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<FileDTO> getFileById(@PathVariable("id") Long fileId) {
        FileDTO savedFileDTO = fileService.getFileById(fileId);
        return ResponseEntity.ok(savedFileDTO);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<FileDTO>> getFilesByPersonId(@PathVariable("id") Long personId) {
        List<FileDTO> fileDTOS = fileService.getFilesByPersonId(personId);
        return ResponseEntity.ok(fileDTOS);
    }

    @GetMapping
    public ResponseEntity<List<FileDTO>> getAllFiles() {
        List<FileDTO> fileDTOS = fileService.getAllFiles();
        return ResponseEntity.ok(fileDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<FileDTO> updateFile(@PathVariable("id") Long fileId, @RequestBody FileDTO fileDTO) {
        FileDTO updateFileDTO = fileService.updateFile(fileId, fileDTO);
        return ResponseEntity.ok(updateFileDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Long fileId) {
        fileService.deleteFile(fileId);
        return ResponseEntity.ok("File deleted successfully!");
    }
}
