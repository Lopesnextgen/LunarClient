package com.moonsworth.lunar.bridge;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Optional;
import java.util.function.Consumer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.joml.Vector4f;

public interface RenderSystemBridge {
   boolean method1();

   boolean method2();

   boolean method3();

   void method4();

   void method5();

   void bridge$scale(float var1, float var2, float var3);

   void bridge$translate(float var1, float var2, float var3);

   void method6(float var1, float var2, float var3, float var4);

   void method7(float var1, float var2, float var3, float var4);

   void method8(float var1, float var2, float var3);

   @com.moonsworth.lunar.ichor.Annotation2(min = 30)
   default Vector4f method9() {
      throw new AbstractMethodErrorImpl();
   }

   default void method10(int var1) {
      this.method12(var1);
   }

   default int method11() {
      return this.method14();
   }

   void method12(int var1);

   int method13();

   int method14();

   void method15(int var1, int var2);

   void method16(int var1, float var2);

   int method17();

   void method18(int var1, int var2, int var3, int var4);

   @com.moonsworth.lunar.ichor.Annotation2(min = 1, max = 5)
   default boolean[] method19() {
      throw new AbstractMethodErrorImpl();
   }

   void method20(boolean var1, boolean var2, boolean var3, boolean var4);

   void method21(boolean var1);

   boolean method22();

   void method23(int var1);

   void method24(int var1);

   int method25();

   void method26(int var1);

   int method27();

   void method28();

   void method29();

   boolean method30();

   void method31();

   void method32();

   boolean method33();

   void method34();

   void method35();

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default void method36() {
      this.method18(BridgeType2_8.SRC_ALPHA.getGlId(), BridgeType2_8.ONE_MINUS_SRC_ALPHA.getGlId(), 1, 0);
   }

   boolean method37();

   void method38();

   boolean method39();

   void method40();

   void method41();

   void method42();

   boolean method43();

   void method44();

   void method45();

   boolean method46();

   void method47();

   void method48();

   @com.moonsworth.lunar.ichor.Annotation2(max = 7)
   boolean method49();

   void method50();

   void method51();

   boolean method52();

   void method53(float var1, float var2);

   void method54(int var1, int var2, int var3, int var4);

   void bridge$loadIdentity();

   void method55();

   void method56();

   boolean method57();

   void method58(int var1);

   void method59(double var1, double var3, double var5, double var7, double var9, double var11);

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default boolean method60() {
      throw new AbstractMethodErrorImpl();
   }

   void method61();

   void method62();

   @com.moonsworth.lunar.ichor.Annotation2(max = 7)
   void method63(float var1);

   float method64();

   float method65();

   default float method66() {
      return Float.MAX_VALUE;
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default float[] method67() {
      throw new AbstractMethodErrorImpl();
   }

   void method68(float var1);

   void method69(float var1);

   void method70(FloatBuffer var1);

   void method71(float var1);

   void method72(boolean var1);

   void method73(int var1, int var2, FloatBuffer var3);

   default void method74(float var1, float var2, float var3, float var4) {
      this.method75(var1, var2, var3, var4);
   }

   void method75(float var1, float var2, float var3, float var4);

   void method76(float var1, float var2, float var3, FloatBuffer var4, FloatBuffer var5, IntBuffer var6, FloatBuffer var7);

   default void method77(boolean var1) {
   }

   default boolean method78() {
      return false;
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 7)
   void method79(MixinHelper_21 var1);

   void method80(int var1);

   void method81(int var1, int var2);

   boolean method82();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default RenderSystemBridge.Extension2 method83() {
      return (RenderSystemBridge.Extension2)this;
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   default RenderSystemBridge.Extension method84() {
      return (RenderSystemBridge.Extension)this;
   }

   Bridge7_2 method85();

   void method86(boolean var1);

   void method87(int var1, int var2, int var3, int var4);

   default Optional<Bridge_29> method88() {
      return Optional.empty();
   }

   default Bridge_35 method89(String var1, long var2, RenderSystemBridge.Type... var4) {
      throw new AbstractMethodErrorImpl();
   }

   default void method90(Bridge_35 var1, long var2, ByteBuffer var4) {
      throw new AbstractMethodErrorImpl();
   }

   default void method91(Bridge_35 var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   interface Extension extends RenderSystemBridge {
      int method6();

      void method2(int var1);

      void method3(int var1);

      @com.moonsworth.lunar.ichor.Annotation2(min = 1, max = 5)
      default Bridge_53 method7() {
         throw new UnsupportedOperationException();
      }

      @com.moonsworth.lunar.ichor.Annotation2(min = 1, max = 5)
      default void method8() {
         throw new UnsupportedOperationException();
      }
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   interface Extension2 extends RenderSystemBridge {
      Bridge_28 method1(AbstractRenderContext var1, float var2, boolean var3);

      MixinHelper_21 method2(float var1, float var2, float var3, float var4);

      void method6();

      void method7();

      void method8();

      MixinHelper_21 method10();

      void method7(MixinHelper_21 var1, @Nullable BridgeType var2);

      @com.moonsworth.lunar.ichor.Annotation2(min = 8)
      Bridge5_16 method12();

      @com.moonsworth.lunar.ichor.Annotation2(min = 8)
      MixinHelper_21 method15();

      Bridge2_5 method10(@NotNull RenderLayerBridge var1, Consumer<Bridge4_6> var2, boolean var3);

      void method11(Bridge4Extension var1, @Nullable BridgeType var2, @Nullable Vector3f var3);

      @Contract("_,_,false -> param1; _,_,true -> new")
      @Nullable
      Bridge2_5 method12(Bridge2_5 var1, Bridge4Extension var2, boolean var3, RenderLayerBridge var4);

      @com.moonsworth.lunar.ichor.Annotation2(min = 8, max = 29)
      default Vector3f[] method16() {
         return null;
      }

      @com.moonsworth.lunar.ichor.Annotation2(min = 8, max = 29)
      default void method14(Vector3f var1, Vector3f var2) {
      }

      void method16(int var1, int var2);

      void method16(int var1, int var2, int var3);

      void method17(int var1, ByteBuffer var2, int var3);

      @com.moonsworth.lunar.ichor.Annotation2(min = 8)
      int method18();

      void method20();
   }

   enum Type {
      COPY_DST(8),
      COPY_SRC(16),
      VERTEX(32),
      INDEX(64),
      UNIFORM(128),
      STORAGE_BUFFER(1073741824);

      public final int mask;

      Type(int var3) {
         this.mask = var3;
      }
   }
}
