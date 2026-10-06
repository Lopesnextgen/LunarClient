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
import java.util.Collection;

public class ArmorStatusHud extends HudElementBase {
   private static final int field9 = 2;
   private static final int field10 = 2;
   private final Armorstatus field11;
   private final ArmorStatusPanelRenderer field12;

   public ArmorStatusHud(Armorstatus armorstatus1) {
      super(0.0F, 0.0F, HudAnchor.BOTTOM_RIGHT);
      this.field11 = armorstatus1;
      this.field12 = new ArmorStatusPanelRenderer(armorstatus1);
   }

   @Override
   public boolean method30() {
      return this.field11.field27.get() ? !(Boolean)this.field11.field28.get() : !(Boolean)this.field11.field11.get();
   }

   @Override
   public boolean method31() {
      return Ref.MC_VERSION >= 6;
   }

   @Override
   public boolean method33() {
      return this.method5();
   }

   @Override
   public float getScale() {
      return this.method5() ? 1.0F / this.IRHROCORHCRRORRCICOOOOHCIOOCHR() : super.getScale();
   }

   private boolean method5() {
      return (Boolean)this.field11.field27.get() && (Boolean)this.field11.field28.get();
   }

   @Override
   public void load(JsonObject json1) {
      super.load(json1);
      if (!json1.has("durabilityPosition") && this.method26().getHorizontal() == HudAlignment.RIGHT) {
         this.field11.field18.OIRHOOIICOCIOOHICRRRICORIHHIHC(com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition.LEFT);
      }
   }

   @Override
   public void method1(RootSettingsBuilder lightingextension231) {
   }

   @Override
   public boolean method4(boolean flag1) {
      if (!(Boolean)this.field11.field10.get() && Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
         return false;
      } else if ((Boolean)this.field11.field27.get()) {
         return true;
      } else if ((Boolean)this.field11.field11.get()) {
         this.method16(0.0F, 0.0F);
         return false;
      } else {
         return !this.field11.method5(flag1).isEmpty();
      }
   }

   @Override
   public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
      MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
      Collection list6 = this.field11.method5(flag4).values();
      if ((Boolean)this.field11.field27.get()) {
         this.field12.method1(this, list6, mixinhelper_45, value2, value3);
      } else {
         this.method8(list6, mixinhelper_45, value2, value3);
      }
   }

   private void method8(Collection<ArmorStatusElement> list1, MixinHelper_4 mixinhelper_42, float value3, float value4) {
      mixinhelper_42.method44(arg0 -> arg0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
      boolean flag5 = this.field11.field19.get() == com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusListMode.VERTICAL;
      com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition gui2extension26;
      if (flag5) {
         gui2extension26 = (com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition)this.field11.field18.get();
      } else {
         gui2extension26 = this.method26().getHorizontal() == HudAlignment.RIGHT
            ? com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition.LEFT
            : com.moonsworth.lunar.client.framework.feature.armorstatus.DurabilityPosition.RIGHT;
      }

      float value7 = 0.0F;
      float value8 = 0.0F;

      for (ArmorStatusElement nameplate10 : list1) {
         nameplate10.method2(gui2extension26);
         if (flag5) {
            value7 = Math.max(value7, nameplate10.getWidth());
            value8 += (value8 == 0.0F ? 0 : 2) + nameplate10.getHeight();
         } else {
            value7 += (value7 == 0.0F ? 0 : 2) + nameplate10.getWidth();
            value8 = Math.max(value8, nameplate10.getHeight());
         }
      }

      this.method16(value7 + 4.0F, value8 + 4.0F);
      if ((Boolean)this.field11.field21.get()) {
         this.field11.field24.method11(mixinhelper_42, value3, value4, this.getWidth(), this.getHeight());
         if ((Boolean)this.field11.field22.get()) {
            this.field11.field25.method11(mixinhelper_42, this, value3, value4, this.getWidth(), this.getHeight(), (Float)this.field11.field23.get());
         }
      }

      value3 += 2.0F;
      value4 += 3.0F;
      RenderItemBridge bridge5_1917 = Ref.method3().bridge$getRenderItem();
      float value18 = bridge5_1917.bridge$getZLevel();
      float value11 = 0.0F;

      for (ArmorStatusElement nameplate13 : list1) {
         if (flag5) {
            float value14 = switch (gui2extension26) {
               case LEFT -> value7 - nameplate13.getWidth();
               case RIGHT -> 0.0F;
               case TOP, BOTTOM -> this.field11.field8.get() ? 0.0F : (value7 - nameplate13.getWidth()) / 2.0F;
            };
            nameplate13.method6(mixinhelper_42, bridge5_1917, value3 + value14, value4 + value11);
            value11 += nameplate13.getHeight() + 2;
         } else {
            nameplate13.method6(mixinhelper_42, bridge5_1917, value3 + value11, value4);
            value11 += nameplate13.getWidth() + 2;
         }
      }

      mixinhelper_42.method44(arg0 -> arg0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
      bridge5_1917.bridge$setZLevel(value18);
   }
}
