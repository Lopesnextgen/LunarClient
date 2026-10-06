package com.moonsworth.lunar.client.framework.feature.armorstatus.hud;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.HudAlignment;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.framework.Ref;

public class ArmorStatusElementHud extends HudElementBase {
   private static final int field9 = 2;
   private final ArmorStatusElementChildMod field10;

   public ArmorStatusElementHud(ArmorStatusElementChildMod framework7extension21) {
      super(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT);
      this.field10 = framework7extension21;
   }

   @Override
   public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
      com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElement nameplate5 = this.field10.method4(flag4);
      nameplate5.method2((com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition)this.field10.field8.get());
      this.method16(nameplate5.getWidth() + 4, nameplate5.getHeight() + 4);
      MixinHelper_4 mixinhelper_46 = highlightimpl1.method2();
      if ((Boolean)this.field10.field9.get()) {
         this.field10.field12.method11(mixinhelper_46, value2, value3, this.getWidth(), this.getHeight());
         if ((Boolean)this.field10.field10.get()) {
            this.field10.field13.method11(mixinhelper_46, this, value2, value3, this.getWidth(), this.getHeight(), (Float)this.field10.field11.get());
         }
      }

      value2 += 2.0F;
      value3 += 2.5F;
      RenderItemBridge bridge5_197 = Ref.method3().bridge$getRenderItem();
      float value8 = bridge5_197.bridge$getZLevel();
      nameplate5.method6(mixinhelper_46, bridge5_197, value2, value3);
      bridge5_197.bridge$setZLevel(value8);
   }

   @Override
   public boolean method4(boolean flag1) {
      Armorstatus armorstatus2 = this.field10.method13();
      boolean flag3 = !(Boolean)armorstatus2.field27.get()
         && (Boolean)armorstatus2.field11.get()
         && ((Boolean)armorstatus2.field10.get() || !Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen())
         && this.field10.method4(flag1) != null;
      if (!flag3) {
         this.method16(0.0F, 0.0F);
      }

      return flag3;
   }

   @Override
   public void method1(RootSettingsBuilder lightingextension231) {
   }

   @Override
   public void load(JsonObject json1) {
      super.load(json1);
      if (!this.method4(json1) && this.method26().getHorizontal() == HudAlignment.RIGHT) {
         this.field10.field8.OIRHOOIICOCIOOHICRRRICORIHHIHC(com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition.LEFT);
      }
   }

   private boolean method4(JsonObject json1) {
      return json1.has("durabilityPosition")
         ? true
         : json1.has("options") && json1.get("options").isJsonObject() && json1.getAsJsonObject("options").has("durabilityPosition");
   }

   @Override
   public boolean method30() {
      return (Boolean)this.field10.method13().field11.get();
   }

   @Override
   public boolean method31() {
      return Ref.MC_VERSION <= 5;
   }
}
