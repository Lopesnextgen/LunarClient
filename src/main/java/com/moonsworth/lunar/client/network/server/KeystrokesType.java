package com.moonsworth.lunar.client.network.server;

import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public enum KeystrokesType {
   HYPIXEL("Hypixel BungeeCord", "hypixel.net", "hypixel.io"),
   TEST("Test Server");

   @Nullable
   private final String brand;
   private final String @Nullable [] addresses;

   KeystrokesType(@Nullable String var3, String @Nullable ... var4) {
      this.brand = var3;
      this.addresses = var4;
   }

   @Nullable
   @Generated
   public String getBrand() {
      return this.brand;
   }

   @Generated
   public String @Nullable [] getAddresses() {
      return this.addresses;
   }
}
