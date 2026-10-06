package com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate;

import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.List;
import java.util.function.Supplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

public class Nameplate2 extends HudElementBase {
   private static final float field9 = 120.0F;
   private static final float field10 = 100.0F;
   private static final HudAnchor field11 = HudAnchor.MIDDLE_LEFT;
   private static final long field12 = 60000L;
   private static final float field13 = 20.0F;
   private static final int field14 = 176;
   private static final int field15 = 222;
   private final Framework7Extension field16;
   private final Supplier<List<TextComponent>> field17;
   private long field18 = -60000L;
   private boolean field19;

   public Nameplate2(@NotNull Framework7Extension var1) {
      this(var1, null);
   }

   public Nameplate2(@NotNull Framework7Extension var1, Supplier<List<TextComponent>> var2) {
      this(var1, 0.0F, 0.0F, field11, var2);
   }

   public Nameplate2(@NotNull Framework7Extension var1, float var2, float var3, @NotNull HudAnchor var4) {
      this(var1, var2, var3, var4, null);
   }

   public Nameplate2(@NotNull Framework7Extension var1, float var2, float var3, @NotNull HudAnchor var4, Supplier<List<TextComponent>> var5) {
      super(var2, var3, var4);
      this.field16 = var1;
      this.field17 = var5;
      this.method16(120.0F, 100.0F);
   }

   @Override
   public final void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      if (var4) {
         MixinHelper_4 var5 = var1.method2();
         List var6 = this.field17 == null ? null : this.field17.get();
         if (var6 != null && !var6.isEmpty()) {
            this.method2(var5, var2, var3, var6);
         } else {
            LcuiScreen.method66(var5, var2, var3, var2 + this.getWidth(), var3 + this.getHeight(), 1862270976);
            ModDetails var7 = (ModDetails)this.field16.method1(Framework.field13);
            TextComponent var8 = Component.text(var7 == null ? "" : var7.getName());
            float var9 = ThreadModuleDump63.method10().method19();
            var5.method27(ThreadModuleDump63.method10(), var8, (int)(var2 + this.getWidth() / 2.0F), (int)(var3 + (this.getHeight() - var9) / 2.0F), -1, true);
         }
      }
   }

   private void method2(MixinHelper_4 var1, float var2, float var3, List<TextComponent> var4) {
      float var5 = ThreadModuleDump63.method10().method19();
      float var6 = 0.0F;

      for (TextComponent var8 : var4) {
         float var9 = ThreadModuleDump63.method10().bridge$getStringWidth(var8);
         if (var9 > var6) {
            var6 = var9;
         }
      }

      float var12 = var6;
      float var13 = var4.size() * var5;
      this.method16(var12, var13);
      LcuiScreen.method66(var1, var2, var3, var2 + var12, var3 + var13, 1862270976);
      float var14 = var3;

      for (TextComponent var11 : var4) {
         var1.method11(ThreadModuleDump63.method10(), var11, var2, var14, -1, true);
         var14 += var5;
      }
   }

   public void markRendered() {
      this.field18 = ThreadModuleDump63.method3().bridge$getSystemTime();
   }

   @Override
   public boolean method4(boolean var1) {
      if (!var1) {
         this.field19 = false;
         return false;
      }

      if (this.method15()) {
         this.field19 = true;
      }

      return this.field19;
   }

   @Override
   public boolean method30() {
      return this.field19 || this.method15();
   }

   @Override
   public double method20(HudAnchor var1, double var2) {
      ThreadModuleDump71 var4 = LcuiScreen.method151();
      double var5 = (this.method9(var4) - 20.0F) / this.method11(var2, var4.getScaledWidth()) - this.getWidth();
      return var5 + this.getX() / this.getScale();
   }

   @Override
   public double method22(HudAnchor var1, double var2) {
      ThreadModuleDump71 var4 = LcuiScreen.method151();
      double var5 = this.method10(var4) / this.method11(var2, var4.getScaledHeight());
      return var5 + this.getY() / this.getScale();
   }

   @Override
   public double method23(double var1) {
      return (this.OCRORCCHCRIOOIRHOHRRHCRRHRCIHO(var1) + this.getWidth()) * this.getScale();
   }

   @Override
   public double method24(double var1) {
      return this.CORRCOHCRHOHHOIHOIOICORROHOOOO(var1) * this.getScale();
   }

   private float method9(ThreadModuleDump71 var1) {
      return ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension_3 var2
         ? var2.bridge$getGuiLeft()
         : (var1.getScaledWidth() - 176) / 2.0F;
   }

   private float method10(ThreadModuleDump71 var1) {
      return ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension_3 var2
         ? var2.bridge$getGuiTop()
         : (var1.getScaledHeight() - 222) / 2.0F;
   }

   private float method11(double var1, double var3) {
      float var5 = var1 <= 0.0 ? 1.0F : (float)(var3 / var1);
      return this.getScale() * var5;
   }

   private boolean method15() {
      return switch ((Gui2Extension)ThreadModuleDump63.method4().method40().method82().method34().get()) {
         case ALWAYS -> true;
         case RECENT -> ThreadModuleDump63.method3().bridge$getSystemTime() - this.field18 <= 60000L;
         case NEVER -> false;
      };
   }
}
