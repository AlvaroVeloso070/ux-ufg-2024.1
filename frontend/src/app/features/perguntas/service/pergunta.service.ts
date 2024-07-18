import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PerguntaService {
  private questions = [
    {
      question: "Qual linguagem de programação é frequentemente usada para desenvolvimento web e possui um framework popular chamado Django?",
      answers: [
        { option: "A", text: "Python", correct: true },
        { option: "B", text: "Java", correct: false },
        { option: "C", text: "JavaScript", correct: false },
        { option: "D", text: "Ruby", correct: false }
      ],
      time: 50
    }
    // Adicione mais perguntas conforme necessário
  ];
  private score: number = 0;
  private lives: number = 3;
  private ranking: { nickname: string, score: number }[] = [
    { nickname: 'Nickname1', score: 987 },
    { nickname: 'Nickname2', score: 123 },
    { nickname: 'Nickname3', score: 10 },
    { nickname: 'Nickname4', score: 0 },
  ];

  constructor() { }

  getQuestion() {
    const randomIndex = Math.floor(Math.random() * this.questions.length);
    return this.questions[randomIndex];
  }

  getScore(): number {
    return this.score;
  }

  setScore(score: number) {
    this.score = score;
  }

  getLives(): number {
    return this.lives;
  }

  setLives(lives: number) {
    this.lives = lives;
  }

  getRanking(): { nickname: string, score: number }[] {
    return this.ranking.sort((a, b) => b.score - a.score);
  }

  updateRanking(nickname: string, score: number) {
    this.ranking.push({ nickname, score });
    this.ranking = this.getRanking();
  }
}
