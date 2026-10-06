package com.moonsworth.lunar.client.framework.feature.armorstatus.hud;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.RenderItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusSlot;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusListMode;
import com.moonsworth.lunar.client.framework.feature.armorstatus.HotbarPosition;
import com.moonsworth.lunar.client.framework.feature.overlay.HudColorOverride;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.render.EventRenderScale;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

class ArmorStatusPanelRenderer {
   private static final int field1 = 182;
   private static final int field2 = 22;
   private static final int field3 = 1;
   private static final int field4 = 20;
   private static final int field5 = 7;
   private static final ResourceLocationBridge field6 = ResourceLocationBridge.create(
      "minecraft", Ref.MC_VERSION < 19 ? "textures/gui/widgets.png" : "textures/gui/sprites/hud/hotbar.png"
   );
   private static final float field7 = Ref.MC_VERSION < 19 ? 256.0F : 182.0F;
   private static final float field8 = Ref.MC_VERSION < 19 ? 256.0F : 22.0F;
   private static final Map<ArmorStatusSlot, ResourceLocationBridge> field9 = new EnumMap<>(ArmorStatusSlot.class);
   private final Armorstatus field10;
   private boolean field11;
   private boolean field12;
   private boolean field13;
   private float field14;
   private int field15;
   private float width;
   private float height;
   private float field16;

   public ArmorStatusPanelRenderer(Armorstatus armorstatus1) {
      this.field10 = armorstatus1;
   }

