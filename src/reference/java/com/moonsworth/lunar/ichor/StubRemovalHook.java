package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import com.moonsworth.lunar.loader.PipelineStage;
import org.objectweb.asm.tree.ClassNode;

public class StubRemovalHook implements IchorInjection {
   public StubRemovalHook() {
   }

   @TransformClass
   public void method1(ClassNode node1, URLClassLoader urlclassloader2) {
      node1.methods.removeIf(arg0 -> AsmUtils.method25(arg0, Stub.class) != null);
      node1.fields.removeIf(arg0 -> AsmUtils.method25(arg0, Stub.class) != null);
   }

   @Override
   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.FINAL};
   }
}
