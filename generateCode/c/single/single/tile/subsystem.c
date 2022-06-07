#include "subsystem.h"
#include <stdio.h>
#include "./sdfactor/sdfactor_Gx.h"
#include "./sdfactor/sdfactor_Abs.h"
#include "./sdfactor/sdfactor_Gy.h"
#include "./sdfactor/sdfactor_GrayScale.h"
#include "./sdfactor/sdfactor_getPx.h"
#include "../datatype/datatype_definition.h"
#include "../circular_fifo_lib/circular_fifo_lib.h"
/*
==============================================
	Subsystem Function
==============================================
*/	
int subsystem(){
		//printf("%s\n","enter GrayScale");
			actor_GrayScale();
		//printf("%s\n","enter getPx");
			actor_getPx();
		//printf("%s\n","enter Gx");
			actor_Gx();
		//printf("%s\n","enter Gy");
			actor_Gy();
		//printf("%s\n","enter Abs");
			actor_Abs();
}


	/*
	*********************************************************
	Initialize All the Channels
	Should be called before subsystem_single_uniprocessor()
	*********************************************************
	*/
int init_subsystem(){
	/* Extern Variables */
	extern int ZeroValue;
	extern int OneValue;
	
	/* extern sdfchannel GrayScaleToAbs*/
	extern void* buffer_GrayScaleToAbs[];
	extern size_t buffer_GrayScaleToAbs_size;
	extern circular_fifo fifo_GrayScaleToAbs;
	/* extern sdfchannel AbsY*/
	extern void* buffer_AbsY[];
	extern size_t buffer_AbsY_size;
	extern circular_fifo fifo_AbsY;
	/* extern sdfchannel gysig*/
	extern void* buffer_gysig[];
	extern size_t buffer_gysig_size;
	extern circular_fifo fifo_gysig;
	/* extern sdfchannel AbsX*/
	extern void* buffer_AbsX[];
	extern size_t buffer_AbsX_size;
	extern circular_fifo fifo_AbsX;
	/* extern sdfchannel GrayScaleToGetPx*/
	extern void* buffer_GrayScaleToGetPx[];
	extern size_t buffer_GrayScaleToGetPx_size;
	extern circular_fifo fifo_GrayScaleToGetPx;
	/* extern sdfchannel gxsig*/
	extern void* buffer_gxsig[];
	extern size_t buffer_gxsig_size;
	extern circular_fifo fifo_gxsig;
	/* extern sdfchannel absysig*/
	extern void* buffer_absysig[];
	extern size_t buffer_absysig_size;
	extern circular_fifo fifo_absysig;
	/* extern sdfchannel absxsig*/
	extern void* buffer_absxsig[];
	extern size_t buffer_absxsig_size;
	extern circular_fifo fifo_absxsig;
	/* extern sdfchannel GrayScaleX*/
	extern void* buffer_GrayScaleX[];
	extern size_t buffer_GrayScaleX_size;
	extern circular_fifo fifo_GrayScaleX;
	/* extern sdfchannel GrayScaleY*/
	extern void* buffer_GrayScaleY[];
	extern size_t buffer_GrayScaleY_size;
	extern circular_fifo fifo_GrayScaleY;
	
	/* initialize the channels*/
		init(&fifo_GrayScaleToAbs,buffer_GrayScaleToAbs,buffer_GrayScaleToAbs_size, sizeof(UInt16));
			
		init(&fifo_AbsY,buffer_AbsY,buffer_AbsY_size, sizeof(UInt16));
			
		init(&fifo_gysig,buffer_gysig,buffer_gysig_size, sizeof(DoubleType));
			
		init(&fifo_AbsX,buffer_AbsX,buffer_AbsX_size, sizeof(UInt16));
			
		init(&fifo_GrayScaleToGetPx,buffer_GrayScaleToGetPx,buffer_GrayScaleToGetPx_size, sizeof(DoubleType));
			
		init(&fifo_gxsig,buffer_gxsig,buffer_gxsig_size, sizeof(DoubleType));
			
		init(&fifo_absysig,buffer_absysig,buffer_absysig_size, sizeof(DoubleType));
			
		init(&fifo_absxsig,buffer_absxsig,buffer_absxsig_size, sizeof(DoubleType));
			
		init(&fifo_GrayScaleX,buffer_GrayScaleX,buffer_GrayScaleX_size, sizeof(UInt16));
			
		init(&fifo_GrayScaleY,buffer_GrayScaleY,buffer_GrayScaleY_size, sizeof(UInt16));
			
		
write_fifo(&fifo_AbsY,(void*)&ZeroValue,1);

write_fifo(&fifo_AbsX,(void*)&ZeroValue,1);

write_fifo(&fifo_GrayScaleX,(void*)&ZeroValue,1);

write_fifo(&fifo_GrayScaleY,(void*)&ZeroValue,1);

		return 0;
	}		


