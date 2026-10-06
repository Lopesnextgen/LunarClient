package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge6Extension3;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemArmor.class)
public abstract class ItemArmorMixin extends Item implements Bridge6Extension3 {
   @Final
   @Shadow
   public ArmorMaterial material;
   @Final
   @Shadow
   public int armorType;
   @Final
   @Shadow
   public EntityEquipmentSlot armorType$v1_12;
   @Shadow
   public int damageReduceAmount;

   @Shadow
   public abstract boolean hasColor(ItemStack var1);

   @Shadow
   public abstract int getColor(ItemStack var1);

   @Override
   public EquipmentSlotBridge bridge$getSlot() {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         if (this.armorType$v1_12 == EntityEquipmentSlot.MAINHAND) {
            return EquipmentSlotBridge.MAINHAND;
         } else if (this.armorType$v1_12 == EntityEquipmentSlot.OFFHAND) {
            return EquipmentSlotBridge.OFFHAND;
         } else if (this.armorType$v1_12 == EntityEquipmentSlot.FEET) {
            return EquipmentSlotBridge.FEET;
         } else if (this.armorType$v1_12 == EntityEquipmentSlot.LEGS) {
            return EquipmentSlotBridge.LEGS;
         } else if (this.armorType$v1_12 == EntityEquipmentSlot.CHEST) {
            return EquipmentSlotBridge.CHEST;
         } else {
            return this.armorType$v1_12 == EntityEquipmentSlot.HEAD ? EquipmentSlotBridge.HEAD : null;
         }
      } else {
         return switch (this.armorType) {
            case 0 -> EquipmentSlotBridge.MAINHAND;
            case 1 -> EquipmentSlotBridge.FEET;
            case 2 -> EquipmentSlotBridge.LEGS;
            case 3 -> EquipmentSlotBridge.CHEST;
            case 4 -> EquipmentSlotBridge.HEAD;
            default -> null;
         };
      }
   }

   @Override
   public boolean bridge$hasColor(ItemStackBridge var1) {
      return this.hasColor((ItemStack)var1);
   }

   @Override
   public int bridge$getColor(ItemStackBridge var1) {
      return this.getColor((ItemStack)var1);
   }

   @Override
   public Bridge6Extension3.Type bridge$getArmorMaterial() {
      return switch (this.material) {
         case CHAIN -> Bridge6Extension3.Type.CHAIN;
         case IRON -> Bridge6Extension3.Type.IRON;
         case goldColor -> Bridge6Extension3.Type.GOLD;
         case DIAMOND -> Bridge6Extension3.Type.DIAMOND;
         default -> (ThreadModuleDump63.MC_VERSION != 0 || this.material != ArmorMaterial.CLOTH$v1_7)
               && (ThreadModuleDump63.MC_VERSION < 1 || this.material != ArmorMaterial.leather)
            ? Bridge6Extension3.Type.UNKNOWN
            : Bridge6Extension3.Type.LEATHER;
      };
   }

   @Override
   public int bridge$getArmorValue(ItemStackBridge var1) {
      return this.damageReduceAmount;
   }

   @Override
   public String bridge$getResourcePath() {
      boolean var1 = this.bridge$getSlot() == EquipmentSlotBridge.LEGS;
      Object var2 = null;
      String var3 = this.bridge$getArmorMaterial().getMaterial();
      String var4 = "minecraft";
      int var5 = var3.indexOf(58);
      if (var5 != -1) {
         var4 = var3.substring(0, var5);
         var3 = var3.substring(var5 + 1);
      }

      return String.format("%s:textures/models/armor/%s_layer_%d%s.png", var4, var3, var1 ? 2 : 1, var2 == null ? "" : String.format("_%s", var2));
   }
}
