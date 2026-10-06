package com.moonsworth.lunar.genesis.mixin;

import com.moonsworth.lunar.genesis.GwtCompatible;
import com.moonsworth.lunar.genesis.Beta;

@Beta
@GwtCompatible
public enum MixinHelperType {
   PRIVATE(':', ','),
   REGISTRY('!', '?');

   private final char innerNodeCode;
   private final char leafNodeCode;

   MixinHelperType(char character3, char character4) {
      this.innerNodeCode = character3;
      this.leafNodeCode = character4;
   }

   char getLeafNodeCode() {
      return this.leafNodeCode;
   }

   char getInnerNodeCode() {
      return this.innerNodeCode;
   }

   static MixinHelperType fromCode(char character0) {
      for (MixinHelperType mixinhelpertype4 : values()) {
         if (mixinhelpertype4.getInnerNodeCode() == character0 || mixinhelpertype4.getLeafNodeCode() == character0) {
            return mixinhelpertype4;
         }
      }

      throw new IllegalArgumentException("No enum corresponding to given code: " + character0);
   }

   static MixinHelperType fromIsPrivate(boolean flag0) {
      return flag0 ? PRIVATE : REGISTRY;
   }
}
