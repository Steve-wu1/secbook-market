package sspu.informationsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import sspu.informationsystem.entity.Book;

@Mapper
public interface BookMapper {
    int insert(Book record);

    void updateBook(Book book);

    void deleteBook(Integer bookId);

    Book getInfo(Integer bId);
}