package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.BiMap;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;

public abstract class AbstractRewindPacketBuilder implements MixinHelper_19 {
   private final AtomicReference<Object> field1 = new AtomicReference<>();
   private final Class<?> field2;

   protected AbstractRewindPacketBuilder(Class<?> var1, List<MixinHelper_19> var2) {
      this.field2 = var1;
      var2.add(this);
   }

   private int method2() {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return (Integer)EnumConnectionState.PLAY.func_150755_b$v1_7().inverse().get(this.field2);
      } else {
         return ThreadModuleDump63.MC_VERSION == 1
            ? (Integer)((BiMap)EnumConnectionState.PLAY.directionMaps.get(EnumPacketDirection.CLIENTBOUND)).inverse().get(this.field2)
            : (Integer)((BiMap)EnumConnectionState.PLAY.directionMaps$v1_12.get(EnumPacketDirection.CLIENTBOUND)).inverse().get(this.field2);
      }
   }

   public boolean method2(ResourceLocationBridge var1, int var2) {
      return this.method5() == var2;
   }

   public boolean method3(Class<?> var1) {
      return this.field2 == null ? false : this.field2.equals(var1);
   }

   public boolean method4(Class<?> var1) {
      return this.field2 == null ? false : this.field2.isAssignableFrom(var1);
   }

   @Generated
   public int method5() {
      Object var1 = this.field1.get();
      if (var1 == null) {
         synchronized (this.field1) {
            var1 = this.field1.get();
            if (var1 == null) {
               int var3 = this.method2();
               var1 = var3;
               this.field1.set(var1);
            }
         }
      }

      return (Integer)var1;
   }

   @Generated
   public Class<?> method1() {
      return this.field2;
   }
}
