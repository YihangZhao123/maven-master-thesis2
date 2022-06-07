package template.fifo.fifo1;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import generator.Generator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;
import utils.Query;

@SuppressWarnings("all")
public class FIFOSrc1 implements InitTemplate {
  private Set<Vertex> typeVertexSet;
  
  public FIFOSrc1() {
    final ForSyDeSystemGraph model = Generator.model;
    final Predicate<Vertex> _function = (Vertex v) -> {
      return (SDFChannel.conforms(v)).booleanValue();
    };
    final Function<Vertex, String> _function_1 = (Vertex v) -> {
      return Query.findSDFChannelDataType(model, v);
    };
    final Function<String, Vertex> _function_2 = (String s) -> {
      return Query.findVertexByName(model, s);
    };
    this.typeVertexSet = model.vertexSet().stream().filter(_function).<String>map(_function_1).<Vertex>map(_function_2).collect(Collectors.<Vertex>toSet());
    boolean _contains = this.typeVertexSet.contains(null);
    if (_contains) {
      this.typeVertexSet.remove(null);
    }
  }
  
  @Override
  public String savePath() {
    return "/circular_fifo_lib/circular_fifo_lib.c";
  }
  
  @Override
  public String create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("This file contains the function definition for ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("token types: ");
    {
      boolean _hasElements = false;
      for(final Vertex typeVertex : this.typeVertexSet) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "\t\t\t");
        }
        String _identifier = typeVertex.getIdentifier();
        _builder.append(_identifier, "\t\t\t");
      }
      if (_hasElements) {
        _builder.append("", "\t\t\t");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("For each token type, there are five functions:");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("init_channel_typeName(...)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("read_non_blocking_typeName(...)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("read_blocking_typeName(...)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("write_non_blocking_typeName(...)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("write_blocking_typeName(...)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#include \"../datatype/datatype_definition.h\"");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#include \"circular_fifo_lib.h\"");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#include <string.h>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _hasElements_1 = false;
      for(final Vertex typeVertex_1 : this.typeVertexSet) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate("", "\t\t");
        }
        _builder.append("\t\t");
        CharSequence _produce = this.produce(typeVertex_1);
        _builder.append(_produce, "\t\t");
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
      }
      if (_hasElements_1) {
        _builder.append("", "\t\t");
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    return _builder.toString();
  }
  
  public CharSequence produce(final Vertex typeVertex) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    final String type = typeVertex.getIdentifier();
    _builder.newLineIfNotEmpty();
    {
      Boolean _hasTrait = typeVertex.hasTrait(VertexTrait.TYPING_DATATYPES_ARRAY);
      boolean _not = (!(_hasTrait).booleanValue());
      if (_not) {
        _builder.append("/*");
        _builder.newLine();
        _builder.append("=============================================================");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append(type, "\t\t\t\t");
        _builder.append(" Channel Definition");
        _builder.newLineIfNotEmpty();
        _builder.append("=============================================================");
        _builder.newLine();
        _builder.append("*/\t\t\t\t");
        _builder.newLine();
        _builder.append("void init_channel_");
        _builder.append(type);
        _builder.append("(circular_fifo_");
        _builder.append(type);
        _builder.append(" *channel ,");
        _builder.append(type);
        _builder.append("* buffer, size_t size){");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("channel->buffer = buffer;");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("channel->size=size;");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("channel->front = 0;");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("channel->rear = 0;\t");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("channel->count=0;\t\t");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.append("void read_fifo_");
        _builder.append(type);
        _builder.append("(circular_fifo_");
        _builder.append(type);
        _builder.append("* channel,");
        _builder.append(type);
        _builder.append("* dst, size_t number){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("while( channel->count < number );");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("for(int i=0; i<number;++i){");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("dst[i] = channel->buffer[channel->front];");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("channel->front= (channel->front+1)%channel->size;");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("--(channel->count);\t\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("void write_fifo_");
        _builder.append(type);
        _builder.append("(circular_fifo_");
        _builder.append(type);
        _builder.append("* channel,");
        _builder.append(type);
        _builder.append("* src, size_t number){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("for(int i=0; i<number; ++i){");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("channel->buffer[channel->rear] = src[i];");
        _builder.newLine();
        _builder.append("     \t");
        _builder.append("channel->rear= (channel->rear+1)%channel->size;");
        _builder.newLine();
        _builder.append("     \t");
        _builder.append("++(channel->count);\t");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        _builder.append("void PRINT_");
        _builder.append(type);
        _builder.append("(circular_fifo_");
        _builder.append(type);
        _builder.append(" * fifo){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("printf(\"buffer addr 0x%p, front: %d , rear %d, count %d\\n\",fifo->buffer,fifo->front,fifo->rear,fifo->count);");
        _builder.newLine();
        _builder.append("}\t\t\t\t");
        _builder.newLine();
      }
    }
    _builder.newLine();
    return _builder;
  }
  
  public boolean isOneDimension(final Vertex v) {
    String inner = Query.getInnerType(Generator.model, v);
    Vertex innerVertex = Query.findVertexByName(Generator.model, inner);
    Boolean _hasTrait = innerVertex.hasTrait(VertexTrait.TYPING_DATATYPES_ARRAY);
    if ((_hasTrait).booleanValue()) {
      return false;
    } else {
      return true;
    }
  }
}
