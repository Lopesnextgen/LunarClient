package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.driver.bridge.JsonSection;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class ModCategory implements Translatable {
   private static final Map<String, ModCategory> field1 = new HashMap<>();
   private static final List<ModCategory> field2 = new ArrayList<>();
   public static final ModCategory field3 = method2("new", true);
   public static final ModCategory field4 = method2("hud", true);
   public static final ModCategory field5 = method2("server", true);
   public static final ModCategory field6 = method2("mechanic", true);
   public static final ModCategory field7 = method2("dev", !LunarBuildData.field4);
   public static final ModCategory field8 = method2("none", false);
   private final String field9;

   @JsonSection("categories")
   @Override
   public String toString() {
      return this.field9;
   }

   public String getLanguagePath() {
      return "gui.components";
   }

   public static Collection<ModCategory> values() {
      return field1.values();
   }

   public static Collection<ModCategory> method1() {
      return field2;
   }

   public static ModCategory method2(String text, boolean flag) {
      if (field1.containsKey(text)) {
         return field1.get(text);
      }

      ModCategory calculator2handler2 = new ModCategory(text);
      field1.put(text, calculator2handler2);
      if (flag) {
         field2.add(calculator2handler2);
      }

      return calculator2handler2;
   }

   @Generated
   private ModCategory(String text) {
      this.field9 = text;
   }

   @Generated
   public String getName() {
      return this.field9;
   }
}
