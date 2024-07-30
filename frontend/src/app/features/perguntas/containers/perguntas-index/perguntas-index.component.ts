import { Component, OnInit } from '@angular/core';
import { PerguntaService } from '../../service/pergunta.service';
import { PlayerService } from '../../service/player.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {Alternative, Player, Question} from "../../service/interfaces/perguntas.interface";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'app-perguntas-index',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatProgressSpinnerModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule
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
  questionsAnswered: number = 0;
  currentPlayer: Player = { uuid: '', nickname: '', score: 0 };
  playerPosition = 0;
  ranking: Player[] = [];

  public get heartLives(){
    return Array(3).fill(0).map((_, index) => index < this.lives ? 1 : 0);
  }

  constructor(
    private perguntaService: PerguntaService,
    private playerService: PlayerService,
    private router: Router,
    private _snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    const player = JSON.parse(localStorage.getItem('player') || '{}');
    this.currentPlayer = {
      uuid: player.uuid,
      nickname: player.nome || 'Jogador',
      score: player.pontuacao || 0,
    };
    this.lives = 3;
    this.score = 0;

    this.loadQuestion(player.uuid);
    this.updateRanking();
  }

  loadQuestion(uuidJogador: string) {
    this.perguntaService.getQuestion(uuidJogador).subscribe((pergunta: Question) => {
      if(!pergunta){
        this._snackBar.open('Nenhuma pergunta não encontrada.', 'Fechar', {
          duration: 2000,
          horizontalPosition: 'center',
          panelClass: ['snackbar-warning']
        });
        this.checkAnswer(false);
      }
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
        this.getRanking();
        this.checkAnswer(false);
      }
    }, 1000);
  }

  checkAnswer(correct: boolean, score?: number) {
    clearInterval(this.interval);
    this.questionsAnswered++;
    if (correct && score) {
      this.score += score;
      this.showFeedbackAnswer('correct', score);
    }
    else {
      this.lives--;
      this.showFeedbackAnswer('wrong');
    }

    this.currentPlayer.score = this.score;
    this.updateRanking();

    setTimeout(() => {
      this.feedback = '';
      this.selectedAnswer = null;
      if (this.lives > 0 && this.questionsAnswered < 10) {
        const player = JSON.parse(localStorage.getItem('player') || '{}');
        this.loadQuestion(player.uuid);
      }
      else {
        this.saveRanking();
        this.router.navigate(['/ranking']);
      }
    }, 2500);
  }

  showFeedbackAnswer(feedback: string, score?: number){
    this._snackBar.dismiss();
    if(feedback === 'correct' && score){
      this._snackBar.open(`Resposta certa! Você obteve ${this._truncateNumber(score)} pontos`, 'Fechar', {
        duration: 2000,
        horizontalPosition: 'center',
        panelClass: ['snackbar-success']
      });
    }
    else if(feedback === 'wrong'){
      this._snackBar.open('Resposta errada! Você não obteve pontos', 'Fechar', {
        duration: 2000,
        horizontalPosition: 'center',
        panelClass: ['snackbar-warning']
      });
    }
  }

  selectAnswer(answer: Alternative) {
    this.selectedAnswer = answer;
    const player = JSON.parse(localStorage.getItem('player') || '{}');
    this.perguntaService.submitAnswer({
      uuidJogador: player.uuid,
      uuidAlternativa: answer.uuid,
      tempoResposta: this.timeLeft
    }).subscribe(answer => {
      this.checkAnswer(answer.resultado === 'CORRETA', this._truncateNumber(answer.pontuacao));
    });
  }

  updateRanking() {
    setTimeout(() => {
      this.getRanking();
    }, 2000);
  }

  getRanking() {
    this.playerService.getRanking().subscribe(ranking => {
      this.playerPosition = ranking.findIndex(p => p.uuid === this.currentPlayer.uuid) + 1;
      const new_ranking: Player[] = ranking.sort((a, b) => b.pontuacao - a.pontuacao).slice(0, 4).map(item => ({
        uuid: item.uuid,
        nickname: item.nomeJogador,
        score: item.pontuacao
      }));
      if(!new_ranking.find(p => p.uuid === this.currentPlayer.uuid)){
        new_ranking.pop();
        new_ranking.push(this.currentPlayer);
      }
      this.ranking = new_ranking;
    });
  }

  saveRanking() {
    localStorage.setItem('ranking', JSON.stringify(this.ranking));
  }

  mapToAlternativeLetter(index: number){
    return String.fromCharCode('a'.charCodeAt(0) + index);
  }

  checkCurrentPlayer(player: Player) {
    return player.uuid === this.currentPlayer.uuid;
  }

  private _truncateNumber(value: number){
    return Math.floor(value* 10) / 10;
  }

}
