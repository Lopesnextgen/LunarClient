package com.moonsworth.lunar.bridge;

import java.io.File;

@com.moonsworth.lunar.ichor.Annotation2(min = 6)
public interface NativeImageBridge {
   int bridge$getWidth();

   int bridge$getHeight();

   void bridge$checkAllocated();

   long bridge$getPixels();

   long bridge$size();

   void bridge$close();

   void bridge$writeToFile(File var1);

   Bridge$Type bridge$getFormat();
}
