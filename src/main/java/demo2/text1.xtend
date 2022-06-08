package demo2
import org.jgrapht.traverse.BreadthFirstIterator

import forsyde.io.java.core.VertexAcessor
import forsyde.io.java.core.VertexTrait
import forsyde.io.java.core.VertexAcessor.VertexPortDirection
import forsyde.io.java.drivers.ForSyDeModelHandler
import org.jgrapht.alg.shortestpath.BFSShortestPath
import forsyde.io.java.core.Vertex
import forsyde.io.java.core.EdgeInfo

class text1 {
	def static void main(String[] args) {
		/* testing example1.fiodl*/
		val path = "simple.fiodl"
		val root = "generateCode/example2/multi_fifo1"
		var loader = (new ForSyDeModelHandler)
		var model = loader.loadModel(path)	
		
		var p4 = model.queryVertex("p4").get()
		var tile0 = model.queryVertex("tile0").get()
		var tile1 = model.queryVertex("tile1").get()
		
		
		var BFSShortestPath<Vertex,EdgeInfo> bfs = new BFSShortestPath(model);
		
		
		var a = bfs.getPath(tile0,p4)
		
		var b = bfs.getPath(tile1,p4)
		
		
		println(a)
		
		println(b)	
	}
}