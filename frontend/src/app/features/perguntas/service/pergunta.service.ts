import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PerguntaService {
  private apiUrl = 'http://localhost:8080'; 

  constructor(private http: HttpClient) {}

  getQuestion(uuidJogador: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/pergunta/random/${uuidJogador}`);
  }

  submitAnswer(answer: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/resposta`, answer);
  }
}
