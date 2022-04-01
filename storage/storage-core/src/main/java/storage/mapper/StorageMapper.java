package storage.mapper;


import personservice.dto.StorageDto;
import personservice.service.CategoryService;
import personservice.service.ProductService;
import storage.model.StorageEntity;

public class StorageMapper implements CustomMapper<StorageDto, StorageEntity> {

    ProductService productService;
    CategoryService categoryService;

    public StorageMapper(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public StorageDto toDto(StorageEntity entity) {
        StorageDto storageDto = new StorageDto();
        storageDto.setProduct(productService.findNameById(entity.getProduct()));
        storageDto.setCategory(categoryService.findCategoryNameById(entity.getCategory()));
        storageDto.setCount(entity.getCount());
        return storageDto;
    }

    @Override
    public StorageEntity toEntity(StorageDto dto) {
        StorageEntity storageEntity = new StorageEntity();
        storageEntity.setId(null);
        storageEntity.setCount(dto.getCount());
        storageEntity.setCategory(categoryService.findIdByCategoryName(dto.getCategory()));
        storageEntity.setProduct(productService.findIdByName(dto.getProduct()));
        return storageEntity;
    }
}
