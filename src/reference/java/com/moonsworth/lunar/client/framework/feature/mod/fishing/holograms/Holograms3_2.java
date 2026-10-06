package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;

@Annotation2(min = 33)
public class Holograms3_2 {
   private static final RewindhandlersExtension field1 = RewindhandlersExtension.method23(-16711936);
   private static final RewindhandlersExtension field2 = RewindhandlersExtension.method23(-12770018);
   private final ArrayList<Holograms6_3> field3;
   private final Holograms6Iterator_2 field4;
   private final Holograms5_2 field5;
   private final Click4 field6 = new Click4(0.0, Click4.Type.SIN_OUT);
   private int field7 = 0;
   public float field8 = 1.0F;
   private long field9 = 0L;
   private List<Component> field10 = null;
   private long field11 = 0L;
   private long field12 = 0L;
   private Holograms_9 field13 = null;
   private Holograms4Updater field14 = null;
   private float field15;
   private float field16;
   private float field17;
   private float field18;

   public Holograms3_2(Holograms_6 var1) {
      this.field3 = new ArrayList<>();
      this.field3.add(new Holograms6Iterator(this));
      this.field3.add(this.field4 = new Holograms6Iterator_2(this));
      this.field3.add(new Holograms6Impl(this));
      this.field5 = new Holograms5_2(this, var1);
   }

   public void method1(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, float var6, MarkerModel<?> var7) {
      this.method5(var2, var3);
      if (var2.method41() || !this.method25()) {
         var1.method44(var0 -> {
            var0.method29().method33();
            var0.method29().method11();
         });
         this.field8 = var6;
         var4 /= var6;
         var5 /= var6;
         var7 = var7.method17(var6);
         this.field10 = null;
         if (ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            this.field6.animateTo(15.0, 250L);
         } else {
            this.field6.animateTo(0.0, 250L);
            if (!this.field6.isAnimating() && this.field11 != 0L) {
               this.field11 = 0L;
               this.field12 = 0L;
               var3.method36(0L);
            }
         }

         Nameplate2.method5(var1);
         this.method6(var1, var2, var3, var4, var5, var7);
         Nameplate2.method6(var1);
         this.method8(var1, var2, var3, var4, var5, var7);
         float var8 = var2.method35();
         this.method13(var1, var2, var4 + var8, var5 + var8, 100.0F - var8 * 2.0F, 100.0F - var8 * 2.0F);
         this.field3.get(this.field7).method1(var1, var2, var3, var4, var5, var7);
         this.method14(var1, var2);
         this.method4(var1, var2, var3, var4, var5, var7);
         this.method2(var1, var2, var3, var4, var5, var7);
         if (var2.method32() != Gui2Extension4.OFF && var3.method29().method7() != null) {
            List var9 = var3.method29().method7().method1();
            float var10 = var2.method43();
            var1.push();
            if (var2.method32() == Gui2Extension4.RIGHT) {
               var1.method38((var4 + 100.0F) * this.field8, var5 * this.field8, 0.0F);
            } else {
               float var11 = 0.0F;

               for (Component var13 : var9) {
                  float var14 = ThreadModuleDump63.method10().bridge$getStringWidth(var13);
                  if (var14 > var11) {
                     var11 = var14;
                  }
               }

               var1.method38(var4 * this.field8 - 8.0F - var11 * var10, var5 * this.field8, 0.0F);
            }

            var1.scale(var10, var10, 0.0F);
            Nameplate3.method4(var1, 0.0F, 0.0F, var9);
            var1.pop();
         }

         if (this.field10 != null && !var2.method53()) {
            float var18 = 0.0F;

            for (Component var20 : this.field10) {
               float var21 = ThreadModuleDump63.method10().bridge$getStringWidth(var20);
               if (var21 > var18) {
                  var18 = var21;
               }
            }

            if (var7.method10() * this.field8 + var18 + 12.0 > ThreadModuleDump63.method3().bridge$getCurrentScreen().bridge$getWidth()) {
               Nameplate3.method3(var1, var7.method12() * this.field8 - var18 - 24.0F, var7.method13() * this.field8, this.field10);
            } else {
               Nameplate3.method3(var1, var7.method12() * this.field8, var7.method13() * this.field8, this.field10);
            }
         }
      }
   }

