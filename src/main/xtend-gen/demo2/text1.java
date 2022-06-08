package demo2;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;

@SuppressWarnings("all")
public class text1 {
  public static void main(final String[] args) {
    try {
      final String path = "simple.fiodl";
      final String root = "generateCode/example2/multi_fifo1";
      ForSyDeModelHandler loader = new ForSyDeModelHandler();
      ForSyDeSystemGraph model = loader.loadModel(path);
      Vertex p4 = model.queryVertex("p4").get();
      Vertex tile0 = model.queryVertex("tile0").get();
      Vertex tile1 = model.queryVertex("tile1").get();
      BFSShortestPath<Vertex, EdgeInfo> bfs = new BFSShortestPath<Vertex, EdgeInfo>(model);
      GraphPath<Vertex, EdgeInfo> a = bfs.getPath(tile0, p4);
      GraphPath<Vertex, EdgeInfo> b = bfs.getPath(tile1, p4);
      InputOutput.<GraphPath<Vertex, EdgeInfo>>println(a);
      InputOutput.<GraphPath<Vertex, EdgeInfo>>println(b);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
