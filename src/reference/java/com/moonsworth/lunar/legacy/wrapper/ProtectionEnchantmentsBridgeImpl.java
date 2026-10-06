package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Bridge2_26;
import com.moonsworth.lunar.bridge.Bridge_4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;

public class ProtectionEnchantmentsBridgeImpl implements Bridge_4 {
   @Override
   public Bridge2_26 method1() {
      return ThreadModuleDump63.MC_VERSION <= 1 ? (Bridge2_26)Enchantment.protection : (Bridge2_26)Enchantments.PROTECTION;
   }

   @Override
   public Bridge2_26 method2() {
      return ThreadModuleDump63.MC_VERSION <= 1 ? (Bridge2_26)Enchantment.efficiencyOnProperMaterial : (Bridge2_26)Enchantments.EFFICIENCY;
   }

   @Override
   public Bridge2_26 method3() {
      return ThreadModuleDump63.MC_VERSION <= 1 ? (Bridge2_26)Enchantment.sharpness : (Bridge2_26)Enchantments.SHARPNESS;
   }

   @Override
   public Bridge2_26 method4() {
      return ThreadModuleDump63.MC_VERSION <= 1 ? (Bridge2_26)Enchantment.power : (Bridge2_26)Enchantments.POWER;
   }

   @Annotation2(min = 2)
   @Override
   public Bridge2_26 method5() {
      if (ThreadModuleDump63.MC_VERSION <= 2) {
         throw new AbstractMethodErrorImpl();
      } else {
         return (Bridge2_26)Enchantments.MENDING;
      }
   }
}
