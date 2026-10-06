package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.NativeImageBridge;
import com.moonsworth.lunar.ichor.Annotation2;
import java.awt.image.BufferedImage;

@Annotation2(min = 6)
public interface BufferedImageConverterBridge {
   BufferedImage method1(NativeImageBridge var1);

   NativeImageBridge method2(BufferedImage var1);

   NativeImageBridge method3(BufferedImage var1);

   NativeImageBridge method4(NativeImageBridge var1, int var2);
}
