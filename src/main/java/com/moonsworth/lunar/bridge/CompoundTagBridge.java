package com.moonsworth.lunar.bridge;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface CompoundTagBridge {
   long bridge$getLong(String text1);

   byte bridge$getByte(String text1);

   byte[] bridge$getByteArray(String text1);

   int bridge$getInteger(String text1);

   String bridge$getString(String text1);

   float bridge$getFloat(String text1);

   double bridge$getDouble(String text1);

   short bridge$getShort(String text1);

   boolean bridge$getBoolean(String text1);

   NBTTagListBridge bridge$getList(String text1, int number2);

   CompoundTagBridge bridge$getCompoundTag(String text1);

   void bridge$putString(@NotNull String text1, String text2);

   void bridge$putCompound(String text1, CompoundTagBridge bridge_572);

   boolean bridge$contains(String text1, int number2);

   boolean bridge$isEmpty();

   Set<String> bridge$getAllKeys();
}
