package template.rtos;

import forsyde.io.java.core.Vertex;
import generator.Generator;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;

@SuppressWarnings("all")
public class SoftTimerSrc implements InitTemplate {
  @Override
  public String create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"FreeRTOS.h\"");
    _builder.newLine();
    _builder.append("#include \"semphr.h\"");
    _builder.newLine();
    _builder.append("#include \"timers.h\"\t");
    _builder.newLine();
    _builder.append("#include \"queue.h\"");
    _builder.newLine();
    _builder.append("#include \"../configRTOS.h\"");
    _builder.newLine();
    _builder.append("/*");
    _builder.newLine();
    _builder.append("********************************************");
    _builder.newLine();
    _builder.append("Soft Timer and Semaphore");
    _builder.newLine();
    _builder.append("********************************************");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _hasElements = false;
      for(final Vertex actor : Generator.sdfcombSet) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("", "");
        }
        String name = actor.getIdentifier();
        _builder.newLineIfNotEmpty();
        _builder.append("/*");
        _builder.newLine();
        _builder.append("============================================");
        _builder.newLine();
        _builder.append("Soft Timer for Actor ");
        _builder.append(name);
        _builder.newLineIfNotEmpty();
        _builder.append("============================================");
        _builder.newLine();
        _builder.append("*/");
        _builder.newLine();
        _builder.append("#if FREERTOS==1");
        _builder.newLine();
        _builder.append("SemaphoreHandle_t timer_sem_");
        _builder.append(name);
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("TimerHandle_t task_timer_");
        _builder.append(name);
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("#endif");
        _builder.newLine();
      }
      if (_hasElements) {
        _builder.append("");
      }
    }
    return _builder.toString();
  }
  
  @Override
  public String savePath() {
    return "/timer/softtimer.c";
  }
}
