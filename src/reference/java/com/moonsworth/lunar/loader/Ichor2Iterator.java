package com.moonsworth.lunar.loader;

import com.moonsworth.lunar.ichor.Annotation7;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class Ichor2Iterator implements IchorInjection {
   public static final String field1 = "net/fabricmc/loader/FabricLoader";
   public static final String field2 = "net/fabricmc/loader/impl/FabricLoaderImpl";
   public static final String field3 = "net/fabricmc/loader/impl/discovery/ModCandidateImpl";
   public static final String field4 = "net/fabricmc/loader/impl/discovery/ModCandidateFinder";
   public static final String field5 = "net/fabricmc/loader/impl/game/patch/GamePatch";

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      String var2 = var1.className();
      return var2.equals("net/fabricmc/loader/FabricLoader")
         || var2.equals("net/fabricmc/loader/impl/FabricLoaderImpl")
         || var2.equals("net/fabricmc/loader/impl/discovery/ModCandidateImpl")
         || var2.equals("net/fabricmc/loader/impl/discovery/ModCandidateFinder")
         || var2.equals("net/fabricmc/loader/impl/game/patch/GamePatch");
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type2.ADVENTURE};
   }

   @Annotation7
   public void method3(ClassNode var1, URLClassLoader var2) {
      boolean var3 = var1.name.equals("net/fabricmc/loader/impl/game/patch/GamePatch");
      var1.access = FatalIchorError6.method4(var1.access, true);

      for (MethodNode var5 : var1.methods) {
         if (var3 || var5.name.startsWith("<init>") || var5.name.startsWith("createBuiltin")) {
            var5.access = FatalIchorError6.method4(var5.access, true);
         }
      }

      for (FieldNode var7 : var1.fields) {
         if (var7.name.equals("INSTANCE")) {
            var7.access = FatalIchorError6.method4(var7.access, true);
         }
      }
   }
}
