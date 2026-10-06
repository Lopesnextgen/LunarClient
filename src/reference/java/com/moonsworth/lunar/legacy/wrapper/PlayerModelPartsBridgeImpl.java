package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.PlayerModelPartsBridge;
import com.moonsworth.lunar.bridge.EnumPlayerModelPartsBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.entity.player.EnumPlayerModelParts;

public class PlayerModelPartsBridgeImpl implements PlayerModelPartsBridge {
   public PlayerModelPartsBridgeImpl() {
   }

   public EnumPlayerModelPartsBridge method1() {
      return Ref.MC_VERSION <= 0 ? null : (EnumPlayerModelPartsBridge)EnumPlayerModelParts.CAPE;
   }

   public EnumPlayerModelPartsBridge method2() {
      return Ref.MC_VERSION <= 0 ? null : (EnumPlayerModelPartsBridge)EnumPlayerModelParts.JACKET;
   }

   public EnumPlayerModelPartsBridge method3() {
      return Ref.MC_VERSION <= 0 ? null : (EnumPlayerModelPartsBridge)EnumPlayerModelParts.LEFT_SLEEVE;
   }

   public EnumPlayerModelPartsBridge method4() {
      return Ref.MC_VERSION <= 0 ? null : (EnumPlayerModelPartsBridge)EnumPlayerModelParts.RIGHT_SLEEVE;
   }

   public EnumPlayerModelPartsBridge method5() {
      return Ref.MC_VERSION <= 0 ? null : (EnumPlayerModelPartsBridge)EnumPlayerModelParts.LEFT_PANTS_LEG;
   }

   public EnumPlayerModelPartsBridge method6() {
      return Ref.MC_VERSION <= 0 ? null : (EnumPlayerModelPartsBridge)EnumPlayerModelParts.RIGHT_PANTS_LEG;
   }

   public EnumPlayerModelPartsBridge method7() {
      return Ref.MC_VERSION <= 0 ? null : (EnumPlayerModelPartsBridge)EnumPlayerModelParts.HAT;
   }
}
