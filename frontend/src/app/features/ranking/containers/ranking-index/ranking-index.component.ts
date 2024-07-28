import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import confetti from 'canvas-confetti';

interface Player {
  nickname: string;
  score: number;
}

@Component({
  selector: 'app-ranking-index',
  standalone: true,
  imports: [CommonModule, FormsModule, FontAwesomeModule],
  templateUrl: './ranking-index.component.html',
  styleUrls: ['./ranking-index.component.scss']
})
export class RankingIndexComponent implements OnInit, AfterViewInit {
  ranking: Player[] = [];
  currentPlayer: Player = { nickname: '', score: 0 };
  playerPosition: number = 0;

  ngOnInit(): void {
    const player = JSON.parse(localStorage.getItem('player') || '{}');
    this.ranking = JSON.parse(localStorage.getItem('ranking') || '[]');

    this.currentPlayer = this.ranking.find(p => p.nickname === player.nome) || { nickname: '', score: 0 };
    this.playerPosition = this.ranking.findIndex(p => p.nickname === this.currentPlayer.nickname) + 1;
  }

  ngAfterViewInit(): void {
    this.launchConfetti();
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
