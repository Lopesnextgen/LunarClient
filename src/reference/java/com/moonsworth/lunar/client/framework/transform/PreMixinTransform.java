package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.ichor.TransformClass;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.loader.PipelineStage;
import org.objectweb.asm.tree.ClassNode;
import com.moonsworth.lunar.client.framework.transform.LunarIntermediaryMapper;

public class PreMixinTransform implements IchorInjection {
   public PreMixinTransform() {
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.PRE_MIXIN};
   }

   @TransformClass
   public void method2(ClassNode node1, URLClassLoader urlclassloader2) {
      LunarIntermediaryMapper.method1(node1, urlclassloader2);
   }
}
