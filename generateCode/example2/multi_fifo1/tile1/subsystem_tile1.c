#include "subsystem_tile1.h"
#include "../datatype/datatype_definition.h"
#include "../circular_fifo_lib/circular_fifo_lib.h"
#include <cheap_s.h>
void subsystem_tile1(){
}	

int init_tile1(){
	xil_printf("tile initialization starts\n");
	extern int ZeroValue1;
	extern int ZeroValue2;

/* Create the channels*/
	
	/*Initialize the channel */
	
	/* wait util other channels are created*/
	xil_printf("tile initialization ends\n");				
	return 0;	
}
