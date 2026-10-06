package com.moonsworth.lunar.client.driver.core.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import lombok.NonNull;
import com.moonsworth.lunar.client.driver.bridge.ButtonProvider;

public class Gui2Task implements JsonProviderLegacy, Calculator2 {
   @NonNull
   private final String field1;
   private final PhosphorIconLegacy field2;
   private final String field3;
   private String field4;
   private Boolean field5;
   private final Gui2Task.Type field6;
   private boolean field7;
   private boolean field8;
   @NonNull
   private final Runnable field9;
   private final BooleanSupplier field10;
   private final Gui2Task.Type2 field11;

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.field1);
      var1.addProperty("style", this.field6.id);
      var1.addProperty("type", this.field11.id);
      if (this.field2 != null) {
         var1.addProperty("icon", this.field2.ordinal());
      } else if (this.field3 != null) {
         var1.addProperty("icon", this.field3);
      }

      if (this.field4 != null) {
         var1.addProperty("badge", this.field4);
      }

      if (this.field5 != null) {
         var1.addProperty("defaultPinned", this.field5);
      }

      if (this.field7) {
         var1.addProperty("unread", true);
      }

      if (this.field8) {
         var1.addProperty("notification", true);
      }

      return var1;
   }

   public String getLanguagePath() {
      return "gui.components";
   }

   @Generated
   private static Boolean method2() {
      return true;
   }

   @Generated
   private static Gui2Task.Type method3() {
      return Gui2Task.Type.PRIMARY;
   }

   @Generated
   private static boolean method4() {
      return false;
   }

   @Generated
   private static boolean method5() {
      return false;
   }

   @Generated
   private static BooleanSupplier method6() {
      return () -> true;
   }

   @Generated
   private static Gui2Task.Type2 method7() {
      return Gui2Task.Type2.LUNAR;
   }

   @Generated
   Gui2Task(
      @NonNull String var1,
      PhosphorIconLegacy var2,
      String var3,
      String var4,
      Boolean var5,
      Gui2Task.Type var6,
      boolean flag,
      boolean flag2,
      @NonNull Runnable var9,
      BooleanSupplier supplier,
      Gui2Task.Type2 type2
   ) {
      if (var1 == null) {
         throw new NullPointerException("id is marked non-null but is null");
      }

      if (var9 == null) {
         throw new NullPointerException("onClick is marked non-null but is null");
      }

      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = flag;
      this.field8 = flag2;
      this.field9 = var9;
      this.field10 = supplier;
      this.field11 = type2;
   }

   @Generated
   public static Gui2Task.Data method8() {
      return new Gui2Task.Data();
   }

   @NonNull
   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public void method8(String var1) {
      this.field4 = var1;
   }

   @Generated
   public void method9(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   public void method10(boolean var1) {
      this.field8 = var1;
   }

   @NonNull
   @Generated
   public Runnable method11() {
      return this.field9;
   }

   @Generated
   public BooleanSupplier method12() {
      return this.field10;
   }

   @Generated
   public Gui2Task.Type2 method13() {
      return this.field11;
   }

   @Generated
   public static class Data {
      @Generated
      private String id;
      @Generated
      private PhosphorIconLegacy field1;
      @Generated
      private String iconPath;
      @Generated
      private String field2;
      @Generated
      private boolean field3;
      @Generated
      private Boolean field4;
      @Generated
      private boolean style$set;
      @Generated
      private Gui2Task.Type field5;
      @Generated
      private boolean field6;
      @Generated
      private boolean field7;
      @Generated
      private boolean field8;
      @Generated
      private boolean field9;
      @Generated
      private Runnable field10;
      @Generated
      private boolean field11;
      @Generated
      private BooleanSupplier field12;
      @Generated
      private boolean field13;
      @Generated
      private Gui2Task.Type2 field14;

      @Generated
      Data() {
      }

      @Generated
      public Gui2Task.Data method1(@NonNull String var1) {
         if (var1 == null) {
            throw new NullPointerException("id is marked non-null but is null");
         }

         this.id = var1;
         return this;
      }

      @Generated
      public Gui2Task.Data method2(PhosphorIconLegacy var1) {
         this.field1 = var1;
         return this;
      }

      @Generated
      public Gui2Task.Data method3(String var1) {
         this.iconPath = var1;
         return this;
      }

      @Generated
      public Gui2Task.Data method4(String var1) {
         this.field2 = var1;
         return this;
      }

      @Generated
      public Gui2Task.Data method5(Boolean var1) {
         this.field4 = var1;
         this.field3 = true;
         return this;
      }

      @Generated
      public Gui2Task.Data method6(Gui2Task.Type var1) {
         this.field5 = var1;
         this.style$set = true;
         return this;
      }

      @Generated
      public Gui2Task.Data method7(boolean var1) {
         this.field7 = var1;
         this.field6 = true;
         return this;
      }

      @Generated
      public Gui2Task.Data method8(boolean var1) {
         this.field9 = var1;
         this.field8 = true;
         return this;
      }

      @Generated
      public Gui2Task.Data method9(@NonNull Runnable var1) {
         if (var1 == null) {
            throw new NullPointerException("onClick is marked non-null but is null");
         }

         this.field10 = var1;
         return this;
      }

      @Generated
      public Gui2Task.Data method10(BooleanSupplier var1) {
         this.field12 = var1;
         this.field11 = true;
         return this;
      }

      @Generated
      public Gui2Task.Data method11(Gui2Task.Type2 var1) {
         this.field14 = var1;
         this.field13 = true;
         return this;
      }

      @Generated
      public Gui2Task method12() {
         Boolean var1 = this.field4;
         if (!this.field3) {
            var1 = Gui2Task.method2();
         }

         Gui2Task.Type var2 = this.field5;
         if (!this.style$set) {
            var2 = Gui2Task.method3();
         }

         boolean var3 = this.field7;
         if (!this.field6) {
            var3 = Gui2Task.method4();
         }

         boolean var4 = this.field9;
         if (!this.field8) {
            var4 = Gui2Task.method5();
         }

         BooleanSupplier var5 = this.field12;
         if (!this.field11) {
            var5 = Gui2Task.method6();
         }

         Gui2Task.Type2 var6 = this.field14;
         if (!this.field13) {
            var6 = Gui2Task.method7();
         }

         return new Gui2Task(this.id, this.field1, this.iconPath, this.field2, var1, var2, var3, var4, this.field10, var5, var6);
      }

      @Generated
      @Override
      public String toString() {
         return "ButtonProvider.ButtonProviderBuilder(id="
            + this.id
            + ", icon="
            + this.field1
            + ", iconPath="
            + this.iconPath
            + ", badge="
            + this.field2
            + ", defaultPinned$value="
            + this.field4
            + ", style$value="
            + this.field5
            + ", unread$value="
            + this.field7
            + ", notification$value="
            + this.field9
            + ", onClick="
            + this.field10
            + ", visible$value="
            + this.field12
            + ", type$value="
            + this.field14
            + ")";
      }
   }

   public enum Type {
      PRIMARY("primary"),
      EMPHASIS("emphasis"),
      STORE("store");

      final String id;

      @Generated
      Type(String var3) {
         this.id = var3;
      }
   }

   public enum Type2 {
      LUNAR("lunar"),
      VANILLA("vanilla"),
      EXTERNAL("external"),
      FABRIC("fabric"),
      FORGE("forge"),
      NEOFORGE("neoforge"),
      QUILT("quilt");

      final String id;

      @Generated
      Type2(String var3) {
         this.id = var3;
      }
   }
}
