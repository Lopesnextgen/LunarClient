package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.client.framework.Gui2Extension2;
import com.moonsworth.lunar.client.framework.MixinCore9;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl;
import com.moonsworth.lunar.client.lighting.LightingExtension23;
import com.moonsworth.lunar.client.mod.render.PotionEffects;
import com.moonsworth.lunar.client.mod.render.PotionEffects.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Potioneffects4 extends MixinCore9 {
   private static final List<Fog> field9 = Arrays.asList(Bridge.method36().method10(1, "speed", 1200, 3), Bridge.method36().method10(5, "strength", 30, 3));
   private final PotionEffects field10;
   private final Potioneffects3 field11;
   private final Potioneffects3 field12;
   private final Potioneffects3 field13;
   private final List<Fog> field14 = new ArrayList<>();

   protected Potioneffects4(PotionEffects var1) {
      super(0.0F, 0.0F, Gui2Extension2.MIDDLE_LEFT);
      this.field10 = var1;
      this.field11 = new Potioneffects3Impl2(var1);
      this.field12 = new Potioneffects3Impl(var1);
      this.field13 = new Potioneffects3Impl3(var1);
   }

   public void tick() {
      this.field14.clear();
      if (ThreadModuleDump63.method7() != null) {
         for (Fog var2 : ThreadModuleDump63.method7().bridge$getActivePotionEffects()) {
            if (!this.field10.method10(var2)) {
               this.field14.add(var2);
            }
         }
      }

      if (this.field14.isEmpty() && MixinCore9.method21()) {
         this.field14.addAll(field9);
      }

      this.method15().method1(this.field14);
   }

   @Override
   public boolean method33() {
      return this.field10.method17();
   }

   @Override
   public boolean method6() {
      return !this.field10.method17();
   }

   @Override
   public float getScale() {
      return this.field10.method17() ? 1.0F / this.IRHROCORHCRRORRCICOOOOHCIOOCHR() : super.getScale();
   }

   @Override
   public boolean method4(boolean var1) {
      if ((Boolean)this.field10.field27.get()) {
         this.CCROIHHHCOCHHOHORCIRHOCRROIOCI(0.0F, 0.0F);
         return false;
      } else if (ThreadModuleDump63.method7() != null
         && !(Boolean)this.field10.field19.get()
         && ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
         this.CCROIHHHCOCHHOHORCIRHOCRROIOCI(0.0F, 0.0F);
         return false;
      } else if (var1) {
         return true;
      } else if (this.field14.isEmpty()) {
         this.CCROIHHHCOCHHOHORCIRHOCRROIOCI(0.0F, 0.0F);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public void method1(LightingExtension23 var1) {
   }

   @Override
   public void method3(HighlightImpl var1, float var2, float var3, boolean var4) {
      this.method15().method2(this, var1.method2(), this.field14, var2, var3);
   }

   private Potioneffects3 method15() {
      return switch ((Type)this.field10.field8.get()) {
         case NORMAL -> this.field11;
         case MINIMAL -> this.field12;
         case VANILLA -> this.field13;
         default -> throw new IncompatibleClassChangeError();
      };
   }
}
