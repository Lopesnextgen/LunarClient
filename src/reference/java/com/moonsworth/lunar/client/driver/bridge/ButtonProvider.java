package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.driver.PhosphorIcon;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import lombok.NonNull;

public class ButtonProvider implements JsonProvider, Translatable {
   @NonNull
   private final String field1;
   private final PhosphorIcon field2;
   private final String field3;
   private String field4;
   private Boolean field5;
   private final ButtonProvider.Type field6;
   private boolean field7;
   private boolean field8;
   @NonNull
   private final Runnable field9;
   private final BooleanSupplier field10;
   private final ButtonProvider.ModLoader field11;

   @Override
   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.field1);
      json1.addProperty("style", this.field6.id);
      json1.addProperty("type", this.field11.id);
      if (this.field2 != null) {
         json1.addProperty("icon", this.field2.ordinal());
      } else if (this.field3 != null) {
         json1.addProperty("icon", this.field3);
      }

      if (this.field4 != null) {
         json1.addProperty("badge", this.field4);
      }

      if (this.field5 != null) {
         json1.addProperty("defaultPinned", this.field5);
      }

      if (this.field7) {
         json1.addProperty("unread", true);
      }

      if (this.field8) {
         json1.addProperty("notification", true);
      }

      return json1;
   }

   public String getLanguagePath() {
      return "gui.components";
   }

   @Generated
   private static Boolean method2() {
      return true;
   }

   @Generated
   private static ButtonProvider.Type method3() {
      return ButtonProvider.Type.PRIMARY;
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
   private static ButtonProvider.ModLoader method7() {
      return ButtonProvider.ModLoader.LUNAR;
   }

   @Generated
   ButtonProvider(
      @NonNull String text1,
      PhosphorIcon phosphorIcon,
      String text3,
      String text,
      Boolean booleanValue,
      ButtonProvider.Type type,
      boolean flag,
      boolean flag2,
      @NonNull Runnable runnable9,
      BooleanSupplier booleansupplier10,
      ButtonProvider.ModLoader modLoader
   ) {
      if (text1 == null) {
         throw new NullPointerException("id is marked non-null but is null");
      }

      if (runnable9 == null) {
         throw new NullPointerException("onClick is marked non-null but is null");
      }

      this.field1 = text1;
      this.field2 = phosphorIcon;
      this.field3 = text3;
      this.field4 = text;
      this.field5 = booleanValue;
      this.field6 = type;
      this.field7 = flag;
      this.field8 = flag2;
      this.field9 = runnable9;
      this.field10 = booleansupplier10;
      this.field11 = modLoader;
   }

   @Generated
   public static ButtonProvider.Data method8() {
      return new ButtonProvider.Data();
   }

   @NonNull
   @Generated
   public String getId() {
      return this.field1;
   }

   @Generated
   public void method8(String text1) {
      this.field4 = text1;
   }

   @Generated
   public void method9(boolean flag1) {
      this.field7 = flag1;
   }

   @Generated
   public void method10(boolean flag1) {
      this.field8 = flag1;
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
   public ButtonProvider.ModLoader method13() {
      return this.field11;
   }

   @Generated
   public static class Data {
      @Generated
      private String id;
      @Generated
      private PhosphorIcon field1;
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
      private ButtonProvider.Type field5;
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
      private ButtonProvider.ModLoader field14;

      @Generated
      Data() {
      }

      @Generated
      public ButtonProvider.Data method1(@NonNull String text1) {
         if (text1 == null) {
            throw new NullPointerException("id is marked non-null but is null");
         }

         this.id = text1;
         return this;
      }

      @Generated
      public ButtonProvider.Data method2(PhosphorIcon phosphorIcon) {
         this.field1 = phosphorIcon;
         return this;
      }

      @Generated
      public ButtonProvider.Data method3(String text1) {
         this.iconPath = text1;
         return this;
      }

      @Generated
      public ButtonProvider.Data method4(String text1) {
         this.field2 = text1;
         return this;
      }

      @Generated
      public ButtonProvider.Data method5(Boolean flag1) {
         this.field4 = flag1;
         this.field3 = true;
         return this;
      }

      @Generated
      public ButtonProvider.Data method6(ButtonProvider.Type type) {
         this.field5 = type;
         this.style$set = true;
         return this;
      }

      @Generated
      public ButtonProvider.Data method7(boolean flag1) {
         this.field7 = flag1;
         this.field6 = true;
         return this;
      }

      @Generated
      public ButtonProvider.Data method8(boolean flag1) {
         this.field9 = flag1;
         this.field8 = true;
         return this;
      }

      @Generated
      public ButtonProvider.Data method9(@NonNull Runnable runnable1) {
         if (runnable1 == null) {
            throw new NullPointerException("onClick is marked non-null but is null");
         }

         this.field10 = runnable1;
         return this;
      }

      @Generated
      public ButtonProvider.Data method10(BooleanSupplier booleansupplier1) {
         this.field12 = booleansupplier1;
         this.field11 = true;
         return this;
      }

      @Generated
      public ButtonProvider.Data method11(ButtonProvider.ModLoader modLoader) {
         this.field14 = modLoader;
         this.field13 = true;
         return this;
      }

      @Generated
      public ButtonProvider method12() {
         Boolean flag1 = this.field4;
         if (!this.field3) {
            flag1 = ButtonProvider.method2();
         }

         ButtonProvider.Type type2 = this.field5;
         if (!this.style$set) {
            type2 = ButtonProvider.method3();
         }

         boolean flag3 = this.field7;
         if (!this.field6) {
            flag3 = ButtonProvider.method4();
         }

         boolean flag4 = this.field9;
         if (!this.field8) {
            flag4 = ButtonProvider.method5();
         }

         BooleanSupplier booleansupplier5 = this.field12;
         if (!this.field11) {
            booleansupplier5 = ButtonProvider.method6();
         }

         ButtonProvider.ModLoader type26 = this.field14;
         if (!this.field13) {
            type26 = ButtonProvider.method7();
         }

         return new ButtonProvider(this.id, this.field1, this.iconPath, this.field2, flag1, type2, flag3, flag4, this.field10, booleansupplier5, type26);
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
      Type(String text3) {
         this.id = text3;
      }
   }

   public enum ModLoader {
      LUNAR("lunar"),
      VANILLA("vanilla"),
      EXTERNAL("external"),
      FABRIC("fabric"),
      FORGE("forge"),
      NEOFORGE("neoforge"),
      QUILT("quilt");

      final String id;

      @Generated
      ModLoader(String text3) {
         this.id = text3;
      }
   }
}
