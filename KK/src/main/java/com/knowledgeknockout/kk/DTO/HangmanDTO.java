package com.knowledgeknockout.kk.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HangmanDTO {

    private String letter;

    private Integer tries;
}
