/* Includes */

#include "../../datatype/datatype_definition.h"
#include "../../circular_fifo_lib/circular_fifo_lib.h"
#include "sdfactor_p4.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */
extern circular_fifo fifo_s2;

/* Output FIFO */
extern circular_fifo fifo_s4;
/*
========================================
	Declare Extern Global Variables
========================================
*/			
	
/*
========================================
		Actor Function
========================================
*/	

void actor_p4(){
	
	/*  initialize memory*/

	UInt32 s4; 
	UInt32 s2; 
	/* Read From Input Port  */
	int ret=0;
	read_fifo(&fifo_s2,(void*)&s2,1);
	
		
	/* Inline Code           */
	/* in combFunction p4Body */
	;
		
	/* Write To Output Ports */
	 
	write_fifo(&fifo_s4,(void*)&s4,1);
	
}
