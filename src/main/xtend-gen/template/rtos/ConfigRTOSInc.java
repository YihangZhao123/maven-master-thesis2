package template.rtos;

import forsyde.io.java.core.Vertex;
import generator.Generator;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;

@SuppressWarnings("all")
public class ConfigRTOSInc implements InitTemplate {
  @Override
  public String create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef CONFIG_H_");
    _builder.newLine();
    _builder.append("#define CONFIG_H_");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("/*");
    _builder.newLine();
    _builder.append("************************************************");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("Config");
    _builder.newLine();
    _builder.append("************************************************");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.append("#define FREERTOS 1");
    _builder.newLine();
    _builder.append("#define UCOS_2  0");
    _builder.newLine();
    _builder.append("#define STARTTASK_STACKSIZE 2048");
    _builder.newLine();
    {
      boolean _hasElements = false;
      for(final Vertex actor : Generator.sdfcombSet) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("", "");
        }
        _builder.append("#define ");
        String _upperCase = actor.getIdentifier().toUpperCase();
        _builder.append(_upperCase);
        _builder.append("_STACKSIZE 2048");
        _builder.newLineIfNotEmpty();
      }
      if (_hasElements) {
        _builder.append("");
      }
    }
    _builder.append("#endif\t\t");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String savePath() {
    return "/configRTOS.h";
  }
}
