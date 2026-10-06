package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.driver.core.gui.Annotation;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class Calculator2Handler implements Calculator2 {
   private static final Map<String, Calculator2Handler> field1 = new HashMap<>();
   private static final List<Calculator2Handler> field2 = new ArrayList<>();
   public static final Calculator2Handler field3 = method2("new", true);
   public static final Calculator2Handler field4 = method2("hud", true);
   public static final Calculator2Handler field5 = method2("server", true);
   public static final Calculator2Handler field6 = method2("mechanic", true);
   public static final Calculator2Handler field7 = method2("dev", !LunarBuildData.field4);
   public static final Calculator2Handler field8 = method2("none", false);
   private final String field9;

   @Annotation("categories")
   @Override
   public String toString() {
      return this.field9;
   }

   public String getLanguagePath() {
      return "gui.components";
   }

   public static Collection<Calculator2Handler> values() {
      return field1.values();
   }

   public static Collection<Calculator2Handler> method1() {
      return field2;
   }

   public static Calculator2Handler method2(String text, boolean var1) {
      if (field1.containsKey(text)) {
         return field1.get(text);
      }

      Calculator2Handler var2 = new Calculator2Handler(text);
      field1.put(text, var2);
      if (var1) {
         field2.add(var2);
      }

      return var2;
   }

   @Generated
   private Calculator2Handler(String var1) {
      this.field9 = var1;
   }

   @Generated
   public String getName() {
      return this.field9;
   }
}
