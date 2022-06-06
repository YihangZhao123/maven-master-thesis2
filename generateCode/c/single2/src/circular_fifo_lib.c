#include "../inc/config.h"

/*
*******************************************************
	This file contains the function definition for 
	token types: uint32
	For each token type, there are five functions:
	init_channel_typeName(...)
	read_non_blocking_typeName(...)
	read_blocking_typeName(...)
	write_non_blocking_typeName(...)
	write_blocking_typeName(...)
*******************************************************
*/
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include <string.h>

void ref_init(ref_fifo* fifo_ptr, void** buffer, size_t size){
	
	fifo_ptr->front=0;
	fifo_ptr->rear=0;
	fifo_ptr->buffer=buffer;
	fifo_ptr->size=size;
}
void read_non_blocking(ref_fifo* fifo_ptr, void** dst){
	if(fifo_ptr->front==fifo_ptr->rear){
	 //empty
	 return;
	}else{
		*dst = fifo_ptr->buffer[fifo_ptr->front];					
		fifo_ptr->front= (fifo_ptr->front+1)%fifo_ptr->size;
		return;
	}
}
void write_non_blocking(ref_fifo* channel, void* src){
    if((channel->rear+1)%channel->size == channel->front){
		//full
		      return ;
		   }else{
		      channel->buffer[channel->rear] = src;
		     channel->rear= (channel->rear+1)%channel->size;
		      return;
		  }					
}		


void init2(copy_fifo* fifo_ptr, void* buf, size_t token_number, size_t token_size){
	fifo_ptr->front=0;
	fifo_ptr->rear=0;
	fifo_ptr->token_number=token_number;
	fifo_ptr->token_size=token_size;
}
void read_non_blocking2(copy_fifo* fifo_ptr,void* dst){
	if(fifo_ptr->front==fifo_ptr->rear){
		//empty
		return;
	}else{
		
		memcpy(dst,fifo_ptr->buffer+fifo_ptr->front*fifo_ptr->token_size,fifo_ptr->token_size);
		fifo_ptr->front= (fifo_ptr->front+1)%fifo_ptr->token_number;
		
	}
}
void write_non_blocking2(copy_fifo* channel,void* src){
	if((channel->rear+1)%channel->token_number == channel->front){
		//full	
	}else{
		memcpy(channel->rear*channel->token_size+channel->buffer, src , channel->token_size);
		 channel->rear= (channel->rear+1)%channel->token_number;
	}
}


/*
=============================================================
				uint32 Channel Definition
=============================================================
*/				
void init_channel_uint32(circular_fifo_uint32 *channel ,uint32* buffer, size_t size){
    channel->buffer = buffer;
    channel->size=size;
    channel->front = 0;
    channel->rear = 0;			
}

		int read_non_blocking_uint32(circular_fifo_uint32 *channel, uint32 *data){
			if(channel->front==channel->rear){
			    	//empty 
			    	return -1;
			    			
			   }else{
			    	*data = channel->buffer[channel->front];
			    	channel->front= (channel->front+1)%channel->size;
			    	return 0;
			    }
		}
		int read_blocking_uint32(circular_fifo_uint32* channel,uint32* data,spinlock* lock){
			spinlock_get(lock);
			if(channel->front==channel->rear){
			    	//empty 
			    	spinlock_release(lock);
			    	return -1;
			    			
			   }else{
			    	*data = channel->buffer[channel->front];
			    	//printf("buffer uint32: before read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
			    	channel->front= (channel->front+1)%channel->size;
			    	//printf("buffer uint32: after read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
			    	spinlock_release(lock);
			    	return 0;
			    }
		}				

		int write_non_blocking_uint32(circular_fifo_uint32* channel, uint32 value){
		    /*if the buffer is full*/
		    if((channel->rear+1)%channel->size == channel->front){
		        //full!
		        //discard the data
		        //printf("buffer full error\n!");
		        return -1;
		     }else{
		        channel->buffer[channel->rear] = value;
		       //printf("buffer uint32:before write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
		        channel->rear= (channel->rear+1)%channel->size;
		        //printf("buffer uint32:after write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
		        return 0;
		    }			
		
		}	

		int write_blocking_uint32(circular_fifo_uint32* channel, uint32 value,spinlock* lock){
			spinlock_get(lock);
			
			   /*if the buffer is full*/
			   if((channel->rear+1)%channel->size == channel->front){
			       //full!
			       //discard the data
			       //printf("buffer full error\n!");
			       spinlock_release(lock);
			       return -1;
			    }else{
			       channel->buffer[channel->rear] = value;
			      //printf("buffer uint32:before write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
			       channel->rear= (channel->rear+1)%channel->size;
			       //printf("buffer uint32:after write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
			       spinlock_release(lock);
			       return 0;
			   }				
		}


