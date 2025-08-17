import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Auth } from '@angular/fire/auth'
import { map, Observable } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const auth = inject(Auth);
  const router = inject(Router);

  return new Observable<boolean>(observer => {
    const unsubscribe = auth.onAuthStateChanged(
      user => {
        if (user) {
          observer.next(true);
        } else {
          router.navigate(['/login']);
          observer.next(false);
        }
        observer.complete();
      },
      error => {
        observer.error(error);
      }
    );
    return () => unsubscribe();
  }).pipe(
    map(authenticated => authenticated)
  );
};
