package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import org.joml.Vector2f;

@Annotation2(min = 33)
public abstract class Holograms6_3 {
   private final String field1;
   protected final Holograms3_2 field2;
   private final Click4 field3;
   private boolean field4;
   private float field5 = 0.0F;
   private final Vector2f field6 = new Vector2f();

   public Holograms6_3(String var1, Holograms3_2 var2) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = new Click4(0.0, Click4.Type.SIN_IN_OUT);
      this.field4 = false;
   }

   public abstract void method1(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6);

   public abstract void method2(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6);

   public abstract boolean method3(Holograms_9 var1, Holograms2_5 var2);

   public boolean method4() {
      return this.field4;
   }

   public float method5() {
      return (float)this.field3.getValue();
   }

   public float method6() {
      if (this.field5 == 0.0F) {
         this.field5 = ThreadModuleDump63.method10().bridge$getStringWidth(this.field1);
      }

      return this.field5 * 0.75F + 5.0F;
   }

   public String getName() {
      return this.field1;
   }

   public void method7(Holograms_9 var1, Holograms2_5 var2) {
      if (this.method3(var1, var2)) {
         this.field3.animateTo(10.0, 500L);
         if (!this.field4) {
            this.field4 = true;
            this.field2.method22(this);
         }
      } else {
         this.field3.animateTo(0.0, 500L);
         if (this.field4) {
            this.field4 = false;
            this.field2.method23();
         }
      }
   }

   protected void method8(
      MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6, float var7, float var8, Holograms6$Extension var9
   ) {
      var1.push();
      Holograms4Updater var10 = var3.method29();
      float var11 = this.field2.field8;
      if (!var2.method33()) {
         if (var2.method34()) {
            var1.method38((var4 + 50.0F) * var11, (var5 + 50.0F) * var11, 0.0F);
            var6 = var6.method15(var4 + 50.0F, var5 + 50.0F);
            var1.scale(var2.method50(), var2.method50(), 1.0F);
            var6 = var6.method17(var2.method50());
            var1.method38(-(var4 + 50.0F) * var11, -(var5 + 50.0F) * var11, 0.0F);
            var6 = var6.method14(var4 + 50.0F, var5 + 50.0F);
            this.field6.set(50.0F - var7, 50.0F - var8);
            var1.method38((50.0F - var7) * var11, (50.0F - var8) * var11, 0.0F);
            var6 = var6.method14(var7 - 50.0F, var8 - 50.0F);
         }

         var9.run(var4, var5, var6);
      } else {
         var1.method38((var4 + 50.0F) * var11, (var5 + 50.0F) * var11, 0.0F);
         var6 = var6.method15(var4 + 50.0F, var5 + 50.0F);
         var1.scale(var2.method50(), var2.method50(), 1.0F);
         var6 = var6.method17(var2.method50());
         var1.method42(-var10.method14());
         float var12 = var6.method12();
         float var13 = var6.method13();
         float var14 = (float)Math.sin(var10.method14() / 180.0F * Math.PI);
         float var15 = (float)Math.cos(var10.method14() / 180.0F * Math.PI);
         var6 = new Data2(var15 * var12 - var14 * var13, var14 * var12 + var15 * var13);
         if (var2.method34()) {
            this.field6.set(50.0F - var7, 50.0F - var8);
            var1.method38((50.0F - var7) * var11, (50.0F - var8) * var11, 0.0F);
            var6 = var6.method14(var7 - 50.0F, var8 - 50.0F);
         }

         var9.run(-50.0F, -50.0F, var6);
      }

      var1.pop();
   }

   protected boolean method9(Holograms_9 var1, Holograms2_5 var2, float var3, float var4, float var5, float var6) {
      var5 += this.field6.x;
      var6 += this.field6.y;
      Holograms4Updater var7 = var2.method29();
      if (var1.method33()) {
         float var8 = var5;
         float var9 = var6;
         float var10 = (float)Math.sin(-var7.method14() / 180.0F * Math.PI);
         float var11 = (float)Math.cos(-var7.method14() / 180.0F * Math.PI);
         var5 = var11 * var8 - var10 * var9;
         var6 = var10 * var8 + var11 * var9;
      }

      if (var1.method34() || var1.method33()) {
         var5 *= var1.method50();
         var6 *= var1.method50();
      }

      return var5 > var3 - var1.method37() && var5 < var3 + 100.0F + var1.method37() && var6 > var4 - var1.method37() && var6 < var4 + 100.0F + var1.method37();
   }
}
