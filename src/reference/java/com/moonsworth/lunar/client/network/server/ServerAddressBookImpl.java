package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import lombok.Generated;

public class ServerAddressBookImpl extends ServerAddressBook {
   @SerializedName("date")
   private LocalDate field15 = LocalDate.now();

   @Generated
   public LocalDate method1() {
      return this.field15;
   }

   @Generated
   public void method2(LocalDate localDate) {
      this.field15 = localDate;
   }
}
