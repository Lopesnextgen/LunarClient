package com.moonsworth.lunar.client.framework.feature.tps;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Generated;

public class Tps2 {
   @SerializedName("formats")
   private List<Tps2.Data> field1;

   @Generated
   public List<Tps2.Data> method1() {
      return this.field1;
   }

   public static class Data {
      @SerializedName("name")
      private String name;
      @SerializedName("colors")
      private String colors;
      @SerializedName("symbol")
      private String symbol;

      @Generated
      public String getName() {
         return this.name;
      }

      @Generated
      public String method1() {
         return this.colors;
      }

      @Generated
      public String getSymbol() {
         return this.symbol;
      }
   }
}
