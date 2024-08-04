package com.knowledgeknockout.kk.DTO;

import com.knowledgeknockout.kk.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoloQuizDTO {
    private Optional<User> user;
    private Map<Long,String> answers;
}
