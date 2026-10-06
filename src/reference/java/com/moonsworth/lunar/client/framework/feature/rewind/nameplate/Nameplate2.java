package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Nameplate2 {
   public abstract void method1(ByteBufLoader var1);

   public abstract void method2(ByteBufLoader var1);

   public abstract void method3(Nameplate4 var1);

   public Nameplate2 method4(Nameplate4 var1) {
      return null;
   }

   public String name() {
      return this.getClass().getSimpleName();
   }

   public String data() {
      return this.method5(this);
   }

   protected String method5(Object var1) {
      StringBuilder var2 = new StringBuilder();
      ArrayList var3 = new ArrayList<>(List.of(var1.getClass().getFields()));

      for (Field var7 : var1.getClass().getDeclaredFields()) {
         if (!var3.contains(var7)) {
            var3.add(var7);
         }
      }

      for (Field var10 : var3) {
         var10.setAccessible(true);

         try {
            if (!var10.getName().toLowerCase().contains("codec")) {
               if (!var2.isEmpty()) {
                  var2.append(", ");
               }

               Object var11 = var10.get(var1);
               if (var11 != null && var11.toString().length() > 50) {
                  var11 = "LARGE_DATA";
               }

               var2.append(var10.getName()).append("=").append(var11);
            }
         } catch (IllegalAccessException var8) {
            var2.append(var10.getName()).append("=ERROR");
         }
      }

      return var2.toString();
   }
}
