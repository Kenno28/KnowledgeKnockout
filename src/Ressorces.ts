
export type QuizCard={
    id:number,
    titel: string,
    question: string,
    genre:string,
    quizChoice:string,
    answer: string,
    answerOptions: string[],
    userId: number;
}

export type User={
    id:number,
    Username: string,
    EmailAddress: string,
    Password: string,
    Verify:Boolean,
    Coins:number,
}
