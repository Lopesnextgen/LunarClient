package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.TexturedBoxRenderer;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter4;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.bridge.lighting.Lighting;
import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.config.Config;
import io.netty.buffer.ByteBuf;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.joml.Vector3ic;

public interface BridgeImplementation {
   void enable();

   default void method1(BridgeImplementation.Extension var1) {
   }

   default void method2(BridgeImplementation.Extension var1) {
   }

   void method3();

   Config getMinecraftVersion();

   Horsestats20Extension2 method4(int var1, int var2, int var3);

   default Horsestats20Extension2 method5(Vector3ic var1) {
      return this.method4(var1.x(), var1.y(), var1.z());
   }

   default Horsestats20Extension2 method6(double var1, double var3, double var5) {
      return this.method4((int)Math.floor(var1), (int)Math.floor(var3), (int)Math.floor(var5));
   }

   default Horsestats20Extension2 method7(Vec3Bridge var1) {
      return this.method4((int)Math.floor(var1.bridge$xCoord()), (int)Math.floor(var1.bridge$yCoord()), (int)Math.floor(var1.bridge$zCoord()));
   }

   default Horsestats20Extension2 method8(Vector3d var1) {
      return this.method4((int)Math.floor(var1.x), (int)Math.floor(var1.y), (int)Math.floor(var1.z));
   }

   Vector3iBridge.Extension method9(int var1, int var2, int var3);

   default Vector3iBridge.Extension method10(double var1, double var3, double var5) {
      return this.method9((int)Math.floor(var1), (int)Math.floor(var3), (int)Math.floor(var5));
   }

   Horsestats20Extension method11(int var1, int var2, int var3);

   MixinHelper_15 method12(String var1, int var2, String var3, boolean var4);

   Bridge2_42 method13(String var1);

   default Bridge20Extension method14(File var1, String var2, ResourceLocationBridge var3, ResourceLocationBridge var4) {
      return this.method15(var1, var2, var3, var4, false);
   }

   Bridge20Extension method15(File var1, String var2, ResourceLocationBridge var3, ResourceLocationBridge var4, boolean var5);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   AutoCloseableExtension method16(NativeImageBridge var1);

   Horsestats method17(String var1, String var2, String var3, String var4);

   Bridge5Extension62 method18(Bridge7_8 var1);

   Bridge5Extension6 method19();

   Bridge4_11 method20();

   Bridge8Extension33 method21(int var1, int var2);

   Bridge8Extension33 method22(BufferedImage var1);

   Bridge7_9 method23(ByteBuf var1);

   Bridge3Extension_2 method24(String var1, Bridge7_9 var2);

   Bridge3Extension_6 method25(String var1, Bridge7_9 var2);

   Bridge3_23 method26(Bridge6_4 var1);

   Bridge3_30 method27();

   Bridge5Extension67 method28(@Nullable Bridge5Extension6 var1);

   Bridge5Extension65 method29(@Nullable Bridge5Extension6 var1);

   Bridge5Extension64 method30(@Nullable Bridge5Extension6 var1);

   Bridge5Extension63 method31(@Nullable Bridge5Extension6 var1);

   Bridge5Extension6 method32(@Nullable Bridge5Extension6 var1);

   Bridge5Extension662 method33(@Nullable Bridge5Extension6 var1, String var2, URI var3, boolean var4);

   Bridge5Extension66 method34(String var1, String var2, String var3, String var4, Runnable var5, Runnable var6);

   Bridge5Extension68 method35(Runnable var1, String var2, String var3);

   @com.moonsworth.lunar.ichor.Annotation2(max = 23)
   Bridge5Extension_2 method36(@Nullable Bridge5Extension6 var1);

   void method37();

   ItemStackBridge method38(Bridge6_4 var1);

   ItemStackBridge method39(Bridge3_23 var1);

   ItemStackBridge method40(Bridge_57 var1);

