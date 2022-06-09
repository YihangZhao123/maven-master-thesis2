main: main.c  ./single/circular_fifo_lib/*.c   ./single/datatype/*.c   ./single/tile/sdfactor/*.c ./single/tile/sdfchannel/*.c    ./single/tile/*.c  
	gcc -o main  main.c ./single/circular_fifo_lib/*.c   ./single/datatype/*.c   ./single/tile/sdfactor/*.c  ./single/tile/sdfchannel/*.c    ./single/tile/*.c 




time: main.c  ./single/circular_fifo_lib/*.c   ./single/datatype/*.c   ./single/tile/sdfactor/*.c ./single/tile/sdfchannel/*.c    ./single/tile/*.c  
	gcc -Wall -pg main.c  ./single/circular_fifo_lib/*.c   ./single/datatype/*.c   ./single/tile/sdfactor/*.c ./single/tile/sdfchannel/*.c    ./single/tile/*.c   -o  time_prof

clean:
	rm *.exe  