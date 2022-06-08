#include <stdio.h>
#include "./single/tile/subsystem.h"
#include "./single/datatype/datatype_definition.h"
#include "./single/circular_fifo_lib/circular_fifo_lib.h"
extern circular_fifo fifo_s_in;
int main(){
   int a[2] = {1,2};

init_subsystem();
 PRINT(&fifo_s_in);


   write_fifo(&fifo_s_in,a,2); 
    PRINT(&fifo_s_in);
   subsystem();
    return 0;
}