package com.moonsworth.lunar.client.network.hostedworld;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.gui.HostWorldScreen.Type;
import com.moonsworth.lunar.client.gui.HostWorldScreen.Type2;
import com.moonsworth.lunar.client.gui.HostWorldScreen.Type4;
import lombok.Generated;

public class LocalHostedWorldSettings {
   @SerializedName("allowCheats")
   private boolean field1;
   @SerializedName("customPort")
   private boolean field2;
   @SerializedName("port")
   private int port;
   @SerializedName("gamemode")
   private Type field3;
   @SerializedName("difficulty")
   private Type2 field4;
   @SerializedName("worldPrivacy")
   private Type4 field5;
   @SerializedName("startupPrivacy")
   private Type4 field6;
   @SerializedName("autoHostOnJoin")
   private boolean field7;
   @SerializedName("maxSlots")
   private int field8;

   @Generated
   LocalHostedWorldSettings(boolean flag1, boolean flag, int value, Type type, Type2 type2, Type4 type4, Type4 type42, boolean flag2, int value2) {
      this.field1 = flag1;
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
   public static LocalHostedWorldSettings.Data method1() {
      return new LocalHostedWorldSettings.Data();
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
   public Type method4() {
      return this.field3;
   }

   @Generated
   public Type2 method5() {
      return this.field4;
   }

   @Generated
   public Type4 method6() {
      return this.field5;
   }

   @Generated
   public Type4 method7() {
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
   public void method10(boolean flag1) {
      this.field1 = flag1;
   }

   @Generated
   public void method11(boolean flag1) {
      this.field2 = flag1;
   }

   @Generated
   public void setPort(int number1) {
      this.port = number1;
   }

   @Generated
   public void method13(Type type1) {
      this.field3 = type1;
   }

   @Generated
   public void method14(Type2 type21) {
      this.field4 = type21;
   }

   @Generated
   public void method15(Type4 type41) {
      this.field5 = type41;
   }

   @Generated
   public void method16(Type4 type41) {
      this.field6 = type41;
   }

   @Generated
   public void method17(boolean flag1) {
      this.field7 = flag1;
   }

   @Generated
   public void method18(int number1) {
      this.field8 = number1;
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
      private Type field3;
      @Generated
      private Type2 field4;
      @Generated
      private Type4 field5;
      @Generated
      private Type4 field6;
      @Generated
      private boolean field7;
      @Generated
      private int field8;

      @Generated
      Data() {
      }

      @Generated
      public LocalHostedWorldSettings.Data method1(boolean flag1) {
         this.field1 = flag1;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method2(boolean flag1) {
         this.field2 = flag1;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method3(int number1) {
         this.port = number1;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method4(Type type1) {
         this.field3 = type1;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method5(Type2 type21) {
         this.field4 = type21;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method6(Type4 type41) {
         this.field5 = type41;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method7(Type4 type41) {
         this.field6 = type41;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method8(boolean flag1) {
         this.field7 = flag1;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings.Data method9(int number1) {
         this.field8 = number1;
         return this;
      }

      @Generated
      public LocalHostedWorldSettings method10() {
         return new LocalHostedWorldSettings(this.field1, this.field2, this.port, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8);
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
