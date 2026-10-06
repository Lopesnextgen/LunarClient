package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.minecraft.KeyBindingEntry;
import java.util.Set;

public interface KeyBindingBridge {
   KeyCode bridge$getKey();

   void bridge$setKey(KeyCode bridgetype_81);

   boolean bridge$isKeyDown();

   String bridge$getKeyName();

   String bridge$getKeyDescription();

   String bridge$getUntranslatedKeyDescription();

   void bridge$setKeyBindState(boolean flag1);

   void bridge$setKeyBindPressed(boolean flag1);

   Set<KeyBindingEntry> bridge$getClashesWith();

   void bridge$setSiblingName(String text1);

   void bridge$setClashesWith(Set<KeyBindingEntry> set1);

   String bridge$getCategory();
}
