import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookComponent } from './book/book.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BookResolverService } from './services/book-resolver.service';

const routes: Routes = [
  // { path: '', redirectTo: '/search', pathMatch: 'full' },
  // {
  //   path: 'search',
  //   component: BookSearchComponent,
  // },

  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'book/:id',
    component: BookComponent,
    resolve: [BookResolverService],
  },
  { path: '**', redirectTo: '/' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
