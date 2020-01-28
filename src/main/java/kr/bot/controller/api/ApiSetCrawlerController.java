package kr.bot.controller.api;

import kr.bot.comm.exception.CustomException;
import kr.bot.controller.api.config.ApiResultForm;
import kr.bot.controller.api.dto.SetCrawlerDto;
import kr.bot.controller.api.dto.SetCrawlerSaveDto;
import kr.bot.jpa.entity.SetCrawler;
import kr.bot.jpa.repo.SetCrawlerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ApiSetCrawlerController {

    private final SetCrawlerRepository setCrawlerRepository;
    private final ApiSetCrawlerService apiSetCrawlerService;

    @GetMapping("/api/setCralwer")
    public ApiResultForm apiSetCralwer(@RequestParam Long bookId) {
        System.out.println("bookId = " + bookId);

        List<SetCrawler> list = setCrawlerRepository.findAllByBook(bookId);

        List<SetCrawlerDto> collect = list.stream().map(SetCrawlerDto::new).collect(Collectors.toList());

        return ApiResultForm.success(collect);
    }

    @PostMapping("/api/setCralwer/save")
    public ApiResultForm apiSetCralwerSave(@RequestBody SetCrawlerSaveDto setCrawlerSaveDto) throws CustomException {
        apiSetCrawlerService.saveStore(setCrawlerSaveDto);
        return ApiResultForm.success();
    }
}
