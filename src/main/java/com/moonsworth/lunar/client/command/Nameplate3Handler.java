package com.moonsworth.lunar.client.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nameplate3Handler implements Nameplate3_2 {
   private final String field1;
   private final int field2;
   private final List<String> field3;

   public Nameplate3Handler(String var1) {
      this(var1, 0, new ArrayList<>());
   }

   private Nameplate3Handler(String var1, int value, List<String> list) {
      this.field1 = var1;
      this.field2 = value;
      this.field3 = list;
   }

   @Override
   public String method1() {
      return this.field2 <= this.field1.length() ? this.field1.substring(this.field2) : "";
   }

   @Override
   public int getStart() {
      return this.field2;
   }

   @Override
   public Nameplate3_2 method2(String var1) {
      if (var1.equals(this.method1())) {
         return this;
      }

      this.field3.add(var1);
      return this;
   }

   @Override
   public Nameplate3_2 method3(int var1) {
      return new Nameplate3Handler(this.field1, var1, this.field3);
   }

   public List<String> method4() {
      return Collections.unmodifiableList(this.field3);
   }
}
