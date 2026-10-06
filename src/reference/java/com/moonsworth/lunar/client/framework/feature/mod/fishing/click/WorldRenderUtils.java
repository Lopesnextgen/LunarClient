package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.optifine.ShadersBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.config.GeneralSettings.Type;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.NameplateComponent;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.math.LineSegment;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.math.FastMath;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import toxi.geom.Line3D;
import toxi.geom.Vec3D;

public final class WorldRenderUtils {
   public static void drawBoxAtCoordinate(AbstractRenderContext bridgeextension_90, Vector3ic vector3ic1) {
      drawBoxAtCoordinate(bridgeextension_90, vector3ic1, 570490624);
   }

   public static void drawBoxAtCoordinate(AbstractRenderContext bridgeextension_90, Vector3ic vector3ic1, int number2) {
      drawBoxAtCoordinate(bridgeextension_90, vector3ic1, number2, false);
   }

   public static void drawBoxAtCoordinate(AbstractRenderContext bridgeextension_90, Vector3ic vector3ic1, int number2, boolean flag3) {
      drawFancyBox(bridgeextension_90, AxisAlignedBBBridge.method2(vector3ic1.x(), vector3ic1.y(), vector3ic1.z(), vector3ic1.x() + 1, vector3ic1.y() + 1, vector3ic1.z() + 1).method11(0.01F), number2, flag3);
   }

   public static void drawFancyBox(AbstractRenderContext bridgeextension_90, AxisAlignedBBBridge horsestats121, int number2, boolean flag3) {
      drawFancyBox(bridgeextension_90, horsestats121, number2, flag3, (Float)Ref.method4().method40().method82().method19().get());
   }

   public static void drawFancyBox(AbstractRenderContext bridgeextension_90, AxisAlignedBBBridge horsestats121, int number2, boolean flag3, float value4) {
      DrawBufferBridge bridge2_325 = bridgeextension_90.method10(flag3 ? LunarRenderTypes.field52 : LunarRenderTypes.field15);
      bridge2_325.method1();
      fillBox(
         bridge2_325, horsestats121.bridge$getMinX(), horsestats121.bridge$getMinY(), horsestats121.bridge$getMinZ(), horsestats121.bridge$getMaxX(), horsestats121.bridge$getMaxY(), horsestats121.bridge$getMaxZ(), number2
      );
      bridge2_325.method17(BufferMode.BATCHED);
      BufferBuilderBridge bridge_286 = bridgeextension_90.method12(value4, flag3);
      drawBoxOutline(
         bridge_286,
         horsestats121.bridge$getMinX(),
         horsestats121.bridge$getMinY(),
         horsestats121.bridge$getMinZ(),
         horsestats121.bridge$getMaxX(),
         horsestats121.bridge$getMaxY(),
         horsestats121.bridge$getMaxZ(),
         0xFF000000 | number2
      );
      bridge_286.end();
   }

   public static void drawFilledBox(AbstractRenderContext bridgeextension_90, AxisAlignedBBBridge horsestats121, int number2, boolean flag3) {
      drawFilledBox(bridgeextension_90, horsestats121, number2, flag3 ? LunarRenderTypes.field52 : LunarRenderTypes.field15);
   }

   public static void drawFilledBox(AbstractRenderContext bridgeextension_90, AxisAlignedBBBridge horsestats121, int number2, RenderTypeBridge bridge203) {
      DrawBufferBridge bridge2_324 = bridgeextension_90.method10(bridge203);
      bridge2_324.method1();
      fillBox(
         bridge2_324, horsestats121.bridge$getMinX(), horsestats121.bridge$getMinY(), horsestats121.bridge$getMinZ(), horsestats121.bridge$getMaxX(), horsestats121.bridge$getMaxY(), horsestats121.bridge$getMaxZ(), number2
      );
      bridge2_324.method17(BufferMode.BATCHED);
   }

   public static void highlightBlockFace(AbstractRenderContext bridgeextension_90, Vector3i vector3i1, RewindhandlersExtension rewindhandlersextension2, HorsestatsType_2 horsestatstype_23) {
      int number4 = ColorUtils.method18(rewindhandlersextension2.method1(0.0F), 0.66F);
      DrawBufferBridge bridge2_325 = bridgeextension_90.method10(LunarRenderTypes.field15);
      bridge2_325.method1();
      bridgeextension_90.translate(vector3i1.x, vector3i1.y, vector3i1.z);
      switch (horsestatstype_23) {
         case NORTH:
            bridge2_325.method2(0.0, 0.0, -0.01).method9(number4).method16();
            bridge2_325.method2(0.0, 1.0, -0.01).method9(number4).method16();
            bridge2_325.method2(1.0, 1.0, -0.01).method9(number4).method16();
            bridge2_325.method2(1.0, 0.0, -0.01).method9(number4).method16();
            break;
         case SOUTH:
            bridge2_325.method2(0.0, 0.0, 1.01).method9(number4).method16();
            bridge2_325.method2(1.0, 0.0, 1.01).method9(number4).method16();
            bridge2_325.method2(1.0, 1.0, 1.01).method9(number4).method16();
            bridge2_325.method2(0.0, 1.0, 1.01).method9(number4).method16();
            break;
         case EAST:
            bridge2_325.method2(1.01, 0.0, 0.0).method9(number4).method16();
            bridge2_325.method2(1.01, 1.0, 0.0).method9(number4).method16();
            bridge2_325.method2(1.01, 1.0, 1.0).method9(number4).method16();
            bridge2_325.method2(1.01, 0.0, 1.0).method9(number4).method16();
            break;
         case WEST:
            bridge2_325.method2(-0.01, 0.0, 0.0).method9(number4).method16();
            bridge2_325.method2(-0.01, 0.0, 1.0).method9(number4).method16();
            bridge2_325.method2(-0.01, 1.0, 1.0).method9(number4).method16();
            bridge2_325.method2(-0.01, 1.0, 0.0).method9(number4).method16();
            break;
         case UP:
            bridge2_325.method2(0.0, 1.01, 0.0).method9(number4).method16();
            bridge2_325.method2(0.0, 1.01, 1.0).method9(number4).method16();
            bridge2_325.method2(1.0, 1.01, 1.0).method9(number4).method16();
            bridge2_325.method2(1.0, 1.01, 0.0).method9(number4).method16();
            break;
         case DOWN:
            bridge2_325.method2(0.0, -0.01, 0.0).method9(number4).method16();
            bridge2_325.method2(1.0, -0.01, 0.0).method9(number4).method16();
            bridge2_325.method2(1.0, -0.01, 1.0).method9(number4).method16();
            bridge2_325.method2(0.0, -0.01, 1.0).method9(number4).method16();
      }

      bridge2_325.method17(BufferMode.BATCHED);
      bridgeextension_90.translate(-vector3i1.x, -vector3i1.y, -vector3i1.z);
   }

