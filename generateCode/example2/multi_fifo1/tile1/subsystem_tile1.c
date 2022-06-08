#include "subsystem_tile1.h"
#include "../datatype/datatype_definition.h"
#include "../circular_fifo_lib/circular_fifo_lib.h"
#include <cheap_s.h>


void subsystem_tile1(){
	xil_printf("fire actor p4\n");
	actor_p4();
	xil_printf("actor p4 ends\n");
	xil_printf("fire actor p5\n");
	actor_p5();
	xil_printf("actor p5 ends\n");
}	

int init_tile1(){
	

	xil_printf("tile initialization starts\n");
			/* extern */
	extern int ZeroValue1;
	extern int ZeroValue2;
	extern UInt32 buffer_s4[];
	extern int buffer_s4_size;
	extern circular_fifo_UInt32 fifo_s4;
	extern cheap fifo_admin_s5;
	extern volatile UInt32 * const fifo_data_s5;
	extern size_t  buffer_s5_size;
	extern size_t token_s5_size;
	extern cheap fifo_admin_s2;
	extern volatile UInt32 * const fifo_data_s2;
	extern size_t buffer_s2_size;
	extern size_t token_s2_size;

/* Create the channels*/
	init_channel_UInt32(&fifo_s4,buffer_s4,buffer_s4_size);
	if (cheap_init_r (fifo_admin_s5, (void *) fifo_data_s5, buffer_s5_size, token_s5_size) == NULL) {
		//xil_printf("%04u/%010u: cheap_init_r failed\n", (uint32_t)(t>>32),(uint32_t)t);
		return 1;
	}				

/* SDF Delays */

/*wait util all other fifos are created*/
	while (cheap_get_buffer_capacity (fifo_admin_s2) == 0); 

			
				xil_printf("tile initialization ends\n");				
	return 0;	
}
