package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;

public class PingHistory implements PingEntry<PingHistory> {
   @SerializedName("value")
   private float value;

   public void method1(float var1) {
      this.value += var1;
   }

   @Override
   public float getValue() {
      return this.value;
   }

   @Override
   public String method1() {
      return String.valueOf(this.value);
   }

   public PingHistory method3(PingHistory var1) {
      PingHistory var2 = new PingHistory();
      var2.value = this.value + var1.value;
      return var2;
   }
}
