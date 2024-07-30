import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {PlayerResponse} from "./interfaces/perguntas.interface";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiUrl = 'https://perguntinhas.up.railway.app';

  constructor(private http: HttpClient) {}

  createPlayer(name: string): Observable<PlayerResponse> {
    return this.http.post<PlayerResponse>(`${this.apiUrl}/jogador`, { nome: name });
  }

  getRanking(): Observable<PlayerResponse[]> {
    return this.http.get<PlayerResponse[]>(`${this.apiUrl}/jogador/pontuacao`);
  }
}
