package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.BiMap;
import com.moonsworth.lunar.bridge.PacketBuilder;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Generated;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;

public abstract class RewindPacketBuilder implements PacketBuilder {
   private final AtomicReference<Object> field1 = new AtomicReference<>();
   private final Class<?> field2;

   protected RewindPacketBuilder(Class<?> clazz1, List<PacketBuilder> list2) {
      this.field2 = clazz1;
      list2.add(this);
   }

   private int method2() {
      if (Ref.MC_VERSION <= 0) {
         return (Integer)EnumConnectionState.PLAY.func_150755_b$v1_7().inverse().get(this.field2);
      } else {
         return Ref.MC_VERSION == 1
            ? (Integer)((BiMap)EnumConnectionState.PLAY.directionMaps.get(EnumPacketDirection.CLIENTBOUND)).inverse().get(this.field2)
            : (Integer)((BiMap)EnumConnectionState.PLAY.directionMaps$v1_12.get(EnumPacketDirection.CLIENTBOUND)).inverse().get(this.field2);
      }
   }

   public boolean method2(ResourceLocationBridge horsestats141, int number2) {
      return this.method5() == number2;
   }

   public boolean method3(Class<?> clazz1) {
      return this.field2 == null ? false : this.field2.equals(clazz1);
   }

   public boolean method4(Class<?> clazz1) {
      return this.field2 == null ? false : this.field2.isAssignableFrom(clazz1);
   }

   @Generated
   public int method5() {
      Object obj1 = this.field1.get();
      if (obj1 == null) {
         synchronized (this.field1) {
            obj1 = this.field1.get();
            if (obj1 == null) {
               int number3 = this.method2();
               obj1 = number3;
               this.field1.set(obj1);
            }
         }
      }

      return (Integer)obj1;
   }

   @Generated
   public Class<?> method1() {
      return this.field2;
   }
}
