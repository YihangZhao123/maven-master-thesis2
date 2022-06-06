package generator

import forsyde.io.java.typed.viewers.moc.sdf.SDFActor


import forsyde.io.java.core.Vertex
import java.util.Set
import template.templateInterface.ActorTemplate
import java.util.HashSet
import utils.Save
import utils.Name
import fileAnnotation.FileTypeAnno
import java.lang.reflect.*
import fileAnnotation.FileType
import forsyde.io.java.core.ForSyDeSystemGraph
import utils.Query

class SDFCombProcessingModule  implements ModuleInterface{
	Set<ActorTemplate> templates
	new (){
		templates= new HashSet
	}
	
	override create() {

		Generator.model.vertexSet().stream().filter([v|SDFActor::conforms(v)]).forEach([v|process(v)])
	}
	
	def void process(Vertex v){
		templates.stream().forEach( [t| 
			
			save(Generator.model,v,t) 
		] )
	}

	def void add(ActorTemplate t){
		templates.add(t)
	}
	def save(ForSyDeSystemGraph model, Vertex actor, ActorTemplate t){
		if(Generator.platform==1){
			Save.save(t.create(actor),Generator.root+"/tile/"+t.savePath());
		}
		
		
		if(Generator.platform==2){
			var Vertex tile=Query.findTile(Generator.model,actor)	
			if(tile!==null){
				Save.save(t.create(actor),Generator.root+"/"+tile.getIdentifier()+"/"+t.savePath())
			}	
		}
		
		if(Generator.platform==3){
			Save.save(t.create(actor),Generator.root+t.savePath());
		}
	}	
}