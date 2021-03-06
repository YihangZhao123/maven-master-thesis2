/* Includes */

#include "../../datatype/datatype_definition.h"
#include "../../circular_fifo_lib/circular_fifo_lib.h"
#include "sdfactor_Gx.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */
extern circular_fifo_DoubleType fifo_gxsig;
extern spinlock spinlock_gxsig;	

/* Output FIFO */
extern circular_fifo_DoubleType fifo_absxsig;
extern spinlock spinlock_absxsig;
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
			
void actor_Gx(){
	
	/*  initialize memory*/
				DoubleType gx; 
				Array6OfDoubleType imgBlockX; 
		/* Read From Input Port  */
		int ret=0;
		for(int i=0;i<6;++i){
			#if GXSIG_BLOCKING==0
			ret=read_non_blocking_DoubleType(&fifo_gxsig,&imgBlockX[i]);
			if(ret==-1){
				printf("fifo_gxsig read error\n");
			}
			#else
			read_blocking_DoubleType(&fifo_gxsig,&imgBlockX[i],&spinlock_gxsig);
			#endif
		}
		
	
		
		/* Inline Code           */
		/* in combFunction GxImpl */
		gx=0;
		gx=gx-imgBlockX[0];
		gx=gx+imgBlockX[1];
		gx=gx-2.0*imgBlockX[2];
		gx=gx+2.0*imgBlockX[3];
		gx=gx-imgBlockX[4];
		gx=gx+imgBlockX[5];
		
		/* Write To Output Ports */
		#if ABSXSIG_BLOCKING==0
		write_non_blocking_DoubleType(&fifo_absxsig,gx);
		#else
		write_blocking_DoubleType(&fifo_absxsig,gx,&spinlock_absxsig);
		#endif
	
	}
