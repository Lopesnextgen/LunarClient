package com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.HudPlacement;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Collection;

public class Nameplate5 extends HudElementBase {
   private static final int field9 = 2;
   private static final int field10 = 2;
   private final Armorstatus field11;
   private final Nameplate4 field12;

   public Nameplate5(Armorstatus var1) {
      super(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT);
      this.field11 = var1;
      this.field12 = new Nameplate4(var1);
   }

   @Override
   public boolean method30() {
      return this.field11.field27.get() ? !(Boolean)this.field11.field28.get() : !(Boolean)this.field11.field11.get();
   }

   @Override
   public boolean method31() {
      return ThreadModuleDump63.MC_VERSION >= 6;
   }

   @Override
   public boolean method33() {
      return this.method5();
   }

   @Override
   public float getScale() {
      return this.method5() ? 1.0F / this.getScale() : super.getScale();
   }

   private boolean method5() {
      return (Boolean)this.field11.field27.get() && (Boolean)this.field11.field28.get();
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      if (!var1.has("durabilityPosition") && this.method26().getHorizontal() == HudPlacement.RIGHT) {
         this.field11.field18.OIRHOOIICOCIOOHICRRRICORIHHIHC(com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2.LEFT);
      }
   }

   @Override
   public void method1(RootSettingsAssembler var1) {
   }

   @Override
   public boolean method4(boolean var1) {
      if (!(Boolean)this.field11.field10.get() && ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
         return false;
      } else if ((Boolean)this.field11.field27.get()) {
         return true;
      } else if ((Boolean)this.field11.field11.get()) {
         this.method16(0.0F, 0.0F);
         return false;
      } else {
         return !this.field11.method5(var1).isEmpty();
      }
   }

   @Override
   public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      MixinHelper_4 var5 = var1.method2();
      Collection var6 = this.field11.method5(var4).values();
      if ((Boolean)this.field11.field27.get()) {
         this.field12.method1(this, var6, var5, var2, var3);
      } else {
         this.method8(var6, var5, var2, var3);
      }
   }

   private void method8(Collection<Nameplate> var1, MixinHelper_4 var2, float var3, float var4) {
      var2.method44(var0 -> var0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
      boolean var5 = this.field11.field19.get() == com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension.VERTICAL;
      com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2 var6;
      if (var5) {
         var6 = (com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2)this.field11.field18.get();
      } else {
         var6 = this.method26().getHorizontal() == HudPlacement.RIGHT
            ? com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2.LEFT
            : com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2.RIGHT;
      }

      float var7 = 0.0F;
      float var8 = 0.0F;

      for (Nameplate var10 : var1) {
         var10.method2(var6);
         if (var5) {
            var7 = Math.max(var7, var10.getWidth());
            var8 += (var8 == 0.0F ? 0 : 2) + var10.getHeight();
         } else {
            var7 += (var7 == 0.0F ? 0 : 2) + var10.getWidth();
            var8 = Math.max(var8, var10.getHeight());
         }
      }

      this.method16(var7 + 4.0F, var8 + 4.0F);
      if ((Boolean)this.field11.field21.get()) {
         this.field11.field24.method11(var2, var3, var4, this.getWidth(), this.getHeight());
         if ((Boolean)this.field11.field22.get()) {
            this.field11.field25.method11(var2, this, var3, var4, this.getWidth(), this.getHeight(), (Float)this.field11.field23.get());
         }
      }

      var3 += 2.0F;
      var4 += 3.0F;
      Bridge5_19 var17 = ThreadModuleDump63.method3().bridge$getRenderItem();
      float var18 = var17.bridge$getZLevel();
      float var11 = 0.0F;

      for (Nameplate var13 : var1) {
         if (var5) {
            float var14 = switch (var6) {
               case LEFT -> var7 - var13.getWidth();
               case RIGHT -> 0.0F;
               case TOP, BOTTOM -> this.field11.field8.get() ? 0.0F : (var7 - var13.getWidth()) / 2.0F;
            };
            var13.method6(var2, var17, var3 + var14, var4 + var11);
            var11 += var13.getHeight() + 2;
         } else {
            var13.method6(var2, var17, var3 + var11, var4);
            var11 += var13.getWidth() + 2;
         }
      }

      var2.method44(var0 -> var0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
      var17.bridge$setZLevel(var18);
   }
}
