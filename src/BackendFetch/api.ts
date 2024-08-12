import {fetchWithErrorHandling} from "./fetchWithErrorHandling";

export async function getRandomQuizCards(genre:String){
const url= `${process.env.REACT_APP_API_SERVER_URL}/quizCard/randomQuestion/${genre}`;
    const response = await fetchWithErrorHandling(url, {
        credentials: "include" as RequestCredentials,
    });
    return response.json();
}