package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BedrockGeometryFile {
   private FormatVersion field1;
   private BedrockModel[] field2;

   public BedrockGeometryFile() {
   }

   @JsonProperty("format_version")
   public FormatVersion method1() {
      return this.field1;
   }

   @JsonProperty("format_version")
   public void method2(FormatVersion rewindhandlerstype21) {
      this.field1 = rewindhandlerstype21;
   }

   @JsonProperty("minecraft:geometry")
   public BedrockModel[] method3() {
      return this.field2;
   }

   @JsonProperty("minecraft:geometry")
   public void method4(BedrockModel[] items1) {
      this.field2 = items1;
   }
}
