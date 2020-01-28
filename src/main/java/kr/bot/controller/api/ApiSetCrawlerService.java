package kr.bot.controller.api;

import kr.bot.comm.EnumStore;
import kr.bot.comm.exception.CustomException;
import kr.bot.comm.exception.ErrorCode;
import kr.bot.controller.api.dto.SetCrawlerDto;
import kr.bot.controller.api.dto.SetCrawlerSaveDto;
import kr.bot.jpa.entity.Book;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.BookRepository;
import kr.bot.jpa.repo.SetCrawlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApiSetCrawlerService {

    private final SetCrawlerRepository setCrawlerRepository;

    private final BookRepository bookRepository;

    @Transactional
    public void saveStore(SetCrawlerSaveDto setCrawlerSaveDto) throws CustomException {
        for (SetCrawlerDto setCrawlerDto : setCrawlerSaveDto.getStoreList()) {
            Long id = setCrawlerDto.getId();

            SetCrawler findSetCrawler = setCrawlerRepository.findOneById(id);
            if(findSetCrawler == null) {
                insertSetCrawler(setCrawlerSaveDto.getBookId()
                        , EnumStore.valueOf(setCrawlerDto.getStoreName())
                        , setCrawlerDto.getUrl()
                        , setCrawlerDto.isUseYn());

                continue;
            }

            findSetCrawler.changeUrlAndUseYn(
                    setCrawlerDto.getUrl()
                    , setCrawlerDto.isUseYn());
        }
    }

    // 신규저장
    @Transactional
    void insertSetCrawler(Long bookId, EnumStore enumStore, String url, boolean useYn) throws CustomException {
        if(url.equals("") == true) {
            return;
        }

        Book book = bookRepository.findById(bookId);
        if(book == null) {
            throw new CustomException(ErrorCode.NO_EXIST_BOOK
                    , "없는책 입니다. 다시 확인해주세요.");
        }

        SetCrawler setCrawler = new SetCrawler(book, enumStore, url, useYn);
        setCrawlerRepository.persist(setCrawler);
    }
}
