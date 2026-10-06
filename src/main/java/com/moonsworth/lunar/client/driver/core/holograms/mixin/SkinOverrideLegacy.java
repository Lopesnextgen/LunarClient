package com.moonsworth.lunar.client.driver.core.holograms.mixin;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class SkinOverrideLegacy {
   @SerializedName("hash")
   private String hash;
   @SerializedName("url")
   private String url;
   @SerializedName("type")
   private String type;

   @Generated
   public String getHash() {
      return this.hash;
   }

   @Generated
   public String getUrl() {
      return this.url;
   }

   @Generated
   public String getType() {
      return this.type;
   }
}
