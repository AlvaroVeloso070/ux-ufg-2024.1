import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/containers/home/home.component';
import { PerguntasIndexComponent } from './features/perguntas/containers/perguntas-index/perguntas-index.component';
import { RankingIndexComponent } from './features/ranking/containers/ranking-index/ranking-index.component';

export const routes: Routes = [
    { path: '', pathMatch: 'full', component: HomeComponent },
    { path: 'questions', component: PerguntasIndexComponent },
    { path: 'ranking', component: RankingIndexComponent },
];
