package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge4_24;
import com.moonsworth.lunar.bridge.Bridge5_6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class Bridge4Handler_2 implements Bridge4_24 {
   public Bridge5_6 method1() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? null : (Bridge5_6)EnumPlayerModelParts.CAPE;
   }

   public Bridge5_6 method2() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? null : (Bridge5_6)EnumPlayerModelParts.JACKET;
   }

   public Bridge5_6 method3() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? null : (Bridge5_6)EnumPlayerModelParts.LEFT_SLEEVE;
   }

   public Bridge5_6 method4() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? null : (Bridge5_6)EnumPlayerModelParts.RIGHT_SLEEVE;
   }

   public Bridge5_6 method5() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? null : (Bridge5_6)EnumPlayerModelParts.LEFT_PANTS_LEG;
   }

   public Bridge5_6 method6() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? null : (Bridge5_6)EnumPlayerModelParts.RIGHT_PANTS_LEG;
   }

   public Bridge5_6 method7() {
      return ThreadModuleDump63.MC_VERSION <= 0 ? null : (Bridge5_6)EnumPlayerModelParts.HAT;
   }
}
