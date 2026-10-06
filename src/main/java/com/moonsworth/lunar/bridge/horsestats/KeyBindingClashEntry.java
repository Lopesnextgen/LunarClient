package com.moonsworth.lunar.bridge.horsestats;

public class KeyBindingClashEntry {
   private final String field1;
   private final KeyBindingOrigin field2;
   private final Object field3;

   public KeyBindingClashEntry(String text, KeyBindingOrigin keyBindingOrigin, Object object) {
      this.field1 = text;
      this.field2 = keyBindingOrigin;
      this.field3 = object;
   }

   public String id() {
      return this.field1;
   }

   public KeyBindingOrigin method1() {
      return this.field2;
   }

   public Object method2() {
      return this.field3;
   }
}
