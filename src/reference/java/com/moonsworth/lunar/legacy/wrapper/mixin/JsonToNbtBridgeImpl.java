package com.moonsworth.lunar.legacy.wrapper.mixin;

import com.moonsworth.lunar.bridge.JsonToNBTBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;

public class JsonToNbtBridgeImpl implements JsonToNBTBridge {
   public JsonToNbtBridgeImpl() {
   }

   public CompoundTagBridge method1(String text1) {
      try {
         return (Ref.MC_VERSION >= 1 ? JsonToNBT.getTagFromJson(text1) : JsonToNBT.func_150315_a$v1_7(text1)) instanceof CompoundTagBridge bridge_573
            ? bridge_573
            : null;
      } catch (NBTException nbtexception4) {
         nbtexception4.printStackTrace();
         return null;
      }
   }
}
