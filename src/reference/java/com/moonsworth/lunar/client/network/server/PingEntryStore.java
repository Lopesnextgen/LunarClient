package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class PingEntryStore implements PingEntry<PingEntryStore> {
   @SerializedName("successes")
   private int field1;
   @SerializedName("failures")
   private int field2;

   public void method2() {
      this.field1++;
   }

   public void method3() {
      this.field2++;
   }

   @Override
   public float getValue() {
      return this.field2 + this.field1 == 0 ? 0.0F : (float)this.field1 / (this.field2 + this.field1);
   }

   @Override
   public String method1() {
      return (int)(this.getValue() * 100.0F) + "%";
   }

   public PingEntryStore method4(PingEntryStore var1) {
      PingEntryStore var2 = new PingEntryStore();
      var2.field1 = this.field1 + var1.field1;
      var2.field2 = this.field2 + var1.field2;
      return var2;
   }

   @Generated
   public int method5() {
      return this.field1;
   }

   @Generated
   public int method6() {
      return this.field2;
   }
}