   ItemStackBridge method41();

   List<Bridge2_11> method42();

   Lighting4 method43();

   Lighting method44(Lighting4 var1, String var2);

   AxisAlignedBBBridge method45(double var1, double var3, double var5, double var7, double var9, double var11);

   Itemcounter4Extension method46(String var1, int var2);

   Bridge8Extension34 method47(Bridge3_4 var1);

   Bridge5Extension_5 method48();

   MovementStateBridge method49(GameOptionsBridge var1);

   Bridge3_19 method50(String var1, String var2, boolean var3);

   Bridge4_4 method51();

   @com.moonsworth.lunar.ichor.Annotation2(max = 5)
   Bridge2_23 method52();

   @NotNull
   TexturedBoxRenderer method53(int var1, int var2);

   Bridge4_21 method54();

   BufferedImage method55(InputStream var1);

   Bridge3_24 method56(int var1, int var2, boolean var3);

   Bridge3_24 method57(Bridge_52 var1);

   Bridge3_24 method58(int var1, int var2);

   Bridge7_10 method59(Bridge3_24 var1, ResourceLocationBridge var2);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default NativeImageBridge method60(int var1, int var2) {
      throw new AbstractMethodErrorImpl();
   }

   default Bridge5_16 method61() {
      return null;
   }

   @Nullable
   default Bridge3_10 method62(MixinHelper_21 var1, Matrix3fBridge var2) {
      return null;
   }

   Bridge6_2 method63();

   boolean method64(URI var1);

   boolean method65(String var1);

   boolean method66(File var1);

   void method67();

   Bridge_57 method68();

   @com.moonsworth.lunar.ichor.Annotation2(min = 1)
   Optional<Bridge_57> method69(String var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 8)
   default Bridge4_25 method70(Bridge4_25 var1, boolean var2) {
      throw new RuntimeException("initOptionFieldAccess()");
   }

   String method71(String var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Itemcounter_4 method72(AxisAlignedBBBridge var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Bridge4Extension method73(RenderLayerBridge var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   @Nullable
   default Bridge2_5 method74(boolean var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   @Nullable
   default BatchingBufferSourceBridge method75(Function<RenderLayerBridge, Bridge4_6> var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   @Nullable
   default Bridge17Extension method76(Function<RenderLayerBridge, Bridge4_6> var1) {
      throw new AbstractMethodErrorImpl();
   }

   NetworkConnectionBridge method77(UUID var1, String var2, String var3);

   BridgeExtension2_5 method78(Itemcounter6 var1);

   IResourcePackBridge method79(File var1, boolean var2);

   RenderPipelineBuilder method80();

   RenderTypeBuilder method81();

   Itemcounter4 method82(String var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 17)
   default Bridge8_2 method83() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 17)
   default Bridge8_2 method84() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(1)
   Bridge_46 method85();

   @com.moonsworth.lunar.ichor.Annotation2(min = 6, max = 34)
   default RenderStateLifecycleBridge method86(String var1) {
      throw new AbstractMethodErrorImpl();
   }

   default Bridge6_8 method87(String var1, Bridge_63 var2) {
      throw new AbstractMethodErrorImpl();
   }

   Bridge5Extension612 method88(String var1);

   MixinHelper$Extension3 method89(Component var1);

   MixinHelper$Extension2 method90(MixinHelper$Extension var1, int var2, int var3);

   default void method91(Bridge8_7 var1, int var2, int var3, int var4, int var5, TexturePixelFormat var6, Consumer<ByteBuffer> var7) {
      throw new AbstractMethodErrorImpl();
   }

   default int method92() {
      return 15728880;
   }

   OutputStateShardBridge method93(String var1, Supplier<Bridge3_24> var2);

   IResourceBridge method94(ResourceLocationBridge var1, Supplier<InputStream> var2);

   @FunctionalInterface
   interface Extension {
      void register(Bridge_45... var1);
   }
}