   public static void drawString(AbstractRenderContext bridgeextension_90, String text1, double value2, double value4, double value6, int number8, boolean flag9) {
      Bridge10_2 bridge10_210 = Ref.method10();
      if (bridge10_210 != null) {
         applyBillboardTransform(bridgeextension_90, value2, value4, value6, false);
         bridge10_210.method7(bridgeextension_90, text1, -bridge10_210.bridge$getStringWidth(text1) / 2.0F, 0.0F, number8, flag9);
         bridgeextension_90.pop();
      }
   }

   public static void drawComponent(AbstractRenderContext bridgeextension_90, Component component1, double value2, double value4, double value6, boolean flag8) {
      drawComponent(bridgeextension_90, component1, value2, value4, value6, flag8, 1.0F, false);
   }

   public static void drawComponent(AbstractRenderContext bridgeextension_90, Component component1, double value2, double value4, double value6, boolean flag8, float value9) {
      drawComponent(bridgeextension_90, component1, value2, value4, value6, flag8, value9, false);
   }

   public static void drawComponent(AbstractRenderContext bridgeextension_90, Component component1, double value2, double value4, double value6, boolean flag8, float value9, boolean flag10) {
      Bridge10_2 bridge10_211 = Ref.method10();
      if (bridge10_211 != null) {
         applyBillboardTransform(bridgeextension_90, value2, value4, value6, flag10);
         bridgeextension_90.scale(value9, value9, value9);
         bridge10_211.method11(bridgeextension_90, component1, -bridge10_211.bridge$getStringWidth(component1) / 2.0F, 0.0F, -1, flag8);
         bridgeextension_90.pop();
      }
   }

   private static void applyBillboardTransform(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, boolean flag7) {
      EntityRenderDispatcherBridge bridge2_438 = Ref.method13();
      float value9 = 1.6F;
      float value10 = 0.016666668F * value9;
      if (!Bridge.getMinecraftVersion().method23()) {
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      }

      bridgeextension_90.push();
      bridgeextension_90.translate(value1, value3, value5);
      bridgeextension_90.method4((float)(-bridge2_438.bridge$playerViewY()), 0.0F, 1.0F, 0.0F);
      bridgeextension_90.method4((float)bridge2_438.bridge$playerViewX(), 1.0F, 0.0F, 0.0F);
      bridgeextension_90.scale(-value10, -value10, value10);
      if (bridgeextension_90.method39()) {
         bridgeextension_90.method14();
         bridgeextension_90.method4(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ZERO);
      }
   }

   public static void highlightBlockAt(AbstractRenderContext bridgeextension_90, Vector3i vector3i1, int number2) {
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (itemcounter6extension3 != null) {
         Bridge3_23 bridge3_234 = itemcounter6extension3.bridge$getBlockAt(vector3i1.x(), vector3i1.y(), vector3i1.z());
         if (!bridge3_234.bridge$isAir()) {
            DrawBufferBridge bridge2_325 = bridgeextension_90.method10(LunarRenderTypes.field52);
            bridge2_325.method1();
            AxisAlignedBBBridge horsestats126 = bridge3_234.bridge$getAABB(itemcounter6extension3, Bridge.method8().method4(vector3i1.x(), vector3i1.y(), vector3i1.z()))
               .bridge$offset(vector3i1.x(), vector3i1.y(), vector3i1.z())
               .method11(0.01);
            fillBox(
               bridge2_325,
               horsestats126.bridge$getMinX(),
               horsestats126.bridge$getMinY(),
               horsestats126.bridge$getMinZ(),
               horsestats126.bridge$getMaxX(),
               horsestats126.bridge$getMaxY(),
               horsestats126.bridge$getMaxZ(),
               number2
            );
            bridge2_325.method17(BufferMode.BATCHED);
            BufferBuilderBridge bridge_287 = bridgeextension_90.method12((Float)Ref.method4().method40().method82().method19().get(), true);
            drawBoxOutline(
               bridge_287,
               horsestats126.bridge$getMinX(),
               horsestats126.bridge$getMinY(),
               horsestats126.bridge$getMinZ(),
               horsestats126.bridge$getMaxX(),
               horsestats126.bridge$getMaxY(),
               horsestats126.bridge$getMaxZ(),
               0xFF000000 | number2
            );
            bridge_287.end();
         }
      }
   }

