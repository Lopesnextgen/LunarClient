package com.moonsworth.lunar.bridge.minecraft;

public class KeyBindingEntry {
   private final String field1;
   private final KeyBindingSource field2;
   private final Object field3;

   public KeyBindingEntry(String text, KeyBindingSource keyBindingSource, Object object) {
      this.field1 = text;
      this.field2 = keyBindingSource;
      this.field3 = object;
   }

   public String id() {
      return this.field1;
   }

   public KeyBindingSource method1() {
      return this.field2;
   }

   public Object method2() {
      return this.field3;
   }
}
