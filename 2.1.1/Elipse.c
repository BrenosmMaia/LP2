#include <stdio.h>

typedef struct {
  int f1x, f1y;
  int f2x, f2y;
  int eixo;
} Elipse;

void print (Elipse* e) {
  printf("Elipse com focos (%d, %d) e (%d, %d) e eixo maior %d.\n", e->f1x, e->f1y, e->f2x, e->f2y, e->eixo);
}
