package storage.model;

import lombok.Data;

import java.time.LocalDateTime;

// Журнал истории операций на складе (привоз, вывоз...)

@Data
public class JournalEntity {

    private Long id;

    private String type;

    private Long product;

    private Long category;

    private LocalDateTime dttm;

    private Long count;
}
