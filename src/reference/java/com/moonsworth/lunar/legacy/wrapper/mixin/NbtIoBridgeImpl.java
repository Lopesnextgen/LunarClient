package com.moonsworth.lunar.legacy.wrapper.mixin;

import com.moonsworth.lunar.bridge.NbtIoBridge;
import com.moonsworth.lunar.bridge.CompoundTagBridge;
import java.io.InputStream;
import net.minecraft.nbt.CompressedStreamTools;

public class NbtIoBridgeImpl implements NbtIoBridge {
   public NbtIoBridgeImpl() {
   }

   public CompoundTagBridge method1(InputStream input1) {
      return (CompoundTagBridge)CompressedStreamTools.readCompressed(input1);
   }
}
