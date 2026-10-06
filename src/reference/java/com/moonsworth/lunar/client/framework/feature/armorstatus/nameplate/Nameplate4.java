package com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_19;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.ArmorstatusType;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.gui.Gui2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.render.RenderScaleEvent;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

class Nameplate4 {
   private static final int field1 = 182;
   private static final int field2 = 22;
   private static final int field3 = 1;
   private static final int field4 = 20;
   private static final int field5 = 7;
   private static final ResourceLocationBridge field6 = ResourceLocationBridge.create(
      "minecraft", ThreadModuleDump63.MC_VERSION < 19 ? "textures/gui/widgets.png" : "textures/gui/sprites/hud/hotbar.png"
   );
   private static final float field7 = ThreadModuleDump63.MC_VERSION < 19 ? 256.0F : 182.0F;
   private static final float field8 = ThreadModuleDump63.MC_VERSION < 19 ? 256.0F : 22.0F;
   private static final Map<ArmorstatusType, ResourceLocationBridge> field9 = new EnumMap<>(ArmorstatusType.class);
   private final Armorstatus field10;
   private boolean field11;
   private boolean field12;
   private boolean field13;
   private float field14;
   private int field15;
   private float width;
   private float height;
   private float field16;

   public Nameplate4(Armorstatus var1) {
      this.field10 = var1;
   }

