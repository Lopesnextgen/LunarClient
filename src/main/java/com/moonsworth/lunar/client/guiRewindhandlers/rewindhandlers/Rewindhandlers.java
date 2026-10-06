package com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Nullable;

public class Rewindhandlers {
   public static Rewindhandlers field1 = new Rewindhandlers("", "");
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

   private Rewindhandlers(String text, String text2) {
      this.server = text;
      this.field2 = text2;
   }
}
