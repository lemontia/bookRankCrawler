package kr.bot.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.bot.comm.exception.ErrorCode;
import kr.bot.controller.api.config.ApiResultForm;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.repo.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
class ApiBookControllerTest {

//    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookRepository bookRepository;

    @Test
    @Transactional
    public void 목록조회() throws Exception{
        //given
        String url = "/api/book/list";

        //when
        MvcResult mvcResult = mockMvc.perform(get(url))
                .andReturn();

        //then
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiResultForm dto = objectMapper.readValue(contentAsString, ApiResultForm.class);
        System.out.println("dto = " + dto);

        Assertions.assertTrue(dto.getHeader().getCode().equals("E0000"));
        Assertions.assertTrue(dto.getValue() != null);
    }

    @Test
    @Transactional
    public void 책_저장() throws Exception{
        // given
        String url = "/api/book/new";
        String bookName = "테스트책";
        String author = "저자명";
        String publisher = "테스트출판사";
        String publishDate = "2020-01-10";

        HashMap map = new HashMap();
        map.put("bookName", bookName);
        map.put("author", author);
        map.put("publisher", publisher);
        map.put("publishDate", publishDate);


        // when
        MvcResult mvcResult = mockMvc.perform(post(url)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(map))
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();


        //then
        System.out.println(mvcResult.getRequest().getContentAsString());
    }


    @Test
    @Transactional
    public void 중복책_저장() throws Exception{
        // given
        String url = "/api/book/new";
        String bookName = "탁월한 인생을 만드는 법";
        String author = "저자명";
        String publisher = "테스트출판사";
        String publishDate = "2020-01-10";

        HashMap map = new HashMap();
        map.put("bookName", bookName);
        map.put("author", author);
        map.put("publisher", publisher);
        map.put("publishDate", publishDate);


        // when
        MvcResult mvcResult = mockMvc.perform(post(url)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(map))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        //then
        System.out.println("=======================");
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        System.out.println("=======================");

        ApiResultForm dto = objectMapper.readValue(contentAsString, ApiResultForm.class);
        Assertions.assertTrue(dto.getHeader().getCode().equals(ErrorCode.SUCCESS.getCode()) == false);
    }

    @Test
    @Transactional
    public void 파라미터_전달_확인() throws Exception{
        // given
        String urlNew = "/api/book/new";

        String urlModify = "/api/book/modify";
        String bookName = "BB";
        String author = "저자명";
        String publisher = "테스트출판사";
        String publishDate = "2020-01-10";
        Book book = new Book(bookName, author, LocalDate.parse(publishDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")), publisher);
        bookRepository.persist(book);

        HashMap map = new HashMap();
        map.put("id", book.getId());
        map.put("bookName", bookName);
        map.put("author", author);
        map.put("publisher", publisher);
        map.put("publishDate", publishDate);

        //when
        MvcResult mvcResult = mockMvc.perform(post(urlModify)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(map))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("=======================");
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        System.out.println("=======================");

        ApiResultForm dto = objectMapper.readValue(contentAsString, ApiResultForm.class);
        Assertions.assertTrue(dto.getHeader().getCode().equals(ErrorCode.SUCCESS.getCode()) == true);
    }
}