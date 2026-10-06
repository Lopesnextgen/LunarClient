package com.moonsworth.lunar.client.mod.misc.rewind;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lombok.Generated;

public class RewindHandlers2 extends AbstractFeature {
   private final Set<Rewindhandlers2<?>> field8 = new HashSet<>();

   public RewindHandlers2(RewindHandlers var1) {
      super(true);
      Framework10Extension var2 = (Framework10Extension)var1.method64(Framework.field12, var0 -> Framework10Extension.method13());
      if (var2 != null) {
         var2.method4(this);
      }
   }

   public void method1(Rewindhandlers2<?> var1) {
      this.field8.add(var1);
      var1.play();
   }

   public boolean method2(Rewindhandlers2<?> var1) {
      return this.field8.contains(var1);
   }

   public void method13() {
      Iterator var1 = this.field8.iterator();

      while (var1.hasNext()) {
         Rewindhandlers2 var2 = (Rewindhandlers2)var1.next();
         if (!var2.isAlive()) {
            var1.remove();

            try {
               var2.cleanup();
            } catch (IOException var4) {
               Inventorymod2.method5(var4, "Rewind");
            }
         }

         var2.setAlive(false);
      }
   }

   public void cleanup() {
      for (Rewindhandlers2 var2 : this.field8) {
         try {
            var2.cleanup();
         } catch (IOException var4) {
            Inventorymod2.method5(var4, "Rewind");
         }
      }

      this.field8.clear();
   }

   public void reload() {
      for (Rewindhandlers2 var2 : this.field8) {
         try {
            var2.reload();
         } catch (IOException var4) {
            Inventorymod2.method5(var4, "Rewind");
         }
      }
   }

   public void method4(long var1) {
      for (Rewindhandlers2 var4 : this.field8) {
         var4.method14(var1);
      }
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public Set<Rewindhandlers2<?>> getSources() {
      return this.field8;
   }
}
