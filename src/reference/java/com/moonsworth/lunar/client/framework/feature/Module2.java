package com.moonsworth.lunar.client.framework.feature;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Generated;

public class Module2 {
   private final Framework7Extension field1;
   private final Map<String, Object> field2 = new HashMap<>();

   public <T> T method1(String var1, T var2) {
      this.method2(var1, var2);
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      return (T)(!var3.method19() ? var2 : this.field2.getOrDefault(var1, var2));
   }

   private void method2(String var1, Object var2) {
      Object var3 = this.field2.get(var1);
      if (!Objects.equals(var3, var2)) {
         Rewind var4 = ThreadModuleDump63.method4().method40().method85();
         if (!var4.method19()) {
            this.set(var1, var2);
         }

         if (var4.isRecording()) {
            RewindHandlers5 var5 = var4.method34();

            try {
               var5.method23().putIfAbsent(this.field1.getId(), new HashMap());
               ((Map)var5.method23().get(this.field1.getId())).put(var1, var2);
            } catch (Exception var7) {
               throw new RuntimeException(var7);
            }
         }
      }
   }

   public void set(String var1, Object var2) {
      this.field2.put(var1, var2);
   }

   @Generated
   public Module2(Framework7Extension var1) {
      this.field1 = var1;
   }

   @Generated
   public Map<String, Object> method3() {
      return this.field2;
   }
}
