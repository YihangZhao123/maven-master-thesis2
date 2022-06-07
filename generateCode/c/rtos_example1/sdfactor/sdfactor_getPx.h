#ifndef ACTOR_GETPX_H_
#define ACTOR_GETPX_H_
#include "../datatype/datatype_definition.h"
#include "../configRTOS.h"
#include "FreeRTOS.h"
#include "semphr.h"
#include "timers.h"	
#include "queue.h"
#if FREERTOS==1
void task_getPx(void* pdata);
void timer_getPx_callback(TimerHandle_t xTimer);
#endif

#endif
