package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_9;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.joml.Vector2i;

public class Nameplate4 {
   private final Click4 field1;
   private final Click4 field2;
   private final Holograms2_5 field3;

   public Nameplate4(double var1, double var3, Holograms2_5 var5) {
      this.field1 = new Click4(var1, Click4.Type.LINEAR);
      this.field2 = new Click4(var3, Click4.Type.LINEAR);
      this.field3 = var5;
   }

   public static Nameplate4 method1(int value, int var1, Holograms2_5 var2) {
      return new Nameplate4(value * 32 + Holograms2_5.field7.x(), var1 * 32 + Holograms2_5.field7.y(), var2);
   }

   public float method2() {
      return (float)this.field1.getValue();
   }

   public float method3() {
      return (float)this.field2.getValue();
   }

   public void method4(double var1) {
      this.field1.animateTo(var1, 0L);
   }

   public void method5(double var1) {
      this.field2.animateTo(var1, 0L);
   }

   public void method6(double var1, long var3) {
      this.field1.animateTo(var1, var3);
   }

   public void method7(double var1, long var3) {
      this.field2.animateTo(var1, var3);
   }

   public int method8() {
      return (int)((this.field1.getValue() - Holograms2_5.field7.x()) / 32.0);
   }

   public int method9() {
      return (int)((this.field2.getValue() - Holograms2_5.field7.y()) / 32.0);
   }

   public int method10() {
      return (int)((this.field1.getValue2() - Holograms2_5.field7.x()) / 32.0);
   }

   public int method11() {
      return (int)((this.field2.getValue2() - Holograms2_5.field7.y()) / 32.0);
   }

   public float method12(Holograms_9 var1) {
      int var2 = this.field3.method27();
      int var3 = this.field3.method28();
      float var4 = 0.0F;
      if (var3 > var2) {
         var4 += 0.5F / var2;
      }

      float var5 = ((float)this.field1.getValue() - Holograms2_5.field7.x()) / 32.0F;
      return (var4 + var5 / Math.max(var2, var3)) * (100.0F - var1.method35() * 2.0F);
   }

   public float method13(Holograms_9 var1) {
      int var2 = this.field3.method27();
      int var3 = this.field3.method28();
      float var4 = 0.0F;
      if (var2 > var3) {
         var4 += 0.5F / var3;
      }

      float var5 = ((float)this.field2.getValue() - Holograms2_5.field7.y()) / 32.0F;
      return (var4 + var5 / Math.max(var2, var3)) * (100.0F - var1.method35() * 2.0F);
   }

   public boolean isLoaded() {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 == null) {
         return false;
      }

      int var2 = (int)Math.floor(this.method2() / 16.0);
      int var3 = (int)Math.floor(this.method3() / 16.0);

      for (int var4 = 0; var4 <= 2; var4++) {
         for (int var5 = 0; var5 <= 2; var5++) {
            if (!var1.bridge$isChunkLoaded(var2 + var4, var3 + var5)) {
               return false;
            }
         }
      }

      return true;
   }

   public void method14() {
      this.method4(this.method10() * 32 + Holograms2_5.field7.x());
      this.method5(this.method11() * 32 + Holograms2_5.field7.y());
   }

   public Vector2i getProvider() {
      return new Vector2i(this.method8(), this.method9());
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof Nameplate4 var2 && var2.method8() == this.method8() && var2.method9() == this.method9();
   }
}
