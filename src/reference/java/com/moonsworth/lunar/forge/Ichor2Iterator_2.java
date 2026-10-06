package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.Annotation5;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.ichor.util.FatalIchorError14;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.launchwrapper.IClassTransformer;

public class Ichor2Iterator_2 implements IchorInjection {
   private List<IClassTransformer> transformers = null;
   private boolean field1 = false;
   private Set<String> field2 = Collections.newSetFromMap(new ConcurrentHashMap<>());

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.POST_FORGE_PATCH};
   }

   @Annotation5
   public byte[] method2(String var1, byte[] var2, URLClassLoader var3) {
      var1 = var1.replace('/', '.');
      if (Ichor6Iterator.field4.contains(var1)) {
         return var2;
      }

      if (this.field2.contains(var1)) {
         return var2;
      }

      if (this.transformers == null) {
         this.method3(var3);
      }

      if (this.transformers != null) {
         for (IClassTransformer var5 : this.transformers) {
            byte[] var6 = var5.transform(var1, var1, var2);
            if (var6 != null && !Arrays.equals(var2, var6)) {
               Ichor6Iterator.field2.info("Transformed " + var1);
               return var6;
            }
         }
      }

      return var2;
   }

   public synchronized void method3(URLClassLoader var1) {
      if (!this.field1) {
         this.field1 = true;
         ClassLoader var2 = var1.getParent();
         Ichor6Iterator.field2.info("Building forge transformers");
         List var3 = Ichor6Iterator.method6(var1);
         ArrayList var4 = new ArrayList();

         for (Object var6 : var3) {
            try {
               Ichor6Iterator.field2.info("Finding transformers for " + var6.getClass().getName());
               String[] var7 = (String[])var6.getClass().getDeclaredMethod("getASMTransformerClass").invoke(var6);
               if (var7 != null) {
                  this.field2.addAll(List.of(var7));

                  for (String var11 : var7) {
                     Ichor6Iterator.field2.info("Loading transformer " + var11);
                     FatalIchorError14 var12 = var1.method7(var11, true);
                     if (var12.method1() == null) {
                        throw new FatalIchorError("Failed to find class bytes for " + var11);
                     }

                     Class var13 = var1.loadClass(var11, true);
                     Ichor6Iterator.field2.info("Instantiating transformer " + var13.getName());
                     IClassTransformer var14 = (IClassTransformer)var13.newInstance();
                     var4.add(var14);
                  }
               }
            } catch (Throwable var15) {
               throw new IllegalStateException("Couldn't load IFMLLoadingPlugin: " + var6.getClass().getName(), var15);
            }
         }

         Ichor6Iterator.field2.info("DONE Building forge transformers");
         this.transformers = var4;
      }
   }
}
