package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.Ichor4Type;
import java.util.List;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.SimpleRemapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InnerClassNode;

public class Ichor2Iterator_2 implements IchorInjection {
   private final NestClassNameMapper field1;
   private final Remapper field2;

   public Ichor2Iterator_2(NestClassNameMapper var1) {
      this.field1 = var1;
      this.field2 = new SimpleRemapper(var1.method6());
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.INIT};
   }

   @Annotation7
   public void method2(ClassNode var1, URLClassLoader var2) {
      ClassNodeRemapper.method1(var2.method1(), var1, this.field2);
      List var3 = this.field1.method5().get(var1.name);
      if (var3 != null) {
         for (InnerClassRenameInfo var5 : var3) {
            if (var1.name.equals(var5.method2())) {
               var1.name = var5.method1();
               var1.outerClass = var5.method3();
               var1.outerMethod = var5.method4();
               var1.outerMethodDesc = var5.getOuterMethodDesc();
               var1.access = var5.getAccess();
            } else if (var1.name.equals(var5.method3())) {
               var1.innerClasses.add(new InnerClassNode(var5.method3() + "$" + var5.getInnerName(), var5.method3(), var5.getInnerName(), var5.getAccess()));
            }
         }
      }
   }

   @Annotation8
   public MixinMisc2 method3() {
      return this.field1;
   }
}
