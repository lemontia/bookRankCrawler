package kr.bot.services.crawler.abs;

import kr.bot.jpa.entity.Book;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class AbstractCrawler<T> {

    public abstract T doCrawler(Book book, String url) throws IOException;

    protected abstract T rank(Book book, Document doc) throws IOException;
}
