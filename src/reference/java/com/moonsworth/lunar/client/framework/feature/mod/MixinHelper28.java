package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.Bridge2_45;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.bridge.Bridge_7;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@Annotation2(min = 1)
public class MixinHelper28 extends MixinHelper2_3 {
   private final Map<String, Map<String, Bridge2_45>> field2 = new HashMap<>();
   private long field3 = 0L;

   public MixinHelper28(MixinHelper var1) {
      super(var1);
   }

   public void method17() {
      Map var1 = this.field2.get(this.method15());
      if (var1 != null) {
         for (Bridge2_45 var3 : var1.values()) {
            if (var3.selected) {
               var3.field1 = true;
            }
         }
      }
   }

   public boolean method2(String var1, String var2) {
      return this.method12(var1, this.method15(), var2).selected;
   }

   public void method3(String var1, String var2) {
      this.method12(var1, this.method15(), var2).selected = true;
   }

   public void method4(String var1, String var2, String var3) {
      this.method12(var1, var2, var3).selected = true;
   }

   public boolean method5(String var1) {
      Map var2 = this.field2.get(var1);
      if (var2 != null) {
         for (Bridge2_45 var4 : var2.values()) {
            if (var4.selected) {
               return true;
            }
         }
      }

      return false;
   }

   public void method6(String var1) {
      this.field2.remove(var1);
   }

   public String method7(String var1, int var2, int var3, int var4, int var5, String var6, boolean var7) {
      Bridge2_45 var8 = this.method12("textinput", this.method15(), var1);
      var8.width = var4;
      var8.height = var5;
      int var9 = var7 ? 2 : 1;
      this.method11(var2, var3, var4, var5, var8, var9);
      if (var8.selected) {
         this.method10(var8);
      }

      boolean var10 = (ThreadModuleDump63.method3().bridge$getSystemTime() - this.field3) % 650L < 325L;
      boolean var11 = (var8.field5 < var8.text.length() || var8.text.length() >= var8.field2) && var8.selected;
      String var12 = this.method9(var6, var8, var10, var11);
      if (this.method1().field7) {
         this.method8(var2, var3, var4, var5, var7, var8, var10, var11, var12);
      }

      if (var8.field1) {
         var8.selected = false;
         var8.field1 = false;
      }

      if (this.field1.method42(var2 - 4 * var9, var3 - 4 * var9, var4 + 8 * var9, var5 + 8 * var9)) {
         this.method4().method20();
      }

      return var8.text;
   }

   private void method8(int var1, int var2, int var3, int var4, boolean var5, Bridge2_45 var6, boolean var7, boolean var8, String var9) {
      if (var5) {
         this.field1.method37(var1 - 2, var2 - 2, var3 + 4, var4 + 4);
      }

      this.field1.method38(var1, var2, var3, var4);
      double var10 = var4 / 10.0;
      this.field1.method3();
      this.field1.method18(var1, var2, var3, var4);
      int var12 = (int)(var1 + this.field1.getStringWidth(var6.text.substring(var6.field3, Math.max(var6.field3, var6.field5))) * var10);
      if (var6.selected && var6.field4 != var6.field5) {
         int var13 = (int)(
            var1 + this.field1.getStringWidth(var6.text.substring(var6.field3, Math.max(var6.field3, var6.field4))) * var10
         );
         this.field1.method43(var13 + 1, var2, var12 - var13, var4, -865704193);
      }

      this.field1.method25(var9, var1 + 1, var2 + 1, !var6.text.isEmpty() || var6.selected, (float)var10);
      this.field1.method4();
      if (var7 && var8) {
         this.field1.method43(var12, var2, 1, var4, -3750202);
      }
   }

   @NotNull
   private String method9(String var1, Bridge2_45 var2, boolean var3, boolean var4) {
      String var5 = var2.text;
      if (var5.isEmpty() && !var2.selected) {
         var5 = AdventureChatFormatting.GRAY + var1;
      } else if (var2.selected) {
         if (var2.field4 != var2.field5) {
            int var6 = Math.min(var2.field5, var2.field4);
            int var7 = Math.max(var2.field5, var2.field4);
            var5 = var2.text.substring(var2.field3, Math.max(var2.field3, var6))
               + AdventureChatFormatting.BLUE
               + var2.text.substring(var6, var7)
               + AdventureChatFormatting.RESET
               + var2.text.substring(var7);
         } else {
            var5 = var2.text.substring(var2.field3);
         }

         if (var3 && !var4) {
            var5 = var5 + "_";
         }
      }

      return var5;
   }

   private void method10(Bridge2_45 var1) {
      for (Bridge_7 var3 : this.method13().method18()) {
         if (var3.method2() && var3.method3()) {
            var1.selected = false;
         }

         if (this.field1.method44() instanceof Bridge5Extension_3 var4) {
            var4.lunar$handleKeyEventOnState(var1, var3);
         }

         this.field3 = ThreadModuleDump63.method3().bridge$getSystemTime();
      }

      this.method13().method18().clear();
   }

   private void method11(int var1, int var2, int var3, int var4, Bridge2_45 var5, int var6) {
      MixinHelper_2 var7 = this.field1.method40(var1 - 4 * var6, var2 - 4 * var6, var3 + 8 * var6, var4 + 8 * var6);
      if (var7 != null) {
         if (var5.selected && this.field1.method44() instanceof Bridge5Extension_3 var8) {
            var8.lunar$handleMousePressOnState(var5, var7.method2(), var7.x() - var1 + 6, var7.y() - var2);
         }

         var5.selected = true;
         var5.field1 = false;
         this.field3 = ThreadModuleDump63.method3().bridge$getSystemTime();
      }
   }

   private Bridge2_45 method12(String var1, String var2, String var3) {
      Map var4 = this.field2.computeIfAbsent(var2, var0 -> new HashMap<>());
      return var4.computeIfAbsent(var1 + "-" + var3, var0 -> new Bridge2_45(0, 0, 1000));
   }
}
