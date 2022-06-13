package processingModule;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.platform.GenericProcessingModule;
import generator.Generator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import template.templateInterface.SubsystemTemplate;
import utils.Save;

@SuppressWarnings("all")
public class SubsystemMultiprocessorModule implements ModuleInterface {
  private Set<SubsystemTemplate> templates;
  
  public SubsystemMultiprocessorModule() {
    HashSet<SubsystemTemplate> _hashSet = new HashSet<SubsystemTemplate>();
    this.templates = _hashSet;
  }
  
  @Override
  public void create() {
    final Predicate<Vertex> _function = (Vertex v) -> {
      return (GenericProcessingModule.conforms(v)).booleanValue();
    };
    Set<Vertex> tiles = Generator.model.vertexSet().stream().filter(_function).collect(
      Collectors.<Vertex>toSet());
    final Consumer<Vertex> _function_1 = (Vertex tile) -> {
      this.process(tile);
    };
    tiles.stream().forEach(_function_1);
  }
  
  public void process(final Vertex tile) {
    final Consumer<SubsystemTemplate> _function = (SubsystemTemplate t) -> {
      String _create = t.create(tile);
      String _savePath = t.savePath();
      String _plus = (Generator.root + _savePath);
      Save.save(_create, _plus);
    };
    this.templates.stream().forEach(_function);
  }
  
  public boolean add(final SubsystemTemplate t) {
    return this.templates.add(t);
  }
}
