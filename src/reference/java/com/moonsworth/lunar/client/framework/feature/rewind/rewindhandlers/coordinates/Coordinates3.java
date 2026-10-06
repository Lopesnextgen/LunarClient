package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import java.util.UUID;

public class Coordinates3 {
   public static class Data {
      @SerializedName("layerId")
      public UUID field1;
      @SerializedName("keyframeType")
      public String field2;
      @SerializedName("keyframeId")
      public String field3;
      @SerializedName("frame")
      public int frame;
   }

   public static class Data2 {
      @SerializedName("layerId")
      public UUID field1;
      @SerializedName("parentType")
      public String field2;
      @SerializedName("keyframeType")
      public String field3;
      @SerializedName("keyframeId")
      public String field4;
      @SerializedName("currentFrame")
      public int field5;
      @SerializedName("newFrame")
      public int field6;
   }

   public static class Data3 {
      @SerializedName("layerId")
      public UUID field1;
      @SerializedName("trackId")
      public UUID field2;
      @SerializedName("frame")
      public int frame;
   }

   public class Data4 {
      private final Fishing2Loader<?, ?> field1;
      private final int field2;
      private final Fishing2Loader.Data field3;

      public Data4(Fishing2Loader<?, ?> fishing2Loader, int value, Fishing2Loader.Data data) {
         this.field1 = fishing2Loader;
         this.field2 = value;
         this.field3 = data;
      }

      public Fishing2Loader<?, ?> method1() {
         return this.field1;
      }

      public int method2() {
         return this.field2;
      }

      public Fishing2Loader.Data method3() {
         return this.field3;
      }
   }
}
