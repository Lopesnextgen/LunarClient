package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
import org.objectweb.asm.tree.ClassNode;

public class Ichor2Handler implements IchorInjection {
   @Annotation7
   public void method1(ClassNode type, URLClassLoader type2) {
      type.methods.removeIf(var0 -> FatalIchorError6.method25(var0, Annotation13.class) != null);
      type.fields.removeIf(var0 -> FatalIchorError6.method25(var0, Annotation13.class) != null);
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.FINAL};
   }
}
