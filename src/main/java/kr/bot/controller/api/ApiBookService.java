package kr.bot.controller.api;

import kr.bot.comm.exception.CustomException;
import kr.bot.comm.exception.ErrorCode;
import kr.bot.controller.api.dto.ApiBookForm;
import kr.bot.controller.api.dto.BookModityDto;
import kr.bot.controller.api.dto.BookNewDto;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApiBookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;


    @Transactional
    public ApiBookForm newBook(BookNewDto bookSaveDto) throws CustomException {
        Book findBook = bookRepository.findByBookName(bookSaveDto.getBookName());
        if(findBook != null) {
            throw new CustomException(ErrorCode.ALREADY_BOOK, "이미 등록된 책입니다");
        }

        Book newBook = new Book(bookSaveDto.getBookName()
                , bookSaveDto.getAuthor()
                , bookSaveDto.getPublishDate()
                , bookSaveDto.getPublisher());

        bookRepository.persist(newBook);

        return modelMapper.map(newBook, ApiBookForm.class);
    }

    @Transactional
    public void modifyBook(BookModityDto body) throws CustomException {
        Book findBook = bookRepository.findById(body.getId());
        if(findBook == null) {
            throw new CustomException(ErrorCode.ALREADY_BOOK, "검색되지 않는 책입니다");
        }
        findBook.modify(body.getPublisher(), body.getPublishDate());
    }


    @Transactional
    public void deleteBook(BookModityDto body) {
        Book findBook = bookRepository.findById(body.getId());
        findBook.delete();
    }
}
