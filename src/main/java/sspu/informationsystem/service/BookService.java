package sspu.informationsystem.service;

import org.springframework.web.multipart.MultipartFile;
import sspu.informationsystem.entity.Book;

public interface BookService {


    int insert(Book record);

    void updateBook(Book book);

    void deleteBookById(Integer dishesId);

    void updatePhoto(MultipartFile file);
}
