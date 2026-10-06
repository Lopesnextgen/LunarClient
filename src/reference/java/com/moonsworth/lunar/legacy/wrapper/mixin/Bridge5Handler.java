package com.moonsworth.lunar.legacy.wrapper.mixin;

import com.moonsworth.lunar.bridge.Bridge5_2;
import com.moonsworth.lunar.bridge.Bridge_57;
import java.io.InputStream;
import net.minecraft.nbt.CompressedStreamTools;

public class Bridge5Handler implements Bridge5_2 {
   public Bridge_57 method1(InputStream var1) {
      return (Bridge_57)CompressedStreamTools.readCompressed(var1);
   }
}
