package com.knowledgeknockout.kk.Help;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizResult {
    private long userId;
    private double winRate;
    private long wonCoins;
    private boolean wL;

}
