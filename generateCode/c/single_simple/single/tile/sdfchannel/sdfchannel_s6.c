
#include "sdfchannel_s6.h"
#include "../../circular_fifo_lib/circular_fifo_lib.h"
circular_fifo fifo_s6;
volatile UInt32 buffer_s6[2];
int channel_s6_size=1;
/*Because of circular fifo, the buffer_size=channel_size+1 */
int buffer_s6_size = 2;
