package com.moonsworth.lunar.legacy.wrapper.mixin;

import com.moonsworth.lunar.bridge.Bridge2_34;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;

public class Bridge2Handler implements Bridge2_34 {
   public Bridge_57 method1(String var1) {
      try {
         return (ThreadModuleDump63.MC_VERSION >= 1 ? JsonToNBT.getTagFromJson(var1) : JsonToNBT.func_150315_a$v1_7(var1)) instanceof Bridge_57 var3
            ? var3
            : null;
      } catch (NBTException var4) {
         var4.printStackTrace();
         return null;
      }
   }
}
