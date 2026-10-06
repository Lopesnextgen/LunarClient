package com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.util.Annotation7;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

@Annotation7
public class Rewindhandlers2 {
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
   public final Gui2Extension3 field6;

   public Rewindhandlers2(Rewindhandlers rewindhandlers, Gui2Extension3 gui2Extension3) {
      this.field1 = rewindhandlers.server;
      this.field2 = rewindhandlers.field2;
      this.field3 = rewindhandlers.mode;
      this.field4 = rewindhandlers.field3;
      this.field5 = rewindhandlers.field4;
      this.field6 = gui2Extension3;
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
