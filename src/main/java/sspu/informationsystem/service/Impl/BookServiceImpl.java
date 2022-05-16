package sspu.informationsystem.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sspu.informationsystem.entity.Book;
import sspu.informationsystem.mapper.BookMapper;
import sspu.informationsystem.service.BookService;
import sspu.informationsystem.utils.FtpUtil;

import javax.annotation.Resource;


@Service
public class BookServiceImpl implements BookService {

    @Resource
    private FtpUtil ftpUtil;

    @Resource
    private BookMapper bookMapper;

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void deleteBookById(Integer bookId) {
        bookMapper.deleteBook(bookId);
    }

    @Override
    public void updatePhoto(MultipartFile file) {

    }

}
