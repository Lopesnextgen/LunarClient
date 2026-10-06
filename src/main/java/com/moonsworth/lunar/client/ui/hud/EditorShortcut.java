package com.moonsworth.lunar.client.ui.hud;

import java.util.List;
import org.jetbrains.annotations.Nullable;

public class EditorShortcut {
   private final List<String> keys;
   @Nullable
   private final String field1;
   private final String field2;

   public EditorShortcut(List<String> var1, @Nullable String var2, String var3) {
      this.keys = var1;
      this.field1 = var2;
      this.field2 = var3;
   }

   public static EditorShortcut method1(String var0, @Nullable String var1, String var2) {
      return new EditorShortcut(List.of(var0), var1, var2);
   }

   public static EditorShortcut method2(String var0, String var1, @Nullable String var2, String var3) {
      return new EditorShortcut(List.of(var0, var1), var2, var3);
   }

   public List<String> getKeys() {
      return this.keys;
   }

   @Nullable
   public String method4() {
      return this.field1;
   }

   public String method5() {
      return this.field2;
   }
}
