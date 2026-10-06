package com.moonsworth.lunar.client.framework.listener;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.util.io.SerializedNameOnly;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

@SerializedNameOnly
public class HypixelLocation {
   @SerializedName("server")
   @NotNull
   public final String field1;
   @SerializedName("gametype")
   @NotNull
   public final String field2;
   @Nullable
   @SerializedName("mode")
   public final String field3;
   @Nullable
   @SerializedName("map")
   public final String field4;
   @Nullable
   @SerializedName("lobbyname")
   public final String field5;
   @SerializedName("skyBlockLocation")
   @NotNull
   public final SkyblockIsland field6;

   public HypixelLocation(LocrawResponse rewindhandlers1, SkyblockIsland skyblockIsland) {
      this.field1 = rewindhandlers1.server;
      this.field2 = rewindhandlers1.field2;
      this.field3 = rewindhandlers1.mode;
      this.field4 = rewindhandlers1.field3;
      this.field5 = rewindhandlers1.field4;
      this.field6 = skyblockIsland;
   }

   public boolean method1() {
      return this.field5 != null;
   }

   public boolean method2() {
      return this.field1.isEmpty() && this.field2.isEmpty();
   }

   @Generated
   @Override
   public String toString() {
      return "HypixelLocation(server="
         + this.field1
         + ", gametype="
         + this.field2
         + ", mode="
         + this.field3
         + ", map="
         + this.field4
         + ", lobbyname="
         + this.field5
         + ", skyBlockLocation="
         + this.field6
         + ")";
   }
}
