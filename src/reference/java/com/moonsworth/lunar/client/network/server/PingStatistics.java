package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class PingStatistics implements PingEntry<PingStatistics> {
   @SerializedName("record")
   private int field1;

   public void method1(int var1) {
      this.field1 = Math.max(this.field1, var1);
   }

   @Override
   public float getValue() {
      return this.field1;
   }

   @Override
   public String method1() {
      return String.valueOf(this.field1);
   }

   public PingStatistics method3(PingStatistics var1) {
      PingStatistics var2 = new PingStatistics();
      var2.field1 = Math.max(this.field1, var1.field1);
      return var2;
   }

   @Generated
   public int method4() {
      return this.field1;
   }
}
