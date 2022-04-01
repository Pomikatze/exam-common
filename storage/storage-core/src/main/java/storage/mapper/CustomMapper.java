package storage.mapper;

public interface CustomMapper<T, T2> {

    T toDto (T2 entity);

    T2 toEntity (T dto);
}
