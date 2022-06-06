package template.rtos;

import forsyde.io.java.core.Vertex;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.ActorTemplate;

@SuppressWarnings("all")
public class SDFActorInc implements ActorTemplate {
  private Vertex actor;
  
  @Override
  public String savePath() {
    String _identifier = this.actor.getIdentifier();
    String _plus = ("/sdfactor/sdfactor_" + _identifier);
    return (_plus + ".h");
  }
  
  @Override
  public String create(final Vertex vertex) {
    String _xblockexpression = null;
    {
      this.actor = vertex;
      String name = vertex.getIdentifier();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#ifndef ACTOR_");
      String _upperCase = name.toUpperCase();
      _builder.append(_upperCase);
      _builder.append("_H_");
      _builder.newLineIfNotEmpty();
      _builder.append("#define ACTOR_");
      String _upperCase_1 = name.toUpperCase();
      _builder.append(_upperCase_1);
      _builder.append("_H_");
      _builder.newLineIfNotEmpty();
      _builder.append("#include \"../datatype/datatype_definition.h\"");
      _builder.newLine();
      _builder.append("#include \"../configRTOS.h\"");
      _builder.newLine();
      _builder.append("#include \"FreeRTOS.h\"");
      _builder.newLine();
      _builder.append("#include \"semphr.h\"");
      _builder.newLine();
      _builder.append("#include \"timers.h\"\t");
      _builder.newLine();
      _builder.append("#include \"queue.h\"");
      _builder.newLine();
      _builder.append("#if FREERTOS==1");
      _builder.newLine();
      _builder.append("void task_");
      _builder.append(name);
      _builder.append("(void* pdata);");
      _builder.newLineIfNotEmpty();
      _builder.append("void timer_");
      _builder.append(name);
      _builder.append("_callback(TimerHandle_t xTimer);");
      _builder.newLineIfNotEmpty();
      _builder.append("#endif");
      _builder.newLine();
      _builder.newLine();
      _builder.append("#endif");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
