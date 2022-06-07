/* Includes */

#include "../../datatype/datatype_definition.h"
#include "../../circular_fifo_lib/circular_fifo_lib.h"
#include "sdfactor_p1.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */
extern circular_fifo_<ERROR! p1 Not Connected To Any ConbFunctions ! > fifo_s_in;
extern spinlock spinlock_s_in;	

/* Output FIFO */
extern circular_fifo_<ERROR! p2 Not Connected To Any ConbFunctions ! > fifo_s1;
extern spinlock spinlock_s1;
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
			
void actor_p1(){
	
	/*  initialize memory*/
				 s1; 
		/* Read From Input Port  */
		int ret=0;
	
		
		/* Inline Code           */
		/* in combFunction p1Body */
		s1=5;
		
		/* Write To Output Ports */
		#if S1_BLOCKING==0
		write_non_blocking_<ERROR! p2 Not Connected To Any ConbFunctions ! >(&fifo_s1,s1);
		#else
		write_blocking_<ERROR! p2 Not Connected To Any ConbFunctions ! >(&fifo_s1,s1,&spinlock_s1);
		#endif
	
	}
