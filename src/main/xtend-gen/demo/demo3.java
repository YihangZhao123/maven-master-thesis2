package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import generator.Generator;
import generator.InitProcessingModule;
import generator.SDFChannelProcessingModule;
import generator.SDFCombProcessingModule;
import generator.SubsystemUniprocessorModule;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import template.datatype.DataTypeInc;
import template.datatype.DataTypeSrc;
import template.rtos.ConfigRTOSInc;
import template.rtos.SDFActorInc;
import template.rtos.SDFActorSrc;
import template.rtos.SDFChannelSrc;
import template.rtos.SoftTimerSrc;
import template.rtos.StartTaskInc;
import template.rtos.StartTaskSrc;

/**
 * rtos
 */
@SuppressWarnings("all")
public class demo3 {
  public static void main(final String[] args) {
    try {
      final String path = "forsyde-io/modified1/complete-mapped-sobel-model.forsyde.xmi";
      final String path2 = "forsyde-io/modified1/sobel-application.fiodl";
      final String root = "generateCode/c/rtos2";
      ForSyDeModelHandler loader = new ForSyDeModelHandler();
      ForSyDeSystemGraph model = loader.loadModel(path);
      model.mergeInPlace(loader.loadModel(path2));
      Generator gen = new Generator(model, root);
      Generator.PC = 0;
      Generator.NUCLEO = 1;
      Generator.platform = 3;
      InitProcessingModule initModule = new InitProcessingModule();
      SDFCombProcessingModule actorModule = new SDFCombProcessingModule();
      SDFChannelProcessingModule sdfchannelModule = new SDFChannelProcessingModule();
      SubsystemUniprocessorModule subsystem = new SubsystemUniprocessorModule();
      ConfigRTOSInc _configRTOSInc = new ConfigRTOSInc();
      initModule.add(_configRTOSInc);
      SoftTimerSrc _softTimerSrc = new SoftTimerSrc();
      initModule.add(_softTimerSrc);
      SDFChannelSrc _sDFChannelSrc = new SDFChannelSrc();
      initModule.add(_sDFChannelSrc);
      StartTaskSrc _startTaskSrc = new StartTaskSrc();
      initModule.add(_startTaskSrc);
      StartTaskInc _startTaskInc = new StartTaskInc();
      initModule.add(_startTaskInc);
      DataTypeInc _dataTypeInc = new DataTypeInc();
      initModule.add(_dataTypeInc);
      DataTypeSrc _dataTypeSrc = new DataTypeSrc();
      initModule.add(_dataTypeSrc);
      SDFActorSrc _sDFActorSrc = new SDFActorSrc();
      actorModule.add(_sDFActorSrc);
      SDFActorInc _sDFActorInc = new SDFActorInc();
      actorModule.add(_sDFActorInc);
      gen.add(initModule);
      gen.add(actorModule);
      gen.add(sdfchannelModule);
      gen.add(subsystem);
      gen.create();
      InputOutput.<String>println(" RTOS end!");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
