package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.PipelineStage;
import java.util.List;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.SimpleRemapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InnerClassNode;

public class SyntheticClassRenameHook implements IchorInjection {
   private final NestClassNameMapper field1;
   private final Remapper field2;

   public SyntheticClassRenameHook(NestClassNameMapper fieldtypeprovider21) {
      this.field1 = fieldtypeprovider21;
      this.field2 = new SimpleRemapper(fieldtypeprovider21.method6());
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.INIT};
   }

   @TransformClass
   public void method2(ClassNode node1, URLClassLoader urlclassloader2) {
      ClassNodeRemapper.method1(urlclassloader2.method1(), node1, this.field2);
      List list3 = (List)this.field1.method5().get(node1.name);
      if (list3 != null) {
         for (InnerClassRenameInfo mixincore5 : list3) {
            if (node1.name.equals(mixincore5.method2())) {
               node1.name = mixincore5.method1();
               node1.outerClass = mixincore5.method3();
               node1.outerMethod = mixincore5.method4();
               node1.outerMethodDesc = mixincore5.getOuterMethodDesc();
               node1.access = mixincore5.getAccess();
            } else if (node1.name.equals(mixincore5.method3())) {
               node1.innerClasses.add(new InnerClassNode(mixincore5.method3() + "$" + mixincore5.getInnerName(), mixincore5.method3(), mixincore5.getInnerName(), mixincore5.getAccess()));
            }
         }
      }
   }

   @ProvideRemapper
   public ClassNameMapper method3() {
      return this.field1;
   }
}
