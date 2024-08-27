package com.book.bookstoreapi.metrics;

import com.book.bookstoreapi.service.BookService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics implements MeterBinder {

    private final BookService bookService;

    public CustomMetrics(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        meterRegistry.gauge("book_creation_count",
                Tags.of("service", "bookService"),
                bookService,
                BookService::getBookCreationCount);
    }
}