   public static void renderDebugLine(AbstractRenderContext bridgeextension_90, Vector3dc vector3dc1, Vector3dc vector3dc2, int number3) {
      BufferBuilderBridge bridge_284 = bridgeextension_90.method12(1.0F, true);
      bridge_284.method1(number3);
      bridge_284.method3(vector3dc1.x(), vector3dc1.y(), vector3dc1.z(), vector3dc2.x(), vector3dc2.y(), vector3dc2.z());
      bridge_284.end();
   }

   public static void renderDebugLine(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, double value7, double value9, double value11, int number13) {
      BufferBuilderBridge bridge_2814 = bridgeextension_90.method12(1.0F, true);
      bridge_2814.method1(number13);
      bridge_2814.method3(value1, value3, value5, value7, value9, value11);
      bridge_2814.end();
   }

   public static void renderLines(AbstractRenderContext bridgeextension_90, Collection<Line3D> list1, int number2) {
      BufferBuilderBridge bridge_283 = bridgeextension_90.method12(1.0F, true);
      bridge_283.method1(number2);

      for (Line3D line3d5 : list1) {
         bridge_283.method3(line3d5.a.x(), line3d5.a.y(), line3d5.a.z(), line3d5.b.x(), line3d5.b.y(), line3d5.b.z());
      }

      bridge_283.end();
   }

   public static void renderLinesJoml(AbstractRenderContext bridgeextension_90, Set<LineSegment> set1, int number2) {
      BufferBuilderBridge bridge_283 = bridgeextension_90.method12(1.0F, true);
      bridge_283.method1(number2);

      for (LineSegment fishing5 : set1) {
         bridge_283.method3(fishing5.method1().x(), fishing5.method1().y(), fishing5.method1().z(), fishing5.method2().x(), fishing5.method2().y(), fishing5.method2().z());
      }

      bridge_283.end();
   }

   public static void renderLineToBuffer(AbstractRenderContext bridgeextension_90, Line3D line3d1, int number2, float value3, boolean flag4) {
      BufferBuilderBridge bridge_285 = bridgeextension_90.method12(value3, flag4);
      bridge_285.method1(number2);
      addLineVertices(bridge_285, line3d1);
      bridge_285.end();
   }

   public static void renderLine(
      AbstractRenderContext bridgeextension_90, float value1, float value2, float value3, float value4, float value5, float value6, int number7, float value8, boolean flag9
   ) {
      renderLineToBuffer(bridgeextension_90, new Line3D(new Vec3D(value1, value2, value3), new Vec3D(value4, value5, value6)), number7, value8, flag9);
   }

   public static void renderThickLines(AbstractRenderContext bridgeextension_90, Set<Line3D> set1, int number2, float value3, boolean flag4) {
      BufferBuilderBridge bridge_285 = bridgeextension_90.method12(value3, flag4);
      bridge_285.method1(number2);

      for (Line3D line3d7 : set1) {
         addLineVertices(bridge_285, line3d7);
      }

      bridge_285.end();
   }

   private static void addLineVertices(BufferBuilderBridge bridge_280, Line3D line3d1) {
      Vec3D vec3d2 = line3d1.b.sub(line3d1.a).normalize();
      float value3 = line3d1.a.distanceTo(line3d1.b);

      byte number4;
      for (number4 = 0; number4 + 2 < value3; number4 += 2) {
         Vec3D vec3d5 = line3d1.a.add(vec3d2.scale(number4));
         Vec3D vec3d6 = line3d1.a.add(vec3d2.scale(number4 + 2));
         bridge_280.method3(vec3d5.x(), vec3d5.y(), vec3d5.z(), vec3d6.x(), vec3d6.y(), vec3d6.z());
      }

      Vec3D vec3d7 = line3d1.a.add(vec3d2.scale(number4));
      bridge_280.method3(vec3d7.x(), vec3d7.y(), vec3d7.z(), line3d1.b.x(), line3d1.b.y(), line3d1.b.z());
   }

   public static void drawBoxOutline(BufferBuilderBridge bridge_280, AxisAlignedBBBridge horsestats121, int number2) {
      drawBoxOutline(
         bridge_280, horsestats121.bridge$getMinX(), horsestats121.bridge$getMinY(), horsestats121.bridge$getMinZ(), horsestats121.bridge$getMaxX(), horsestats121.bridge$getMaxY(), horsestats121.bridge$getMaxZ(), number2
      );
   }

   public static void drawBoxOutline(BufferBuilderBridge bridge_280, double value1, double value3, double value5, double value7, double value9, double value11, int number13) {
      bridge_280.method1(number13);
      bridge_280.method3(value1, value3, value5, value7, value3, value5);
      bridge_280.method3(value7, value3, value5, value7, value3, value11);
      bridge_280.method3(value7, value3, value11, value1, value3, value11);
      bridge_280.method3(value1, value3, value5, value1, value3, value11);
      bridge_280.method3(value1, value9, value5, value7, value9, value5);
      bridge_280.method3(value7, value9, value5, value7, value9, value11);
      bridge_280.method3(value7, value9, value11, value1, value9, value11);
      bridge_280.method3(value1, value9, value5, value1, value9, value11);
      bridge_280.method3(value1, value3, value5, value1, value9, value5);
      bridge_280.method3(value7, value3, value5, value7, value9, value5);
      bridge_280.method3(value1, value3, value11, value1, value9, value11);
      bridge_280.method3(value7, value3, value11, value7, value9, value11);
   }

   public static void fillBox(DrawBufferBridge bridge2_320, AxisAlignedBBBridge horsestats121, int number2) {
      fillBox(
         bridge2_320, horsestats121.bridge$getMinX(), horsestats121.bridge$getMinY(), horsestats121.bridge$getMinZ(), horsestats121.bridge$getMaxX(), horsestats121.bridge$getMaxY(), horsestats121.bridge$getMaxZ(), number2
      );
   }

