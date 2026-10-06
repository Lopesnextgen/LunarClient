package com.moonsworth.lunar.client.guiRewindhandlers;

import com.google.common.collect.Sets;
import com.moonsworth.lunar.client.event.ClientEventBus;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Supplier;

public class GuiRewindhandlers3 {
   private static Set<GuiRewindhandlers2> field1 = null;
   private final Set<GuiRewindhandlers2> field2 = Sets.newSetFromMap(new IdentityHashMap());
   private final Runnable field3;

   private GuiRewindhandlers3(Runnable var1) {
      this.field3 = var1;
   }

   public static void method1(GuiRewindhandlers2 var0) {
      if (field1 != null) {
         if (ClientEventBus.method29().method26()) {
            field1.add(var0);
         }
      }
   }

   public static GuiRewindhandlers3 method2(Runnable var0) {
      return new GuiRewindhandlers3(var0);
   }

   public <T> T method3(Supplier<T> var1) {
      return ClientEventBus.method29().method25(() -> {
         Set var2 = Sets.newSetFromMap(new IdentityHashMap());
         field1 = var2;
         Object var3 = var1.get();
         field1 = null;
         Iterator var4 = this.field2.iterator();

         while (var4.hasNext()) {
            GuiRewindhandlers2 var5 = (GuiRewindhandlers2)var4.next();
            if (!var2.contains(var5)) {
               var5.method2(this.field3);
               var4.remove();
            }
         }

         for (GuiRewindhandlers2 var7 : var2) {
            if (this.field2.add(var7)) {
               var7.method1(this.field3);
            }
         }

         return (T)var3;
      });
   }
}
