package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class PingEntryRegistry implements PingEntry<PingEntryRegistry> {
   @SerializedName("count")
   private int count;

   public void increment() {
      this.count++;
   }

   @Override
   public float getValue() {
      return this.count;
   }

   @Override
   public String method1() {
      return String.valueOf(this.count);
   }

   public PingEntryRegistry method2(PingEntryRegistry handler) {
      PingEntryRegistry var2 = new PingEntryRegistry();
      var2.count = this.count + handler.count;
      return var2;
   }

   @Generated
   public int getCount() {
      return this.count;
   }
}
