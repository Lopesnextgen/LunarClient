package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.serverdiscovery.v1.AutocompleteSuggestion;
import java.util.ArrayList;
import java.util.List;

public class ServerMapping {
   @SerializedName("name")
   private final String field1;
   @SerializedName("primaryAddress")
   private final String field2;
   @SerializedName("addresses")
   private final List<String> field3;

   public ServerMapping(String var1, String var2, List<String> var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public static ServerMapping method1(AutocompleteSuggestion var0) {
      return new ServerMapping(var0.getName(), var0.getPrimaryAddress(), new ArrayList<>(var0.getAddressesList()));
   }

   @SerializedName("name")
   public String name() {
      return this.field1;
   }

   @SerializedName("primaryAddress")
   public String method2() {
      return this.field2;
   }

   @SerializedName("addresses")
   public List<String> method3() {
      return this.field3;
   }
}