   private void method2(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      float var7 = (float)this.field6.getValue();
      if (var7 != 0.0F) {
         byte var8 = 100;
         if (var2.method31() != Gui2Extension5.NONE) {
            var8 += 15;
         }

         long var9 = ThreadModuleDump63.method3().bridge$getSystemTime();
         if (this.field13 == var2) {
            if (Bridge.method20().method1(0)) {
               double var11 = ThreadModuleDump67.method12((var6.method12() - 11.5F - var4) / 77.0F, 0.0F, 1.0F);
               if (var11 == 1.0) {
                  this.field11 = 0L;
                  this.field12 = 0L;
               } else {
                  this.field11 = (long)(var3.method47() - var11 * (var3.method47() - var9));
                  this.field12 = var9;
               }

               var3.method36(this.field11);
            } else {
               this.field13 = null;
            }
         }

         if (this.field12 != 0L) {
            var3.method36(this.field11 + var9 - this.field12);
         }

         double var13 = (var3.method47() - (this.field11 + var9 - this.field12)) / (var3.method47() - var9);
         if (this.field11 == 0L) {
            var13 = 1.0;
         }

         if (!(var7 - var2.method35() < 0.0F)) {
            this.method13(var1, var2, var4, var5 + var8, 100.0F, var7 - var2.method35());
            Nameplate2.method5(var1);
            this.method17(var1, var4 + 10.0F, var5 + 4.0F + var8, 80.0F, 3.0F, field2);
            this.method17(var1, (float)(var4 + 10.0F + 77.0 * var13), var5 + var8, 3.0F, 10.0F, field1);
            Nameplate2.method6(var1);
            this.method14(var1, var2);
         }
      }
   }

   private void method3(Holograms_9 var1, float var2, float var3, Data4 var4) {
      float var5 = (float)this.field6.getValue();
      if (var5 != 0.0F) {
         byte var6 = 100;
         if (var1.method31() != Gui2Extension5.NONE) {
            var6 += 15;
         }

         if (var4.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= var2
            && var4.IIRCROICCRROCOCOIOIHHOCRHOIHIR() <= var2 + 100.0F
            && var4.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() >= var3 + var6
            && var4.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() <= var3 + var6 + 10.0F
            && var4.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() <= var3 + var6 + var5 - var1.method35()) {
            this.field13 = var1;
         }
      }
   }

   private void method4(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      if (var2.method31() == Gui2Extension5.SIMPLIFIED) {
         this.field5.method1(var1, var2, var3, var4, var5 + 100.0F, var6);
      }

      if (var2.method31() == Gui2Extension5.LEGAL_MAP) {
         this.field5.method2(var1, var2, var3, var4, var5 + 100.0F, var6);
      }
   }

   private void method5(Holograms_9 var1, Holograms2_5 var2) {
      long var3 = ThreadModuleDump63.method3().bridge$getSystemTime();
      if (var3 - this.field9 > 1000L) {
         for (Holograms6_3 var6 : this.field3) {
            var6.method7(var1, var2);
         }

         this.field9 = var3;
      }
   }

   private void method6(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      int var7 = 100 + (int)this.field6.getValue();
      if (var2.method31() != Gui2Extension5.NONE) {
         var7 += 15;
      }

      float var8 = var2.method35();
      this.method17(var1, var4 + var8, var5 + var8, 100.0F - var8 * 2.0F, var7 - var8 * 2.0F, var2.method29());
      this.field3.get(this.field7).method2(var1, var2, var3, var4, var5, var6);
      this.method17(var1, var4, var5 + var8, var8, var7 - var8, var2.method30());
      this.method17(var1, var4 + 100.0F - var8, var5 + var8, var8, var7 - var8, var2.method30());
      this.method7(var1, var2, var3, var4, var5, var6);
      this.method17(var1, var4 + var8, var5 + var7 - var8, 100.0F - var8 * 2.0F, var8, var2.method30());
   }

