package com.moonsworth.lunar.client.coordinates;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.gui.HostWorldScreen;
import lombok.Generated;

public class Coordinates5 {
   @SerializedName("allowCheats")
   private boolean field1;
   @SerializedName("customPort")
   private boolean field2;
   @SerializedName("port")
   private int port;
   @SerializedName("gamemode")
   private HostWorldScreen.Type field3;
   @SerializedName("difficulty")
   private HostWorldScreen.Type2 field4;
   @SerializedName("worldPrivacy")
   private HostWorldScreen.Type4 field5;
   @SerializedName("startupPrivacy")
   private HostWorldScreen.Type4 field6;
   @SerializedName("autoHostOnJoin")
   private boolean field7;
   @SerializedName("maxSlots")
   private int field8;

   @Generated
   Coordinates5(
      boolean var1,
      boolean flag,
      int value,
      HostWorldScreen.Type type,
      HostWorldScreen.Type2 type2,
      HostWorldScreen.Type4 type4,
      HostWorldScreen.Type4 type42,
      boolean flag2,
      int value2
   ) {
      this.field1 = var1;
      this.field2 = flag;
      this.port = value;
      this.field3 = type;
      this.field4 = type2;
      this.field5 = type4;
      this.field6 = type42;
      this.field7 = flag2;
      this.field8 = value2;
   }

   @Generated
   public static Coordinates5.Data method1() {
      return new Coordinates5.Data();
   }

   @Generated
   public boolean method2() {
      return this.field1;
   }

   @Generated
   public boolean method3() {
      return this.field2;
   }

   @Generated
   public int getPort() {
      return this.port;
   }

   @Generated
   public HostWorldScreen.Type method4() {
      return this.field3;
   }

   @Generated
   public HostWorldScreen.Type2 method5() {
      return this.field4;
   }

   @Generated
   public HostWorldScreen.Type4 method6() {
      return this.field5;
   }

   @Generated
   public HostWorldScreen.Type4 method7() {
      return this.field6;
   }

   @Generated
   public boolean method8() {
      return this.field7;
   }

   @Generated
   public int method9() {
      return this.field8;
   }

   @Generated
   public void method10(boolean var1) {
      this.field1 = var1;
   }

   @Generated
   public void method11(boolean var1) {
      this.field2 = var1;
   }

   @Generated
   public void setPort(int var1) {
      this.port = var1;
   }

   @Generated
   public void method13(HostWorldScreen.Type var1) {
      this.field3 = var1;
   }

   @Generated
   public void method14(HostWorldScreen.Type2 var1) {
      this.field4 = var1;
   }

   @Generated
   public void method15(HostWorldScreen.Type4 var1) {
      this.field5 = var1;
   }

   @Generated
   public void method16(HostWorldScreen.Type4 var1) {
      this.field6 = var1;
   }

   @Generated
   public void method17(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   public void method18(int var1) {
      this.field8 = var1;
   }

   @Generated
   public static class Data {
      @Generated
      private boolean field1;
      @Generated
      private boolean field2;
      @Generated
      private int port;
      @Generated
      private HostWorldScreen.Type field3;
      @Generated
      private HostWorldScreen.Type2 field4;
      @Generated
      private HostWorldScreen.Type4 field5;
      @Generated
      private HostWorldScreen.Type4 field6;
      @Generated
      private boolean field7;
      @Generated
      private int field8;

      @Generated
      Data() {
      }

      @Generated
      public Coordinates5.Data method1(boolean var1) {
         this.field1 = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method2(boolean var1) {
         this.field2 = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method3(int var1) {
         this.port = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method4(HostWorldScreen.Type var1) {
         this.field3 = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method5(HostWorldScreen.Type2 var1) {
         this.field4 = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method6(HostWorldScreen.Type4 var1) {
         this.field5 = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method7(HostWorldScreen.Type4 var1) {
         this.field6 = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method8(boolean var1) {
         this.field7 = var1;
         return this;
      }

      @Generated
      public Coordinates5.Data method9(int var1) {
         this.field8 = var1;
         return this;
      }

      @Generated
      public Coordinates5 method10() {
         return new Coordinates5(this.field1, this.field2, this.port, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8);
      }

      @Generated
      @Override
      public String toString() {
         return "LocalHostedWorldSettings.LocalHostedWorldSettingsBuilder(allowCheats="
            + this.field1
            + ", customPort="
            + this.field2
            + ", port="
            + this.port
            + ", gamemode="
            + this.field3
            + ", difficulty="
            + this.field4
            + ", worldPrivacy="
            + this.field5
            + ", startupPrivacy="
            + this.field6
            + ", autoHostOnJoin="
            + this.field7
            + ", maxSlots="
            + this.field8
            + ")";
      }
   }
}
