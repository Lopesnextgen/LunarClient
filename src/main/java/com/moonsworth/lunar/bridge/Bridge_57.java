package com.moonsworth.lunar.bridge;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface Bridge_57 {
   long bridge$getLong(String var1);

   byte bridge$getByte(String var1);

   byte[] bridge$getByteArray(String var1);

   int bridge$getInteger(String var1);

   String bridge$getString(String var1);

   float bridge$getFloat(String var1);

   double bridge$getDouble(String var1);

   short bridge$getShort(String var1);

   boolean bridge$getBoolean(String var1);

   Bridge3_6 bridge$getList(String var1, int var2);

   Bridge_57 bridge$getCompoundTag(String var1);

   void bridge$putString(@NotNull String var1, String var2);

   void bridge$putCompound(String var1, Bridge_57 var2);

   boolean bridge$contains(String var1, int var2);

   boolean bridge$isEmpty();

   Set<String> bridge$getAllKeys();
}