   private void method7(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      float var7 = var2.method35();
      if (!var2.method28()) {
         this.method17(var1, var4, var5, 100.0F, var7, var2.method30());
      } else {
         float var8 = 0.0F;

         for (Holograms6_3 var10 : this.field3) {
            var8 += var10.method6();
         }

         float var17 = (100.0F - var8) / (this.field3.size() - 1);
         float var18 = 0.0F;
         int var11 = 0;

         for (Holograms6_3 var13 : this.field3) {
            boolean var14 = this.field7 == var11;
            if (var18 > 0.0F) {
               this.method17(var1, var4 + var18, var5, var17, var7, var2.method30());
               var18 += var17;
            }

            float var15 = var13.method6();
            float var16 = var13.method5();
            this.method17(var1, var4 + var18 + var7, var5 - var16 + var7, var15 - var7 * 2.0F, var16 - (var14 ? 0.0F : var7), var2.method29());
            this.method17(var1, var4 + var18, var5 - var16 + var7, var7, var16, var2.method30());
            this.method17(var1, var4 + var18, var5 - var16, var15, var7, var2.method30());
            if (!var14) {
               this.method17(var1, var4 + var18 + var7, var5, var15 - var7 * 2.0F, var7, var2.method30());
            }

            var18 += var15;
            this.method17(var1, var4 + var18 - var7, var5 - var16 + var7, var7, var16, var2.method30());
            var11++;
         }

         this.method17(var1, var4 + var18, var5, 100.0F - var18, var7, var2.method30());
      }
   }

   private void method8(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      if (var2.method28()) {
         float var7 = 0.0F;

         for (Holograms6_3 var9 : this.field3) {
            var7 += var9.method6();
         }

         float var18 = (100.0F - var7) / (this.field3.size() - 1);
         float var19 = 0.0F;
         int var10 = 0;

         for (Holograms6_3 var12 : this.field3) {
            boolean var13 = this.field7 == var10;
            if (var19 > 0.0F) {
               var19 += var18;
            }

            float var14 = var12.method6();
            float var15 = var12.method5();
            boolean var16 = var6.method12() > var4 + var19
               && var6.method12() < var4 + var19 + var14
               && var6.method13() > var5 - var15
               && var6.method13() < var5;
            float var17 = var2.method35();
            this.method13(var1, var2, var4 + var19 + var17, var5 - var15 + var17, var14 - var17 * 2.0F, var15);
            this.method11(
               var1,
               (!var13 && !var16 ? AdventureChatFormatting.GRAY : "") + var12.getName(),
               var4 + var19 + var14 / 2.0F,
               var5 + var17 - 5.0F,
               0.65F,
               Nameplate.Type.BORDER
            );
            this.method14(var1, var2);
            var19 += var14;
            var10++;
         }
      }
   }

   public void method9(Holograms_9 var1, float var2, float var3, float var4, Data4 var5, int var6) {
      if (var6 == 0) {
         var2 /= var4;
         var3 /= var4;
         var5 = (Data4)var5.IHCORIOHOHHOIORHCCOOIIIHOCROOI(var4);
         this.method10(var2, var3, var5);
         this.method3(var1, var2, var3, var5);
      }
   }

   private void method10(float var1, float var2, Data4 var3) {
      float var4 = 0.0F;

      for (Holograms6_3 var6 : this.field3) {
         var4 += var6.method6();
      }

      float var13 = (100.0F - var4) / (this.field3.size() - 1);
      float var14 = 0.0F;
      int var7 = 0;

      for (Holograms6_3 var9 : this.field3) {
         if (var14 > 0.0F) {
            var14 += var13;
         }

         float var10 = var9.method6();
         float var11 = var9.method5();
         boolean var12 = var3.IIRCROICCRROCOCOIOIHHOCRHOIHIR() > var1 + var14
            && var3.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < var1 + var14 + var10
            && var3.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() > var2 - var11
            && var3.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < var2;
         if (var12) {
            this.field7 = var7;
         }

         var14 += var10;
         var7++;
      }
   }

