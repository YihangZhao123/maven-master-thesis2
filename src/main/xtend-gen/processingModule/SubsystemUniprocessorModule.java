package processingModule;

import generator.Generator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import template.templateInterface.SubsystemTemplate;
import utils.Save;

@SuppressWarnings("all")
public class SubsystemUniprocessorModule implements ModuleInterface {
  private Set<SubsystemTemplate> templates;
  
  public SubsystemUniprocessorModule() {
    HashSet<SubsystemTemplate> _hashSet = new HashSet<SubsystemTemplate>();
    this.templates = _hashSet;
  }
  
  @Override
  public void create() {
    this.process();
  }
  
  public void process() {
    final Consumer<SubsystemTemplate> _function = (SubsystemTemplate t) -> {
      String _create = t.create(null);
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
