package com.auth.jwt.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class FindBooksTagRequestDto {

    private String tags;
}
