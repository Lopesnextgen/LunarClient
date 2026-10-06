package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.cosmetics.gecko.BedrockBone;
import java.util.HashMap;

public class BoneHierarchyNode {
   public HashMap<String, BoneHierarchyNode> field1 = new HashMap<>();
   public BedrockBone field2;

   public BoneHierarchyNode(BedrockBone rewindhandlers4_21) {
      this.field2 = rewindhandlers4_21;
   }
}
