package com.moonsworth.lunar.client.framework.feature.waypoints;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.bridge.DriverDataProvider;
import com.moonsworth.lunar.client.util.text.DateUtils;
import java.util.UUID;
import javax.annotation.Nullable;
import lombok.Generated;
import lombok.NonNull;

public class GuiHandler implements DriverDataProvider {
   @NonNull
   private UUID id;
   @NonNull
   private String name;
   @Nullable
   private String server;
   private boolean visible;
   private int field1;
   private boolean isDefault;
   private long field2;
   private boolean field3;
   private GuiLoader field4;
   private boolean field5;

   public JsonElement method128() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.id.toString());
      json1.addProperty("name", this.name);
      if (this.server != null) {
         json1.addProperty("server", this.server);
      }

      json1.addProperty("visible", this.visible);
      json1.addProperty("sortIndex", this.field1);
      json1.addProperty("isDefault", this.isDefault);
      json1.addProperty("renderOptionsOverrideMembers", this.method24());
      json1.add("renderConfig", this.field4.method128());
      json1.addProperty("addedAt", DateUtils.method4(this.field2));
      return json1;
   }

   public void method2(JsonObject json1) {
      json1.addProperty("id", this.getId().toString());
      json1.addProperty("name", this.getName());
      json1.addProperty("visible", this.isVisible());
      json1.addProperty("sortIndex", this.method21());
      json1.addProperty("isDefault", this.isDefault());
      json1.addProperty("addedAtMs", this.method22());
      json1.addProperty("renderOptionsOverrideMembers", this.method24());
      if (this.getServer() != null) {
         json1.addProperty("server", this.getServer());
      }

      GuiLoader guiloader2 = this.method23();
      JsonObject json3 = new JsonObject();
      guiloader2.method3(json3);
      json1.add("renderConfig", json3);
   }

   public static GuiHandler method3(String text, JsonObject json1) {
      GuiLoader guiloader2 = new GuiLoader();
      guiloader2.load(json1.get("renderConfig").getAsJsonObject());
      GuiHandler guihandler3 = method13()
         .method1(UUID.fromString(text))
         .method2(json1.get("name").getAsString())
         .method4(json1.get("visible").getAsBoolean())
         .method5(json1.get("sortIndex").getAsInt())
         .method6(json1.get("isDefault").getAsBoolean())
         .method7(json1.get("addedAtMs").getAsLong())
         .method10(json1.get("renderOptionsOverrideMembers").getAsBoolean())
         .method9(guiloader2)
         .method11();
      if (json1.has("server")) {
         guihandler3.setServer(json1.get("server").getAsString());
      }

      return guihandler3;
   }

   @Generated
   private static UUID method4() {
      return UUID.randomUUID();
   }

   @Generated
   private static String method5() {
      return "";
   }

   @Generated
   private static boolean method6() {
      return true;
   }

   @Generated
   private static int method7() {
      return -1;
   }

   @Generated
   private static boolean method8() {
      return false;
   }

   @Generated
   private static long method9() {
      return System.currentTimeMillis();
   }

   @Generated
   private static boolean method10() {
      return false;
   }

   @Generated
   private static GuiLoader method11() {
      return GuiLoader.field1;
   }

   @Generated
   private static boolean method12() {
      return false;
   }

   @Generated
   public static GuiHandler.Data method13() {
      return new GuiHandler.Data();
   }

   @Generated
   public void method14(@NonNull UUID uuid1) {
      if (uuid1 == null) {
         throw new NullPointerException("id is marked non-null but is null");
      }

      this.id = uuid1;
   }

   @Generated
   public void setName(@NonNull String text1) {
      if (text1 == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      this.name = text1;
   }

   @Generated
   public void setServer(@Nullable String text1) {
      this.server = text1;
   }

   @Generated
   public void setVisible(boolean flag1) {
      this.visible = flag1;
   }

   @Generated
   public void method16(int number1) {
      this.field1 = number1;
   }

   @Generated
   public void setDefault(boolean flag1) {
      this.isDefault = flag1;
   }

   @Generated
   public void method17(long number1) {
      this.field2 = number1;
   }

   @Generated
   public void method18(boolean flag1) {
      this.field3 = flag1;
   }

   @Generated
   public void method19(GuiLoader guiloader1) {
      this.field4 = guiloader1;
   }

   @Generated
   public void method20(boolean flag1) {
      this.field5 = flag1;
   }

   @NonNull
   @Generated
   public UUID getId() {
      return this.id;
   }

   @NonNull
   @Generated
   public String getName() {
      return this.name;
   }

   @Nullable
   @Generated
   public String getServer() {
      return this.server;
   }

   @Generated
   public boolean isVisible() {
      return this.visible;
   }

   @Generated
   public int method21() {
      return this.field1;
   }

   @Generated
   public boolean isDefault() {
      return this.isDefault;
   }

   @Generated
   public long method22() {
      return this.field2;
   }

   @Generated
   public boolean isOrdered() {
      return this.field3;
   }

   @Generated
   public GuiLoader method23() {
      return this.field4;
   }

   @Generated
   public boolean method24() {
      return this.field5;
   }

   @Generated
   @Override
   public String toString() {
      return "WaypointGroup(id="
         + this.getId()
         + ", name="
         + this.getName()
         + ", server="
         + this.getServer()
         + ", visible="
         + this.isVisible()
         + ", sortIndex="
         + this.method21()
         + ", isDefault="
         + this.isDefault()
         + ", addedAtMs="
         + this.method22()
         + ", isOrdered="
         + this.isOrdered()
         + ", renderConfig="
         + this.method23()
         + ", renderOptionsOverrideMembers="
         + this.method24()
         + ")";
   }

   @Generated
   public GuiHandler(
      @NonNull UUID uuid1,
      @NonNull String text2,
      @Nullable String text3,
      boolean flag,
      int value,
      boolean flag2,
      long value2,
      boolean flag3,
      GuiLoader guiLoader,
      boolean flag4
   ) {
      if (uuid1 == null) {
         throw new NullPointerException("id is marked non-null but is null");
      }

      if (text2 == null) {
         throw new NullPointerException("name is marked non-null but is null");
      }

      this.id = uuid1;
      this.name = text2;
      this.server = text3;
      this.visible = flag;
      this.field1 = value;
      this.isDefault = flag2;
      this.field2 = value2;
      this.field3 = flag3;
      this.field4 = guiLoader;
      this.field5 = flag4;
   }

   @Generated
   public GuiHandler() {
      this.id = method4();
      this.name = method5();
      this.visible = method6();
      this.field1 = method7();
      this.isDefault = method8();
      this.field2 = method9();
      this.field3 = method10();
      this.field4 = method11();
      this.field5 = method12();
   }

   @Generated
   public static class Data {
      @Generated
      private boolean field1;
      @Generated
      private UUID field2;
      @Generated
      private boolean field3;
      @Generated
      private String field4;
      @Generated
      private String server;
      @Generated
      private boolean field5;
      @Generated
      private boolean field6;
      @Generated
      private boolean field7;
      @Generated
      private int field8;
      @Generated
      private boolean field9;
      @Generated
      private boolean field10;
      @Generated
      private boolean field11;
      @Generated
      private long field12;
      @Generated
      private boolean field13;
      @Generated
      private boolean field14;
      @Generated
      private boolean field15;
      @Generated
      private GuiLoader field16;
      @Generated
      private boolean field17;
      @Generated
      private boolean field18;

      @Generated
      Data() {
      }

      @Generated
      public GuiHandler.Data method1(@NonNull UUID uuid1) {
         if (uuid1 == null) {
            throw new NullPointerException("id is marked non-null but is null");
         }

         this.field2 = uuid1;
         this.field1 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method2(@NonNull String text1) {
         if (text1 == null) {
            throw new NullPointerException("name is marked non-null but is null");
         }

         this.field4 = text1;
         this.field3 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method3(@Nullable String text1) {
         this.server = text1;
         return this;
      }

      @Generated
      public GuiHandler.Data method4(boolean flag1) {
         this.field6 = flag1;
         this.field5 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method5(int number1) {
         this.field8 = number1;
         this.field7 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method6(boolean flag1) {
         this.field10 = flag1;
         this.field9 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method7(long number1) {
         this.field12 = number1;
         this.field11 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method8(boolean flag1) {
         this.field14 = flag1;
         this.field13 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method9(GuiLoader guiloader1) {
         this.field16 = guiloader1;
         this.field15 = true;
         return this;
      }

      @Generated
      public GuiHandler.Data method10(boolean flag1) {
         this.field18 = flag1;
         this.field17 = true;
         return this;
      }

      @Generated
      public GuiHandler method11() {
         UUID uuid1 = this.field2;
         if (!this.field1) {
            uuid1 = GuiHandler.method4();
         }

         String text2 = this.field4;
         if (!this.field3) {
            text2 = GuiHandler.method5();
         }

         boolean flag3 = this.field6;
         if (!this.field5) {
            flag3 = GuiHandler.method6();
         }

         int number4 = this.field8;
         if (!this.field7) {
            number4 = GuiHandler.method7();
         }

         boolean flag5 = this.field10;
         if (!this.field9) {
            flag5 = GuiHandler.method8();
         }

         long number6 = this.field12;
         if (!this.field11) {
            number6 = GuiHandler.method9();
         }

         boolean flag8 = this.field14;
         if (!this.field13) {
            flag8 = GuiHandler.method10();
         }

         GuiLoader guiloader9 = this.field16;
         if (!this.field15) {
            guiloader9 = GuiHandler.method11();
         }

         boolean flag10 = this.field18;
         if (!this.field17) {
            flag10 = GuiHandler.method12();
         }

         return new GuiHandler(uuid1, text2, this.server, flag3, number4, flag5, number6, flag8, guiloader9, flag10);
      }

      @Generated
      @Override
      public String toString() {
         return "WaypointGroup.WaypointGroupBuilder(id$value="
            + this.field2
            + ", name$value="
            + this.field4
            + ", server="
            + this.server
            + ", visible$value="
            + this.field6
            + ", sortIndex$value="
            + this.field8
            + ", isDefault$value="
            + this.field10
            + ", addedAtMs$value="
            + this.field12
            + ", isOrdered$value="
            + this.field14
            + ", renderConfig$value="
            + this.field16
            + ", renderOptionsOverrideMembers$value="
            + this.field18
            + ")";
      }
   }
}
