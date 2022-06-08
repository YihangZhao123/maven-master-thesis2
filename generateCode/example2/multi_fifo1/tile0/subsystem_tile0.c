#include "subsystem_tile0.h"
#include "../datatype/datatype_definition.h"
#include "../circular_fifo_lib/circular_fifo_lib.h"
#include <cheap_s.h>
void subsystem_tile0(){
	xil_printf("fire actor p1\n");
	actor_p1();
	xil_printf("actor p1 ends\n");
	xil_printf("fire actor p2\n");
	actor_p2();
	xil_printf("actor p2 ends\n");
	xil_printf("fire actor p1\n");
	actor_p1();
	xil_printf("actor p1 ends\n");
	xil_printf("fire actor p2\n");
	actor_p2();
	xil_printf("actor p2 ends\n");
	xil_printf("fire actor p3\n");
	actor_p3();
	xil_printf("actor p3 ends\n");
}	

int init_tile0(){
	xil_printf("tile initialization starts\n");
	extern int ZeroValue1;
	extern int ZeroValue2;

	/* extern sdfchannel s6*/
	extern UInt32 buffer_s6[];
	extern int buffer_s6_size;
	extern circular_fifo_UInt32 fifo_s6;
	/* extern sdfchannel s1*/
	extern UInt32 buffer_s1[];
	extern int buffer_s1_size;
	extern circular_fifo_UInt32 fifo_s1;
	extern cheap fifo_admin_s2;
	extern volatile UInt32 * const fifo_data_s2;
	extern unsigned int buffer_s2_size;
	extern unsigned int token_s2_size;
	/* extern sdfchannel s3*/
	extern UInt32 buffer_s3[];
	extern int buffer_s3_size;
	extern circular_fifo_UInt32 fifo_s3;
extern cheap fifo_admin_s5;
extern volatile UInt32 * const fifo_data_s5;
extern unsigned int buffer_s5_size;
extern unsigned int token_s5_size;
/* extern sdfchannel s_in*/
extern UInt32 buffer_s_in[];
extern int buffer_s_in_size;
extern circular_fifo_UInt32 fifo_s_in;
/* Create the channels*/
	init_channel_UInt32(&fifo_s6,buffer_s6,buffer_s6_size);
	init_channel_UInt32(&fifo_s1,buffer_s1,buffer_s1_size);
	
	if (cheap_init_r (fifo_admin_s2, (void *) fifo_data_s2, buffer_s2_size, token_s2_size) == NULL) {
		//xil_printf("%04u/%010u: cheap_init_r failed\n", (uint32_t)(t>>32),(uint32_t)t);
		return 1;
	}				
	init_channel_UInt32(&fifo_s3,buffer_s3,buffer_s3_size);
	
	/*Initialize the channel */

	write_fifo_UInt32(&fifo_s6,&ZeroValue1,1);
	write_fifo_UInt32(&fifo_s6,&ZeroValue2,1);
	
	/* wait util other channels are created*/
	while (cheap_get_buffer_capacity (fifo_admin_s5) == 0); 
	while (cheap_get_buffer_capacity (fifo_admin_s_in) == 0); 
	xil_printf("tile initialization ends\n");				
	return 0;	
}
