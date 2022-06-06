package processingModule;

import generator.Generator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import template.templateInterface.InitTemplate;
import utils.Save;

@SuppressWarnings("all")
public class InitProcessingModule implements ModuleInterface {
  private Set<InitTemplate> templateSet;
  
  public InitProcessingModule() {
    HashSet<InitTemplate> _hashSet = new HashSet<InitTemplate>();
    this.templateSet = _hashSet;
  }
  
  @Override
  public void create() {
    final Consumer<InitTemplate> _function = (InitTemplate t) -> {
      this.process(t);
    };
    this.templateSet.stream().forEach(_function);
  }
  
  public void process(final InitTemplate t) {
    String _create = t.create();
    String _savePath = t.savePath();
    String _plus = (Generator.root + _savePath);
    Save.save(_create, _plus);
  }
  
  public boolean add(final InitTemplate t) {
    return this.templateSet.add(t);
  }
}
