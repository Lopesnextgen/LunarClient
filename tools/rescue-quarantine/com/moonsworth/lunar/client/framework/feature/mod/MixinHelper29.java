package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5;
import com.moonsworth.lunar.bridge.Bridge8_2;
import com.moonsworth.lunar.bridge.BridgeExtension_4;
import com.moonsworth.lunar.bridge.MixinHelper_13;
import com.moonsworth.lunar.bridge.MixinHelper.Extension3;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType8;
import com.moonsworth.lunar.client.click.holograms.Bridge7Iterator;
import com.moonsworth.lunar.client.highlight.Highlight2;
import com.moonsworth.lunar.client.highlight.HighlightImpl17.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump70;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;

@Annotation2(min = 1)
public class MixinHelper29 extends MixinHelper2_3 {
   private List<Component> field2 = null;
   private BridgeExtension_4 field3 = null;

   public MixinHelper29(MixinHelper var1) {
      super(var1);
   }

   @Override
   public void end() {
      if (this.field2 != null || this.field3 != null) {
         if (ThreadModuleDump63.MC_VERSION >= 30) {
            this.IOHOIIIHHRCHHHHHHOROHRHHHHHOOC().method45(var1x -> {
               Bridge8_2 var2x = var1x.method31();
               int var3 = (int)this.method18();
               int var4x = (int)this.method19();
               this.method4(() -> {
                  if (this.field3 != null) {
                     var2x.bridge$setTooltipForNextFrame$1_21_6(ThreadModuleDump63.method10(), this.field3, var3, var4x);
                  } else {
                     var2x.bridge$setTooltipForNextFrame$1_21_6(ThreadModuleDump63.method10(), this.field2, var3, var4x);
                  }

                  var2x.bridge$renderDeferredElements$1_21_6(var3, var4x);
               });
            });
            this.field2 = null;
            this.field3 = null;
         } else {
            List var1 = this.method17();
            this.field2 = null;
            this.field3 = null;
            if (var1 != null) {
               this.IOCOOROCROIIIIHORRCICOHCCHOCRI().method17();
               double var2 = this.method5(var1);
               double var4 = this.method6(var1);
               Runnable var6 = () -> this.method4(() -> Bridge7Iterator.method84(this.IOHOIIIHHRCHHHHHHOROHRHHHHHOOC(), var1, (int)var2, (int)var4));
               if (ThreadModuleDump63.MC_VERSION <= 29 && ThreadModuleDump63.MC_VERSION >= 6) {
                  double var7 = this.method7(var1);
                  double var9 = this.method8(var1);
                  ThreadModuleDump70 var11 = ThreadModuleDump70.method3((int)var2 + 12, (int)var4 - 12, (int)Math.ceil(var7), (int)Math.ceil(var9));
                  this.RORCOCRIHCIOHORIIRROHRIHOHCOOR().method2(var11, var6);
                  this.RORCOCRIHCIOHORIIRROHRIHOHCOOR().method1(var11, 0.0F);
               } else {
                  var6.run();
               }
            }
         }
      }
   }

   private List<Component> method17() {
      if (this.field2 != null) {
         return this.field2;
      }

      List var1 = this.field3
         .bridge$getTooltipComponents(ThreadModuleDump63.method7(), ThreadModuleDump63.method3().bridge$getGameSettings().bridge$isAdvancedItemTooltips());
      if (!var1.isEmpty()
         && (var1.size() != 1 || !HorsestatsType8.getTextWithoutFormattingCodes(Bridge5.asLegacyString((Component)var1.get(0))).trim().isEmpty())) {
         Data2 var2 = (Data2)Highlight2.method29().method12(Data2.class, () -> {
            ArrayList var2x = new ArrayList();

            for (Component var4 : var1) {
               var2x.add((MixinHelper_13)Bridge.method8().method89(var4));
            }

            return new Data2(this.IOHOIIIHHRCHHHHHHOROHRHHHHHOOC(), var2x, 0, 0, this.field3);
         });
         if (var2 != null && var2.isModified()) {
            ArrayList var3 = new ArrayList();

            for (MixinHelper_13 var5 : var2.method3()) {
               if (var5 instanceof Extension3 var6) {
                  var3.add(var6.bridge$getComponent());
               }
            }

            return var3;
         } else {
            return var1;
         }
      } else {
         return null;
      }
   }

   private double method18() {
      return this.OCIHOCIOHOORHOIICCIICCHIICRORR().method18() * this.ORHIOOIROCIOCHCICIIHOROIORCCHI().field9;
   }

   private double method19() {
      return this.OCIHOCIOHOORHOIICCIICCHIICRORR().method19() * this.ORHIOOIROCIOCHCICIIHOROIORCCHI().field10;
   }

   private void method4(Runnable var1) {
      this.IOHOIIIHHRCHHHHHHOROHRHHHHHOOC().push();
      this.IOHOIIIHHRCHHHHHHOROHRHHHHHOOC()
         .scale(
            (float)(1.0 / this.ORHIOOIROCIOCHCICIIHOROIORCCHI().field9),
            (float)(1.0 / this.ORHIOOIROCIOCHCICIIHOROIORCCHI().field10),
            (float)(1.0 / this.ORHIOOIROCIOCHCICIIHOROIORCCHI().field11)
         );
      var1.run();
      this.IOHOIIIHHRCHHHHHHOROHRHHHHHOOC().pop();
   }

   private double method5(List<Component> var1) {
      double var2 = this.method18();
      double var4 = Bridge7Iterator.method151().method1();
      double var6 = this.method7(var1);
      if (var2 + var6 + 3.0 + 12.0 > var4) {
         var2 -= var6 + 24.0;
      }

      if (var2 - 3.0 + 12.0 < 0.0) {
         var2 = -9.0;
      }

      return var2;
   }

   private double method6(List<Component> var1) {
      double var2 = this.method19();
      double var4 = Bridge7Iterator.method151().method2();
      double var6 = this.method8(var1);
      if (var2 + var6 + 3.0 - 12.0 > var4) {
         var2 = var4 - var6 - 3.0 + 12.0;
      }

      if (var2 - 3.0 - 12.0 < 0.0) {
         var2 = 15.0;
      }

      return var2;
   }

   private double method7(List<Component> var1) {
      return var1.stream().mapToDouble(var0 -> ThreadModuleDump63.method10().bridge$getStringWidth(var0)).max().orElse(0.0);
   }

   private double method8(List<Component> var1) {
      double var2 = 8.0;
      if (var1.size() > 1) {
         var2 += 2 + (var1.size() - 1) * 10;
      }

      return var2;
   }

   public void method9(List<Component> var1) {
      this.field2 = var1;
      this.field3 = null;
   }

   public void method10(BridgeExtension_4 var1) {
      this.field2 = null;
      this.field3 = var1;
   }

   public void method20() {
      this.field2 = null;
      this.field3 = null;
   }
}
