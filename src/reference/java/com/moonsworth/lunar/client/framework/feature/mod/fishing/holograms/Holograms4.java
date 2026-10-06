package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

public class Holograms4 {
   private final Holograms2_5 field1;
   private String field2;
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 field3 = null;

   public Holograms4(Holograms2_5 var1, String var2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 hologramsType2) {
      this.field1 = var1;
      this.method2(var2, hologramsType2);
   }

   private void method1(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 var1) {
      if (this.field3 != var1) {
         if ((this.field3 == null || this.field3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT)
            && (
               var1 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.OPENED
                  || var1 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED
                  || var1 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.FAILED
            )
            && !this.field1.method35(this)) {
            this.field3 = com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT;
         } else {
            this.field3 = var1;
         }
      }
   }

   public String getName() {
      return this.field2;
   }

   public void method2(String var1, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 var2) {
      this.field2 = var1;
      if (this.field2.equals("???")) {
         var2 = com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT;
      }

      this.method1(var2);
   }
}
