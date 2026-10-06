package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class HudRenderContext {
   private final MixinHelper_4 field1;
   private final boolean field2;
   private final Data2 field3;
   private final float field4;
   private final double field5;
   private final double field6;
   @Nullable
   private List<Component> field7;

   public HudRenderContext(MixinHelper_4 var1, boolean var2, Data2 var3, float var4, double var5, double var7) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var7;
   }

   public void method1() {
      if (this.field7 != null) {
         int var1 = (int)this.method2(this.field7, this.field3.IIRCROICCRROCOCOIOIHHOCRHOIHIR() * this.field4);
         int var2 = (int)this.method3(this.field7, this.field3.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() * this.field4);
         LcuiScreen.method84(this.field1, this.field7, var1, var2);
      }
   }

   private double method2(List<Component> var1, float var2) {
      float var3 = (float)this.field5;
      float var4 = this.method4(var1);
      if (var2 + var4 + 3.0F + 12.0F > var3) {
         var2 -= var4 + 24.0F;
      }

      if (var2 - 3.0F + 12.0F < 0.0F) {
         var2 = -9.0F;
      }

      return var2;
   }

   private double method3(List<Component> var1, float var2) {
      float var3 = (float)this.field6;
      float var4 = this.method5(var1);
      if (var2 + var4 + 3.0F - 12.0F > var3) {
         var2 = var3 - var4 - 3.0F + 12.0F;
      }

      if (var2 - 3.0F - 12.0F < 0.0F) {
         var2 = 15.0F;
      }

      return var2;
   }

   private float method4(List<Component> var1) {
      return (float)var1.stream().mapToDouble(var0 -> ThreadModuleDump63.method10().bridge$getStringWidth(var0)).max().orElse(0.0);
   }

   private float method5(List<Component> var1) {
      float var2 = 8.0F;
      if (var1.size() > 1) {
         var2 += 2 + (var1.size() - 1) * 10;
      }

      return var2;
   }

   @Generated
   public MixinHelper_4 method6() {
      return this.field1;
   }

   @Generated
   public boolean method7() {
      return this.field2;
   }

   @Generated
   public Data2 method8() {
      return this.field3;
   }

   @Generated
   public float getScale() {
      return this.field4;
   }

   @Generated
   public void method9(@Nullable List<Component> var1) {
      this.field7 = var1;
   }
}