   public void method1(ArmorStatusHud nameplate51, Collection<ArmorStatusElement> list2, MixinHelper_4 mixinhelper_43, float value4, float value5) {
      this.method3(nameplate51, list2);
      if (this.field15 == 0) {
         nameplate51.method16(0.0F, 0.0F);
      } else {
         if (this.field12) {
            nameplate51.method16(0.0F, 0.0F);
         } else {
            nameplate51.method16(this.width, this.height + this.field16);
         }

         mixinhelper_43.method44(arg0 -> arg0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
         mixinhelper_43.push();
         if (this.field12) {
            this.method4(mixinhelper_43);
         } else {
            mixinhelper_43.method38(value4, value5 + this.field16, 0.0F);
         }

         this.method2(mixinhelper_43);
         this.method10(list2, mixinhelper_43);
         mixinhelper_43.pop();
         mixinhelper_43.method44(arg0 -> arg0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
      }
   }

   private void method2(MixinHelper_4 mixinhelper_41) {
      OverlayMod overlaymod2 = Ref.method4().method40().method84();
      if (!overlaymod2.isHotbarTintEnabled()) {
         this.method5(mixinhelper_41);
      } else {
         HudColorOverride.method1(overlaymod2.getHotbarTint());

         try {
            this.method5(mixinhelper_41);
         } finally {
            HudColorOverride.method2();
         }
      }
   }

   private void method3(ArmorStatusHud nameplate51, Collection<ArmorStatusElement> list2) {
      this.field11 = this.field10.field19.get() == ArmorStatusListMode.VERTICAL;
      this.field12 = (Boolean)this.field10.field28.get() && LcuiScreen.method151() != null;
      this.field13 = this.field12
         ? this.field10.field29.get() == HotbarPosition.LEFT
         : nameplate51.method26().getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudAlignment.RIGHT;
      Bridge10_2 bridge10_23 = Ref.method10();
      boolean flag4 = (Boolean)this.field10.field30.get();
      boolean flag5 = false;
      boolean flag6 = false;
      float value7 = 0.0F;
      this.field15 = 0;

      for (ArmorStatusElement nameplate9 : list2) {
         if (!flag4 || nameplate9.method13() != null) {
            this.field15++;
            if (!this.field11) {
               String text10 = (String)nameplate9.method1(false).getKey();
               value7 = Math.max(value7, bridge10_23.bridge$getStringWidth(text10));
               flag5 |= !text10.isEmpty();
               flag6 |= this.method13(nameplate9.method13());
            }
         }
      }

      this.width = 2 + (this.field11 ? 1 : this.field15) * 20;
      this.height = 2 + (this.field11 ? this.field15 : 1) * 20;
      if (this.field11) {
         this.field14 = 1.0F;
         this.field16 = 0.0F;
      } else {
         this.field14 = value7 <= 20.0F ? 1.0F : (value7 * 0.75F <= 20.0F ? 0.75F : 0.5F);
         this.field16 = 0.0F;
         if (flag5) {
            this.field16 = this.field16 + Math.round(bridge10_23.method19() * this.field14);
         }

         if (flag6) {
            this.field16 += 8.0F;
         }
      }
   }

   private void method4(MixinHelper_4 mixinhelper_41) {
      EventRenderScale highlightimpl_22 = (EventRenderScale)LunarEventBus.method29().method12(EventRenderScale.class, EventRenderScale::new);
      float value3 = highlightimpl_22 != null && !(highlightimpl_22.getScale() <= 0.0F) ? highlightimpl_22.getScale() : 1.0F;
      GuiResolution threadmoduledump714 = LcuiScreen.method151();
      int number5 = Math.round(threadmoduledump714.getScaledWidth() / value3);
      int number6 = Math.round(threadmoduledump714.getScaledHeight() / value3);
      int number7 = number5 / 2;
      byte number8 = 29;
      byte number9 = 7;
      Bridge5Extension_5 bridge5extension_510 = Ref.method7();
      ItemStackBridge bridgeextension_411 = bridge5extension_510 != null && Ref.MC_VERSION >= 5 ? bridge5extension_510.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.OFFHAND) : null;
      byte number12 = bridgeextension_411 != null && !bridgeextension_411.bridge$isEmpty() ? number8 : 0;
      boolean flag13 = bridge5extension_510 == null || Ref.MC_VERSION < 5 || !bridge5extension_510.bridge$isMainHandSwapped();
      float value14;
      if (this.field10.field29.get() == HotbarPosition.LEFT) {
         value14 = number7 - 91.0F - (flag13 ? number12 : 0) - number9 - this.width;
      } else {
         value14 = number7 + 91.0F + (flag13 ? 0 : number12) + number9;
      }

      mixinhelper_41.scale(value3, value3, 1.0F);
      mixinhelper_41.method38(value14, number6 - this.height, 0.0F);
   }

   private void method5(MixinHelper_4 mixinhelper_41) {
      int number2 = this.field11 ? this.field15 : 1;
      int number3 = this.field11 ? 1 : this.field15;
      this.method7(mixinhelper_41, 0.0F, 0.0F, number3);

      for (int index4 = 0; index4 < number2; index4++) {
         this.method8(mixinhelper_41, 1 + index4 * 20, number3, 1.0F, 20.0F);
      }

      float value5 = 1 + number2 * 20;
      this.method7(mixinhelper_41, value5, 21.0F, number3);
      if ((Boolean)this.field10.field31.get()) {
         this.method6(mixinhelper_41);
      }
   }

   private void method6(MixinHelper_4 mixinhelper_41) {
      float value2 = this.width - 2.0F;
      float value3 = this.height - 2.0F;
      this.method9(mixinhelper_41, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F);
      this.method9(mixinhelper_41, value2, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F);
      this.method9(mixinhelper_41, 1.0F, value3, 0.0F, 0.0F, 1.0F, 1.0F);
      this.method9(mixinhelper_41, value2, value3, 0.0F, 0.0F, 1.0F, 1.0F);
   }

   private void method7(MixinHelper_4 mixinhelper_41, float value2, float value3, int number4) {
      if (!(Boolean)this.field10.field31.get()) {
         this.method8(mixinhelper_41, value2, number4, value3, 1.0F);
      } else {
         float value5 = number4 * 20;
         this.method9(mixinhelper_41, 1.0F, value2, 1.0F, value3, value5, 1.0F);
      }
   }

   private void method8(MixinHelper_4 mixinhelper_41, float value2, int number3, float value4, float value5) {
      float value6 = 1 + number3 * 20;
      this.method9(mixinhelper_41, 0.0F, value2, 0.0F, value4, value6, value5);
      this.method9(mixinhelper_41, value6, value2, 181.0F, value4, 1.0F, value5);
   }

   private void method9(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, float value6, float value7) {
      int number8 = -1;
      if (Ref.MC_VERSION < 30) {
         number8 = HudColorOverride.method5(number8);
      }

      LcuiScreen.method46(mixinhelper_41, field6, value2, value3, value4, value5, value6, value7, field7, field8, number8);
   }

   private void method10(Collection<ArmorStatusElement> list1, MixinHelper_4 mixinhelper_42) {
      boolean flag3 = (Boolean)this.field10.field30.get();
      RenderItemBridge bridge5_194 = Ref.method3().bridge$getRenderItem();
      int index5 = 0;

      for (ArmorStatusElement nameplate7 : list1) {
         ItemStackBridge bridgeextension_48 = nameplate7.method13();
         if (bridgeextension_48 != null || !flag3) {
            int number9 = index5++ * 20;
            int number10 = 3 + (this.field11 ? 0 : number9);
            int number11 = 3 + (this.field11 ? number9 : 0);
            if (bridgeextension_48 == null) {
               ResourceLocationBridge horsestats1412 = field9.get(nameplate7.method12());
               if (horsestats1412 != null) {
                  LcuiScreen.method46(mixinhelper_42, horsestats1412, number10, number11, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, 16.0F, -1);
               }
            } else {
               nameplate7.method6(mixinhelper_42, bridge5_194, number10, number11);
               this.method11(mixinhelper_42, nameplate7, number10, number11);
            }
         }
      }
   }

   private void method11(MixinHelper_4 mixinhelper_41, ArmorStatusElement nameplate2, int number3, int number4) {
      Pair pair5 = nameplate2.method1(false);
      String text6 = (String)pair5.getKey();
      boolean flag7 = this.method13(nameplate2.method13());
      if (!text6.isEmpty() || flag7) {
         Bridge10_2 bridge10_28 = Ref.method10();
         float value9 = bridge10_28.bridge$getStringWidth(text6) * this.field14;
         float value10 = text6.isEmpty() ? 0.0F : Math.round(bridge10_28.method19() * this.field14);
         float value11 = text6.isEmpty() ? 0.0F : (this.field11 ? value9 : value10);
         if (!this.field11) {
            this.method14(mixinhelper_41, pair5, number3 + this.method12(value9), -value10);
            if (flag7) {
               this.method15(mixinhelper_41, number3 + this.method12(7.0F) - 1.0F, -value11 - 1.0F - 7.0F, 7.0F);
            }
         } else {
            float value12 = this.field13 ? -value9 - 1.0F : this.width + 2.0F;
            float value13 = this.field13 ? -value11 - 7.0F - 3.0F : this.width + value11 + 3.0F;
            this.method14(mixinhelper_41, pair5, value12, number4 + this.method12(value10));
            if (flag7) {
               this.method15(mixinhelper_41, value13, number4 + this.method12(7.0F) - 0.5F, 7.0F);
            }
         }
      }
   }

   private float method12(float value1) {
      return Math.round((16.0F - value1) / 2.0F);
   }

   private boolean method13(@Nullable ItemStackBridge bridgeextension_41) {
      return (Boolean)this.field10.field32.get() && Armorstatus.method7(bridgeextension_41, (Integer)this.field10.field33.get());
   }

   private void method14(MixinHelper_4 mixinhelper_41, Pair<String, ColorOption> pair2, float value3, float value4) {
      String text5 = (String)pair2.getKey();
      if (!text5.isEmpty()) {
         mixinhelper_41.push();
         mixinhelper_41.scale(this.field14, this.field14, 1.0F);
         ((ColorOption)pair2.getValue())
            .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text5, Math.round(value3 / this.field14), Math.round(value4 / this.field14), (Boolean)this.field10.field12.get());
         mixinhelper_41.pop();
      }
   }

