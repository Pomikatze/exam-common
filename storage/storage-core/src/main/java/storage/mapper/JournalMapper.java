package storage.mapper;


import personservice.dto.JournalDto;
import personservice.service.CategoryService;
import personservice.service.ProductService;
import storage.model.JournalEntity;

public class JournalMapper implements CustomMapper<JournalDto, JournalEntity> {

    ProductService productService;
    CategoryService categoryService;

    public JournalMapper(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public JournalDto toDto(JournalEntity entity) {
        JournalDto journalDto = new JournalDto();
        journalDto.setType(entity.getType());
        journalDto.setDttm(entity.getDttm());
        journalDto.setProduct(productService.findNameById(entity.getProduct()));
        journalDto.setCategory(categoryService.findCategoryNameById(entity.getCategory()));
        journalDto.setCount(entity.getCount());
        return journalDto;
    }

    @Override
    public JournalEntity toEntity(JournalDto dto) {
        JournalEntity journalEntity = new JournalEntity();
        journalEntity.setDttm(dto.getDttm());
        journalEntity.setId(null);
        journalEntity.setCount(dto.getCount());
        journalEntity.setType(dto.getType());
        journalEntity.setCategory(categoryService.findIdByCategoryName(dto.getCategory()));
        journalEntity.setProduct(productService.findIdByName(dto.getProduct()));
        return journalEntity;
    }
}
