package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.function.Consumer;
import lombok.Generated;

@Annotation2(min = 33)
public class Holograms5 {
   private final Holograms_6 field1;
   private final Consumer<String> field2;
   private Nameplate field3;
   private boolean field4;
   private final Holograms3_2 field5;
   private Fishing field6;

   public Holograms5(Consumer<String> var1, Holograms_6 var2) {
      this.field1 = var2;
      this.field5 = new Holograms3_2(var2);
      this.field2 = var1;
   }

   public void method1(AbstractRenderContext var1, Holograms_9 var2, Data5 var3) {
      if (this.field3 != null && this.field4) {
         if (this.field6 == null) {
            this.field6 = new Fishing("spirit-leap-map", false, 60);
         }

         if (this.field6.method2(var1, (int)this.field3.method1(), (int)this.field3.method2())) {
            var1.method40();
            if (ThreadModuleDump63.MC_VERSION >= 39) {
               var1.method35(0.0, (int)this.field3.method1(), (int)this.field3.method2(), 0.0, 21000.0, 1000.0);
            } else {
               var1.method35(0.0, (int)this.field3.method1(), (int)this.field3.method2(), 0.0, 1000.0, 21000.0);
            }

            Holograms2_5 var4 = this.field1.method1();
            float var5 = 0.0F;
            float var6 = 0.0F;
            float var7 = Math.min(this.field3.method1(), this.field3.method2());
            float var8 = var7 / 100.0F;
            if (this.field3.method1() > this.field3.method2()) {
               float var9 = (this.field3.method1() - var7) / 2.0F;
               var5 += var9;
            } else if (this.field3.method2() > this.field3.method1()) {
               float var11 = (this.field3.method2() - var7) / 2.0F;
               var6 += var11;
            }

            if (var4 == null) {
               var1.push();
               var1.scale(var8, var8, 1.0F);
               LcuiScreen.method97(var1, var5 / var8, var6 / var8, 100.0F, 100.0F, 1862270976);
               ThreadModuleDump63.method10()
                  .method13(var1, "Dungeon Map", var5 / var8 + 50.0F, var6 / var8 + 50.0F - ThreadModuleDump63.method10().method19() / 2.0F, 16777215, true);
               var1.pop();
            } else {
               var3 = (Data5)var3.HICCRORORCRIHCORCCIORIOROORIHR(var5 + this.field3.x(), var6 + this.field3.y());
               this.field5.method1(new LegacyGuiGraphicsBridge(var1), var2, var4, var5, var6, var8, var3);
            }

            var1.method30().method48();
            var1.method41();
            this.field6.method3(var1);
         }
      }
   }

   public void method2() {
      Holograms4Updater var1 = this.field5.method27();
      if (var1 != null) {
         this.field2.accept(var1.method20(false));
      }
   }

   public void method3(boolean var1) {
      if (!var1 && this.field6 != null) {
         this.field6.delete();
         this.field6 = null;
      }

      this.field4 = var1;
   }

   @Generated
   public void method4(Nameplate var1) {
      this.field3 = var1;
   }

   @Generated
   public Nameplate method5() {
      return this.field3;
   }

   @Generated
   public Holograms3_2 method6() {
      return this.field5;
   }
}
