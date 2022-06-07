package template.rtos;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexAcessor;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.moc.sdf.SDFActor;
import forsyde.io.java.typed.viewers.typing.TypedDataBlockViewer;
import forsyde.io.java.typed.viewers.typing.TypedOperation;
import forsyde.io.java.typed.viewers.typing.datatypes.DataType;
import generator.Generator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import template.templateInterface.ActorTemplate;
import utils.Query;

@SuppressWarnings("all")
public class SDFActorSrc implements ActorTemplate {
  private Set<Vertex> implActorSet;
  
  private Set<Vertex> inputSDFChannelSet;
  
  private Set<Vertex> outputSDFChannelSet;
  
  private Vertex actor;
  
  @Override
  public String savePath() {
    String _identifier = this.actor.getIdentifier();
    String _plus = ("/sdfactor/sdfactor_" + _identifier);
    return (_plus + ".c");
  }
  
  @Override
  public String create(final Vertex actor) {
    String _xblockexpression = null;
    {
      ForSyDeSystemGraph model = Generator.model;
      this.actor = actor;
      this.implActorSet = VertexAcessor.getMultipleNamedPort(Generator.model, actor, "combFunctions", 
        VertexTrait.IMPL_ANSICBLACKBOXEXECUTABLE, VertexAcessor.VertexPortDirection.OUTGOING);
      String name = actor.getIdentifier();
      this.inputSDFChannelSet = Query.findInputSDFChannels(Generator.model, actor);
      this.outputSDFChannelSet = Query.findOutputSDFChannels(Generator.model, actor);
      Set<Vertex> datablocks = null;
      datablocks = Query.findAllExternalDataBlocks(model, SDFActor.safeCast(actor).get());
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\t");
      _builder.append("#include \"../configRTOS.h\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#include \"../datatype/datatype_definition.h\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#include \"sdfactor_");
      _builder.append(name, "\t");
      _builder.append(".h\"");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("#include \"FreeRTOS.h\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#include \"semphr.h\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#include \"timers.h\"\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#include \"queue.h\"");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Define Task Stack");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#if FREERTOS==1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("StackType_t task_");
      _builder.append(name, "\t");
      _builder.append("_stk[");
      String _upperCase = name.toUpperCase();
      _builder.append(_upperCase, "\t");
      _builder.append("_STACKSIZE];");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("StaticTask_t tcb_");
      _builder.append(name, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("#endif");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Declare Extern Message Queue Handler");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/* Input Message Queue */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#if FREERTOS==1");
      _builder.newLine();
      {
        boolean _hasElements = false;
        for(final Vertex sdfchannel : this.inputSDFChannelSet) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate("", "\t");
          }
          _builder.append("\t");
          _builder.append("extern QueueHandle_t msg_queue_");
          String _identifier = sdfchannel.getIdentifier();
          _builder.append(_identifier, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements) {
          _builder.append("", "\t");
        }
      }
      _builder.append("\t");
      _builder.append("/* Output Message Quueue */");
      _builder.newLine();
      {
        boolean _hasElements_1 = false;
        for(final Vertex sdfchannel_1 : this.outputSDFChannelSet) {
          if (!_hasElements_1) {
            _hasElements_1 = true;
          } else {
            _builder.appendImmediate("", "\t");
          }
          {
            boolean _contains = this.inputSDFChannelSet.contains(sdfchannel_1);
            boolean _not = (!_contains);
            if (_not) {
              _builder.append("\t");
              _builder.append("extern QueueHandle_t msg_queue_");
              String _identifier_1 = sdfchannel_1.getIdentifier();
              _builder.append(_identifier_1, "\t");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        if (_hasElements_1) {
          _builder.append("", "\t");
        }
      }
      _builder.append("\t");
      _builder.append("#endif");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("Extern Variables");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      {
        for(final Vertex datablock : datablocks) {
          _builder.append("\t");
          _builder.append("extern ");
          String _findType = this.findType(model, datablock);
          _builder.append(_findType, "\t");
          _builder.append(" ");
          String _identifier_2 = datablock.getIdentifier();
          _builder.append(_identifier_2, "\t");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Define Soft Timer and Soft Timer Semaphore");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#if FREERTOS==1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("SemaphoreHandle_t timer_sem_");
      _builder.append(name, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("TimerHandle_t timer_");
      _builder.append(name, "\t");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("#endif");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t ");
      _builder.append("Task Function");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("void task_");
      _builder.append(name, "\t");
      _builder.append("(void* pdata){");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("/* Initilize Memory           */");
      _builder.newLine();
      _builder.append("\t\t");
      String _initMemory = this.initMemory(model, actor);
      _builder.append(_initMemory, "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("while(1){");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Read From SDF Channels");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t\t\t");
      String _read = this.read(model, actor);
      _builder.append(_read, "\t\t\t");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Inline Code");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t\t\t");
      String _inlineCode = this.getInlineCode();
      _builder.append(_inlineCode, "\t\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Write To SDF Channels");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t\t\t");
      String _write = this.write(actor);
      _builder.append(_write, "\t\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Pend Timer\'s Semaphore");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("==============================================\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/\t");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("xSemaphoreTake(timer_sem_");
      _builder.append(name, "\t\t\t");
      _builder.append(", portMAX_DELAY);\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/*");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("=============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Soft Timer Callback Function");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("=============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#if FREERTOS==1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("void timer_");
      _builder.append(name, "\t");
      _builder.append("_callback(TimerHandle_t xTimer){");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("xSemaphoreGive(timer_sem_");
      _builder.append(name, "\t\t");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("#endif");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  /**
   * initMemory is copied from initMemory in SDFCombTemplateSrc class
   */
  public String initMemory(final ForSyDeSystemGraph model, final Vertex actor) {
    Set<String> impls = Query.findCombFuntionVertex(model, actor);
    Set<String> variableNameRecord = new HashSet<String>();
    String ret = "";
    for (final String impl : impls) {
      {
        Vertex actorimpl = model.queryVertex(impl).get();
        Set<String> ports = new HashSet<String>();
        ports.addAll(Query.findImplInputPorts(actorimpl));
        ports.addAll(Query.findImplOutputPorts(actorimpl));
        for (final String port : ports) {
          {
            String datatype = Query.findImplPortDataType(model, actorimpl, port);
            boolean _contains = variableNameRecord.contains(port);
            boolean _not = (!_contains);
            if (_not) {
              String _isSystemChannel = Query.isSystemChannel(model, actorimpl, port);
              boolean _tripleEquals = (_isSystemChannel == null);
              if (_tripleEquals) {
                String _ret = ret;
                StringConcatenation _builder = new StringConcatenation();
                _builder.append(datatype);
                _builder.append(" ");
                _builder.append(port);
                _builder.append("; ");
                _builder.newLineIfNotEmpty();
                ret = (_ret + _builder);
              } else {
                String _ret_1 = ret;
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append(datatype);
                _builder_1.append(" ");
                _builder_1.append(port);
                _builder_1.append(" = ");
                String _isSystemChannel_1 = Query.isSystemChannel(model, actorimpl, port);
                _builder_1.append(_isSystemChannel_1);
                _builder_1.append("; ");
                _builder_1.newLineIfNotEmpty();
                ret = (_ret_1 + _builder_1);
              }
              variableNameRecord.add(port);
            }
          }
        }
      }
    }
    return ret;
  }
  
  /**
   * copied and modified from method read in SDFCombTemplateSrc class
   */
  public String read(final ForSyDeSystemGraph model, final Vertex actor) {
    Set<Vertex> impls = VertexAcessor.getMultipleNamedPort(model, actor, "combFunctions", 
      VertexTrait.IMPL_ANSICBLACKBOXEXECUTABLE, VertexAcessor.VertexPortDirection.OUTGOING);
    Set<String> variableNameRecord = new HashSet<String>();
    String ret = "";
    for (final Vertex impl : impls) {
      {
        List<String> inputPorts = TypedOperation.safeCast(impl).get().getInputPorts();
        for (final String port : inputPorts) {
          if (((!variableNameRecord.contains(port)) && (Query.isSystemChannel(model, impl, port) == null))) {
            String actorPortName = Query.findActorPortConnectedToImplInputPort(model, actor, impl, port);
            String sdfchannelName = Query.findInputSDFChannelConnectedToActorPort(model, actor, actorPortName);
            String datatype = Query.findSDFChannelDataType(model, model.queryVertex(sdfchannelName).get());
            Integer consumption = SDFActor.safeCast(actor).get().getConsumption().get(actorPortName);
            if (((consumption).intValue() == 1)) {
              String _ret = ret;
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("#if FREERTOS==1");
              _builder.newLine();
              _builder.append("xQueueReceive(msg_queue_");
              _builder.append(sdfchannelName);
              _builder.append(",&");
              _builder.append(port);
              _builder.append(",portMAX_DELAY);");
              _builder.newLineIfNotEmpty();
              _builder.append("#endif");
              _builder.newLine();
              ret = (_ret + _builder);
            } else {
              String _ret_1 = ret;
              StringConcatenation _builder_1 = new StringConcatenation();
              _builder_1.append("for(int i=0;i<");
              _builder_1.append(consumption);
              _builder_1.append(";++i){");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("\t");
              _builder_1.append("#if FREERTOS==1");
              _builder_1.newLine();
              _builder_1.append("\t");
              _builder_1.append("xQueueReceive(msg_queue_");
              _builder_1.append(sdfchannelName, "\t");
              _builder_1.append(",&");
              _builder_1.append(port, "\t");
              _builder_1.append("[i],portMAX_DELAY);");
              _builder_1.newLineIfNotEmpty();
              _builder_1.append("\t");
              _builder_1.append("#endif");
              _builder_1.newLine();
              _builder_1.append("}");
              _builder_1.newLine();
              ret = (_ret_1 + _builder_1);
            }
            variableNameRecord.add(port);
          }
        }
      }
    }
    return ret;
  }
  
  /**
   * copied and modified from method write in SDFCombTemplateSrc class
   */
  public String write(final Vertex actor) {
    ForSyDeSystemGraph model = Generator.model;
    Set<String> impls = Query.findCombFuntionVertex(model, actor);
    Set<String> variableNameRecord = new HashSet<String>();
    String ret = "";
    for (final String impl : impls) {
      {
        Vertex actorimpl = Query.findVertexByName(model, impl);
        List<String> outputPortSet = Query.findImplOutputPorts(actorimpl);
        for (final String outport : outputPortSet) {
          boolean _contains = variableNameRecord.contains(outport);
          boolean _not = (!_contains);
          if (_not) {
            String actorPortName = Query.findActorPortConnectedToImplOutputPort(model, actor, actorimpl, outport);
            String sdfchannelName = Query.findOutputSDFChannelConnectedToActorPort(model, actor, actorPortName);
            String datatype = Query.findSDFChannelDataType(model, model.queryVertex(sdfchannelName).get());
            try {
              Integer production = SDFActor.enforce(actor).getProduction().get(actorPortName);
              if (((production).intValue() == 1)) {
                String _ret = ret;
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("#if FREERTOS==1");
                _builder.newLine();
                _builder.append("xQueueSend(msg_queue_");
                _builder.append(sdfchannelName);
                _builder.append(",&");
                _builder.append(outport);
                _builder.append(",portMAX_DELAY);");
                _builder.newLineIfNotEmpty();
                _builder.append("#endif");
                _builder.newLine();
                ret = (_ret + _builder);
              } else {
                String _ret_1 = ret;
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("for(int i=0;i<");
                _builder_1.append(production);
                _builder_1.append(";++i){");
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("\t");
                _builder_1.append("#if FREERTOS==1");
                _builder_1.newLine();
                _builder_1.append("\t");
                _builder_1.append("xQueueSend(msg_queue_");
                _builder_1.append(sdfchannelName, "\t");
                _builder_1.append(",");
                _builder_1.append(outport, "\t");
                _builder_1.append("+i,portMAX_DELAY);");
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("\t");
                _builder_1.append("#endif");
                _builder_1.newLine();
                _builder_1.append("}");
                _builder_1.newLine();
                ret = (_ret_1 + _builder_1);
              }
              variableNameRecord.add(outport);
            } catch (final Throwable _t) {
              if (_t instanceof Exception) {
                String _identifier = actor.getIdentifier();
                String _plus = ("In actor " + _identifier);
                String _plus_1 = (_plus + " port ");
                String _plus_2 = (_plus_1 + outport);
                String _plus_3 = (_plus_2 + " no production");
                InputOutput.<String>println(_plus_3);
                return (("error " + outport) + ";");
              } else {
                throw Exceptions.sneakyThrow(_t);
              }
            }
          }
        }
      }
    }
    return ret;
  }
  
  /**
   * This method is same as getInlineCode in SDFCombTemplateSrc class
   */
  private String getInlineCode() {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasElements = false;
      for(final Vertex impl : this.implActorSet) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("", "");
        }
        _builder.append("//in combFunction ");
        String _identifier = impl.getIdentifier();
        _builder.append(_identifier);
        _builder.newLineIfNotEmpty();
        String _inlineCode = Query.getInlineCode(impl);
        _builder.append(_inlineCode);
        _builder.newLineIfNotEmpty();
      }
      if (_hasElements) {
        _builder.append("");
      }
    }
    return _builder.toString();
  }
  
  public String findType(final ForSyDeSystemGraph model, final Vertex datablock) {
    Optional<DataType> a = new TypedDataBlockViewer(datablock).getDataTypePort(model);
    boolean _isPresent = a.isPresent();
    boolean _not = (!_isPresent);
    if (_not) {
      return null;
    }
    return a.get().getIdentifier();
  }
}
