package com.moonsworth.lunar.client.glintcolorizer.nameplate.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge20;
import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.BridgeType_17;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper5_6;
import com.moonsworth.lunar.bridge.Bridge2.Data;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20.Extension;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.glintcolorizer.Glintcolorizer3_2;
import com.moonsworth.lunar.client.glintcolorizer.Glintcolorizer4_2;
import com.moonsworth.lunar.client.glintcolorizer.Glintcolorizer5_2;
import com.moonsworth.lunar.client.glintcolorizer.Glintcolorizer_6;
import com.moonsworth.lunar.client.glintcolorizer.nameplate.Bridge8Extension3;
import com.moonsworth.lunar.client.holograms.Holograms7;
import com.moonsworth.lunar.client.holograms.Holograms8;
import com.moonsworth.lunar.client.holograms.HologramsType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4f;
import lombok.Generated;

public class Glintcolorizer5Iterator extends Glintcolorizer5_2 implements Holograms8 {
   private Extension field32;
   public int perspective;
   public float field33;
   public float field34;
   public double field35;
   public double field36;
   public double field37;
   public Holograms7 field38 = Holograms7.field2;
   public String bone;
   private boolean field39;

   @Override
   public double method13(Bridge2_19 var1) {
      double var2 = var1.bridge$getPosX() - this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.x;
      double var4 = var1.bridge$getPosY() - this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.y;
      double var6 = var1.bridge$getPosZ() - this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.z;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public double method2(Glintcolorizer4_2 var1) {
      Vector3d var2 = var1.method2(this);
      double var3 = this.field35 - var2.x;
      double var5 = this.field36 - var2.y;
      double var7 = this.field37 - var2.z;
      return var3 * var3 + var5 * var5 + var7 * var7;
   }

   public Horsestats20 method14() {
      return Bridge.method8().method6(this.field35, this.field36, this.field37);
   }

   public void method5(BridgeExtension var1) {
      this.HORROICHRIRIROCIIHCIICRRHOHOCC = var1;
      this.method7(var1 == null ? null : var1.bridge$getWorld());
   }

   public void method5(BridgeExtension var1, Itemcounter6Extension var2) {
      this.HORROICHRIRIROCIIHCIICRRHOHOCC = var1;
      this.method7(var2);
   }

   public BridgeExtension method6() {
      return this.HORROICHRIRIROCIIHCIICRRHOHOCC;
   }

   public void method7(Itemcounter6 var1) {
      this.COROIOOCHRRCCHHCCOOOIOCIORHRRI = var1;
   }

   @Override
   public void method3(Glintcolorizer3_2 var1) {
      super.method3(var1);
      this.bone = var1.getBone();
   }

   @Override
   public void method7(BridgeExtension_9 var1, Bridge2_19 var2) {
      if (this.COHHICCCHOICIHRICIICCRHCCHICRI != null) {
         float var3 = 1.0F;
         if (this.HORROICHRIRIROCIIHCIICRRHOHOCC instanceof BridgeExtension2_5 var4) {
            var3 = var4.bridge$getScale();
         }

         float var12 = var1.method28();
         this.method14(var2);
         List var13 = this.COHHICCCHOICIHRICIICCRHCCHICRI.field10;

         for (com.moonsworth.lunar.client.glintcolorizer.GlintcolorizerExtension3 var7 : var13) {
            var7.method1(this, var12);
         }

         if (!this.HRHHIIRICRHHHCCHICOCCCCIRORIHH.isEmpty()) {
            if (Glintcolorizer_6.method1()) {
               this.HRHHIIRICRHHHCCHICOCCCCIRORIHH.sort((var1x, var2x) -> {
                  double var3x = this.method2(var1x);
                  double var5 = this.method2(var2x);
                  if (var3x < var5) {
                     return 1;
                  } else {
                     return var3x > var5 ? -1 : 0;
                  }
               });
            }

            Bridge8Extension3.method1(this.COHHICCCHOICIHRICIICCRHCCHICRI.texture, this.CCIORHRHHCCRHRIRCRIROCCRRHCOCR, var12);
            Bridge20 var14 = MixinHelper5_6.field39.get(this.COHHICCCHOICIHRICIICCRHCCHICRI.texture);
            Bridge2_32 var16 = var1.method10(var14);
            var16.method1();

            for (Glintcolorizer4_2 var9 : this.HRHHIIRICRHHHCCHICOCCCCIRORIHH) {
               this.HOHCHROCICCHRHRRRHOHIIIOHRROHR(var12);
               this.method2(var9, var12);

               for (com.moonsworth.lunar.client.glintcolorizer.GlintcolorizerExtension3 var11 : var13) {
                  if (var11 instanceof GlintcolorizerExtension3) {
                     ((GlintcolorizerExtension3)var11).method1(this, var9, var16, var1, var3, this.COHHICCCHOICIHRICIICCRHCCHICRI.texture);
                  }
               }
            }

            var16.method17(BridgeType_17.BATCHED);
            if (var1.method38() && this.perspective == 100) {
               var1.method30().method33(var14);
            }
         }

         for (com.moonsworth.lunar.client.glintcolorizer.GlintcolorizerExtension3 var17 : var13) {
            var17.method2(this, var12);
         }
      }
   }

   public void method9() {
      this.running = false;
   }

   public boolean method12() {
      return !this.isFinished();
   }

   public HologramsType method11() {
      return HologramsType.PARTICLE;
   }

   private void method14(Bridge2_19 var1) {
      if (Bridge.getMinecraftVersion().method21()) {
         Bridge2_43 var2 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
         this.field33 = 180.0F - (float)var2.bridge$playerViewY();
         this.field34 = 180.0F - (float)var2.bridge$playerViewX();
      } else {
         this.field33 = 180.0F - var1.bridge$getYaw();
         this.field34 = 180.0F - var1.bridge$getPitch();
      }

      this.field35 = var1.bridge$getPosX();
      this.field36 = var1.bridge$getPosY();
      this.field37 = var1.bridge$getPosZ();
      if (var1 instanceof Data var3 && var3.method1()) {
         this.perspective = 100;
         this.field37 = -0.5;
         this.field33 = 180.0F - var1.bridge$getYaw();
         this.field34 = 180.0F - var1.bridge$getPitch();
      } else {
         this.perspective = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView();
      }
   }

   @Override
   public int method17(float var1, double var2, double var4, double var6) {
      if (!this.ROOIRCOCCOIIOROCCROIRICOHCOCRC && this.COROIOOCHRRCCHHCCOOOIOCIORHRRI != null && this.perspective != 100) {
         if (this.field32 == null) {
            this.field32 = Bridge.method8().method10(var2, var4, var6);
         } else {
            this.field32.method1(var2, var4, var6);
         }

         return this.COROIOOCHRRCCHHCCOOOIOCIORHRRI.bridge$isBlockLoaded(this.field32)
            ? this.COROIOOCHRRCCHHCCOOOIOCIORHRRI.bridge$getPackedLight(this.field32)
            : 0;
      } else {
         return Bridge.method8().method92();
      }
   }

   public String getBone() {
      return this.bone;
   }

   public Holograms7 method2() {
      return this.field38;
   }

   public void method3() {
      this.update();

      for (com.moonsworth.lunar.client.glintcolorizer.GlintcolorizerExtension3 var2 : this.COHHICCCHOICIHRICIICCRHCCHICRI.field10) {
         var2.method2(this, ThreadModuleDump63.method3().bridge$getTimer().method1());
      }
   }

   public void method4(Vector4f var1, Matrix3f var2) {
      if (!this.field39) {
         this.field39 = true;
         double var3 = var1.x - this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.x;
         double var5 = var1.y - this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.y;
         double var7 = var1.z - this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.z;
         if (var3 != 0.0 || var5 != 0.0 || var7 != 0.0) {
            for (Glintcolorizer4_2 var10 : this.HRHHIIRICRHHHCCHICOCCCCIRORIHH) {
               if (!var10.field7 || !var10.field8) {
                  var10.field25.x += var3;
                  var10.field25.y += var5;
                  var10.field25.z += var7;
                  var10.field27.x += var3;
                  var10.field27.y += var5;
                  var10.field27.z += var7;
                  var10.field26.x += var3;
                  var10.field26.y += var5;
                  var10.field26.z += var7;
               }
            }
         }

         this.OHCICICRRICOIIRCHRCRIIHHCCRIHO.set(var1.x, var1.y, var1.z);
      }

      if (this.OHRIIOHHOROCROIHCCHIRHRRICRCOO != this.CCIORHRHHCCRHRIRCRIROCCRRHCOCR) {
         this.IIICIIHHOIIORRHHOIHOROIORICORH.set(this.IIHCRIOCIOCROOIRCHHORRORIOOCRC);
         this.OHCICICRRICOIIRCHRCRIIHHCCRIHO.set(this.RCRRHICIHIRCIIHHICOHRCORIIOHIC);
      }

      this.OHRIIOHHOROCROIHCCHIRHRRICRCOO = this.CCIORHRHHCCRHRIRCRIROCCRRHCOCR;
      this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.x = var1.x;
      this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.y = var1.y;
      this.RCRRHICIHIRCIIHHICOHRCORIIOHIC.z = var1.z;
      this.IIHCRIOCIOCROOIRCHHORRORIOOCRC = var2;
   }

   public void reset(boolean var1) {
      this.HRHHIIRICRHHHCCHICOCCCCIRORIHH.clear();
      if (var1) {
         this.running = false;
      }
   }
}
