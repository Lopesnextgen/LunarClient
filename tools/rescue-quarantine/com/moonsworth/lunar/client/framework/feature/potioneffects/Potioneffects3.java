package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import com.moonsworth.lunar.bridge.horsestats.Horsestats30;
import com.moonsworth.lunar.client.click.holograms.Bridge7Iterator;
import com.moonsworth.lunar.client.glintcolorizer.Glintcolorizer5;
import com.moonsworth.lunar.client.lighting.LightingExtension4222;
import com.moonsworth.lunar.client.mod.render.PotionEffects;
import com.moonsworth.lunar.client.mod.render.PotionEffects.Type;
import com.moonsworth.lunar.client.util.ThreadModuleDump11;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.List;

abstract class Potioneffects3 {
   @Annotation2(max = 1)
   protected static final Horsestats14 field1 = Horsestats14.create("lunar", "misc/potions_inventory_legacy.png");
   @Annotation2(max = 18)
   protected static final Horsestats14 field2 = Horsestats14.create("textures/gui/container/inventory.png");
   protected final PotionEffects field3;
   private int field4 = 0;

   protected Potioneffects3(PotionEffects var1) {
      this.field3 = var1;
   }

   protected void method1(List<Fog> var1) {
      this.field4++;
   }

   protected abstract void method2(Potioneffects4 var1, MixinHelper_4 var2, List<Fog> var3, float var4, float var5);

   protected void method3(Potioneffects4 var1, MixinHelper_4 var2, float var3, float var4, float var5, float var6) {
      if ((Boolean)this.field3.field28.get()) {
         this.field3.field29.method11(var2, var3, var4, var5, var6);
      }

      if ((Boolean)this.field3.field30.get()) {
         this.field3.field32.method11(var2, var1, var3, var4, var5, var6, (Float)this.field3.field31.get());
      }
   }

   protected void method4(MixinHelper_4 var1, Fog var2, Fog2 var3, float var4, float var5) {
      if (Bridge.getMinecraftVersion().method19()) {
         var2.bridge$renderEffectIcon(var1, var4, var5, (int)(this.method10(var2) * 255.0F) << 24 | 16777215);
      } else if (var3 != null && var3.bridge$hasStatusIcon()) {
         int var6 = var3.bridge$getStatusIconIndex();
         Bridge7Iterator.method47(var1, field2, var4, var5, var6 % 8 * 18, 198 + var6 / 8 * 18, 18, 18, (int)(this.method10(var2) * 255.0F) << 24 | 16777215);
      }
   }

   protected void method5(MixinHelper_4 var1, Fog var2, Fog2 var3, String var4, float var5, float var6, LightingExtension4222 var7, boolean var8) {
      if (var8) {
         var1.method19(ThreadModuleDump63.method10(), var4, var5, var6, PotionEffects.method12(var2, var3), (Boolean)this.field3.field33.get());
      } else {
         var7.HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var4, var5, var6, (Boolean)this.field3.field33.get());
      }
   }

   protected String method6(Fog var1) {
      if (this.field3.field8.get() == Type.VANILLA) {
         if (PotionEffects.method11(var1)) {
            return "∞";
         } else {
            int var2 = (int)(var1.bridge$getDuration() / 20.0F);
            if (var2 >= 3600) {
               return var2 / 3600 + "h";
            } else if (var2 >= 600) {
               return var2 / 60 + "m";
            } else {
               return var2 >= 60 ? var2 / 60 + ":" + (var2 % 60 < 10 ? "0" : "") + var2 % 60 : Integer.toString(var2);
            }
         }
      } else if ((Boolean)this.field3.field25.get()) {
         return PotionEffects.method11(var1) ? "**:**" : ThreadModuleDump11.method1((long)(var1.bridge$getDuration() / 20.0F) * 1000L);
      } else {
         return Bridge.method36().method13(var1);
      }
   }

   protected String method7(Fog var1) {
      int var2 = var1.bridge$getAmplifier();

      return switch (var2) {
         case 1 -> "II";
         case 2 -> "III";
         case 3 -> "IV";
         case 4 -> "V";
         case 5 -> "VI";
         case 6 -> "VII";
         case 7 -> "VIII";
         case 8 -> "IX";
         case 9 -> "X";
         default -> var2 > 9 ? String.valueOf(var2 + 1) : "";
      };
   }

   protected boolean method8(Fog var1) {
      if ((Boolean)this.field3.field48.get() && var1.bridge$getIsAmbient()) {
         return this.field3.field8.get() != Type.VANILLA;
      }

      if (!this.method9(var1)) {
         return true;
      }

      if (this.field4 > 20) {
         this.field4 = 0;
      }

      return this.field4 <= 10;
   }

   protected boolean method9(Fog var1) {
      if ((Boolean)this.field3.field20.get() && !var1.bridge$getIsAmbient()) {
         int var2 = (int)var1.bridge$getDuration();
         return var2 >= 0 && var2 <= (Integer)this.field3.field22.get() * 20;
      } else {
         return false;
      }
   }

   protected float method10(Fog var1) {
      if ((Boolean)this.field3.field21.get() && this.method9(var1)) {
         int var2 = (int)var1.bridge$getDuration();
         int var3 = (Integer)this.field3.field22.get() * 20;
         float var4 = Glintcolorizer5.clamp(var2 * 2.0F / var3, 0.0F, 0.5F);
         float var5 = Glintcolorizer5.clamp((1.0F - (float)var2 / var3) * 0.25F, 0.0F, 0.25F);
         float var6 = var4 + Horsestats30.method1(var2 * Math.PI / 5.0) * var5;
         return Glintcolorizer5.clamp(var6, 0.0F, 1.0F);
      } else {
         return 1.0F;
      }
   }
}
