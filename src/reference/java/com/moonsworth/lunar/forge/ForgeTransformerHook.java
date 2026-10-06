package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.TransformBytecode;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.ichor.util.ClassBytes;
import com.moonsworth.lunar.loader.PipelineStage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.launchwrapper.IClassTransformer;

public class ForgeTransformerHook implements IchorInjection {
   private List<IClassTransformer> transformers = null;
   private boolean field1 = false;
   private Set<String> field2 = Collections.newSetFromMap(new ConcurrentHashMap<>());

   public ForgeTransformerHook() {
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.POST_FORGE_PATCH};
   }

   @TransformBytecode
   public byte[] method2(String text1, byte[] items2, URLClassLoader urlclassloader3) {
      text1 = text1.replace('/', '.');
      if (Ichor6Iterator.field4.contains(text1)) {
         return items2;
      }

      if (this.field2.contains(text1)) {
         return items2;
      }

      if (this.transformers == null) {
         this.method3(urlclassloader3);
      }

      if (this.transformers != null) {
         for (IClassTransformer iclasstransformer5 : this.transformers) {
            byte[] items6 = iclasstransformer5.transform(text1, text1, items2);
            if (items6 != null && !Arrays.equals(items2, items6)) {
               Ichor6Iterator.field2.info("Transformed " + text1);
               return items6;
            }
         }
      }

      return items2;
   }

   public synchronized void method3(URLClassLoader urlclassloader1) {
      if (!this.field1) {
         this.field1 = true;
         ClassLoader classloader2 = urlclassloader1.getParent();
         Ichor6Iterator.field2.info("Building forge transformers");
         List list3 = Ichor6Iterator.method6(urlclassloader1);
         ArrayList list4 = new ArrayList();

         for (Object obj6 : list3) {
            try {
               Ichor6Iterator.field2.info("Finding transformers for " + obj6.getClass().getName());
               String[] items7 = (String[])obj6.getClass().getDeclaredMethod("getASMTransformerClass").invoke(obj6);
               if (items7 != null) {
                  this.field2.addAll(List.of(items7));

                  for (String text11 : items7) {
                     Ichor6Iterator.field2.info("Loading transformer " + text11);
                     ClassBytes fatalichorerror1412 = urlclassloader1.method7(text11, true);
                     if (fatalichorerror1412.method1() == null) {
                        throw new FatalIchorError("Failed to find class bytes for " + text11);
                     }

                     Class clazz13 = urlclassloader1.loadClass(text11, true);
                     Ichor6Iterator.field2.info("Instantiating transformer " + clazz13.getName());
                     IClassTransformer iclasstransformer14 = (IClassTransformer)clazz13.newInstance();
                     list4.add(iclasstransformer14);
                  }
               }
            } catch (Throwable exception15) {
               throw new IllegalStateException("Couldn't load IFMLLoadingPlugin: " + obj6.getClass().getName(), exception15);
            }
         }

         Ichor6Iterator.field2.info("DONE Building forge transformers");
         this.transformers = list4;
      }
   }
}
