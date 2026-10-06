package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.nio.ByteBuffer;
import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

@VersionGate(min = 6)
public interface ModernRenderSystemBridge extends RenderSystemBridge {
   BufferBuilderBridge method1(AbstractRenderContext bridgeextension_91, float value2, boolean flag3);

   MixinHelper_21 method2(float value1, float value2, float value3, float value4);

   void method6();

   void method7();

   void method8();

   MixinHelper_21 method10();

   void method7(MixinHelper_21 mixinhelper_211, @Nullable VertexSortingMode bridgetype2);

   @VersionGate(min = 8)
   Bridge5_16 method12();

   @VersionGate(min = 8)
   MixinHelper_21 method15();

   Bridge2_5 method10(@NotNull RenderTypeBridge bridge201, Consumer<VertexConsumerBridge> consumer2, boolean flag3);

   void method11(Bridge4Extension bridge4extension1, @Nullable VertexSortingMode bridgetype2, @Nullable Vector3f vector3f3);

   @Contract("_,_,false -> param1; _,_,true -> new")
   @Nullable
   Bridge2_5 method12(Bridge2_5 bridge2_51, Bridge4Extension bridge4extension2, boolean flag3, RenderTypeBridge bridge204);

   @VersionGate(min = 8, max = 29)
   default Vector3f[] method16() {
      return null;
   }

   @VersionGate(min = 8, max = 29)
   default void method14(Vector3f vector3f1, Vector3f vector3f2) {
   }

   void method16(int number1, int number2);

   void method16(int number1, int number2, int number3);

   void method17(int number1, ByteBuffer buffer2, int number3);

   @VersionGate(min = 8)
   int method18();

   void method20();
}
