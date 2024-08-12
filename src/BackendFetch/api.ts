import {fetchWithErrorHandling} from "./fetchWithErrorHandling";
import {QuizCard} from "../Ressorces";

export async function getRandomQuizCards(genre:String) :Promise<QuizCard[]> {
const url= `${process.env.REACT_APP_API_SERVER_URL}/quizCard/randomQuestion/${genre}`;
    const response = await fetchWithErrorHandling(url, {
        credentials: "include" as RequestCredentials,
    });
    return response.json();
}

export async function createQuizCard(quizCard:QuizCard): Promise<QuizCard>{
    const url= `${process.env.REACT_APP_API_SERVER_URL}/quizCard/`;
    const response = await fetchWithErrorHandling(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            credentials: 'include'
        },
        body: JSON.stringify(quizCard)
    });
    return response.json();
}