package template.fifo.fifo2;

import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;

@SuppressWarnings("all")
public class FIFOSrc2 implements InitTemplate {
  public FIFOSrc2() {
  }
  
  @Override
  public String savePath() {
    return "/circular_fifo_lib/circular_fifo_lib.c";
  }
  
  @Override
  public String create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/*");
    _builder.newLine();
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("copy by value");
    _builder.newLine();
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"../datatype/datatype_definition.h\"");
    _builder.newLine();
    _builder.append("#include \"../circular_fifo_lib/circular_fifo_lib.h\"");
    _builder.newLine();
    _builder.append("#include <string.h>");
    _builder.newLine();
    _builder.append("#include <stdio.h>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void init_fifo(circular_fifo* fifo_ptr, void* buf, size_t capacity, size_t token_size){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fifo_ptr->buffer=buf;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fifo_ptr->front=0;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fifo_ptr->rear=0;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fifo_ptr->capacity=capacity;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fifo_ptr->token_size=token_size;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fifo_ptr->count=0;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("void read_fifo(circular_fifo* channel, void* dst, size_t number){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("while(channel->count< number);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("char* memcpy_dst,*memcpy_src;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for(int i=0; i<number;++i){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("memcpy_dst=(char*)dst+i*channel->token_size;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("memcpy_src=((char*)channel->buffer+channel->front*channel->token_size);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("memcpy(memcpy_dst, memcpy_src ,channel->token_size);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("channel->front= (channel->front+1)%channel->capacity;\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("--(channel->count);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("void write_fifo(circular_fifo* channel,void* src, size_t number){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("char* memcpy_dst,*memcpy_src;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("for(int i=0; i<number;++i){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("memcpy_dst=(channel->rear*channel->token_size+ (char*)channel->buffer);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("memcpy_src = (char*)src+i*channel->token_size;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("memcpy(memcpy_dst, memcpy_src, channel->token_size);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("channel->rear= (channel->rear+1)%channel->capacity;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("++(channel->count);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}\t\t\t\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("void PRINT(circular_fifo * fifo){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("printf(\"buffer addr 0x%p, front: %d , rear %d, count %d\\n\",fifo->buffer,fifo->front,fifo->rear,fifo->count);");
    _builder.newLine();
    _builder.append("}\t\t\t\t");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    return _builder.toString();
  }
}
