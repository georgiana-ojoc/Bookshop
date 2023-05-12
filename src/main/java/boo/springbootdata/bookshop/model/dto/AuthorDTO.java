package boo.springbootdata.bookshop.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorDTO {
    private String name;
    private String birthplace;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
}
