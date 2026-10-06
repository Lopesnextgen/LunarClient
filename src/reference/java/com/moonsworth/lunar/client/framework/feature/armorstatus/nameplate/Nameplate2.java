package com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.framework.feature.armorstatus.mixin.ArmorstatusType;
import com.moonsworth.lunar.client.framework.feature.armorstatus.nameplate.mixin.Framework7Extension2;
import com.moonsworth.lunar.client.mod.render.armorstatus.Armorstatus;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.EnumMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class Nameplate2 {
   private final Armorstatus field1;
   private final Map<ArmorstatusType, Framework7Extension2> field2;
   private final Map<ArmorstatusType, Nameplate> elements = new EnumMap<>(ArmorstatusType.class);
   private final Map<ArmorstatusType, Nameplate> field3 = new EnumMap<>(ArmorstatusType.class);
   private final Map<ArmorstatusType, ItemStackBridge> field4 = new EnumMap<>(ArmorstatusType.class);
   private boolean field5 = true;

   public Nameplate2(Armorstatus var1, Map<ArmorstatusType, Framework7Extension2> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public void update() {
      this.elements.clear();
      this.field5 = true;
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 != null) {
         boolean var2 = (Boolean)this.field1.field27.get();

         for (Framework7Extension2 var4 : this.field2.values()) {
            if (var4.isEnabled()) {
               ItemStackBridge var5 = this.method3(var1, var4.field14);
               boolean var6 = this.method6(var5);
               if (var6 || var2) {
                  this.elements.put(var4.field14, new Nameplate(this.field1, var4.field14, var6 ? var5 : null, !var2));
               }
            }
         }
      }
   }

   public Map<ArmorstatusType, Nameplate> method1(boolean var1) {
      if (!var1) {
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
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      boolean var2 = (Boolean)this.field1.field27.get();

      for (Framework7Extension2 var4 : this.field2.values()) {
         if (var4.isEnabled()) {
            ArmorstatusType var5 = var4.field14;
            ItemStackBridge var6 = var5.isHeld() && var1 != null ? this.method3(var1, var5) : null;
            if (!this.method6(var6)) {
               var6 = this.method4(var5);
               if (var6 == null && !var2) {
                  continue;
               }
            }

            this.field3.put(var5, new Nameplate(this.field1, var5, var6, !var2));
         }
      }
   }

   @Nullable
   private ItemStackBridge method3(Bridge5Extension_5 var1, ArmorstatusType var2) {
      if (var2 == ArmorstatusType.HELD_ITEM) {
         return var1.bridge$getCurrentEquippedItem();
      }

      if (var2 == ArmorstatusType.OFF_HAND_HELD_ITEM) {
         return var1.bridge$getEquipmentInSlot(EquipmentSlotBridge.OFFHAND);
      }

      int var3 = ThreadModuleDump63.MC_VERSION >= 29 ? 3 - var2.getSlotId() : var2.getSlotId();
      return (ItemStackBridge)var1.bridge$getInventory().bridge$getArmorInventory().get(var3);
   }

   @Nullable
   private ItemStackBridge method4(ArmorstatusType var1) {
      ItemStackBridge var2 = this.field4.get(var1);
      if (var2 != null) {
         return var2;
      }

      Bridge6_4 var3 = this.method5(var1);
      if (var3 == null) {
         return null;
      }

      var2 = Bridge.method8().method38(var3);
      this.field4.put(var1, var2);
      return var2;
   }

   @Nullable
   private Bridge6_4 method5(ArmorstatusType var1) {
      return switch (var1) {
         case HELD_ITEM -> Bridge.method28().method16();
         case OFF_HAND_HELD_ITEM -> Bridge.method28().method32();
         case HELMET -> Bridge.method28().method12();
         case CHESTPLATE -> Bridge.method28().method13();
         case LEGGINGS -> Bridge.method28().method14();
         case BOOTS -> Bridge.method28().method15();
      };
   }

   private boolean method6(@Nullable ItemStackBridge var1) {
      return var1 != null && var1.bridge$getItem() != Bridge.method28().method25();
   }
}
