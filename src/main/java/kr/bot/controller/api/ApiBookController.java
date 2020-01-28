package kr.bot.controller.api;

import kr.bot.comm.exception.CustomException;
import kr.bot.controller.api.dto.ApiBookForm;
import kr.bot.controller.api.config.ApiResultForm;
import kr.bot.controller.api.dto.BookModityDto;
import kr.bot.controller.api.dto.BookNewDto;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ApiBookController {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    private final ApiBookService apiBookService;

//    @GetMapping("/api/book/list")
//    public List<ApiBookForm> apiBookList2() {
//        List<Book> all = bookRepository.findAll();
//        List<ApiBookForm> collect = all.stream().map(book -> modelMapper.map(book, ApiBookForm.class)).collect(Collectors.toList());
//        return collect;
//    }

    @GetMapping("/api/book/list")
    public ApiResultForm<List<ApiBookForm>> apiBookList() {

        List<Book> all = bookRepository.findAll();
        List<ApiBookForm> collect = all.stream().map(book -> modelMapper.map(book, ApiBookForm.class)).collect(Collectors.toList());

        return ApiResultForm.success(collect);
    }

    @PostMapping("/api/book/new")
    public ApiResultForm<ApiBookForm> apiBookNew(@RequestBody @Valid BookNewDto body) throws CustomException {
        System.out.println("post ====== " + body.toString());
        return ApiResultForm.success(apiBookService.newBook(body));
    }

//    @PostMapping("/api/book/new2")
//    public ApiBookForm apiBookNew2(@RequestBody @Valid BookSaveDto body) throws CustomException {
//        System.out.println("post ====== " + body.toString());
//        return apiBookService.saveBook(body);
//    }

    @PostMapping("/api/book/modify")
    public ApiResultForm<ApiBookForm> apiBooModify(@RequestBody @Valid BookModityDto body) throws CustomException {
        apiBookService.modifyBook(body);
        return ApiResultForm.success();
    }

    @PostMapping("/api/book/delete")
    public ApiResultForm<Object> apiBookDelete(@RequestBody @Valid BookModityDto body) {
        apiBookService.deleteBook(body);
        return ApiResultForm.success();
    }
}
