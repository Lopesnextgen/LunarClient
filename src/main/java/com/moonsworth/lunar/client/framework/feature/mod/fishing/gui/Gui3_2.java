package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import java.util.List;
import javax.annotation.Nullable;

public class Gui3_2 {
   private final String field1;
   private final String field2;
   private final double cost;
   private final Gui3$Type field3;
   @Nullable
   private final String field4;
   @Nullable
   private final String field5;
   @Nullable
   private final Gui_3 field6;
   private final List<Gui5> field7;

   public Gui3_2(String text, String text2, double value, Gui3$Type type, @Nullable String var6, @Nullable String var7, @Nullable Gui_3 var8, List<Gui5> list) {
      this.field1 = text;
      this.field2 = text2;
      this.cost = value;
      this.field3 = type;
      this.field4 = var6;
      this.field5 = var7;
      this.field6 = var8;
      this.field7 = list;
   }

   public String method1() {
      return this.field1;
   }

   public String method2() {
      return this.field2;
   }

   public double getCost() {
      return this.cost;
   }

   public Gui3$Type method4() {
      return this.field3;
   }

   @Nullable
   public String displayName() {
      return this.field4;
   }

   @Nullable
   public String method5() {
      return this.field5;
   }

   @Nullable
   public Gui_3 method6() {
      return this.field6;
   }

   public List<Gui5> method7() {
      return this.field7;
   }
}
