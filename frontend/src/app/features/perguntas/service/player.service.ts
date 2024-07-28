import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) {}

  createPlayer(name: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/jogador`, { nome: name });
  }

  getRanking(player: any): Observable<any> {
    const ranking = JSON.parse(localStorage.getItem('ranking') || '[]');
    return of(ranking);
  }
}
