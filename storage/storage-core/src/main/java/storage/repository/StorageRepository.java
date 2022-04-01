package storage.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import personservice.model.ProductEntity;
import storage.model.StorageEntity;

import java.util.List;

@Mapper
public interface StorageRepository {

    @Select("select * from storage")
    List<StorageEntity> getAll ();

    @Select("select * from storage where id = #{storageId}")
    StorageEntity getById (Long storageId);

    @Delete("delete from storage where id = #{storageId}")
    void deleteById (Long storageId);

    @Select("select count from storage where product = #{name}")
    Long getCount (String name);

    @Update("update storage set count = #{count} where name = #{name}")
    void updateCount (Long count, String name);
}
