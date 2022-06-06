package generator

import java.util.Set
import forsyde.io.java.core.VertexTrait
import java.util.HashSet
import java.util.stream.Collectors
import forsyde.io.java.core.Vertex
import java.util.List
import java.util.ArrayList
import forsyde.io.java.core.VertexAcessor
import template.templateInterface.InitTemplate
import fileAnnotation.FileTypeAnno
import fileAnnotation.FileType
import utils.Name
import utils.Save

class InitProcessingModule implements ModuleInterface {
	Set<InitTemplate> templateSet

	new() {
		templateSet = new HashSet
		
	}



	override create() {
		templateSet.stream().forEach([t|process(t)])
	}

 	def void process(InitTemplate t){
 		
 		Save.save(t.create(),Generator.root+t.savePath())

 	}



	def add(InitTemplate t) {
		templateSet.add(t)
	}

}
