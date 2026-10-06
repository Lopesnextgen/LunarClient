package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.BridgeType2_2;
import com.moonsworth.lunar.bridge.PacketDirection;
import com.moonsworth.lunar.bridge.PacketFactoryBridge;
import com.moonsworth.lunar.bridge.MixinHelper2_7;
import com.moonsworth.lunar.bridge.WorldBorderPacketTranslator;
import com.moonsworth.lunar.bridge.SpawnExperienceOrbPacketTranslator;
import com.moonsworth.lunar.bridge.SpawnPositionPacketFactory;
import com.moonsworth.lunar.bridge.MixinHelper_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.Generated;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.Packet_v1_7;

public class BridgeIterator implements PacketFactoryBridge {
   private final List<MixinHelper_19> field1 = new ArrayList<>();
   private final com.moonsworth.lunar.bridge.WorldJoinPacketFactory field2 = new JoinGamePacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper27 field3 = new ServerDifficultyPacketFactory(this.field1);
   private final SpawnPositionPacketFactory field4 = new SpawnPointPacketBuilder(this.field1);
   private final WorldBorderPacketTranslator field5 = new WorldBorderPacketBuilder(this.field1);
   private final com.moonsworth.lunar.bridge.TimeUpdatePacketTranslator field6 = new TimeUpdatePacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper21 field7 = new ChunkDataPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper12 field8 = new BlockBreakAnimPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper23 field9 = new EffectPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper11 field10 = new SpawnMobPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper18 field11 = new PlayerAbilitiesPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper14 field12 = new HeldItemChangePacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper20 field13 = new SetExperiencePacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper29 field14 = new PlayerListItemPacketBuilder(this.field1);
   private final com.moonsworth.lunar.bridge.EntityPlayerPacketFactory field15 = new SpawnPlayerPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.EntityHeadLookPacketTranslator field16 = new EntityHeadLookPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper25 field17 = new WindowItemsPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper17 field18 = new OpenWindowPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper16 field19 = new BossInfoPacketBuilder(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper26 field20 = new UnsupportedSnapshotPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper19 field21 = new UnsupportedPacketListFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper15 field22 = new UnsupportedStatePacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.NoOpWorldPacketFactory field23 = new UnsupportedWorldPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper10 field24 = new TabListHeaderFooterPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper28 field25 = new EntityMovePacketBuilder(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper13 field26 = new ChunkUnloadPacketFactory(this.field1);
   private final MixinHelper2_7 field27 = new UnsupportedPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper24 field28 = new SpawnObjectPacketFactory(this.field1);
   private final SpawnExperienceOrbPacketTranslator field29 = new SpawnExperienceOrbPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.SpawnPaintingPacketTranslator field30 = new SpawnPaintingPacketFactory(this.field1);
   private final com.moonsworth.lunar.bridge.MixinHelper22 field31 = new UnsupportedNetPacketFactory(this.field1);
   private final Map<Class<?>, MixinHelper_19> field32 = new HashMap<>();

   public BridgeIterator() {
      for (MixinHelper_19 var2 : this.field1) {
         Class var3 = var2.method1();
         if (var3 != null) {
            this.field32.putIfAbsent(var3, var2);
            Class var4 = var3.getSuperclass();
            if (Bridge3_21.class.isAssignableFrom(var4)) {
               this.field32.putIfAbsent(var4, var2);
            }
         }
      }
   }

   @Override
   public void method35(Consumer<Object[]> var1, Bridge3_21 var2) {
      MixinHelper_19 var3 = this.field32.get(var2.getClass());
      if (var3 == null) {
         var3 = this.field32.get(var2.getClass().getSuperclass());
      }

      if (var3 != null) {
         var3.method5(var1, var2);
      }
   }

   @Override
   public Bridge3_21 method36(Supplier<Object[]> var1, Bridge3_21 var2) {
      MixinHelper_19 var3 = this.field32.get(var2.getClass());
      if (var3 == null) {
         var3 = this.field32.get(var2.getClass().getSuperclass());
      }

      return var3 != null ? var3.method6(var1, var2) : null;
   }

   private EnumConnectionState method3(BridgeType2_2 var1) {
      return EnumConnectionState.values()[var1.ordinal()];
   }

   @Annotation2(min = 1)
   private EnumPacketDirection method4(PacketDirection var1) {
      return EnumPacketDirection.values()[var1.ordinal()];
   }

   @Override
   public Bridge3_21 method1(PacketDirection var1, BridgeType2_2 var2, int var3, Bridge7_9 var4) {
      Bridge3_21 var5;
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         if (var1 == PacketDirection.SERVERBOUND) {
            var5 = (Bridge3_21)((Class)this.method3(var2).func_150753_a$v1_7().get(var3)).newInstance();
         } else {
            var5 = (Bridge3_21)((Class)this.method3(var2).func_150755_b$v1_7().get(var3)).newInstance();
         }

         ((Packet_v1_7)var5).readPacketData((PacketBuffer)var4);
      } else if (ThreadModuleDump63.MC_VERSION == 1) {
         var5 = (Bridge3_21 & Packet)this.method3(var2).getPacket(this.method4(var1), var3);
         ((Packet)var5).readPacketData((PacketBuffer)var4);
      } else {
         var5 = (Bridge3_21 & Packet)this.method3(var2).getPacket(this.method4(var1), var3);
         ((Packet)var5).readPacketData((PacketBuffer)var4);
      }

      return var5;
   }

   @Override
   public int method2(PacketDirection var1, BridgeType2_2 var2, Bridge3_21 var3) {
      if (ThreadModuleDump63.MC_VERSION <= 0) {
         return var1 == PacketDirection.SERVERBOUND
            ? (Integer)this.method3(var2).func_150753_a$v1_7().inverse().get(var3.getClass())
            : (Integer)this.method3(var2).func_150755_b$v1_7().inverse().get(var3.getClass());
      } else {
         return ThreadModuleDump63.MC_VERSION == 1
            ? this.method3(var2).getPacketId(this.method4(var1), (Packet)var3)
            : this.method3(var2).getPacketId(this.method4(var1), (Packet)var3);
      }
   }

   @Generated
   public List<MixinHelper_19> method35() {
      return this.field1;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.WorldJoinPacketFactory method5() {
      return this.field2;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper27 method6() {
      return this.field3;
   }

   @Generated
   @Override
   public SpawnPositionPacketFactory method7() {
      return this.field4;
   }

   @Generated
   @Override
   public WorldBorderPacketTranslator method8() {
      return this.field5;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.TimeUpdatePacketTranslator method9() {
      return this.field6;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper21 method10() {
      return this.field7;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper12 method11() {
      return this.field8;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper23 method12() {
      return this.field9;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper11 method13() {
      return this.field10;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper18 method14() {
      return this.field11;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper14 method15() {
      return this.field12;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper20 method16() {
      return this.field13;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper29 method17() {
      return this.field14;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.EntityPlayerPacketFactory method18() {
      return this.field15;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.EntityHeadLookPacketTranslator method19() {
      return this.field16;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper25 method20() {
      return this.field17;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper17 method21() {
      return this.field18;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper16 method22() {
      return this.field19;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper26 method23() {
      return this.field20;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper19 method24() {
      return this.field21;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper15 method25() {
      return this.field22;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.NoOpWorldPacketFactory method26() {
      return this.field23;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper10 method27() {
      return this.field24;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper28 method28() {
      return this.field25;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper13 method29() {
      return this.field26;
   }

   @Generated
   @Override
   public MixinHelper2_7 method30() {
      return this.field27;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper24 method31() {
      return this.field28;
   }

   @Generated
   @Override
   public SpawnExperienceOrbPacketTranslator method33() {
      return this.field29;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.SpawnPaintingPacketTranslator method32() {
      return this.field30;
   }

   @Generated
   @Override
   public com.moonsworth.lunar.bridge.MixinHelper22 method34() {
      return this.field31;
   }

   @Generated
   public Map<Class<?>, MixinHelper_19> method38() {
      return this.field32;
   }
}
