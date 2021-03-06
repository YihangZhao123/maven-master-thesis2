package template.fifo.fifo3;

import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;

@SuppressWarnings("all")
public class FIFOInc3 implements InitTemplate {
  public FIFOInc3() {
  }
  
  @Override
  public String savePath() {
    return "/circular_fifo_lib/circular_fifo_lib.h";
  }
  
  @Override
  public String create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef CIRCULAR_FIFO_LIB_H_");
    _builder.newLine();
    _builder.append("#define CIRCULAR_FIFO_LIB_H_");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("#include \"../datatype/datatype_definition.h\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("#include \"../circular_fifo_lib/spinlock.h\"\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("#include <string.h>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("copy by value");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("typedef struct{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("void* buffer;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("size_t front;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("size_t rear;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("size_t token_number; // the number of tokens in fifo");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("size_t token_size; // size of one token");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}circular_fifo;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("void init(circular_fifo* fifo_ptr, void* buf, size_t token_number, size_t token_size);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("void read_non_blocking(circular_fifo* fifo_ptr,void* dst);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("void write_non_blocking(circular_fifo* fifo_ptr,void* src);\t");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#endif");
    _builder.newLine();
    return _builder.toString();
  }
}