   private void method15(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4) {
      int number5 = (int)value4 - 1;
      int number6 = -12641766;
      int number7 = -38037;

      for (int index8 = 0; index8 < number5; index8++) {
         float value9 = Math.min(value4, 3 + index8 / 2 * 2);
         float value10 = value2 + (value4 - value9) / 2.0F;
         LcuiScreen.method94(mixinhelper_41, value10 + 1.0F, value3 + index8 + 1.0F, value9, 1.0F, number6);
         LcuiScreen.method94(mixinhelper_41, value10, value3 + index8, value9, 1.0F, number7);
      }

      float value11 = value2 + value4 / 2.0F;
      LcuiScreen.method94(mixinhelper_41, value11 - 0.5F, value3 + 1.0F, 1.0F, 2.0F, number6);
      LcuiScreen.method94(mixinhelper_41, value11 - 0.5F, value3 + number5 - 2.0F, 1.0F, 1.0F, number6);
   }

   private static ResourceLocationBridge method16(String text0) {
      String text1;
      if (Ref.MC_VERSION >= 28) {
         text1 = "textures/gui/sprites/container/slot/" + text0 + ".png";
      } else if (Ref.MC_VERSION >= 6) {
         text1 = "textures/item/empty_armor_slot_" + text0 + ".png";
      } else {
         text1 = "textures/items/empty_armor_slot_" + text0 + ".png";
      }

      return ResourceLocationBridge.create("minecraft", text1);
   }

   static {
      field9.put(ArmorStatusSlot.HELMET, method16("helmet"));
      field9.put(ArmorStatusSlot.CHESTPLATE, method16("chestplate"));
      field9.put(ArmorStatusSlot.LEGGINGS, method16("leggings"));
      field9.put(ArmorStatusSlot.BOOTS, method16("boots"));
      if (Ref.MC_VERSION >= 5) {
         field9.put(ArmorStatusSlot.OFF_HAND_HELD_ITEM, method16("shield"));
      }
   }
}
