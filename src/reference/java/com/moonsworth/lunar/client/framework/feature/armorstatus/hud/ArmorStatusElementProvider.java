package com.moonsworth.lunar.client.framework.feature.armorstatus.hud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.feature.armorstatus.ArmorStatusSlot;
import com.moonsworth.lunar.client.framework.feature.armorstatus.hud.ArmorStatusElementChildMod;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.EnumMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class ArmorStatusElementProvider {
   private final Armorstatus field1;
   private final Map<ArmorStatusSlot, ArmorStatusElementChildMod> field2;
   private final Map<ArmorStatusSlot, ArmorStatusElement> elements = new EnumMap<>(ArmorStatusSlot.class);
   private final Map<ArmorStatusSlot, ArmorStatusElement> field3 = new EnumMap<>(ArmorStatusSlot.class);
   private final Map<ArmorStatusSlot, ItemStackBridge> field4 = new EnumMap<>(ArmorStatusSlot.class);
   private boolean field5 = true;

   public ArmorStatusElementProvider(Armorstatus armorstatus1, Map<ArmorStatusSlot, ArmorStatusElementChildMod> map2) {
      this.field1 = armorstatus1;
      this.field2 = map2;
   }

   public void update() {
      this.elements.clear();
      this.field5 = true;
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         boolean flag2 = (Boolean)this.field1.field27.get();

         for (ArmorStatusElementChildMod framework7extension24 : this.field2.values()) {
            if (framework7extension24.isEnabled()) {
               ItemStackBridge bridgeextension_45 = this.method3(bridge5extension_51, framework7extension24.field14);
               boolean flag6 = this.method6(bridgeextension_45);
               if (flag6 || flag2) {
                  this.elements.put(framework7extension24.field14, new ArmorStatusElement(this.field1, framework7extension24.field14, flag6 ? bridgeextension_45 : null, !flag2));
               }
            }
         }
      }
   }

   public Map<ArmorStatusSlot, ArmorStatusElement> method1(boolean flag1) {
      if (!flag1) {
         return this.elements;
      }

      if (this.field5) {
         this.method2();
         this.field5 = false;
      }

      return this.field3;
   }

   private void method2() {
      this.field3.clear();
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      boolean flag2 = (Boolean)this.field1.field27.get();

      for (ArmorStatusElementChildMod framework7extension24 : this.field2.values()) {
         if (framework7extension24.isEnabled()) {
            ArmorStatusSlot armorstatustype5 = framework7extension24.field14;
            ItemStackBridge bridgeextension_46 = armorstatustype5.isHeld() && bridge5extension_51 != null ? this.method3(bridge5extension_51, armorstatustype5) : null;
            if (!this.method6(bridgeextension_46)) {
               bridgeextension_46 = this.method4(armorstatustype5);
               if (bridgeextension_46 == null && !flag2) {
                  continue;
               }
            }

            this.field3.put(armorstatustype5, new ArmorStatusElement(this.field1, armorstatustype5, bridgeextension_46, !flag2));
         }
      }
   }

   @Nullable
   private ItemStackBridge method3(Bridge5Extension_5 bridge5extension_51, ArmorStatusSlot armorstatustype2) {
      if (armorstatustype2 == ArmorStatusSlot.HELD_ITEM) {
         return bridge5extension_51.bridge$getCurrentEquippedItem();
      }

      if (armorstatustype2 == ArmorStatusSlot.OFF_HAND_HELD_ITEM) {
         return bridge5extension_51.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.OFFHAND);
      }

      int index3 = Ref.MC_VERSION >= 29 ? 3 - armorstatustype2.getSlotId() : armorstatustype2.getSlotId();
      return (ItemStackBridge)bridge5extension_51.bridge$getInventory().bridge$getArmorInventory().get(index3);
   }

   @Nullable
   private ItemStackBridge method4(ArmorStatusSlot armorstatustype1) {
      ItemStackBridge bridgeextension_42 = this.field4.get(armorstatustype1);
      if (bridgeextension_42 != null) {
         return bridgeextension_42;
      }

      ItemBridge bridge6_43 = this.method5(armorstatustype1);
      if (bridge6_43 == null) {
         return null;
      }

      bridgeextension_42 = Bridge.method8().method38(bridge6_43);
      this.field4.put(armorstatustype1, bridgeextension_42);
      return bridgeextension_42;
   }

   @Nullable
   private ItemBridge method5(ArmorStatusSlot armorstatustype1) {
      return switch (armorstatustype1) {
         case HELD_ITEM -> Bridge.method28().method16();
         case OFF_HAND_HELD_ITEM -> Bridge.method28().method32();
         case HELMET -> Bridge.method28().method12();
         case CHESTPLATE -> Bridge.method28().method13();
         case LEGGINGS -> Bridge.method28().method14();
         case BOOTS -> Bridge.method28().method15();
      };
   }

   private boolean method6(@Nullable ItemStackBridge bridgeextension_41) {
      return bridgeextension_41 != null && bridgeextension_41.bridge$getItem() != Bridge.method28().method25();
   }
}
