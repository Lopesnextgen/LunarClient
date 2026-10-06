package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.SPacketEntity.S15PacketEntityRelMove;
import net.minecraft.network.play.server.SPacketEntity.S17PacketEntityLookMove;

public class EntityMovePacketBuilder extends AbstractRewindPacketBuilder implements com.moonsworth.lunar.bridge.MixinHelper28 {
   public EntityMovePacketBuilder(List<MixinHelper_19> var1) {
      super(S14PacketEntity.class, var1);
   }

   @Override
   public Bridge3_21 method6(Supplier<Object[]> var1, Bridge3_21 var2) {
      Object var3 = null;
      if (var2 instanceof S15PacketEntityRelMove) {
         var3 = new S15PacketEntityRelMove();
      }

      if (var2 instanceof S17PacketEntityLookMove) {
         var3 = new S17PacketEntityLookMove();
      }

      if (var3 == null) {
         return null;
      }

      S14PacketEntity var4 = (S14PacketEntity)var2;
      ((S14PacketEntity)var3).field_179775_c = var4.field_179775_c;
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         ((S14PacketEntity)var3).field_149072_b$v1_7 = (byte)(-var4.field_149072_b$v1_7);
         ((S14PacketEntity)var3).field_149073_c$v1_7 = (byte)(-var4.field_149073_c$v1_7);
         ((S14PacketEntity)var3).field_149070_d$v1_7 = (byte)(-var4.field_149070_d$v1_7);
      } else if (ThreadModuleDump63.MC_VERSION == 1) {
         ((S14PacketEntity)var3).posX = (byte)(-var4.posX);
         ((S14PacketEntity)var3).posY = (byte)(-var4.posY);
         ((S14PacketEntity)var3).posZ = (byte)(-var4.posZ);
      } else {
         ((S14PacketEntity)var3).posX$v1_12 = (byte)(-var4.posX$v1_12);
         ((S14PacketEntity)var3).posY$v1_12 = (byte)(-var4.posY$v1_12);
         ((S14PacketEntity)var3).posZ$v1_12 = (byte)(-var4.posZ$v1_12);
      }

      ((S14PacketEntity)var3).yaw = (byte)(-var4.yaw);
      ((S14PacketEntity)var3).pitch = (byte)(-var4.pitch);
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         ((S14PacketEntity)var3).onGround = var4.onGround;
      }

      return (Bridge3_21)var3;
   }
}
