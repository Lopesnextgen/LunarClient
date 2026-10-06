package com.moonsworth.lunar.ichor;

import org.objectweb.asm.tree.ClassNode;

public interface MixinInternal {
   boolean method1(ClassNode var1, IchorPipeline var2);

   void method2(ClassNode var1, URLClassLoader var2);
}
