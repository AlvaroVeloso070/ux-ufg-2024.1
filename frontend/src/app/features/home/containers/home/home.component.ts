import { Component } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';
import { RegrasModalComponent } from '../../components/regras-modal/regras-modal.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PlayerService } from '../../../perguntas/service/player.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSnackBarModule
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  public apelido: string = '';
  
  constructor(
    private _dialog: MatDialog, 
    private _snackBar: MatSnackBar,
    private _router: Router,
    private playerService: PlayerService
  ) {}

  public jogar(): void {
    if (this.apelido.trim() === '') {
      this._snackBar.open('Por favor, insira um apelido para jogar.', 'Fechar', {
        duration: 2000,
        horizontalPosition: 'center',
        panelClass: ['snackbar-warning']
      });
    } else {
      this.playerService.createPlayer(this.apelido).subscribe(
        (response) => {
          localStorage.setItem('player', JSON.stringify(response));
          this._router.navigate(['/questions']);
        },
        (error) => {
          console.error('Erro ao criar jogador:', error);
          this._snackBar.open('Erro ao criar jogador. Tente novamente.', 'Fechar', {
            duration: 2000,
            horizontalPosition: 'center',
            panelClass: ['snackbar-error']
          });
        }
      );
    }
  }

  public openRegrasDialog(): void {
    this._dialog.open(RegrasModalComponent,
      {
        maxWidth: '35vw'
      }
    );
  }
}
