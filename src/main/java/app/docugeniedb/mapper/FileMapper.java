package app.docugeniedb.mapper;

import app.docugeniedb.dto.ChatDTO;
import app.docugeniedb.dto.FileDTO;
import app.docugeniedb.entity.Chat;
import app.docugeniedb.entity.File;

import java.util.Set;
import java.util.stream.Collectors;

public class FileMapper {

    public static FileDTO mapToFileDTO(File file) {
        return new FileDTO(
                file.getId(),
                file.getFilename(),
                file.getFilepath(),
                file.getDescription(),
                file.getCategory(),
                file.getPerson().getId()
        );
    }

    public static File mapToFile(FileDTO fileDTO) {
        return new File(
                fileDTO.getFile_id(),
                fileDTO.getFilename(),
                fileDTO.getFilepath(),
                fileDTO.getDescription(),
                fileDTO.getCategory(),
                null,
                null
        );
    }
}
