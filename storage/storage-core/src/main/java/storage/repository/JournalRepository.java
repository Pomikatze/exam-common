package storage.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import storage.model.JournalEntity;

import java.util.List;

@Mapper
public interface JournalRepository {

    @Select("select * from journal")
    List<JournalEntity> getAll ();

    @Insert("insert into journal (type, product, category, dttm, count)" +
            " values (#{type}, #{product}, #{category}, #{dttm}, #{count})")
    void writeJournal (JournalEntity journalEntity);
}
