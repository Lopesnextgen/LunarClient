package com.moonsworth.lunar.framework.mixin;

import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.ichor.Annotation7;
import com.moonsworth.lunar.ichor.Annotation8;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor5Handler;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.util.HashSet;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.srg.csrg.CSrgReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class Ichor2Iterator2 implements IchorInjection {
   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.POST_OPTIFINE_PATCH};
   }

   @Annotation8(method5 = true, optional = true)
   public MappingSet method2(URLClassLoader var1) {
      IchorPipeline var2 = var1.method1();
      MappingSet var3 = var2.method9(Files.Data2.field6, CSrgReader::new).orElseThrow(() -> new FatalIchorError("Can't find mappings"));
      var2.method19(Ichor5Handler.class).ifPresent(var1x -> var1x.method6(var3));
      return var3;
   }

   @Annotation7
   public void method3(ClassNode var1) {
      HashSet var2 = new HashSet(var1.methods.size());

      for (MethodNode var4 : var1.methods) {
         if (!var2.add(var4.name + var4.desc)) {
            throw new FatalIchorError("Class " + var1.name + " has duplicate methods: " + var4.name + var4.desc);
         }
      }
   }
}
