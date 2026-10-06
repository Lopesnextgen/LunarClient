package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.KeyBindingClashEntry;
import java.util.Set;

public interface MixinHelper_15 {
   KeyCode bridge$getKey();

   void bridge$setKey(KeyCode var1);

   boolean bridge$isKeyDown();

   String bridge$getKeyName();

   String bridge$getKeyDescription();

   String bridge$getUntranslatedKeyDescription();

   void bridge$setKeyBindState(boolean var1);

   void bridge$setKeyBindPressed(boolean var1);

   Set<KeyBindingClashEntry> bridge$getClashesWith();

   void bridge$setSiblingName(String var1);

   void bridge$setClashesWith(Set<KeyBindingClashEntry> var1);

   String bridge$getCategory();
}
