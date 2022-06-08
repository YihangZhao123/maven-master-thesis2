#include <stdio.h>
#include "./single/tile/subsystem.h"
#include "./single/datatype/datatype_definition.h"
#include "./single/circular_fifo_lib/circular_fifo_lib.h"
extern circular_fifo fifo_s_in;
int main(){


init_subsystem();
 PRINT(&fifo_s_in);


   
    //PRINT(&fifo_s_in);
   for(int i=0;i<5;++i){

   int a[4];
   a[0]=4*i+1;
   a[1]=4*i+2;
   a[2]=4*i+3;
   a[3]=4*i+4;
   printf("----->in loop %d, value in s_in %d, %d, %d, %d\n",i,a[0],a[1],a[2],a[3]);
   write_fifo(&fifo_s_in,a,4);
   subsystem();
 } 

    return 0;
}