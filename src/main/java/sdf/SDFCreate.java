package sdf;
import forsyde.io.java.core.*;

import forsyde.io.java.drivers.ForSyDeModelHandler;
//import forsyde.io.java.graphviz.ForSyDeGraphVizDriver;
import forsyde.io.java.typed.viewers.decision.sdf.BoundedSDFChannel;
import forsyde.io.java.typed.viewers.decision.sdf.PASSedSDFActor;
import forsyde.io.java.typed.viewers.impl.ANSICBlackBoxExecutable;
import forsyde.io.java.typed.viewers.impl.TokenizableDataBlock;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFActor;
import forsyde.io.java.typed.viewers.typing.TypedOperation;
import forsyde.io.java.typed.viewers.typing.datatypes.Integer;
import forsyde.io.java.typed.viewers.visualization.Visualizable;
import forsyde.io.java.validation.SDFValidator;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.AsUndirectedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
public class SDFCreate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			create();
	}
	public static void create() {
        final ForSyDeSystemGraph model = new ForSyDeSystemGraph();
        final SDFValidator validator = new SDFValidator();

        // since we only want the SDF actor, we can do it in 1 line
        // as the vertex is still accessible by the viewer
        final SDFActor p1 = SDFActor.enforce(model.newVertex("p1"));
        p1.getPorts().addAll(Set.of("s_in_port", "s1_port"));
        p1.setProduction(Map.of("s1_port", 1));
        p1.setConsumption(Map.of("s_in_port", 1));

        // this could be the way it both as always accessible
        final Vertex p2Vertex = new Vertex("p2");
        final SDFActor p2 = SDFActor.enforce(p2Vertex);
        model.addVertex(p2Vertex); // same as using the viewer as above
        p2.getPorts().add("s1_port");
        p2.setConsumption(Map.of("s1_port", 1));

        // or a mix of both previous syntaxes
        final SDFChannel s1 = SDFChannel.enforce(new Vertex("s1"));
        model.addVertex(s1.getViewedVertex());

        final SDFChannel sIn = SDFChannel.enforce(model.newVertex("s_in"));

        // you can use the veiwer setters to connect the vertexes, which
        // also takes care of edge traits.
        // this syntax is presetn from ForSyDe 0.5.9 onwards
        sIn.setConsumerPort(model, p1, "s_in_port"); // this is currently the recommended way

        // you can also connect things directly, but
        // then you need to know which syntactially correct ports
        // the vertexes connect to each other. Like 'producer' here, for example.
        // the hint is that the viewer's set and get functions denote the ports
        model.connect(p1, s1, "s1_port", "producer", EdgeTrait.MOC_SDF_SDFDATAEDGE);

        // the order is inverted here to symbolize that the outgoing port
        // is part of s1 and not p2.
        s1.setConsumerPort(model, p2, "s1_port");

        // this model admits a sime schedule
        final PASSedSDFActor p1pass = PASSedSDFActor.enforce(p1);
        p1pass.setFiringSlots(List.of(0));

        final PASSedSDFActor p2pass = PASSedSDFActor.enforce(p2);
        p2pass.setFiringSlots(List.of(1));

        final BoundedSDFChannel sInBounded = BoundedSDFChannel.enforce(sIn);
        sInBounded.setMaximumTokens(1);

        final BoundedSDFChannel s1Bounded = BoundedSDFChannel.enforce(s1);
        s1Bounded.setMaximumTokens(1);

        // now lets put some types and code
        final Integer uint32Type = Integer.enforce(model.newVertex("UInt32"));
        uint32Type.setNumberOfBits(32);

        // the body for p1
        final ANSICBlackBoxExecutable p1Body = ANSICBlackBoxExecutable.enforce(model.newVertex("p1Body"));
        p1Body.getPorts().add("s1");
        p1Body.setInlinedCode("s1 = 5;");
        // its types
        final TypedOperation p1TypedOp = TypedOperation.enforce(p1Body);
        p1TypedOp.setOutputPorts(List.of("s1"));
        p1TypedOp.setOutputPortTypesPort(model, List.of(uint32Type));
        // and we connect it to the actual SDF actor
        p1.setCombFunctionsPort(model, Set.of(p1Body));
        // just for consistency, we also connec the ports of the sdf to the body
        model.connect(p1Body, p1,"s1", "s1_port", EdgeTrait.MOC_ABSTRACTIONEDGE); //output

        // now we do the same for p2
        final ANSICBlackBoxExecutable p2Body = ANSICBlackBoxExecutable.enforce(model.newVertex("p2Body"));
        p2Body.getPorts().add("s1");
        p2Body.setInlinedCode("int c = s1;");
        // its types
        final TypedOperation p2TypedOp = TypedOperation.enforce(p2Body);
        p2TypedOp.setInputPorts(List.of("s1"));
        p2TypedOp.setInputPortTypesPort(model, List.of(uint32Type));
        // and we connect it to the actual SDF actor
        p2.setCombFunctionsPort(model, Set.of(p2Body));
        // just for consistency, we also connec the ports of the sdf to the body
        model.connect(p1,p1Body, "s1_port", "s1", EdgeTrait.MOC_ABSTRACTIONEDGE); //output



        // lets also make the input token be a tokenizeable datablock
        final TokenizableDataBlock sInDB = TokenizableDataBlock.enforce(sIn);
        sInDB.setTokenSizeInBits(32L);
        sInDB.setMaxSizeInBits(32L);

        // lets make everything visualizeable just for the sake of it
        Visualizable.enforce(p1);
        Visualizable.enforce(p2);
        Visualizable.enforce(sIn);
        Visualizable.enforce(s1);
        // and make the connections between them
        model.connect(sIn, p1, EdgeTrait.VISUALIZATION_VISUALCONNECTION);
        model.connect(p1, s1, EdgeTrait.VISUALIZATION_VISUALCONNECTION);
        model.connect(s1, p2, EdgeTrait.VISUALIZATION_VISUALCONNECTION);






        
        try {
			(new ForSyDeModelHandler()).writeModel(model,"simple.fiodl");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //forSyDeModelHandler.writeModel(model, "simple.fiodl");


	}
}
