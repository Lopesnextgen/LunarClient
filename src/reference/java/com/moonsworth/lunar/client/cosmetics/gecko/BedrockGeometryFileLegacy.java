package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.cosmetics.gecko.FormatVersion;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockModel;

public class BedrockGeometryFileLegacy {
   private FormatVersion field1;
   private BedrockModel[] field2;

   @JsonProperty("format_version")
   public FormatVersion method1() {
      return this.field1;
   }

   @JsonProperty("format_version")
   public void method2(FormatVersion var1) {
      this.field1 = var1;
   }

   @JsonProperty("minecraft:geometry")
   public BedrockModel[] method3() {
      return this.field2;
   }

   @JsonProperty("minecraft:geometry")
   public void method4(BedrockModel[] var1) {
      this.field2 = var1;
   }
}
