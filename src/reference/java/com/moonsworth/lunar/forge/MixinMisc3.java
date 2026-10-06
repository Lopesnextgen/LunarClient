package com.moonsworth.lunar.forge;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class MixinMisc3 {
   private final Map<String, String> field1 = new HashMap<>();
   private final Map<String, String> field2 = new HashMap<>();
   private final Map<MixinMisc2, byte[]> field3 = new HashMap<>();

   public MixinMisc3(MixinMisc mixinMisc) {
      for (MixinMisc2 var3 : mixinMisc.method7()) {
         byte[] var4 = var3.method2(new byte[0]);
         if (var4.length == 0) {
            System.err.println("zero-byte binpatch; src " + var3.sourceClassName + " dst " + var3.field1);
         } else {
            this.field3.put(var3, var4);
         }
      }

      for (MixinMisc2 var14 : mixinMisc.method1()) {
         String var15 = var14.sourceClassName;
         String var5 = var14.field1;
         this.field1.put(var14.sourceClassName, var14.field1);
         int var6 = var15.indexOf(36);
         int var7 = var5.indexOf(36);
         if (var6 != -1 && var7 != -1) {
            String var8 = var15.substring(var6 + 1);
            String var9 = var5.substring(var7 + 1);
            if (!var8.equals(var9)) {
               boolean var10 = var8.chars().allMatch(Character::isDigit) && var9.chars().allMatch(Character::isDigit);
               if (var10) {
                  String var11 = var5.substring(0, var7);
                  String var12 = var11 + "$" + var8;
                  this.field2.put(var5, var12);
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
   public Map<MixinMisc2, byte[]> method3() {
      return this.field3;
   }
}
