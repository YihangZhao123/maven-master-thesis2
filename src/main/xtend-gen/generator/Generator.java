package generator;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexProperty;
import forsyde.io.java.typed.viewers.moc.sdf.SDFActor;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import processingModule.ModuleInterface;

@SuppressWarnings("all")
public class Generator {
  public static String root = null;
  
  public static ForSyDeSystemGraph model;
  
  public static Set<Vertex> sdfchannelSet;
  
  public static Set<Vertex> sdfcombSet;
  
  public static TreeMap<Integer, Vertex> uniprocessorSchedule;
  
  public static int fifoType = 1;
  
  public static int platform = 0;
  
  private Set<ModuleInterface> modules = new HashSet<ModuleInterface>();
  
  public Generator(final ForSyDeSystemGraph model, final String root) {
    Generator.root = root;
    Generator.model = model;
    final Predicate<Vertex> _function = (Vertex v) -> {
      return (SDFChannel.conforms(v)).booleanValue();
    };
    Generator.sdfchannelSet = Generator.model.vertexSet().stream().filter(_function).collect(
      Collectors.<Vertex>toSet());
    final Predicate<Vertex> _function_1 = (Vertex v) -> {
      return (SDFActor.conforms(v)).booleanValue();
    };
    Generator.sdfcombSet = Generator.model.vertexSet().stream().filter(_function_1).collect(
      Collectors.<Vertex>toSet());
    this.createUniprocessorSchedule();
  }
  
  public void create() {
    final Consumer<ModuleInterface> _function = (ModuleInterface m) -> {
      m.create();
    };
    this.modules.stream().forEach(_function);
  }
  
  public boolean add(final ModuleInterface m) {
    return this.modules.add(m);
  }
  
  public void createUniprocessorSchedule() {
    TreeMap<Integer, Vertex> _treeMap = new TreeMap<Integer, Vertex>();
    Generator.uniprocessorSchedule = _treeMap;
    for (final Vertex actor : Generator.sdfcombSet) {
      {
        ArrayList<Integer> tmp = this.getFiringSlot(actor);
        for (int i = 0; (i < tmp.size()); i = (i + 1)) {
          Generator.uniprocessorSchedule.put(tmp.get(i), actor);
        }
      }
    }
  }
  
  private ArrayList<Integer> getFiringSlot(final Vertex actor) {
    VertexProperty firingSlots = actor.getProperties().get("firingSlots");
    if ((firingSlots != null)) {
      Object _unwrap = firingSlots.unwrap();
      ArrayList<Integer> slot = ((ArrayList<Integer>) _unwrap);
      return slot;
    }
    return null;
  }
}
