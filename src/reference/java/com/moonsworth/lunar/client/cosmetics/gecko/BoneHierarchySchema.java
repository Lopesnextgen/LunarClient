package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.cosmetics.gecko.BedrockBoneLegacy;
import java.util.HashMap;

public class BoneHierarchySchema {
   public HashMap<String, BoneHierarchySchema> field1 = new HashMap<>();
   public BedrockBoneLegacy field2;

   public BoneHierarchySchema(BedrockBoneLegacy rewindhandlers4_2) {
      this.field2 = rewindhandlers4_2;
   }
}
