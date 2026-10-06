package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.files.Files6_2;
import java.util.concurrent.TimeUnit;

public class Holograms_8 {
   private final Cache<Holograms2, Boolean> field1 = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.SECONDS).build();
   private final GuiRewindhandlersHandler2_2 field2;
   private NameplateType2 field3 = NameplateType2.NONE;

   public Holograms_8(GuiRewindhandlersHandler2_2 var1) {
      this.field2 = var1;
   }

   public void method1(ItemStackBridge var1) {
      if (var1.bridge$getItem() != null && this.field3 != NameplateType2.NONE) {
         NameplateType2 var2 = NameplateType2.from(var1);
         if (var2 == NameplateType2.NONE) {
            this.field3 = var2;
         }
      }
   }

   public boolean method2(Holograms2 var1) {
      try {
         return this.field2.method5().isEmpty() ? false : Boolean.TRUE.equals(this.field1.get(var1, () -> {
            for (Holograms7 var3x : var1.getSections()) {
               if (!this.method4(var3x)) {
                  return false;
               }
            }

            return true;
         }));
      } catch (Throwable var3) {
         throw var3;
      }
   }

   public boolean method3(Holograms2 var1, Holograms7 var2) {
      if (this.field2.method5().isEmpty()) {
         return false;
      }

      boolean var3 = false;

      for (Holograms7 var5 : var1.getSections()) {
         if (var5 == var2 || var3) {
            var3 = true;
            if (!this.method4(var5)) {
               return false;
            }
         }
      }

      return true;
   }

   private boolean method4(Holograms7 var1) {
      if (this.field3 == NameplateType2.NONE) {
         return true;
      }

      for (Files6_2 var3 : var1.method9()) {
         if (var3.field1 == NameplateType.BREAK_BLOCK) {
            return false;
         }
      }

      return true;
   }
}
