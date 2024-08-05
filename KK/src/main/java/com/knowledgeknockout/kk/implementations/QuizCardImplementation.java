package com.knowledgeknockout.kk.implementations;

import com.knowledgeknockout.kk.entity.QuizCard;
import com.knowledgeknockout.kk.enums.Genre;
import com.knowledgeknockout.kk.enums.QuizChoice;
import com.knowledgeknockout.kk.interfaces.QuizCardInterface;
import com.knowledgeknockout.kk.interfaces.QuizCardJDBC;
import com.knowledgeknockout.kk.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class QuizCardImplementation implements QuizCardInterface {
    @Autowired
    QuizCardJDBC quizCardJDBC;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<QuizCard> updateQuizCard(Long id,QuizCard quizCard) {

        int result =jdbcTemplate.update(
                "UPDATE QUIZ_CARD SET title=? ,question =? ,genre=?, quiz_choice=? ,answer=? ,user_id =? WHERE id=? ",
                ps -> {
                    ps.setString(1, quizCard.getTitle());
                    ps.setString(2, quizCard.getQuestion());
                    ps.setString(3, quizCard.getGenre().toString());
                    ps.setString(4, quizCard.getQuizChoice().toString());
                    ps.setString(5, quizCard.getAnswer());
                    ps.setLong(6,quizCard.getUserId());
                    ps.setLong(7, id);
                }
                );

        jdbcTemplate.update(
                "DELETE FROM QUIZ_CARD_answer_options WHERE quiz_card_id=?",
                ps -> {
                    ps.setLong(1, id);
                }
        );

        List<String> answerOptions = quizCard.getAnswerOptions();
        for (String option : answerOptions) {
            jdbcTemplate.update(
                    "INSERT INTO QUIZ_CARD_answer_options (quiz_card_id, answer_options) VALUES (?, ?)",
                    ps -> {
                        ps.setLong(1, id);
                        ps.setString(2, option);
                    }
            );
        }

        if(result >0) {
            return quizCardJDBC.findById(id);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public List<QuizCard> getQuizCardsGenre(String genre) {
        if (genre.isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be empty");
        }
        try {
            List<QuizCard> quizCards = jdbcTemplate.query(
                    "SELECT * FROM QUIZ_CARD WHERE GENRE = ?",
                    ps -> {
                        ps.setString(1, genre);
                    },
                    (rs, rowNum) -> new QuizCard(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("question"),
                            Genre.valueOf(rs.getString("genre")),
                            QuizChoice.valueOf(rs.getString("quiz_Choice")),
                            rs.getString("answer"),
                            rs.getInt("user_id")
                    )
            );

            if (!quizCards.isEmpty()) {
                return quizCards;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
