package com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate.mixin;

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

public class Nameplate extends HudElementBase {
   private static final int field9 = 2;
   private final Framework7Extension2 field10;

   public Nameplate(Framework7Extension2 var1) {
      super(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT);
      this.field10 = var1;
   }

   @Override
   public void method3(HudBaseRenderEvent var1, float var2, float var3, boolean var4) {
      com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate.Nameplate var5 = this.field10.method4(var4);
      var5.method2((com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2)this.field10.field8.get());
      this.method16(var5.getWidth() + 4, var5.getHeight() + 4);
      MixinHelper_4 var6 = var1.method2();
      if ((Boolean)this.field10.field9.get()) {
         this.field10.field12.method11(var6, var2, var3, this.getWidth(), this.getHeight());
         if ((Boolean)this.field10.field10.get()) {
            this.field10.field13.method11(var6, this, var2, var3, this.getWidth(), this.getHeight(), (Float)this.field10.field11.get());
         }
      }

      var2 += 2.0F;
      var3 += 2.5F;
      Bridge5_19 var7 = ThreadModuleDump63.method3().bridge$getRenderItem();
      float var8 = var7.bridge$getZLevel();
      var5.method6(var6, var7, var2, var3);
      var7.bridge$setZLevel(var8);
   }

   @Override
   public boolean method4(boolean var1) {
      Armorstatus var2 = this.field10.method13();
      boolean var3 = !(Boolean)var2.field27.get()
         && (Boolean)var2.field11.get()
         && ((Boolean)var2.field10.get() || !ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen())
         && this.field10.method4(var1) != null;
      if (!var3) {
         this.method16(0.0F, 0.0F);
      }

      return var3;
   }

   @Override
   public void method1(RootSettingsAssembler var1) {
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      if (!this.method4(var1) && this.method26().getHorizontal() == HudPlacement.RIGHT) {
         this.field10.field8.OIRHOOIICOCIOOHICRRRICORIHHIHC(com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension2.LEFT);
      }
   }

   private boolean method4(JsonObject var1) {
      return var1.has("durabilityPosition")
         ? true
         : var1.has("options") && var1.get("options").isJsonObject() && var1.getAsJsonObject("options").has("durabilityPosition");
   }

   @Override
   public boolean method30() {
      return (Boolean)this.field10.method13().field11.get();
   }

   @Override
   public boolean method31() {
      return ThreadModuleDump63.MC_VERSION <= 5;
   }
}
