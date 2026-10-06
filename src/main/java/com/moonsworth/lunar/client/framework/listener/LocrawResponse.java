package com.moonsworth.lunar.client.framework.listener;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;

public class LocrawResponse {
   public static LocrawResponse field1 = new LocrawResponse("", "");
   @Nullable
   @SerializedName("server")
   public String server = null;
   @Nullable
   @SerializedName("gametype")
   public String field2 = null;
   @Nullable
   @SerializedName("mode")
   public String mode;
   @Nullable
   @SerializedName("map")
   public String field3;
   @Nullable
   @SerializedName("lobbyname")
   public String field4;

   private LocrawResponse(String text, String text2) {
      this.server = text;
      this.field2 = text2;
   }
}
