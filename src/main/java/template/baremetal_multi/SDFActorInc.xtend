package template.baremetal_multi


import forsyde.io.java.core.Vertex
import forsyde.io.java.typed.viewers.impl.Executable
import forsyde.io.java.typed.viewers.moc.sdf.SDFActorViewer
import generator.Generator
import java.util.Set
import template.templateInterface.ActorTemplate
import utils.Query


class SDFActorInc implements ActorTemplate{
	Set<Executable> a
	Vertex actor
	override create(Vertex actor) {
		this.actor=actor
		this.a=   (new SDFActorViewer(actor)).getCombFunctionsPort(Generator.model)
		'''
			«var name = actor.getIdentifier()»
			«var tmp=name.toUpperCase()+"_H_"»
			#ifndef  «tmp»
			#define «tmp»			
			void actor_«name»();
			#endif
		'''
		
	}
	
	override savePath() {
		return "/sdfactor/sdfactor_"+actor.getIdentifier()+".h"
	}
	
}
