import { Component, OnInit } from '@angular/core';
import { PerguntaService } from '../../service/pergunta.service';
import { PlayerService } from '../../service/player.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Player {
  nickname: string;
  score: number;
}

interface Question {
  uuid: string;
  enunciado: string;
  alternativas: Alternative[];
  dificuldade: string;
  categoria: string;
}

interface Alternative {
  uuid: string;
  descricao: string;
}

@Component({
  selector: 'app-perguntas-index',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule 
  ],
  templateUrl: './perguntas-index.component.html',
  styleUrls: ['./perguntas-index.component.scss']
})
export class PerguntasIndexComponent implements OnInit {
  question: Question | null = null;
  lives: number = 3;
  score: number = 0;
  timeLeft: number = 0;
  interval: any;
  selectedAnswer: Alternative | null = null;
  feedback: string = '';
  playerName: string = '';
  questionsAnswered: number = 0;
  currentPlayer: Player = { nickname: '', score: 0 };
  ranking: Player[] = [];

  constructor(
    private perguntaService: PerguntaService,
    private playerService: PlayerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const player = JSON.parse(localStorage.getItem('player') || '{}');
    this.playerName = player.nome || 'Jogador';
    this.currentPlayer.nickname = player.nome;
    this.currentPlayer.score = player.pontuacao || 0;
    this.lives = 3;
    this.score = 0;

    this.initializeFakePlayers();
    this.loadQuestion(player.uuid);
    this.updateRanking();
  }

  initializeFakePlayers() {
    const fakePlayers = [
      { nickname: 'Luciana', score: 0 },
      { nickname: 'Eduardo', score: 0 },
      { nickname: 'Ana', score: 0 }
    ];

    fakePlayers.forEach(fakePlayer => {
      const existingPlayer = this.ranking.find(p => p.nickname === fakePlayer.nickname);
      if (!existingPlayer) {
        this.ranking.push(fakePlayer);
      }
    });
  }

  loadQuestion(uuidJogador: string) {
    this.perguntaService.getQuestion(uuidJogador).subscribe((pergunta: Question) => {
      this.question = pergunta;
      this.timeLeft = 60;
      this.startTimer();
    });
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
    this.questionsAnswered++;
    if (correct) {
      this.score += this.question?.dificuldade === 'FACIL' ? 100 : this.question?.dificuldade === 'MEDIO' ? 200 : this.question?.dificuldade === 'DIFICIL' ? 300 : 400;
      this.feedback = 'Correto!';
    } else {
      this.lives--;
      this.feedback = 'Incorreto!';
    }

    this.currentPlayer.score = this.score;
    this.updateRanking();

    this.updateFakePlayersScore();

    setTimeout(() => {
      this.feedback = '';
      this.selectedAnswer = null;
      if (this.lives > 0 && this.questionsAnswered < 3) {
        const player = JSON.parse(localStorage.getItem('player') || '{}');
        this.loadQuestion(player.uuid);
      } else {
        this.saveRanking();
        this.router.navigate(['/ranking']);
      }
    }, 2000);
  }

  selectAnswer(answer: Alternative) {
    this.selectedAnswer = answer;
    const player = JSON.parse(localStorage.getItem('player') || '{}');
    this.perguntaService.submitAnswer({
      uuidJogador: player.uuid,
      uuidAlternativa: answer.uuid,
      tempoResposta: this.timeLeft
    }).subscribe(result => {
      this.checkAnswer(result.resultado === 'CORRETA');
    });
  }

  updateRanking() {
    const existingPlayer = this.ranking.find(p => p.nickname === this.currentPlayer.nickname);
    if (existingPlayer) {
      existingPlayer.score = this.currentPlayer.score;
    } else {
      this.ranking.push({ nickname: this.currentPlayer.nickname, score: this.currentPlayer.score });
    }

    this.ranking.sort((a, b) => b.score - a.score);
  }

  updateFakePlayersScore() {
    this.ranking.forEach(player => {
      if (['Luciana', 'Eduardo', 'Ana'].includes(player.nickname)) {
        const randomScoreIncrement = this.getRandomScoreIncrement();
        player.score += randomScoreIncrement;
      }
    });

    this.ranking.sort((a, b) => b.score - a.score);
  }

  getRandomScoreIncrement() {
    const increments = [100, 200, 300, 400];
    return increments[Math.floor(Math.random() * increments.length)];
  }

  saveRanking() {
    localStorage.setItem('ranking', JSON.stringify(this.ranking));
  }
}
