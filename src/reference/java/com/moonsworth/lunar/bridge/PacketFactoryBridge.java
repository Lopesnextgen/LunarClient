package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface PacketFactoryBridge {
   Bridge3_21 method1(PacketDirection var1, BridgeType2_2 var2, int var3, Bridge7_9 var4);

   int method2(PacketDirection var1, BridgeType2_2 var2, Bridge3_21 var3);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default void method3(PacketDirection var1, BridgeType2_2 var2, Bridge3_21 var3, Consumer<Bridge3_21> var4) {
   }

   default ResourceLocationBridge method4(PacketDirection var1, int var2) {
      return null;
   }

   WorldJoinPacketFactory method5();

   MixinHelper27 method6();

   SpawnPositionPacketFactory method7();

   WorldBorderPacketTranslator method8();

   TimeUpdatePacketTranslator method9();

   MixinHelper21 method10();

   MixinHelper12 method11();

   MixinHelper23 method12();

   MixinHelper11 method13();

   MixinHelper18 method14();

   MixinHelper14 method15();

   MixinHelper20 method16();

   MixinHelper29 method17();

   EntityPlayerPacketFactory method18();

   EntityHeadLookPacketTranslator method19();

   MixinHelper25 method20();

   MixinHelper17 method21();

   MixinHelper16 method22();

   MixinHelper26 method23();

   MixinHelper19 method24();

   MixinHelper15 method25();

   NoOpWorldPacketFactory method26();

   MixinHelper10 method27();

   MixinHelper28 method28();

   MixinHelper13 method29();

   MixinHelper2_7 method30();

   MixinHelper24 method31();

   SpawnPaintingPacketTranslator method32();

   SpawnExperienceOrbPacketTranslator method33();

   MixinHelper22 method34();

   void method35(Consumer<Object[]> var1, Bridge3_21 var2);

   Bridge3_21 method36(Supplier<Object[]> var1, Bridge3_21 var2);
}
