package demo


import forsyde.io.java.drivers.ForSyDeModelHandler






import generator.Generator

import template.uniprocessor.actor.SDFActorSrc
import template.uniprocessor.actor.SDFActorInc
import template.uniprocessor.subsystem.SubsystemTemplateSrc
import template.uniprocessor.subsystem.SubsystemTemplateInc

import template.uniprocessor.SDFChannel.SDFChannelInc
import template.datatype.DataTypeInc
import template.datatype.DataTypeSrc
import template.fifo.fifo1.FIFOInc1
import template.fifo.fifo1.FIFOSrc1


import template.fifo.SpinLockTemplateInc
import template.fifo.SpinLockTemplateSrc
import processingModule.SDFChannelProcessingModule
import processingModule.SDFCombProcessingModule
import processingModule.SubsystemUniprocessorModule
import processingModule.InitProcessingModule
import template.fifo.fifo2.FIFOInc2
import template.fifo.fifo2.FIFOSrc2
import template.uniprocessor.SDFChannel.SDFChannelSrc

/**
 * one core
 */
class demo1 {
	def static void main(String[] args) {
//		val path = "forsyde-io/modified1/complete-mapped-sobel-model.forsyde.xmi";
//		val path2 = "forsyde-io/modified1/sobel-application.fiodl"
//		val root = "generateCode/c/single/single"
//
//		var loader = (new ForSyDeModelHandler)
//		var model = loader.loadModel(path)
//		model.mergeInPlace(loader.loadModel(path2))
		/* testing example1.fiodl*/
		//val path = "example1-2cores.fiodl"
		val path = "simple.fiodl"
		//val root = "generateCode/c/single/single"
		val root="generateCode/c/single_simple/single"
		var loader = (new ForSyDeModelHandler)
		var model = loader.loadModel(path)				
		
//		val path = "test2.fiodl"
//		val root = "generateCode/c/single2"
//		var loader = (new ForSyDeModelHandler)
//		var model = loader.loadModel(path)		
		
		
		var Generator gen = new Generator(model, root)
		Generator.fifoType=2
		Generator.platform=1 //1 is uniprocessor, 2 is multi, 3 is rtos
		 
		var sdfchannelModule = new SDFChannelProcessingModule
		sdfchannelModule.add(new SDFChannelSrc)
		sdfchannelModule.add(new SDFChannelInc)
		
		gen.add(sdfchannelModule)

		var actorModule = new SDFCombProcessingModule
		actorModule.add(new SDFActorSrc)
		actorModule.add(new SDFActorInc)
		gen.add(actorModule)

		var subsystem = new SubsystemUniprocessorModule
		subsystem.add(new SubsystemTemplateSrc)
		
		subsystem.add(new SubsystemTemplateInc)
		gen.add(subsystem)

		var initModule = new InitProcessingModule
		initModule.add(new DataTypeInc)
		initModule.add(new DataTypeSrc)

		if(Generator.fifoType==1){
			initModule.add(new FIFOInc1)
			initModule.add(new FIFOSrc1)
		}
		
		
		if(Generator.fifoType==2){
			initModule.add(new FIFOInc2)
			initModule.add(new FIFOSrc2)
		}


		
		//initModule.add(new SpinLockTemplateInc)
		//initModule.add(new SpinLockTemplateSrc)

		

		
		gen.add(initModule)

		gen.create()

		println("end!")
	}

}
