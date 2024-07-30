import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Question} from "./interfaces/perguntas.interface";

@Injectable({
  providedIn: 'root'
})
export class PerguntaService {
  private apiUrl = 'https://perguntinhas.up.railway.app';

  constructor(private http: HttpClient) {}

  getQuestion(uuidJogador: string): Observable<Question> {
    return this.http.get<Question>(`${this.apiUrl}/pergunta/random/${uuidJogador}`);
  }

  submitAnswer(answer: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/resposta`, answer);
  }
}
