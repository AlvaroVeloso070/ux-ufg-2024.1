import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import confetti from 'canvas-confetti';
import { PlayerService } from "../../../perguntas/service/player.service";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Player } from "../../../perguntas/service/interfaces/perguntas.interface";

@Component({
  selector: 'app-ranking-index',
  standalone: true,
  imports: [CommonModule, FormsModule, FontAwesomeModule],
  templateUrl: './ranking-index.component.html',
  styleUrls: ['./ranking-index.component.scss']
})
export class RankingIndexComponent implements OnInit, AfterViewInit {
  ranking: Player[] = [];
  currentPlayer: Player = { uuid: '', nickname: '', score: 0 };
  playerPosition: number = 0;

  constructor(
    private _playerService: PlayerService,
    private _snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.getRanking();
  }

  ngAfterViewInit(): void {
    this.launchConfetti();
  }

  getRanking() {
    this._playerService.getRanking().subscribe(ranking => {
      const player = JSON.parse(localStorage.getItem('player') || '{}');
      const new_ranking = ranking.sort((a, b) => b.pontuacao - a.pontuacao).map(item => ({
        uuid: item.uuid,
        nickname: item.nomeJogador,
        score: Math.round(item.pontuacao)
      }));

      this.ranking = new_ranking;
      this.currentPlayer = this.ranking.find(p => p.uuid === player.uuid) || { uuid: '', nickname: player.nome, score: Math.round(player.pontuacao) };
      this.playerPosition = this.ranking.findIndex(p => p.uuid === this.currentPlayer.uuid) + 1;
    });
  }

  launchConfetti(): void {
    const duration = 5 * 1000;
    const end = Date.now() + duration;

    const frame = () => {
      confetti({
        particleCount: 2,
        angle: 60,
        spread: 55,
        origin: { x: 0 },
      });
      confetti({
        particleCount: 2,
        angle: 120,
        spread: 55,
        origin: { x: 1 },
      });

      if (Date.now() < end) {
        requestAnimationFrame(frame);
      }
    };

    frame();
  }
}
