package processingModule;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.sdf.SDFActor;
import generator.Generator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import template.templateInterface.ActorTemplate;
import utils.Query;
import utils.Save;

@SuppressWarnings("all")
public class SDFCombProcessingModule implements ModuleInterface {
  private Set<ActorTemplate> templates;
  
  public SDFCombProcessingModule() {
    HashSet<ActorTemplate> _hashSet = new HashSet<ActorTemplate>();
    this.templates = _hashSet;
  }
  
  @Override
  public void create() {
    final Predicate<Vertex> _function = (Vertex v) -> {
      return (SDFActor.conforms(v)).booleanValue();
    };
    final Consumer<Vertex> _function_1 = (Vertex v) -> {
      this.process(v);
    };
    Generator.model.vertexSet().stream().filter(_function).forEach(_function_1);
  }
  
  public void process(final Vertex v) {
    final Consumer<ActorTemplate> _function = (ActorTemplate t) -> {
      this.save(Generator.model, v, t);
    };
    this.templates.stream().forEach(_function);
  }
  
  public void add(final ActorTemplate t) {
    this.templates.add(t);
  }
  
  public boolean save(final ForSyDeSystemGraph model, final Vertex actor, final ActorTemplate t) {
    boolean _xblockexpression = false;
    {
      if ((Generator.platform == 1)) {
        String _create = t.create(actor);
        String _savePath = t.savePath();
        String _plus = ((Generator.root + "/tile/") + _savePath);
        Save.save(_create, _plus);
      }
      if ((Generator.platform == 2)) {
        Vertex tile = Query.findTile(Generator.model, actor);
        if ((tile != null)) {
          String _create_1 = t.create(actor);
          String _identifier = tile.getIdentifier();
          String _plus_1 = ((Generator.root + "/") + _identifier);
          String _plus_2 = (_plus_1 + "/");
          String _savePath_1 = t.savePath();
          String _plus_3 = (_plus_2 + _savePath_1);
          Save.save(_create_1, _plus_3);
        }
      }
      boolean _xifexpression = false;
      if ((Generator.platform == 3)) {
        String _create_2 = t.create(actor);
        String _savePath_2 = t.savePath();
        String _plus_4 = (Generator.root + _savePath_2);
        _xifexpression = Save.save(_create_2, _plus_4);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
