import { Component } from '@angular/core';
import { PerguntaService } from '../../service/pergunta.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-perguntas-index',
  standalone: true,
  imports: [
    CommonModule,
  ],
  templateUrl: './perguntas-index.component.html',
  styleUrl: './perguntas-index.component.scss'
})
export class PerguntasIndexComponent {
  question: any;
  lives: number = 3;
  score: number = 0;
  timeLeft: number = 0;
  interval: any;
  selectedAnswer: any = null;
  feedback: string = '';
  currentPlayer = '';
  ranking = [
    { nickname: 'Ryan', score: 100, },
    { nickname: 'Alvaro', score: 90, },
    { nickname: 'Heitor', score: 80, },
  ]
  constructor(private questionService: PerguntaService, private router: Router) { }
 ngOnInit(): void {
    this.lives = this.questionService.getLives();
    this.score = this.questionService.getScore();
    this.loadQuestion();
  }

  loadQuestion() {
    this.question = this.questionService.getQuestion();
    this.timeLeft = this.question.time;
    this.startTimer();
  }

  startTimer() {
    this.interval = setInterval(() => {
      if (this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        this.checkAnswer(false);
      }
    }, 1000);
  }

  checkAnswer(correct: boolean) {
    clearInterval(this.interval);
    if (correct) {
      this.score += 100; // Ajuste a pontuação conforme necessário
      this.feedback = 'Correto!';
    } else {
      this.lives--;
      this.feedback = 'Incorreto!';
    }

    this.questionService.setScore(this.score);
    this.questionService.setLives(this.lives);

    setTimeout(() => {
      this.feedback = '';
      this.selectedAnswer = null;
      if (this.lives > 0) {
        this.loadQuestion();
      } else {
        this.router.navigate(['/gameover'], { state: { score: this.score } });
      }
    }, 2000); // Dê um tempo para mostrar o feedback antes de carregar a próxima pergunta
  }

  selectAnswer(answer: any) {
    this.selectedAnswer = answer;
    this.checkAnswer(answer.correct);
  }
}
