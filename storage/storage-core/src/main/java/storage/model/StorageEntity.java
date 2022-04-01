package storage.model;

import lombok.Data;

//Сущность склада, тут хранится информация об остатках

@Data
public class StorageEntity {

    private Long id;

    private Long category;

    private Long product;

    private Long count;
}
