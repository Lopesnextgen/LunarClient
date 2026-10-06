package com.moonsworth.lunar.bridge.minecraft;

import com.moonsworth.lunar.bridge.NativeImageBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.image.BufferedImage;

@VersionGate(min = 6)
public interface ImageConverterBridge {
   BufferedImage method1(NativeImageBridge bridge_101);

   NativeImageBridge method2(BufferedImage bufferedimage1);

   NativeImageBridge method3(BufferedImage bufferedimage1);

   NativeImageBridge method4(NativeImageBridge bridge_101, int number2);
}
