package com.moonsworth.lunar.forge;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class ClassPatchRegistry {
   private final Map<String, String> field1 = new HashMap<>();
   private final Map<String, String> field2 = new HashMap<>();
   private final Map<ClassPatch, byte[]> field3 = new HashMap<>();

   public ClassPatchRegistry(MixinMisc mixinmisc1) {
      for (ClassPatch mixinmisc23 : mixinmisc1.method7()) {
         byte[] items4 = mixinmisc23.method2(new byte[0]);
         if (items4.length == 0) {
            System.err.println("zero-byte binpatch; src " + mixinmisc23.sourceClassName + " dst " + mixinmisc23.field1);
         } else {
            this.field3.put(mixinmisc23, items4);
         }
      }

      for (ClassPatch mixinmisc214 : mixinmisc1.method1()) {
         String text15 = mixinmisc214.sourceClassName;
         String text5 = mixinmisc214.field1;
         this.field1.put(mixinmisc214.sourceClassName, mixinmisc214.field1);
         int index6 = text15.indexOf(36);
         int index7 = text5.indexOf(36);
         if (index6 != -1 && index7 != -1) {
            String text8 = text15.substring(index6 + 1);
            String text9 = text5.substring(index7 + 1);
            if (!text8.equals(text9)) {
               boolean flag10 = text8.chars().allMatch(Character::isDigit) && text9.chars().allMatch(Character::isDigit);
               if (flag10) {
                  String text11 = text5.substring(0, index7);
                  String text12 = text11 + "$" + text8;
                  this.field2.put(text5, text12);
               }
            }
         }
      }
   }

   @Generated
   public Map<String, String> method1() {
      return this.field1;
   }

   @Generated
   public Map<String, String> method2() {
      return this.field2;
   }

   @Generated
   public Map<ClassPatch, byte[]> method3() {
      return this.field3;
   }
}
