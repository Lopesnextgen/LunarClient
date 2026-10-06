package com.moonsworth.lunar.framework.mixin;

import com.moonsworth.lunar.framework.Ichor6Impl;
import com.moonsworth.lunar.ichor.Annotation10;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.MixinShared;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.IOException;
import java.io.InputStream;

public class Ichor2Handler implements IchorInjection {
   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.PRE_OPTIFINE_PATCH};
   }

   @Annotation10
   public void method2(MixinShared mixinShared) {
      try {
         if (mixinShared.method3() == null
            && mixinShared.getResourcePath().endsWith(".class")
            && Ichor6Impl.method3(mixinShared.getResourcePath())
            && !mixinShared.getResourcePath().startsWith("notch/")) {
            String var2 = "notch/" + mixinShared.getResourcePath();
            InputStream var3 = Thread.currentThread().getContextClassLoader().getResourceAsStream(var2);
            if (var3 != null) {
               try (var3) {
                  mixinShared.method4(var3.readAllBytes());
               } catch (IOException var9) {
                  System.err.println("Failed to read stream for " + mixinShared.getResourcePath());
                  var9.printStackTrace();
               }
            }
         }
      } catch (Throwable var10) {
         throw var10;
      }
   }
}
