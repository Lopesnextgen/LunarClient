package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;
import lombok.Generated;

public class ServerAddressBook {
   @SerializedName("melee_accuracy")
   private PingEntryStore field1 = new PingEntryStore();
   @SerializedName("hits_taken")
   private PingEntryRegistry field2 = new PingEntryRegistry();
   @SerializedName("longest_combo")
   private PingStatistics field3 = new PingStatistics();
   @SerializedName("wtap_accuracy")
   private PingEntryStore field4 = new PingEntryStore();
   @SerializedName("lost_health")
   private PingHistory field5 = new PingHistory();
   @SerializedName("recovered_health")
   private PingHistory field6 = new PingHistory();
   @SerializedName("gapples_used")
   private PingEntryRegistry field7 = new PingEntryRegistry();
   @SerializedName("potions_used")
   private PingEntryRegistry field8 = new PingEntryRegistry();
   @SerializedName("hunger_replenished")
   private PingHistory field9 = new PingHistory();
   @SerializedName("bow_accuracy")
   private PingEntryStore field10 = new PingEntryStore();
   @SerializedName("rod_accuracy")
   private PingEntryStore field11 = new PingEntryStore();
   @SerializedName("eggs_and_snowballs_used")
   private PingEntryRegistry field12 = new PingEntryRegistry();
   @SerializedName("pearls_used")
   private PingEntryRegistry field13 = new PingEntryRegistry();
   @SerializedName("total_pearl_distance")
   private PingHistory field14 = new PingHistory();

   public ServerAddressBook method1(@Nullable ServerAddressBook var1) {
      if (var1 == null) {
         return this;
      }

      ServerAddressBook var2 = new ServerAddressBook();
      var2.field1 = this.field1.method4(var1.field1);
      var2.field2 = this.field2.method2(var1.field2);
      var2.field3 = this.field3.method3(var1.field3);
      var2.field4 = this.field4.method4(var1.field4);
      var2.field5 = this.field5.method3(var1.field5);
      var2.field6 = this.field6.method3(var1.field6);
      var2.field7 = this.field7.method2(var1.field7);
      var2.field8 = this.field8.method2(var1.field8);
      var2.field9 = this.field9.method3(var1.field9);
      var2.field10 = this.field10.method4(var1.field10);
      var2.field11 = this.field11.method4(var1.field11);
      var2.field12 = this.field12.method2(var1.field12);
      var2.field13 = this.field13.method2(var1.field13);
      var2.field14 = this.field14.method3(var1.field14);
      return var2;
   }

   @Generated
   public PingEntryStore method2() {
      return this.field1;
   }

   @Generated
   public PingEntryRegistry method3() {
      return this.field2;
   }

   @Generated
   public PingStatistics method4() {
      return this.field3;
   }

   @Generated
   public PingEntryStore method5() {
      return this.field4;
   }

   @Generated
   public PingHistory method6() {
      return this.field5;
   }

   @Generated
   public PingHistory method7() {
      return this.field6;
   }

   @Generated
   public PingEntryRegistry method8() {
      return this.field7;
   }

   @Generated
   public PingEntryRegistry method9() {
      return this.field8;
   }

   @Generated
   public PingHistory method10() {
      return this.field9;
   }

   @Generated
   public PingEntryStore method11() {
      return this.field10;
   }

   @Generated
   public PingEntryStore method12() {
      return this.field11;
   }

   @Generated
   public PingEntryRegistry method13() {
      return this.field12;
   }

   @Generated
   public PingEntryRegistry method14() {
      return this.field13;
   }

   @Generated
   public PingHistory method15() {
      return this.field14;
   }
}
