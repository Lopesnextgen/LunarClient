package com.moonsworth.lunar.bridge;

import org.jetbrains.annotations.Nullable;

public interface NBTTagListBridge {
   @Nullable
   String bridge$getString(int number1);

   CompoundTagBridge bridge$getCompoundAt(int number1);

   int bridge$size();
}
