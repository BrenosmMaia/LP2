#include <stdio.h>

typedef struct {
  int f1, f2;
  int eixo;
} Elipse;

void print (Elipse* e) {
  printf("Elipse com focos (%d, %d) e eixo maior %d.\n", e->f1,e->f2, e->eixo);
}
