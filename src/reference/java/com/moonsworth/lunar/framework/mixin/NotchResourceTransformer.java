package com.moonsworth.lunar.framework.mixin;

import com.moonsworth.lunar.framework.Ichor6Impl;
import com.moonsworth.lunar.ichor.TransformResource;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.ResourcePayload;
import com.moonsworth.lunar.loader.PipelineStage;
import java.io.IOException;
import java.io.InputStream;

public class NotchResourceTransformer implements IchorInjection {
   public NotchResourceTransformer() {
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.PRE_OPTIFINE_PATCH};
   }

   @TransformResource
   public void method2(ResourcePayload mixinshared1) {
      try {
         if (mixinshared1.method3() == null
            && mixinshared1.getResourcePath().endsWith(".class")
            && Ichor6Impl.method3(mixinshared1.getResourcePath())
            && !mixinshared1.getResourcePath().startsWith("notch/")) {
            String text2 = "notch/" + mixinshared1.getResourcePath();
            InputStream input3 = Thread.currentThread().getContextClassLoader().getResourceAsStream(text2);
            if (input3 != null) {
               try (input3) {
                  mixinshared1.method4(input3.readAllBytes());
               } catch (IOException exception9) {
                  System.err.println("Failed to read stream for " + mixinshared1.getResourcePath());
                  exception9.printStackTrace();
               }
            }
         }
      } catch (Throwable exception10) {
         throw exception10;
      }
   }
}