   public void method11(MixinHelper_4 var1, String var2, float var3, float var4, float var5, Nameplate.Type var6) {
      float var7 = ThreadModuleDump63.method10().bridge$getStringWidth(var2) * var5;
      this.method15(var1, var2, var3 - var7 / 2.0F, var4 - 5.0F * var5, var5, var6);
   }

   public void method12(MixinHelper_4 var1, Component var2, float var3, float var4, float var5, Nameplate.Type var6) {
      float var7 = ThreadModuleDump63.method10().bridge$getStringWidth(var2) * var5;
      this.method16(var1, var2, var3 - var7 / 2.0F, var4 - 5.0F * var5, var5, var6);
   }

   public void method13(MixinHelper_4 var1, Holograms_9 var2, float var3, float var4, float var5, float var6) {
      if (!var2.method53()) {
         LcuiScreen.method111(var1, var3 * this.field8, var4 * this.field8, var5 * this.field8, var6 * this.field8, 1.0F);
      }
   }

   public void method14(MixinHelper_4 var1, Holograms_9 var2) {
      if (!var2.method53()) {
         LcuiScreen.method112(var1);
      }
   }

   public void method15(MixinHelper_4 var1, String var2, float var3, float var4, float var5, Nameplate.Type var6) {
      Nameplate.method1(var1, var2, var3 * this.field8, var4 * this.field8, var5 * this.field8, var6);
   }

   public void method16(MixinHelper_4 var1, Component var2, float var3, float var4, float var5, Nameplate.Type var6) {
      Nameplate.method2(var1, var2, var3 * this.field8, var4 * this.field8, var5 * this.field8, var6);
   }

   public void method17(MixinHelper_4 var1, float var2, float var3, float var4, float var5, RewindhandlersExtension var6) {
      Nameplate2.method1(var2 * this.field8, var3 * this.field8, var4 * this.field8, var5 * this.field8, var6);
   }

   public void method18(
      MixinHelper_4 var1, ResourceLocationBridge var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10, int var11
   ) {
      var3 *= this.field8;
      var4 *= this.field8;
      var5 *= this.field8;
      var6 *= this.field8;
      var9 *= this.field8;
      var10 *= this.field8;
      LcuiScreen.method46(var1, var2, var3, var4, var7, var8, var5, var6, var9, var10, var11);
   }

   public void method19(MixinHelper_4 var1, ResourceLocationBridge var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10) {
      this.method18(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, -1);
   }

   public void method20(MixinHelper_4 var1, HologramsType2 var2, float var3, float var4, float var5, float var6, float var7, int var8) {
      var1.push();
      var1.method38(var3 * this.field8, var4 * this.field8, 0.0F);
      var1.method42(var7);
      this.method18(var1, var2.getImage(), -var5 / 2.0F, -var6 / 2.0F, var5, var6, 0.0F, 0.0F, var5, var6, var8);
      var1.pop();
   }

   public void method21(MixinHelper_4 var1, HologramsType2 var2, float var3, float var4, float var5, float var6, float var7) {
      this.method20(var1, var2, var3, var4, var5, var6, var7, -1);
   }

   public void method22(Holograms6_3 var1) {
      this.field7 = this.field3.indexOf(var1);
   }

   public void method23() {
      if (!this.field3.get(this.field7).method4()) {
         int var1 = 0;
         int var2 = 0;

         for (Holograms6_3 var4 : this.field3) {
            if (var4.method4()) {
               var2 = var1;
            }

            var1++;
         }

         this.field7 = var2;
      }
   }

   public boolean method24() {
      return this.field11 != 0L;
   }

   public boolean method25() {
      return this.field4.method4();
   }

   @Generated
   public void method26(List<Component> var1) {
      this.field10 = var1;
   }

   @Generated
   public Holograms4Updater method27() {
      return this.field14;
   }

   @Generated
   public void method28(Holograms4Updater var1) {
      this.field14 = var1;
   }
}
