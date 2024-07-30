export interface PlayerResponse {
  uuid: string;
  nomeJogador: string;
  pontuacao: number;
}
export interface Player {
  uuid?: string;
  nickname: string;
  score: number;
}

export interface Question {
  uuid: string;
  enunciado: string;
  alternativas: Alternative[];
  dificuldade: string;
  categoria: string;
}

export interface Alternative {
  uuid: string;
  descricao: string;
}
