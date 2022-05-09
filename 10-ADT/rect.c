#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

typedef struct Rect {
    int x;
    int y;
    int w;
    int h;
}Rect;

Rect* rect_new(void){
    Rect* this = malloc(sizeof(Rect));
    this->x = 0;
    this->y = 0;
    this->w = 50;
    this->h = 70;
}

void rect_drag (Rect* this, int dx, int dy){
    this->x += dx;
    this->y += dy;
}

void rect_print (Rect* this){
    printf("Rect na posicao(%d,%d),dimensoes(%d,%d)\n",this->x,this->y,this->w,this->h);
}
