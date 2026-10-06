package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_25;
import com.moonsworth.lunar.bridge.Bridge3_6;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.NonNullList;
import org.jetbrains.annotations.Nullable;

public class ItemDataComponentAccessors {
   public static void method1() {
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field1 = var0 -> () -> (Bridge_57)method2(var0, "ExtraAttributes");
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field2 = var0 -> ((ItemStack)var0).getMaxStackSize();
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field3 = var0 -> ThreadModuleDump63.MC_VERSION >= 1
         ? ((ItemStack)var0).getMaxDamage()
         : ((ItemStack)var0).getMaxDurability$v1_7();
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field4 = var0 -> ((ItemStack)var0).itemDamage;
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field6 = var0 -> {
         NBTTagCompound var1 = ((ItemStack)var0).getTagCompound();
         if (var1 != null && var1.hasKey("display")) {
            NBTTagCompound var2 = var1.getCompoundTag("display");
            return var2 != null && var2.hasKey("Name") ? (Bridge3_25)(new ChatComponentText(var2.getString("Name"))) : null;
         } else {
            return null;
         }
      };
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field7 = var0 -> (Bridge3_25)(
         new ChatComponentText(((ItemStack)var0).getItem().getItemStackDisplayName((ItemStack)var0))
      );
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field8 = var0 -> {
         Bridge_57 var1 = (Bridge_57)method2(var0, "display");
         if (var1 != null && var1.bridge$contains("Lore", 9)) {
            Bridge3_6 var2 = var1.bridge$getList("Lore", 8);
            if (var2 != null) {
               ArrayList var3 = new ArrayList();

               for (int var4 = 0; var4 < var2.bridge$size(); var4++) {
                  var3.add((Bridge3_25)(new ChatComponentText(var2.bridge$getString(var4))));
               }

               return () -> var3;
            }
         }

         return null;
      };
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field17 = var0 -> ((ItemStack)var0).getRepairCost();
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field19 = var0 -> false;
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field21 = var0 -> var0.bridge$getFood().orElse(null);
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field26 = var0 -> var0 instanceof ItemArmor var1 ? () -> var1.getColor((ItemStack)var0) : () -> 0;
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field39 = var0 -> () -> (Bridge_57)method2(var0, "EntityTag");
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field40 = com.moonsworth.lunar.bridge.ItemDataComponentTypes.field39;
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field41 = var0 -> () -> (Bridge_57)method2(var0, "BlockEntityTag");
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field43 = var0 -> 0;
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field44 = var0 -> List.of();
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field48 = var0 -> () -> {
         Bridge_57 var1 = (Bridge_57)method2(var0, "SkullOwner");
         if (var1 != null && var1.bridge$contains("Properties", 10)) {
            Bridge_57 var2 = var1.bridge$getCompoundTag("Properties");
            if (var2 != null && !var2.bridge$isEmpty()) {
               return var1x -> {
                  if (var2.bridge$contains(var1x, 9)) {
                     Bridge3_6 var2x = var2.bridge$getList(var1x, 10);
                     if (var2x != null && var2x.bridge$size() >= 1) {
                        ArrayList var3 = new ArrayList();

                        for (int var4 = 0; var4 < var2x.bridge$size(); var4++) {
                           Bridge_57 var5 = var2x.bridge$getCompoundAt(var4);
                           if (var5 != null && !var5.bridge$isEmpty() && var5.bridge$contains("Value", 8)) {
                              var3.add(() -> var5.bridge$getString("Value"));
                           }
                        }

                        return var3;
                     } else {
                        return List.of();
                     }
                  } else {
                     return List.of();
                  }
               };
            }
         }

         return null;
      };
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field49 = var0 -> null;
      com.moonsworth.lunar.bridge.ItemDataComponentTypes.field53 = var0 -> {
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            NBTTagCompound var1 = method2(var0, "BlockEntityTag");
            if (var1 != null && var1.hasKey("Items", 9)) {
               NonNullList var2 = NonNullList.withSize(27, ItemStack.EMPTY$v1_12);
               ItemStackHelper.loadAllItems(var1, var2);
               return new ContainerItemsComponentAdapter(var2);
            }
         }

         return null;
      };
   }

   @Nullable
   private static NBTTagCompound method2(ItemStackBridge var0, String var1) {
      NBTTagCompound var2 = ((ItemStack)var0).getTagCompound();
      return var2 != null && var2.hasKey(var1, 10) ? var2.getCompoundTag(var1) : null;
   }
}
