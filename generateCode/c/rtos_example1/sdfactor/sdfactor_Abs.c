	#include "../configRTOS.h"
	#include "../datatype/datatype_definition.h"
	#include "sdfactor_Abs.h"
	#include "FreeRTOS.h"
	#include "semphr.h"
	#include "timers.h"	
	#include "queue.h"

	/*
	==============================================
	Define Task Stack
	==============================================
	*/
	#if FREERTOS==1
	StackType_t task_Abs_stk[ABS_STACKSIZE];
	StaticTask_t tcb_Abs;
	#endif
	/*
	==============================================
		Declare Extern Message Queue Handler
	==============================================
	*/
	/* Input Message Queue */
	#if FREERTOS==1
	extern QueueHandle_t msg_queue_GrayScaleToAbs;
	extern QueueHandle_t msg_queue_AbsY;
	extern QueueHandle_t msg_queue_AbsX;
	extern QueueHandle_t msg_queue_absysig;
	extern QueueHandle_t msg_queue_absxsig;
	/* Output Message Quueue */
	#endif
	/*
	==============================================
			Extern Variables
	==============================================
	*/
	extern ArrayXOfArrayXOfDoubleType system_img_sink_global;
	extern Array1000OfArrayOfDouble outputImage;
	
	/*
	==============================================
		Define Soft Timer and Soft Timer Semaphore
	==============================================
	*/
	#if FREERTOS==1
	SemaphoreHandle_t timer_sem_Abs;
	TimerHandle_t timer_Abs;
	#endif
	/*
	==============================================
		 Task Function
	==============================================
	*/
	void task_Abs(void* pdata){
		/* Initilize Memory           */
		UInt16 offsetX; 
		UInt16 offsetY; 
		Array2OfUInt16 dims; 
		ArrayXOfArrayXOfDoubleType system_img_sink_address = system_img_sink_global; 
		DoubleType resy; 
		DoubleType resx; 
		while(1){
	/*
	==============================================
		Read From SDF Channels
	==============================================	
	*/
			#if FREERTOS==1
			xQueueReceive(msg_queue_absxsig,&resx,portMAX_DELAY);
			#endif
			#if FREERTOS==1
			xQueueReceive(msg_queue_absysig,&resy,portMAX_DELAY);
			#endif
			for(int i=0;i<2;++i){
				#if FREERTOS==1
				xQueueReceive(msg_queue_GrayScaleToAbs,&dims[i],portMAX_DELAY);
				#endif
			}
			#if FREERTOS==1
			xQueueReceive(msg_queue_AbsX,&offsetX,portMAX_DELAY);
			#endif
			#if FREERTOS==1
			xQueueReceive(msg_queue_AbsY,&offsetY,portMAX_DELAY);
			#endif

			
	/*
	==============================================
		Inline Code
	==============================================	
	*/
			//in combFunction AbsImpl
			if(resx<0.0)resx=-resx;
			if(resy<0.0)resy=-resy;
			if(offsetX>=dims[0]-2){
			offsetY+=1;
			offsetX=0;
			}
			if(offsetY>=dims[1]-2){
			offsetY=0;
			}
			system_img_sink_address[offsetX][offsetY]=resx+resy;
			
			
			
	/*
	==============================================
		Write To SDF Channels
	==============================================	
	*/
			#if FREERTOS==1
			xQueueSend(msg_queue_AbsX,&offsetX,portMAX_DELAY);
			#endif
			#if FREERTOS==1
			xQueueSend(msg_queue_AbsY,&offsetY,portMAX_DELAY);
			#endif
			
	/*

	==============================================
		Pend Timer's Semaphore
	==============================================	
	*/	
			xSemaphoreTake(timer_sem_Abs, portMAX_DELAY);	
		
		}
		
		
	}
	
	/*
	=============================================
	Soft Timer Callback Function
	=============================================
	*/
	#if FREERTOS==1
	void timer_Abs_callback(TimerHandle_t xTimer){
		xSemaphoreGive(timer_sem_Abs);
	}
	#endif