   public void method1(Nameplate5 var1, Collection<Nameplate> var2, MixinHelper_4 var3, float var4, float var5) {
      this.method3(var1, var2);
      if (this.field15 == 0) {
         var1.method58(0.0F, 0.0F);
      } else {
         if (this.field12) {
            var1.method58(0.0F, 0.0F);
         } else {
            var1.method58(this.width, this.height + this.field16);
         }

         var3.method44(var0 -> var0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
         var3.push();
         if (this.field12) {
            this.method4(var3);
         } else {
            var3.method38(var4, var5 + this.field16, 0.0F);
         }

         this.method2(var3);
         this.method10(var2, var3);
         var3.pop();
         var3.method44(var0 -> var0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
      }
   }

   private void method2(MixinHelper_4 var1) {
      OverlayMod var2 = ThreadModuleDump63.method4().method40().method84();
      if (!var2.isHotbarTintEnabled()) {
         this.method5(var1);
      } else {
         Gui2.method1(var2.getHotbarTint());

         try {
            this.method5(var1);
         } finally {
            Gui2.method2();
         }
      }
   }

   private void method3(Nameplate5 var1, Collection<Nameplate> var2) {
      this.field11 = this.field10.field19.get() == Gui2Extension.VERTICAL;
      this.field12 = (Boolean)this.field10.field28.get() && LcuiScreen.method151() != null;
      this.field13 = this.field12
         ? this.field10.field29.get() == Gui2Extension3.LEFT
         : var1.method26().getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudPlacement.RIGHT;
      Bridge10_2 var3 = ThreadModuleDump63.method10();
      boolean var4 = (Boolean)this.field10.field30.get();
      boolean var5 = false;
      boolean var6 = false;
      float var7 = 0.0F;
      this.field15 = 0;

      for (Nameplate var9 : var2) {
         if (!var4 || var9.method13() != null) {
            this.field15++;
            if (!this.field11) {
               String var10 = (String)var9.method1(false).getKey();
               var7 = Math.max(var7, var3.bridge$getStringWidth(var10));
               var5 |= !var10.isEmpty();
               var6 |= this.method13(var9.method13());
            }
         }
      }

      this.width = 2 + (this.field11 ? 1 : this.field15) * 20;
      this.height = 2 + (this.field11 ? this.field15 : 1) * 20;
      if (this.field11) {
         this.field14 = 1.0F;
         this.field16 = 0.0F;
      } else {
         this.field14 = var7 <= 20.0F ? 1.0F : (var7 * 0.75F <= 20.0F ? 0.75F : 0.5F);
         this.field16 = 0.0F;
         if (var5) {
            this.field16 = this.field16 + Math.round(var3.method19() * this.field14);
         }

         if (var6) {
            this.field16 += 8.0F;
         }
      }
   }

   private void method4(MixinHelper_4 var1) {
      RenderScaleEvent var2 = (RenderScaleEvent)ClientEventBus.method29().method12(RenderScaleEvent.class, RenderScaleEvent::new);
      float var3 = var2 != null && !(var2.getScale() <= 0.0F) ? var2.getScale() : 1.0F;
      ThreadModuleDump71 var4 = LcuiScreen.method151();
      int var5 = Math.round(var4.getScaledWidth() / var3);
      int var6 = Math.round(var4.getScaledHeight() / var3);
      int var7 = var5 / 2;
      byte var8 = 29;
      byte var9 = 7;
      Bridge5Extension_5 var10 = ThreadModuleDump63.method7();
      ItemStackBridge var11 = var10 != null && ThreadModuleDump63.MC_VERSION >= 5 ? var10.bridge$getEquipmentInSlot(EquipmentSlotBridge.OFFHAND) : null;
      byte var12 = var11 != null && !var11.bridge$isEmpty() ? var8 : 0;
      boolean var13 = var10 == null || ThreadModuleDump63.MC_VERSION < 5 || !var10.bridge$isMainHandSwapped();
      float var14;
      if (this.field10.field29.get() == Gui2Extension3.LEFT) {
         var14 = var7 - 91.0F - (var13 ? var12 : 0) - var9 - this.width;
      } else {
         var14 = var7 + 91.0F + (var13 ? 0 : var12) + var9;
      }

      var1.scale(var3, var3, 1.0F);
      var1.method38(var14, var6 - this.height, 0.0F);
   }

   private void method5(MixinHelper_4 var1) {
      int var2 = this.field11 ? this.field15 : 1;
      int var3 = this.field11 ? 1 : this.field15;
      this.method7(var1, 0.0F, 0.0F, var3);

      for (int var4 = 0; var4 < var2; var4++) {
         this.method8(var1, 1 + var4 * 20, var3, 1.0F, 20.0F);
      }

      float var5 = 1 + var2 * 20;
      this.method7(var1, var5, 21.0F, var3);
      if ((Boolean)this.field10.field31.get()) {
         this.method6(var1);
      }
   }

   private void method6(MixinHelper_4 var1) {
      float var2 = this.width - 2.0F;
      float var3 = this.height - 2.0F;
      this.method9(var1, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F);
      this.method9(var1, var2, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F);
      this.method9(var1, 1.0F, var3, 0.0F, 0.0F, 1.0F, 1.0F);
      this.method9(var1, var2, var3, 0.0F, 0.0F, 1.0F, 1.0F);
   }

   private void method7(MixinHelper_4 var1, float var2, float var3, int var4) {
      if (!(Boolean)this.field10.field31.get()) {
         this.method8(var1, var2, var4, var3, 1.0F);
      } else {
         float var5 = var4 * 20;
         this.method9(var1, 1.0F, var2, 1.0F, var3, var5, 1.0F);
      }
   }

   private void method8(MixinHelper_4 var1, float var2, int var3, float var4, float var5) {
      float var6 = 1 + var3 * 20;
      this.method9(var1, 0.0F, var2, 0.0F, var4, var6, var5);
      this.method9(var1, var6, var2, 181.0F, var4, 1.0F, var5);
   }

   private void method9(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      int var8 = -1;
      if (ThreadModuleDump63.MC_VERSION < 30) {
         var8 = Gui2.method5(var8);
      }

      LcuiScreen.method46(var1, field6, var2, var3, var4, var5, var6, var7, field7, field8, var8);
   }

   private void method10(Collection<Nameplate> var1, MixinHelper_4 var2) {
      boolean var3 = (Boolean)this.field10.field30.get();
      Bridge5_19 var4 = ThreadModuleDump63.method3().bridge$getRenderItem();
      int var5 = 0;

      for (Nameplate var7 : var1) {
         ItemStackBridge var8 = var7.method13();
         if (var8 != null || !var3) {
            int var9 = var5++ * 20;
            int var10 = 3 + (this.field11 ? 0 : var9);
            int var11 = 3 + (this.field11 ? var9 : 0);
            if (var8 == null) {
               ResourceLocationBridge var12 = field9.get(var7.method12());
               if (var12 != null) {
                  LcuiScreen.method46(var2, var12, var10, var11, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, 16.0F, -1);
               }
            } else {
               var7.method6(var2, var4, var10, var11);
               this.method11(var2, var7, var10, var11);
            }
         }
      }
   }

   private void method11(MixinHelper_4 var1, Nameplate var2, int var3, int var4) {
      Pair var5 = var2.method1(false);
      String var6 = (String)var5.getKey();
      boolean var7 = this.method13(var2.method13());
      if (!var6.isEmpty() || var7) {
         Bridge10_2 var8 = ThreadModuleDump63.method10();
         float var9 = var8.bridge$getStringWidth(var6) * this.field14;
         float var10 = var6.isEmpty() ? 0.0F : Math.round(var8.method19() * this.field14);
         float var11 = var6.isEmpty() ? 0.0F : (this.field11 ? var9 : var10);
         if (!this.field11) {
            this.method14(var1, var5, var3 + this.method12(var9), -var10);
            if (var7) {
               this.getProvider(var1, var3 + this.method12(7.0F) - 1.0F, -var11 - 1.0F - 7.0F, 7.0F);
            }
         } else {
            float var12 = this.field13 ? -var9 - 1.0F : this.width + 2.0F;
            float var13 = this.field13 ? -var11 - 7.0F - 3.0F : this.width + var11 + 3.0F;
            this.method14(var1, var5, var12, var4 + this.method12(var10));
            if (var7) {
               this.getProvider(var1, var13, var4 + this.method12(7.0F) - 0.5F, 7.0F);
            }
         }
      }
   }

   private float method12(float var1) {
      return Math.round((16.0F - var1) / 2.0F);
   }

   private boolean method13(@Nullable ItemStackBridge var1) {
      return (Boolean)this.field10.field32.get() && Armorstatus.method7(var1, (Integer)this.field10.field33.get());
   }

   private void method14(MixinHelper_4 var1, Pair<String, ColorOption> var2, float var3, float var4) {
      String var5 = (String)var2.getKey();
      if (!var5.isEmpty()) {
         var1.push();
         var1.scale(this.field14, this.field14, 1.0F);
         ((ColorOption)var2.getValue())
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(var1, var5, Math.round(var3 / this.field14), Math.round(var4 / this.field14), (Boolean)this.field10.field12.get());
         var1.pop();
      }
   }

   private void getProvider(MixinHelper_4 var1, float var2, float var3, float var4) {
      int var5 = (int)var4 - 1;
      int var6 = -12641766;
      int var7 = -38037;

      for (int var8 = 0; var8 < var5; var8++) {
         float var9 = Math.min(var4, 3 + var8 / 2 * 2);
         float var10 = var2 + (var4 - var9) / 2.0F;
         LcuiScreen.method94(var1, var10 + 1.0F, var3 + var8 + 1.0F, var9, 1.0F, var6);
         LcuiScreen.method94(var1, var10, var3 + var8, var9, 1.0F, var7);
      }

      float var11 = var2 + var4 / 2.0F;
      LcuiScreen.method94(var1, var11 - 0.5F, var3 + 1.0F, 1.0F, 2.0F, var6);
      LcuiScreen.method94(var1, var11 - 0.5F, var3 + var5 - 2.0F, 1.0F, 1.0F, var6);
   }

   private static ResourceLocationBridge method16(String var0) {
      String var1;
      if (ThreadModuleDump63.MC_VERSION >= 28) {
         var1 = "textures/gui/sprites/container/slot/" + var0 + ".png";
      } else if (ThreadModuleDump63.MC_VERSION >= 6) {
         var1 = "textures/item/empty_armor_slot_" + var0 + ".png";
      } else {
         var1 = "textures/items/empty_armor_slot_" + var0 + ".png";
      }

      return ResourceLocationBridge.create("minecraft", var1);
   }

   static {
      field9.put(ArmorstatusType.HELMET, method16("helmet"));
      field9.put(ArmorstatusType.CHESTPLATE, method16("chestplate"));
      field9.put(ArmorstatusType.LEGGINGS, method16("leggings"));
      field9.put(ArmorstatusType.BOOTS, method16("boots"));
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         field9.put(ArmorstatusType.OFF_HAND_HELD_ITEM, method16("shield"));
      }
   }
}
