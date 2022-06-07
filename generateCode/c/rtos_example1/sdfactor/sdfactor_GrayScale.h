#ifndef ACTOR_GRAYSCALE_H_
#define ACTOR_GRAYSCALE_H_
#include "../datatype/datatype_definition.h"
#include "../configRTOS.h"
#include "FreeRTOS.h"
#include "semphr.h"
#include "timers.h"	
#include "queue.h"
#if FREERTOS==1
void task_GrayScale(void* pdata);
void timer_GrayScale_callback(TimerHandle_t xTimer);
#endif

#endif