   public static void fillBox(DrawBufferBridge bridge2_320, double value1, double value3, double value5, double value7, double value9, double value11, int number13) {
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
   }

   public static void fillBoxSingleSided(DrawBufferBridge bridge2_320, double value1, double value3, double value5, double value7, double value9, double value11, int number13) {
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value3, value5).method9(number13).method16();
      bridge2_320.method2(value1, value3, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value11).method9(number13).method16();
      bridge2_320.method2(value1, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value3, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value5).method9(number13).method16();
      bridge2_320.method2(value7, value9, value11).method9(number13).method16();
      bridge2_320.method2(value7, value3, value11).method9(number13).method16();
   }

   public static void draw3DCircle(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, double value7, float value9, int number10) {
      BufferBuilderBridge bridge_2811 = bridgeextension_90.method11(value9);
      bridge_2811.method1(number10);
      List list12 = createCirclePoints(value1, value3, value5, value7);

      for (int index13 = 0; index13 < list12.size() - 1; index13++) {
         Vector3d vector3d14 = (Vector3d)list12.get(index13);
         Vector3d vector3d15 = (Vector3d)list12.get(index13 + 1);
         bridge_2811.method3(vector3d14.x(), vector3d14.y(), vector3d14.z(), vector3d15.x(), vector3d15.y(), vector3d15.z());
      }

      bridge_2811.end();
   }

   public static void drawFilled3DCircle(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, double value7, int number9) {
      bridgeextension_90.method21();
      bridgeextension_90.method13();
      DrawBufferBridge bridge2_3210 = bridgeextension_90.method10(LunarRenderTypes.field16);
      bridge2_3210.method1();
      List list11 = createCirclePoints(value1, value3, value5, value7);

      for (int index12 = 0; index12 < list11.size() - 1; index12++) {
         Vector3d vector3d13 = (Vector3d)list11.get(index12);
         Vector3d vector3d14 = (Vector3d)list11.get(index12 + 1);
         bridge2_3210.method2(value1, value3, value5).method9(number9).method16();
         bridge2_3210.method2(vector3d13.x(), vector3d13.y(), vector3d13.z()).method9(number9).method16();
         bridge2_3210.method2(vector3d14.x(), vector3d14.y(), vector3d14.z()).method9(number9).method16();
         bridge2_3210.method2(value1, value3, value5).method9(number9).method16();
      }

      bridge2_3210.method17(BufferMode.BATCHED);
      bridgeextension_90.method20();
   }

   public static Vec3Bridge getLookVector(Bridge6_10 bridge6_100) {
      float value1 = (float)bridge6_100.bridge$getRotationPitch();
      float value2 = (float)bridge6_100.bridge$getRotationYaw();
      float value3 = value1 * (float) (Math.PI / 180.0);
      float value4 = -value2 * (float) (Math.PI / 180.0);
      float value5 = (float)Math.cos(value4);
      float value6 = (float)Math.sin(value4);
      float value7 = (float)Math.cos(value3);
      float value8 = (float)Math.sin(value3);
      return Vec3Bridge.method2(value6 * value7, -value8, value5 * value7);
   }

   public static void drawBillboardText(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, String text7, boolean flag8, boolean flag9, boolean flag10) {
      drawBillboardText(bridgeextension_90, value1, value3, value5, text7, flag8, flag9, flag10, 1.0F);
   }

   public static void drawBillboardText(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, String text7, boolean flag8, boolean flag9) {
      drawBillboardText(bridgeextension_90, value1, value3, value5, text7, flag8, flag9, true, 1.0F);
   }

   public static void drawBillboardText(AbstractRenderContext bridgeextension_90, Vector3d vector3d1, String text2, boolean flag3, boolean flag4, boolean flag5) {
      drawBillboardText(bridgeextension_90, vector3d1.get(0), vector3d1.get(1), vector3d1.get(2), text2, flag3, flag4, flag5, 1.0F);
   }

   public static void drawBillboardText(
      AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, String text7, boolean flag8, boolean flag9, boolean flag10, float value11
   ) {
      drawBillboardText(bridgeextension_90, value1, value3, value5, text7, flag8, flag9, flag10, value11, RewindhandlersExtension.method24(ChatFormatting.RED));
   }

   public static void drawBillboardText(
      AbstractRenderContext bridgeextension_90,
      double value1,
      double value3,
      double value5,
      String text7,
      boolean flag8,
      boolean flag9,
      boolean flag10,
      float value11,
      RewindhandlersExtension rewindhandlersextension12
   ) {
      drawBillboardText(bridgeextension_90, value1, value3, value5, text7, flag8, flag9, flag10, value11, rewindhandlersextension12.getColor());
   }

   public static void drawBillboardText(
      AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, String text7, boolean flag8, boolean flag9, boolean flag10, float value11, int number12
   ) {
      Bridge10_2 bridge10_213 = Ref.method10();
      if (bridge10_213 != null) {
         EntityRenderDispatcherBridge bridge2_4314 = Ref.method13();
         Vector3d vector3d15 = new Vector3d(bridge2_4314.bridge$renderPosX(), bridge2_4314.bridge$renderPosY(), bridge2_4314.bridge$renderPosZ());
         if (flag10) {
            double value16 = vector3d15.distanceSquared(value1, value3, value5);
            if (value16 > 2500.0) {
               value11 *= 5.0F;
               Vector3d vector3d18 = new Vector3d(value1, value3, value5).sub(vector3d15).normalize(50.0).add(vector3d15);
               value1 = vector3d18.x();
               value3 = vector3d18.y();
               value5 = vector3d18.z();
            } else {
               value11 = (float)(value11 * Math.max(1.0, Math.sqrt(value16) / 10.0));
            }
         }

         bridgeextension_90.push();
         bridgeextension_90.translate(-bridge2_4314.bridge$renderPosX(), -bridge2_4314.bridge$renderPosY(), -bridge2_4314.bridge$renderPosZ());
         applyBillboardTransform(bridgeextension_90, value1, value3, value5, !flag9);
         bridgeextension_90.scale(value11, value11, value11);
         bridge10_213.method7(bridgeextension_90, text7, -bridge10_213.bridge$getStringWidth(text7) / 2.0F, 0.0F, number12, flag8);
         bridgeextension_90.pop();
         bridgeextension_90.pop();
      }
   }

   public static void drawBoxAt(AbstractRenderContext bridgeextension_90, Vec3iBridge horsestats201, RewindhandlersExtension rewindhandlersextension2, boolean flag3, boolean flag4) {
      drawBoxAt(bridgeextension_90, horsestats201.bridge$getX(), horsestats201.bridge$getY(), horsestats201.bridge$getZ(), 1.0, 1.0, 1.0, rewindhandlersextension2, flag3, flag4);
   }

   public static void drawBoxAt(AbstractRenderContext bridgeextension_90, Vec3iBridge horsestats201, RewindhandlersExtension rewindhandlersextension2, float value3, boolean flag4, boolean flag5) {
      drawBoxAt(bridgeextension_90, horsestats201.bridge$getX(), horsestats201.bridge$getY(), horsestats201.bridge$getZ(), 1.0, 1.0, 1.0, rewindhandlersextension2.getColor(), flag4, value3, flag5);
   }

   public static void drawBoxAtEntity(
      AbstractRenderContext bridgeextension_90, BridgeExtension bridgeextension1, double value2, double value4, RewindhandlersExtension rewindhandlersextension6, boolean flag7, boolean flag8, float value9
   ) {
      drawBoxAtEntity(bridgeextension_90, bridgeextension1, value2, value4, rewindhandlersextension6, 1.0F, flag7, flag8, value9);
   }

   public static void drawBoxAtEntity(
      AbstractRenderContext bridgeextension_90, BridgeExtension bridgeextension1, double value2, double value4, RewindhandlersExtension rewindhandlersextension6, float value7, boolean flag8, boolean flag9, float value10
   ) {
      double value11 = bridgeextension1.method8(value10);
      double value13 = bridgeextension1.method9(value10);
      double value15 = bridgeextension1.method10(value10);
      drawBoxAt(bridgeextension_90, value11 - value2 / 2.0, value13, value15 - value2 / 2.0, value2, value4, value2, rewindhandlersextension6.getColor(), flag8, value7, flag9);
   }

   public static void drawBoxAt(AbstractRenderContext bridgeextension_90, Vector3ic vector3ic1, RewindhandlersExtension rewindhandlersextension2, boolean flag3, boolean flag4) {
      drawBoxAt(bridgeextension_90, vector3ic1.get(0), vector3ic1.get(1), vector3ic1.get(2), 1.0, 1.0, 1.0, rewindhandlersextension2, flag3, flag4);
   }

   public static void drawBoxAt(AbstractRenderContext bridgeextension_90, Vector3ic vector3ic1, RewindhandlersExtension rewindhandlersextension2, float value3, boolean flag4, boolean flag5) {
      drawBoxAt(bridgeextension_90, vector3ic1.get(0), vector3ic1.get(1), vector3ic1.get(2), 1.0, 1.0, 1.0, rewindhandlersextension2.getColor(), flag4, value3, flag5);
   }

   public static void drawBoxAt(AbstractRenderContext bridgeextension_90, Vector3dc vector3dc1, RewindhandlersExtension rewindhandlersextension2, boolean flag3, boolean flag4) {
      drawBoxAt(bridgeextension_90, vector3dc1.get(0), vector3dc1.get(1), vector3dc1.get(2), 1.0, 1.0, 1.0, rewindhandlersextension2, flag3, flag4);
   }

   public static void drawBoxAt(AbstractRenderContext bridgeextension_90, Vec3iBridge horsestats201, RewindhandlersExtension rewindhandlersextension2, float value3, boolean flag4) {
      drawBoxAt(bridgeextension_90, horsestats201.bridge$getX(), horsestats201.bridge$getY(), horsestats201.bridge$getZ(), 1.0, 1.0, 1.0, rewindhandlersextension2.getColor(), false, value3, flag4);
   }

   public static void drawBoxAt(
      AbstractRenderContext bridgeextension_90,
      double value1,
      double value3,
      double value5,
      double value7,
      double value9,
      double value11,
      RewindhandlersExtension rewindhandlersextension13,
      boolean flag14,
      boolean flag15
   ) {
      drawBoxAt(bridgeextension_90, value1, value3, value5, value7, value9, value11, rewindhandlersextension13.getColor(), flag14, 1.0F, flag15);
   }

   public static void drawBoxAt(
      AbstractRenderContext bridgeextension_90,
      double value1,
      double value3,
      double value5,
      double value7,
      double value9,
      double value11,
      int number13,
      boolean flag14,
      float value15,
      boolean flag16
   ) {
      EntityRenderDispatcherBridge bridge2_4317 = Ref.method13();
      bridgeextension_90.push();
      bridgeextension_90.translate(-bridge2_4317.bridge$renderPosX(), -bridge2_4317.bridge$renderPosY(), -bridge2_4317.bridge$renderPosZ());
      if (flag14) {
         DrawBufferBridge bridge2_3218 = bridgeextension_90.method10(flag16 ? LunarRenderTypes.field15 : LunarRenderTypes.field52);
         bridge2_3218.method1();
         fillBox(bridge2_3218, value1 - 0.005, value3 - 0.005, value5 - 0.005, value1 + value7 + 0.005, value3 + value9 + 0.005, value5 + value11 + 0.005, number13);
         bridge2_3218.method17(BufferMode.BATCHED);
      } else {
         BufferBuilderBridge bridge_2819 = bridgeextension_90.method12(value15, !flag16);
         drawBoxOutline(bridge_2819, value1 - 0.005, value3 - 0.005, value5 - 0.005, value1 + value7 + 0.005, value3 + value9 + 0.005, value5 + value11 + 0.005, number13);
         bridge_2819.end();
      }

      bridgeextension_90.pop();
   }

   public static void drawLine(AbstractRenderContext bridgeextension_90, Vector3dc vector3dc1, Vector3dc vector3dc2, RewindhandlersExtension rewindhandlersextension3, float value4, boolean flag5) {
      drawLine(bridgeextension_90, vector3dc1.x(), vector3dc1.y(), vector3dc1.z(), vector3dc2.x(), vector3dc2.y(), vector3dc2.z(), rewindhandlersextension3.method1(0.0F), value4, flag5, false);
   }

   public static void drawLine(
      AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, double value7, double value9, double value11, int number13, float value14, boolean flag15
   ) {
      drawLine(bridgeextension_90, value1, value3, value5, value7, value9, value11, number13, value14, flag15, false);
   }

   public static void drawLine(
      AbstractRenderContext bridgeextension_90,
      double value1,
      double value3,
      double value5,
      double value7,
      double value9,
      double value11,
      RewindhandlersExtension rewindhandlersextension13,
      float value14,
      boolean flag15
   ) {
      drawLine(bridgeextension_90, value1, value3, value5, value7, value9, value11, rewindhandlersextension13.getColor(), value14, flag15, false);
   }

   public static void drawLine(AbstractRenderContext bridgeextension_90, Vector3dc vector3dc1, Vector3dc vector3dc2, RewindhandlersExtension rewindhandlersextension3, float value4, boolean flag5, boolean flag6) {
      drawLine(bridgeextension_90, vector3dc1.x(), vector3dc1.y(), vector3dc1.z(), vector3dc2.x(), vector3dc2.y(), vector3dc2.z(), rewindhandlersextension3.method1(0.0F), value4, flag5, flag6);
   }

   public static void drawLine(
      AbstractRenderContext bridgeextension_90,
      double value1,
      double value3,
      double value5,
      double value7,
      double value9,
      double value11,
      int number13,
      float value14,
      boolean flag15,
      boolean flag16
   ) {
      EntityRenderDispatcherBridge bridge2_4317 = Ref.method13();
      bridgeextension_90.push();
      bridgeextension_90.translate(-bridge2_4317.bridge$renderPosX(), -bridge2_4317.bridge$renderPosY(), -bridge2_4317.bridge$renderPosZ());
      BufferBuilderBridge bridge_2818 = bridgeextension_90.method12(value14, !flag15);
      bridge_2818.method1(number13);
      Vector3d vector3d19 = null;
      if (flag16) {
         Vector3d vector3d20 = new Vector3d(value7 - value1, value9 - value3, value11 - value5);
         double value21 = vector3d20.length();
         vector3d20.normalize(Math.max(0.0, value21 - 0.3)).add(value1, value3, value5);
         vector3d19 = new Vector3d(value7, value9, value11);
         value7 = vector3d20.x;
         value9 = vector3d20.y;
         value11 = vector3d20.z;
      }

      bridge_2818.method3(value1, value3, value5, value7, value9, value11);
      bridge_2818.end();
      if (flag16) {
         drawLineEndArrow(bridgeextension_90, value1, value3, value5, vector3d19.x, vector3d19.y, vector3d19.z, number13, flag15);
      }

      bridgeextension_90.pop();
   }

   private static void drawLineEndArrow(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, double value7, double value9, double value11, int number13, boolean flag14) {
      Vector3d vector3d15 = new Vector3d(value1, value3, value5);
      Vector3d vector3d16 = new Vector3d(value7, value9, value11);
      double value17 = 0.3;
      double value19 = 0.2;
      Vector3d vector3d21 = vector3d16.sub(vector3d15, new Vector3d()).normalize(value17);
      Vector3d vector3d22 = vector3d21.cross(vector3d21.rotateAxis(Math.PI / 2, 1.0, 1.0, 1.0, new Vector3d()), new Vector3d()).normalize(value19 / 2.0);
      Vector3d vector3d23 = vector3d22.cross(vector3d21, new Vector3d()).normalize(value19 / 2.0);
      Vector3d vector3d24 = vector3d16.sub(vector3d21, new Vector3d());
      Vector3d vector3d25 = vector3d24.add(vector3d22, new Vector3d());
      Vector3d vector3d26 = vector3d24.sub(vector3d22, new Vector3d());
      Vector3d vector3d27 = vector3d24.add(vector3d23, new Vector3d());
      Vector3d vector3d28 = vector3d24.sub(vector3d23, new Vector3d());
      DrawBufferBridge bridge2_3229 = bridgeextension_90.method10(flag14 ? LunarRenderTypes.field23 : LunarRenderTypes.field19);
      bridge2_3229.method1();
      bridge2_3229.method2(vector3d16.x(), vector3d16.y(), vector3d16.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d25.x(), vector3d25.y(), vector3d25.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d26.x(), vector3d26.y(), vector3d26.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d25.x(), vector3d25.y(), vector3d25.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d16.x(), vector3d16.y(), vector3d16.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d26.x(), vector3d26.y(), vector3d26.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d16.x(), vector3d16.y(), vector3d16.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d27.x(), vector3d27.y(), vector3d27.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d28.x(), vector3d28.y(), vector3d28.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d27.x(), vector3d27.y(), vector3d27.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d16.x(), vector3d16.y(), vector3d16.z()).method9(number13).method16();
      bridge2_3229.method2(vector3d28.x(), vector3d28.y(), vector3d28.z()).method9(number13).method16();
      bridge2_3229.method17(BufferMode.BATCHED);
      bridgeextension_90.method20();
   }

   public static void drawLineFromCamera(Vec3D vec3d0, AbstractRenderContext bridgeextension_91, int number2, float value3) {
      MinecraftBridge bridge5_124 = Ref.method3();
      Bridge5Extension_5 bridge5extension_55 = Ref.method7();
      if (bridge5_124 != null && bridge5extension_55 != null) {
         EntityRenderDispatcherBridge bridge2_436 = Ref.method13();
         if (bridge2_436 != null) {
            Vec3D vec3d7 = new Vec3D((float)bridge2_436.bridge$renderPosX(), (float)bridge2_436.bridge$renderPosY(), (float)bridge2_436.bridge$renderPosZ());
            Vector3d vector3d8 = getLookVector(bridge5extension_55).method6().normalize(3.0);
            int number9 = bridge5_124.bridge$getGameSettings().bridge$getThirdPersonView();
            if (number9 == 2) {
               vec3d7 = vec3d7.sub((float)vector3d8.x(), (float)vector3d8.y(), (float)vector3d8.z());
            } else {
               vec3d7 = vec3d7.add((float)vector3d8.x(), (float)vector3d8.y(), (float)vector3d8.z());
            }

            bridgeextension_91.push();
            bridgeextension_91.translate(-bridge2_436.bridge$renderPosX(), -bridge2_436.bridge$renderPosY(), -bridge2_436.bridge$renderPosZ());
            renderLineToBuffer(bridgeextension_91, new Line3D(vec3d7, vec3d0), number2, value3, true);
            bridgeextension_91.pop();
         }
      }
   }

   public static void draw3DCylinder(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, double value7, double value9, int number11, boolean flag12) {
      bridgeextension_90.method21();
      bridgeextension_90.method13();
      RenderTypeBridge bridge2013 = flag12 ? LunarRenderTypes.field52 : LunarRenderTypes.field16;
      DrawBufferBridge bridge2_3214 = bridgeextension_90.method10(bridge2013);
      bridge2_3214.method1();
      List list15 = createCirclePoints(value1, value3, value5, value7);

      for (int index16 = 0; index16 < list15.size() - 1; index16++) {
         Vector3d vector3d17 = (Vector3d)list15.get(index16);
         Vector3d vector3d18 = (Vector3d)list15.get(index16 + 1);
         bridge2_3214.method2(vector3d17.x(), vector3d17.y(), vector3d17.z()).method9(number11).method16();
         bridge2_3214.method2(vector3d17.x(), vector3d17.y() + value9, vector3d17.z()).method9(number11).method16();
         bridge2_3214.method2(vector3d18.x(), vector3d18.y() + value9, vector3d18.z()).method9(number11).method16();
         bridge2_3214.method2(vector3d18.x(), vector3d18.y(), vector3d18.z()).method9(number11).method16();
      }

      bridge2_3214.method17(BufferMode.BATCHED);
      bridgeextension_90.method20();
   }

   public static List<Vector3d> createCirclePoints(double value0, double value2, double value4, double value6) {
      ArrayList list8 = new ArrayList(66);
      double value9 = Math.PI * 2;
      double value11 = value9 / 64.0;

      for (double value13 = 0.0; value13 < value9; value13 += value11) {
         list8.add(new Vector3d(value0 + value6 * FastMath.method1(-value13), value2, value4 + value6 * FastMath.sin(-value13)));
      }

      list8.add((Vector3d)list8.get(0));
      return list8;
   }

   public static AxisAlignedBBBridge getAABB(Itemcounter6 itemcounter60, Vector3i vector3i1) {
      Horsestats20Extension2 horsestats20extension22 = Bridge.method8().method4(vector3i1.x(), vector3i1.y(), vector3i1.z());
      AxisAlignedBBBridge horsestats123 = itemcounter60.method4(horsestats20extension22).bridge$getAABB(itemcounter60, horsestats20extension22);
      if (horsestats123 == null) {
         horsestats123 = Bridge.method8().method45(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
      }

      return horsestats123.method5(horsestats20extension22).method11(0.01);
   }

   public static void fadingBoxBeam(AbstractRenderContext bridgeextension_90, Vector3ic vector3ic1, int number2) {
      Vec3Bridge horsestats153 = Ref.method7().bridge$getEyePosition();
      double value4 = Math.hypot(vector3ic1.x() - horsestats153.bridge$xCoord(), vector3ic1.z() - horsestats153.bridge$zCoord());
      float value6 = 4.0F;
      float value7 = 4.0F;
      float value8 = (float)MathUtils.method2((value4 - value6) / value7, 0.0, 1.0);
      if (value8 != 0.0F) {
         double value9 = 0.05 + 0.02 * Math.sqrt(value4);
         drawFilledBox(
            bridgeextension_90,
            AxisAlignedBBBridge.method2(vector3ic1.x() + 0.5 - value9, vector3ic1.y() + 1, vector3ic1.z() + 0.5 - value9, vector3ic1.x() + 0.5 + value9, 255.0, vector3ic1.z() + 0.5 + value9),
            ColorUtils.method26(number2, value8),
            LunarRenderTypes.field53
         );
      }
   }

   public static void drawBeaconBeam(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, int number7) {
      WorldBridgeExtension itemcounter6extension8 = Ref.method8();
      if (itemcounter6extension8 != null) {
         drawBeaconBeam(bridgeextension_90, value1, itemcounter6extension8.bridge$getMinBuildHeight(), value3, value5, number7);
      }
   }

   public static void drawBeaconBeam(AbstractRenderContext bridgeextension_90, double value1, double value3, double value5, double value7, int number9) {
      WorldBridgeExtension itemcounter6extension10 = Ref.method8();
      if (itemcounter6extension10 != null) {
         Bridge.method5().ifPresent(arg0x -> {
            if (arg0x.getConfig().hasShaders()) {
               ShadersBridge slayer31x = arg0x.getShaders();
               slayer31x.pushUseProgram(slayer31x.getProgramBasic());
            }
         });
         com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::beginBeacon);
         EntityRenderDispatcherBridge bridge2_4311 = Ref.method3().bridge$getEntityRenderDispatcher();
         double value12 = bridge2_4311.bridge$renderPosX();
         double value14 = bridge2_4311.bridge$renderPosY();
         double value16 = bridge2_4311.bridge$renderPosZ();
         bridgeextension_90.push();
         double value18 = (float)value1 - value12;
         double value20 = (float)value7 - value16;
         bridgeextension_90.translate((float)value18, (float)(-value14), (float)value20);
         DrawBufferBridge bridge2_3222 = bridgeextension_90.method10(LunarRenderTypes.field49);
         float value23 = (number9 >> 24 & 0xFF) / 255.0F;
         float value24 = (number9 >> 16 & 0xFF) / 255.0F;
         float value25 = (number9 >> 8 & 0xFF) / 255.0F;
         float value26 = (number9 & 0xFF) / 255.0F;
         float value27 = 0.75F;
         float value28 = 0.0F;
         float value29 = (float)MathUtils.method2(
            Math.abs(itemcounter6extension10.bridge$getMinBuildHeight()) + Ref.method7().bridge$getPosY() + 50.0, value5, 256.0
         );
         byte number30 = 20;
         byte number31 = 1;
         float value32 = (float) (Math.PI * 2) / number30;
         float value33 = (value28 - value27) / number31;
         float value34 = value29 / number31;
         float value35 = value27;

         for (int index36 = 0; index36 < number31; index36++) {
            bridge2_3222.method1();

            for (int index37 = 0; index37 < number30; index37++) {
               float value38 = (float)FastMath.sin(index37 * value32);
               float value39 = (float)FastMath.method1(index37 * value32);
               float value40 = (float)FastMath.sin((index37 + 1) * value32);
               float value41 = (float)FastMath.method1((index37 + 1) * value32);
               bridge2_3222.method2(value38 * (value35 + value33), value3 + value34, value39 * (value35 + value33)).method8(value24, value25, value26, value23).method16();
               bridge2_3222.method2(value38 * value35, value3, value39 * value35).method8(value24, value25, value26, value23).method16();
               bridge2_3222.method2(value40 * value35, value3, value41 * value35).method8(value24, value25, value26, value23).method16();
            }

            bridge2_3222.method17(BufferMode.BATCHED);
            value35 += value33;
            value3 += value34;
         }

         bridgeextension_90.pop();
         bridgeextension_90.method33();
         com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::endBeacon);
         Bridge.method5().ifPresent(arg0x -> {
            if (arg0x.getConfig().hasShaders()) {
               ShadersBridge slayer31x = arg0x.getShaders();
               slayer31x.popProgram();
            }
         });
      }
   }

   public static float prepareComponentScale(MixinHelper_4 mixinhelper_40, MixinCore9Extension mixincore9extension1) {
      if ((mixincore9extension1 instanceof HudVisibilityWrapper nameplate3 ? nameplate3.method5() : mixincore9extension1) instanceof NameplateComponent nameplate26) {
         nameplate26.markRendered();
      }

      float value7 = getHudScale();
      GuiResolution threadmoduledump714 = LcuiScreen.method151();
      mixincore9extension1.method15(new Data2(threadmoduledump714.getScaledWidth() / value7, threadmoduledump714.getScaledHeight() / value7));
      float value5 = mixincore9extension1.getScale() * value7;
      mixinhelper_40.push();
      mixinhelper_40.method40(value5, value5);
      mixinhelper_40.method39(mixincore9extension1.method1(), mixincore9extension1.method2());
      return value5;
   }

   private static float getHudScale() {
      Type type0 = Ref.method4().method41().method6().method16();
      return type0 != Type.ALL && type0 != Type.MODS ? LcuiScreen.getScale() : 1.0F;
   }

   public static Vector4f renderTooltip(EventRenderContainerSlotPost data30, List<TextComponent> list1, MixinCore9Extension mixincore9extension2) {
      if (list1.isEmpty()) {
         return null;
      }

      float value3 = 0.0F;

      for (TextComponent text5 : list1) {
         float value6 = Ref.method10().bridge$getStringWidth(text5);
         if (value6 > value3) {
            value3 = value6;
         }
      }

      int number10 = Ref.method10().method19();
      float value11 = list1.size() * number10;
      mixincore9extension2.method16(value3, value11);
      MixinHelper_4 mixinhelper_412 = data30.HHHRIHCIOHCICRCCOCIRRROHIOHRIH();
      float value7 = prepareComponentScale(mixinhelper_412, mixincore9extension2);

      for (TextComponent text9 : list1) {
         mixinhelper_412.method10(Ref.method10(), text9, 0, 0, -1, true);
         mixinhelper_412.method39(0.0F, number10);
      }

      mixinhelper_412.pop();
      float value13 = mixincore9extension2.method1() * value7;
      float value14 = mixincore9extension2.method2() * value7;
      return new Vector4f(value13, value13 + value3 * value7, value14, value14 + value11 * value7);
   }

   @Generated
   private WorldRenderUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
