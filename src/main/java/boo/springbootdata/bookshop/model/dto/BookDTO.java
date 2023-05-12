package boo.springbootdata.bookshop.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private String title;
    private Integer numberOfPages;
    private BigDecimal price;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfPublishing;
}
