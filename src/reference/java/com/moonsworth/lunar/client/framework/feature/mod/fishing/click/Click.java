package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge2_32;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.slayer.Slayer3;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.config.GeneralSettings.Type;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.Nameplate2;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent.ContainerSlotPostEvent;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.math.Fishing;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump38;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
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

public final class Click {
   public static void drawBlockOutline(AbstractRenderContext var0, Vector3ic var1) {
      drawBlockOutline(var0, var1, 570490624);
   }

   public static void drawBlockOutline(AbstractRenderContext var0, Vector3ic var1, int var2) {
      drawBlockOutline(var0, var1, var2, false);
   }

   public static void drawBlockOutline(AbstractRenderContext var0, Vector3ic var1, int var2, boolean var3) {
      drawBlockOutline(var0, AxisAlignedBBBridge.method2(var1.x(), var1.y(), var1.z(), var1.x() + 1, var1.y() + 1, var1.z() + 1).method11(0.01F), var2, var3);
   }

   public static void drawBlockOutline(AbstractRenderContext var0, AxisAlignedBBBridge var1, int var2, boolean var3) {
      drawBlockHighlight(var0, var1, var2, var3, (Float)ThreadModuleDump63.method4().method40().method82().method19().get());
   }

   public static void drawBlockHighlight(AbstractRenderContext var0, AxisAlignedBBBridge var1, int var2, boolean var3, float var4) {
      Bridge2_32 var5 = var0.method10(var3 ? LunarRenderTypes.field52 : LunarRenderTypes.field15);
      var5.method1();
      drawBoxFilled(
         var5, var1.bridge$getMinX(), var1.bridge$getMinY(), var1.bridge$getMinZ(), var1.bridge$getMaxX(), var1.bridge$getMaxY(), var1.bridge$getMaxZ(), var2
      );
      var5.method17(BufferBuildMode.BATCHED);
      Bridge_28 var6 = var0.method12(var4, var3);
      drawBoxWires(
         var6,
         var1.bridge$getMinX(),
         var1.bridge$getMinY(),
         var1.bridge$getMinZ(),
         var1.bridge$getMaxX(),
         var1.bridge$getMaxY(),
         var1.bridge$getMaxZ(),
         0xFF000000 | var2
      );
      var6.end();
   }

   public static void drawBlockOutlineBatched(AbstractRenderContext var0, AxisAlignedBBBridge var1, int var2, boolean var3) {
      drawBoxWires(var0, var1, var2, var3 ? LunarRenderTypes.field52 : LunarRenderTypes.field15);
   }

   public static void drawBoxWires(AbstractRenderContext var0, AxisAlignedBBBridge var1, int var2, RenderLayerBridge var3) {
      Bridge2_32 var4 = var0.method10(var3);
      var4.method1();
      drawBoxFilled(
         var4, var1.bridge$getMinX(), var1.bridge$getMinY(), var1.bridge$getMinZ(), var1.bridge$getMaxX(), var1.bridge$getMaxY(), var1.bridge$getMaxZ(), var2
      );
      var4.method17(BufferBuildMode.BATCHED);
   }

   public static void drawFaceWireframe(AbstractRenderContext var0, Vector3i var1, RewindhandlersExtension var2, HorsestatsType_2 var3) {
      int var4 = ThreadModuleDump23.method18(var2.method1(0.0F), 0.66F);
      Bridge2_32 var5 = var0.method10(LunarRenderTypes.field15);
      var5.method1();
      var0.translate(var1.x, var1.y, var1.z);
      switch (var3) {
         case NORTH:
            var5.method2(0.0, 0.0, -0.01).method9(var4).method16();
            var5.method2(0.0, 1.0, -0.01).method9(var4).method16();
            var5.method2(1.0, 1.0, -0.01).method9(var4).method16();
            var5.method2(1.0, 0.0, -0.01).method9(var4).method16();
            break;
         case SOUTH:
            var5.method2(0.0, 0.0, 1.01).method9(var4).method16();
            var5.method2(1.0, 0.0, 1.01).method9(var4).method16();
            var5.method2(1.0, 1.0, 1.01).method9(var4).method16();
            var5.method2(0.0, 1.0, 1.01).method9(var4).method16();
            break;
         case EAST:
            var5.method2(1.01, 0.0, 0.0).method9(var4).method16();
            var5.method2(1.01, 1.0, 0.0).method9(var4).method16();
            var5.method2(1.01, 1.0, 1.0).method9(var4).method16();
            var5.method2(1.01, 0.0, 1.0).method9(var4).method16();
            break;
         case WEST:
            var5.method2(-0.01, 0.0, 0.0).method9(var4).method16();
            var5.method2(-0.01, 0.0, 1.0).method9(var4).method16();
            var5.method2(-0.01, 1.0, 1.0).method9(var4).method16();
            var5.method2(-0.01, 1.0, 0.0).method9(var4).method16();
            break;
         case UP:
            var5.method2(0.0, 1.01, 0.0).method9(var4).method16();
            var5.method2(0.0, 1.01, 1.0).method9(var4).method16();
            var5.method2(1.0, 1.01, 1.0).method9(var4).method16();
            var5.method2(1.0, 1.01, 0.0).method9(var4).method16();
            break;
         case DOWN:
            var5.method2(0.0, -0.01, 0.0).method9(var4).method16();
            var5.method2(1.0, -0.01, 0.0).method9(var4).method16();
            var5.method2(1.0, -0.01, 1.0).method9(var4).method16();
            var5.method2(0.0, -0.01, 1.0).method9(var4).method16();
      }

      var5.method17(BufferBuildMode.BATCHED);
      var0.translate(-var1.x, -var1.y, -var1.z);
   }

   public static void drawStringCentered(AbstractRenderContext var0, String var1, double var2, double var4, double var6, int var8, boolean var9) {
      Bridge10_2 var10 = ThreadModuleDump63.method10();
      if (var10 != null) {
         beginTextTransform(var0, var2, var4, var6, false);
         var10.method7(var0, var1, -var10.bridge$getStringWidth(var1) / 2.0F, 0.0F, var8, var9);
         var0.pop();
      }
   }

   public static void drawComponentCentered(AbstractRenderContext var0, Component var1, double var2, double var4, double var6, boolean var8) {
      drawComponentCentered(var0, var1, var2, var4, var6, var8, 1.0F, false);
   }

   public static void drawComponentCentered(AbstractRenderContext var0, Component var1, double var2, double var4, double var6, boolean var8, float var9) {
      drawComponentCentered(var0, var1, var2, var4, var6, var8, var9, false);
   }

   public static void drawComponentCentered(AbstractRenderContext var0, Component var1, double var2, double var4, double var6, boolean var8, float var9, boolean var10) {
      Bridge10_2 var11 = ThreadModuleDump63.method10();
      if (var11 != null) {
         beginTextTransform(var0, var2, var4, var6, var10);
         var0.scale(var9, var9, var9);
         var11.method11(var0, var1, -var11.bridge$getStringWidth(var1) / 2.0F, 0.0F, -1, var8);
         var0.pop();
      }
   }

   private static void beginTextTransform(AbstractRenderContext var0, double var1, double var3, double var5, boolean var7) {
      Bridge2_43 var8 = ThreadModuleDump63.method13();
      float var9 = 1.6F;
      float var10 = 0.016666668F * var9;
      if (!Bridge.getMinecraftVersion().method23()) {
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      }

      var0.push();
      var0.translate(var1, var3, var5);
      var0.method4((float)(-var8.bridge$playerViewY()), 0.0F, 1.0F, 0.0F);
      var0.method4((float)var8.bridge$playerViewX(), 1.0F, 0.0F, 0.0F);
      var0.scale(-var10, -var10, var10);
      if (var0.method39()) {
         var0.method14();
         var0.method4(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA, BridgeType2_9.GL_ONE, BridgeType2_9.GL_ZERO);
      }
   }

   public static void drawBlockHighlightAt(AbstractRenderContext var0, Vector3i var1, int var2) {
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var3 != null) {
         Bridge3_23 var4 = var3.bridge$getBlockAt(var1.x(), var1.y(), var1.z());
         if (!var4.bridge$isAir()) {
            Bridge2_32 var5 = var0.method10(LunarRenderTypes.field52);
            var5.method1();
            AxisAlignedBBBridge var6 = var4.bridge$getAABB(var3, Bridge.method8().method4(var1.x(), var1.y(), var1.z()))
               .bridge$offset(var1.x(), var1.y(), var1.z())
               .method11(0.01);
            drawBoxFilled(
               var5,
               var6.bridge$getMinX(),
               var6.bridge$getMinY(),
               var6.bridge$getMinZ(),
               var6.bridge$getMaxX(),
               var6.bridge$getMaxY(),
               var6.bridge$getMaxZ(),
               var2
            );
            var5.method17(BufferBuildMode.BATCHED);
            Bridge_28 var7 = var0.method12((Float)ThreadModuleDump63.method4().method40().method82().method19().get(), true);
            drawBoxWires(
               var7,
               var6.bridge$getMinX(),
               var6.bridge$getMinY(),
               var6.bridge$getMinZ(),
               var6.bridge$getMaxX(),
               var6.bridge$getMaxY(),
               var6.bridge$getMaxZ(),
               0xFF000000 | var2
            );
            var7.end();
         }
      }
   }

   public static void drawLine(AbstractRenderContext var0, Vector3dc var1, Vector3dc var2, int var3) {
      Bridge_28 var4 = var0.method12(1.0F, true);
      var4.method1(var3);
      var4.method3(var1.x(), var1.y(), var1.z(), var2.x(), var2.y(), var2.z());
      var4.end();
   }

   public static void drawLine(AbstractRenderContext var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
      Bridge_28 var14 = var0.method12(1.0F, true);
      var14.method1(var13);
      var14.method3(var1, var3, var5, var7, var9, var11);
      var14.end();
   }

   public static void drawLines(AbstractRenderContext var0, Collection<Line3D> var1, int var2) {
      Bridge_28 var3 = var0.method12(1.0F, true);
      var3.method1(var2);

      for (Line3D var5 : var1) {
         var3.method3(var5.a.x(), var5.a.y(), var5.a.z(), var5.b.x(), var5.b.y(), var5.b.z());
      }

      var3.end();
   }

   public static void drawFishingLines(AbstractRenderContext var0, Set<Fishing> var1, int var2) {
      Bridge_28 var3 = var0.method12(1.0F, true);
      var3.method1(var2);

      for (Fishing var5 : var1) {
         var3.method3(var5.method1().x(), var5.method1().y(), var5.method1().z(), var5.method2().x(), var5.method2().y(), var5.method2().z());
      }

      var3.end();
   }

   public static void drawLine(AbstractRenderContext var0, Line3D var1, int var2, float var3, boolean var4) {
      Bridge_28 var5 = var0.method12(var3, var4);
      var5.method1(var2);
      drawDashedLine(var5, var1);
      var5.end();
   }

   public static void drawLine(
      AbstractRenderContext var0, float var1, float var2, float var3, float var4, float var5, float var6, int var7, float var8, boolean var9
   ) {
      drawLine(var0, new Line3D(new Vec3D(var1, var2, var3), new Vec3D(var4, var5, var6)), var7, var8, var9);
   }

   public static void drawLines(AbstractRenderContext var0, Set<Line3D> var1, int var2, float var3, boolean var4) {
      Bridge_28 var5 = var0.method12(var3, var4);
      var5.method1(var2);

      for (Line3D var7 : var1) {
         drawDashedLine(var5, var7);
      }

      var5.end();
   }

   private static void drawDashedLine(Bridge_28 var0, Line3D var1) {
      Vec3D var2 = var1.b.sub(var1.a).normalize();
      float var3 = var1.a.distanceTo(var1.b);

      byte var4;
      for (var4 = 0; var4 + 2 < var3; var4 += 2) {
         Vec3D var5 = var1.a.add(var2.scale(var4));
         Vec3D var6 = var1.a.add(var2.scale(var4 + 2));
         var0.method3(var5.x(), var5.y(), var5.z(), var6.x(), var6.y(), var6.z());
      }

      Vec3D var7 = var1.a.add(var2.scale(var4));
      var0.method3(var7.x(), var7.y(), var7.z(), var1.b.x(), var1.b.y(), var1.b.z());
   }

   public static void drawBoxWires(Bridge_28 var0, AxisAlignedBBBridge var1, int var2) {
      drawBoxWires(
         var0, var1.bridge$getMinX(), var1.bridge$getMinY(), var1.bridge$getMinZ(), var1.bridge$getMaxX(), var1.bridge$getMaxY(), var1.bridge$getMaxZ(), var2
      );
   }

   public static void drawBoxWires(Bridge_28 var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
      var0.method1(var13);
      var0.method3(var1, var3, var5, var7, var3, var5);
      var0.method3(var7, var3, var5, var7, var3, var11);
      var0.method3(var7, var3, var11, var1, var3, var11);
      var0.method3(var1, var3, var5, var1, var3, var11);
      var0.method3(var1, var9, var5, var7, var9, var5);
      var0.method3(var7, var9, var5, var7, var9, var11);
      var0.method3(var7, var9, var11, var1, var9, var11);
      var0.method3(var1, var9, var5, var1, var9, var11);
      var0.method3(var1, var3, var5, var1, var9, var5);
      var0.method3(var7, var3, var5, var7, var9, var5);
      var0.method3(var1, var3, var11, var1, var9, var11);
      var0.method3(var7, var3, var11, var7, var9, var11);
   }

   public static void drawBoxFilled(Bridge2_32 var0, AxisAlignedBBBridge var1, int var2) {
      drawBoxFilled(
         var0, var1.bridge$getMinX(), var1.bridge$getMinY(), var1.bridge$getMinZ(), var1.bridge$getMaxX(), var1.bridge$getMaxY(), var1.bridge$getMaxZ(), var2
      );
   }

   public static void drawBoxFilled(Bridge2_32 var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
   }

   public static void drawBoxSolid(Bridge2_32 var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13) {
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var1, var3, var5).method9(var13).method16();
      var0.method2(var1, var3, var11).method9(var13).method16();
      var0.method2(var1, var9, var11).method9(var13).method16();
      var0.method2(var1, var9, var5).method9(var13).method16();
      var0.method2(var7, var3, var5).method9(var13).method16();
      var0.method2(var7, var9, var5).method9(var13).method16();
      var0.method2(var7, var9, var11).method9(var13).method16();
      var0.method2(var7, var3, var11).method9(var13).method16();
   }

   public static void drawCircleOutline(AbstractRenderContext var0, double var1, double var3, double var5, double var7, float var9, int var10) {
      Bridge_28 var11 = var0.method11(var9);
      var11.method1(var10);
      List var12 = circlePoints(var1, var3, var5, var7);

      for (int var13 = 0; var13 < var12.size() - 1; var13++) {
         Vector3d var14 = (Vector3d)var12.get(var13);
         Vector3d var15 = (Vector3d)var12.get(var13 + 1);
         var11.method3(var14.x(), var14.y(), var14.z(), var15.x(), var15.y(), var15.z());
      }

      var11.end();
   }

   public static void drawCircleFilled(AbstractRenderContext var0, double var1, double var3, double var5, double var7, int var9) {
      var0.method21();
      var0.method13();
      Bridge2_32 var10 = var0.method10(LunarRenderTypes.field16);
      var10.method1();
      List var11 = circlePoints(var1, var3, var5, var7);

      for (int var12 = 0; var12 < var11.size() - 1; var12++) {
         Vector3d var13 = (Vector3d)var11.get(var12);
         Vector3d var14 = (Vector3d)var11.get(var12 + 1);
         var10.method2(var1, var3, var5).method9(var9).method16();
         var10.method2(var13.x(), var13.y(), var13.z()).method9(var9).method16();
         var10.method2(var14.x(), var14.y(), var14.z()).method9(var9).method16();
         var10.method2(var1, var3, var5).method9(var9).method16();
      }

      var10.method17(BufferBuildMode.BATCHED);
      var0.method20();
   }

   public static Vec3Bridge getLookVector(Bridge6_10 var0) {
      float var1 = (float)var0.bridge$getRotationPitch();
      float var2 = (float)var0.bridge$getRotationYaw();
      float var3 = var1 * (float) (Math.PI / 180.0);
      float var4 = -var2 * (float) (Math.PI / 180.0);
      float var5 = (float)Math.cos(var4);
      float var6 = (float)Math.sin(var4);
      float var7 = (float)Math.cos(var3);
      float var8 = (float)Math.sin(var3);
      return Vec3Bridge.method2(var6 * var7, -var8, var5 * var7);
   }

   public static void drawString(AbstractRenderContext var0, double var1, double var3, double var5, String var7, boolean var8, boolean var9, boolean var10) {
      drawString(var0, var1, var3, var5, var7, var8, var9, var10, 1.0F);
   }

   public static void drawString(AbstractRenderContext var0, double var1, double var3, double var5, String var7, boolean var8, boolean var9) {
      drawString(var0, var1, var3, var5, var7, var8, var9, true, 1.0F);
   }

   public static void drawString(AbstractRenderContext var0, Vector3d var1, String var2, boolean var3, boolean var4, boolean var5) {
      drawString(var0, var1.get(0), var1.get(1), var1.get(2), var2, var3, var4, var5, 1.0F);
   }

   public static void drawString(
      AbstractRenderContext var0, double var1, double var3, double var5, String var7, boolean var8, boolean var9, boolean var10, float var11
   ) {
      drawStringWithColor(var0, var1, var3, var5, var7, var8, var9, var10, var11, RewindhandlersExtension.method24(AdventureChatFormatting.RED));
   }

   public static void drawStringWithColor(
      AbstractRenderContext var0,
      double var1,
      double var3,
      double var5,
      String var7,
      boolean var8,
      boolean var9,
      boolean var10,
      float var11,
      RewindhandlersExtension var12
   ) {
      drawStringBillboard(var0, var1, var3, var5, var7, var8, var9, var10, var11, var12.getColor());
   }

   public static void drawStringBillboard(
      AbstractRenderContext var0, double var1, double var3, double var5, String var7, boolean var8, boolean var9, boolean var10, float var11, int var12
   ) {
      Bridge10_2 var13 = ThreadModuleDump63.method10();
      if (var13 != null) {
         Bridge2_43 var14 = ThreadModuleDump63.method13();
         Vector3d var15 = new Vector3d(var14.bridge$renderPosX(), var14.bridge$renderPosY(), var14.bridge$renderPosZ());
         if (var10) {
            double var16 = var15.distanceSquared(var1, var3, var5);
            if (var16 > 2500.0) {
               var11 *= 5.0F;
               Vector3d var18 = new Vector3d(var1, var3, var5).sub(var15).normalize(50.0).add(var15);
               var1 = var18.x();
               var3 = var18.y();
               var5 = var18.z();
            } else {
               var11 = (float)(var11 * Math.max(1.0, Math.sqrt(var16) / 10.0));
            }
         }

         var0.push();
         var0.translate(-var14.bridge$renderPosX(), -var14.bridge$renderPosY(), -var14.bridge$renderPosZ());
         beginTextTransform(var0, var1, var3, var5, !var9);
         var0.scale(var11, var11, var11);
         var13.method7(var0, var7, -var13.bridge$getStringWidth(var7) / 2.0F, 0.0F, var12, var8);
         var0.pop();
         var0.pop();
      }
   }

   public static void drawEntityBox(AbstractRenderContext var0, Vector3iBridge var1, RewindhandlersExtension var2, boolean var3, boolean var4) {
      drawBox(var0, var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ(), 1.0, 1.0, 1.0, var2, var3, var4);
   }

   public static void drawEntityBox(AbstractRenderContext var0, Vector3iBridge var1, RewindhandlersExtension var2, float var3, boolean var4, boolean var5) {
      drawBox(var0, var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ(), 1.0, 1.0, 1.0, var2.getColor(), var4, var3, var5);
   }

   public static void drawEntityBox(
      AbstractRenderContext var0, BridgeExtension var1, double var2, double var4, RewindhandlersExtension var6, boolean var7, boolean var8, float var9
   ) {
      drawEntityBox(var0, var1, var2, var4, var6, 1.0F, var7, var8, var9);
   }

   public static void drawEntityBox(
      AbstractRenderContext var0, BridgeExtension var1, double var2, double var4, RewindhandlersExtension var6, float var7, boolean var8, boolean var9, float var10
   ) {
      double var11 = var1.method8(var10);
      double var13 = var1.method9(var10);
      double var15 = var1.method10(var10);
      drawBox(var0, var11 - var2 / 2.0, var13, var15 - var2 / 2.0, var2, var4, var2, var6.getColor(), var8, var7, var9);
   }

   public static void drawBoxHighlight(AbstractRenderContext var0, Vector3ic var1, RewindhandlersExtension var2, boolean var3, boolean var4) {
      drawBox(var0, var1.get(0), var1.get(1), var1.get(2), 1.0, 1.0, 1.0, var2, var3, var4);
   }

   public static void drawBoxHighlight(AbstractRenderContext var0, Vector3ic var1, RewindhandlersExtension var2, float var3, boolean var4, boolean var5) {
      drawBox(var0, var1.get(0), var1.get(1), var1.get(2), 1.0, 1.0, 1.0, var2.getColor(), var4, var3, var5);
   }

   public static void drawBoxHighlight(AbstractRenderContext var0, Vector3dc var1, RewindhandlersExtension var2, boolean var3, boolean var4) {
      drawBox(var0, var1.get(0), var1.get(1), var1.get(2), 1.0, 1.0, 1.0, var2, var3, var4);
   }

   public static void drawBoxHighlight(AbstractRenderContext var0, Vector3iBridge var1, RewindhandlersExtension var2, float var3, boolean var4) {
      drawBox(var0, var1.bridge$getX(), var1.bridge$getY(), var1.bridge$getZ(), 1.0, 1.0, 1.0, var2.getColor(), false, var3, var4);
   }

   public static void drawBox(
      AbstractRenderContext var0,
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      RewindhandlersExtension var13,
      boolean var14,
      boolean var15
   ) {
      drawBox(var0, var1, var3, var5, var7, var9, var11, var13.getColor(), var14, 1.0F, var15);
   }

   public static void drawBox(
      AbstractRenderContext var0,
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      int var13,
      boolean var14,
      float var15,
      boolean var16
   ) {
      Bridge2_43 var17 = ThreadModuleDump63.method13();
      var0.push();
      var0.translate(-var17.bridge$renderPosX(), -var17.bridge$renderPosY(), -var17.bridge$renderPosZ());
      if (var14) {
         Bridge2_32 var18 = var0.method10(var16 ? LunarRenderTypes.field15 : LunarRenderTypes.field52);
         var18.method1();
         drawBoxFilled(var18, var1 - 0.005, var3 - 0.005, var5 - 0.005, var1 + var7 + 0.005, var3 + var9 + 0.005, var5 + var11 + 0.005, var13);
         var18.method17(BufferBuildMode.BATCHED);
      } else {
         Bridge_28 var19 = var0.method12(var15, !var16);
         drawBoxWires(var19, var1 - 0.005, var3 - 0.005, var5 - 0.005, var1 + var7 + 0.005, var3 + var9 + 0.005, var5 + var11 + 0.005, var13);
         var19.end();
      }

      var0.pop();
   }

   public static void drawLine(AbstractRenderContext var0, Vector3dc var1, Vector3dc var2, RewindhandlersExtension var3, float var4, boolean var5) {
      drawLineImpl(var0, var1.x(), var1.y(), var1.z(), var2.x(), var2.y(), var2.z(), var3.method1(0.0F), var4, var5, false);
   }

   public static void drawLine(
      AbstractRenderContext var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13, float var14, boolean var15
   ) {
      drawLineImpl(var0, var1, var3, var5, var7, var9, var11, var13, var14, var15, false);
   }

   public static void drawLine(
      AbstractRenderContext var0,
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      RewindhandlersExtension var13,
      float var14,
      boolean var15
   ) {
      drawLineImpl(var0, var1, var3, var5, var7, var9, var11, var13.getColor(), var14, var15, false);
   }

   public static void drawLineWithArrow(AbstractRenderContext var0, Vector3dc var1, Vector3dc var2, RewindhandlersExtension var3, float var4, boolean var5, boolean var6) {
      drawLineImpl(var0, var1.x(), var1.y(), var1.z(), var2.x(), var2.y(), var2.z(), var3.method1(0.0F), var4, var5, var6);
   }

   public static void drawLineImpl(
      AbstractRenderContext var0,
      double var1,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      int var13,
      float var14,
      boolean var15,
      boolean var16
   ) {
      Bridge2_43 var17 = ThreadModuleDump63.method13();
      var0.push();
      var0.translate(-var17.bridge$renderPosX(), -var17.bridge$renderPosY(), -var17.bridge$renderPosZ());
      Bridge_28 var18 = var0.method12(var14, !var15);
      var18.method1(var13);
      Vector3d var19 = null;
      if (var16) {
         Vector3d var20 = new Vector3d(var7 - var1, var9 - var3, var11 - var5);
         double var21 = var20.length();
         var20.normalize(Math.max(0.0, var21 - 0.3)).add(var1, var3, var5);
         var19 = new Vector3d(var7, var9, var11);
         var7 = var20.x;
         var9 = var20.y;
         var11 = var20.z;
      }

      var18.method3(var1, var3, var5, var7, var9, var11);
      var18.end();
      if (var16) {
         drawArrowHead(var0, var1, var3, var5, var19.x, var19.y, var19.z, var13, var15);
      }

      var0.pop();
   }

   private static void drawArrowHead(AbstractRenderContext var0, double var1, double var3, double var5, double var7, double var9, double var11, int var13, boolean var14) {
      Vector3d var15 = new Vector3d(var1, var3, var5);
      Vector3d var16 = new Vector3d(var7, var9, var11);
      double var17 = 0.3;
      double var19 = 0.2;
      Vector3d var21 = var16.sub(var15, new Vector3d()).normalize(var17);
      Vector3d var22 = var21.cross(var21.rotateAxis(Math.PI / 2, 1.0, 1.0, 1.0, new Vector3d()), new Vector3d()).normalize(var19 / 2.0);
      Vector3d var23 = var22.cross(var21, new Vector3d()).normalize(var19 / 2.0);
      Vector3d var24 = var16.sub(var21, new Vector3d());
      Vector3d var25 = var24.add(var22, new Vector3d());
      Vector3d var26 = var24.sub(var22, new Vector3d());
      Vector3d var27 = var24.add(var23, new Vector3d());
      Vector3d var28 = var24.sub(var23, new Vector3d());
      Bridge2_32 var29 = var0.method10(var14 ? LunarRenderTypes.field23 : LunarRenderTypes.field19);
      var29.method1();
      var29.method2(var16.x(), var16.y(), var16.z()).method9(var13).method16();
      var29.method2(var25.x(), var25.y(), var25.z()).method9(var13).method16();
      var29.method2(var26.x(), var26.y(), var26.z()).method9(var13).method16();
      var29.method2(var25.x(), var25.y(), var25.z()).method9(var13).method16();
      var29.method2(var16.x(), var16.y(), var16.z()).method9(var13).method16();
      var29.method2(var26.x(), var26.y(), var26.z()).method9(var13).method16();
      var29.method2(var16.x(), var16.y(), var16.z()).method9(var13).method16();
      var29.method2(var27.x(), var27.y(), var27.z()).method9(var13).method16();
      var29.method2(var28.x(), var28.y(), var28.z()).method9(var13).method16();
      var29.method2(var27.x(), var27.y(), var27.z()).method9(var13).method16();
      var29.method2(var16.x(), var16.y(), var16.z()).method9(var13).method16();
      var29.method2(var28.x(), var28.y(), var28.z()).method9(var13).method16();
      var29.method17(BufferBuildMode.BATCHED);
      var0.method20();
   }

   public static void drawLookLine(Vec3D var0, AbstractRenderContext var1, int var2, float var3) {
      Bridge5_12 var4 = ThreadModuleDump63.method3();
      Bridge5Extension_5 var5 = ThreadModuleDump63.method7();
      if (var4 != null && var5 != null) {
         Bridge2_43 var6 = ThreadModuleDump63.method13();
         if (var6 != null) {
            Vec3D var7 = new Vec3D((float)var6.bridge$renderPosX(), (float)var6.bridge$renderPosY(), (float)var6.bridge$renderPosZ());
            Vector3d var8 = getLookVector(var5).method6().normalize(3.0);
            int var9 = var4.bridge$getGameSettings().bridge$getThirdPersonView();
            if (var9 == 2) {
               var7 = var7.sub((float)var8.x(), (float)var8.y(), (float)var8.z());
            } else {
               var7 = var7.add((float)var8.x(), (float)var8.y(), (float)var8.z());
            }

            var1.push();
            var1.translate(-var6.bridge$renderPosX(), -var6.bridge$renderPosY(), -var6.bridge$renderPosZ());
            drawLine(var1, new Line3D(var7, var0), var2, var3, true);
            var1.pop();
         }
      }
   }

   public static void drawCylinderWalls(AbstractRenderContext var0, double var1, double var3, double var5, double var7, double var9, int var11, boolean var12) {
      var0.method21();
      var0.method13();
      RenderLayerBridge var13 = var12 ? LunarRenderTypes.field52 : LunarRenderTypes.field16;
      Bridge2_32 var14 = var0.method10(var13);
      var14.method1();
      List var15 = circlePoints(var1, var3, var5, var7);

      for (int var16 = 0; var16 < var15.size() - 1; var16++) {
         Vector3d var17 = (Vector3d)var15.get(var16);
         Vector3d var18 = (Vector3d)var15.get(var16 + 1);
         var14.method2(var17.x(), var17.y(), var17.z()).method9(var11).method16();
         var14.method2(var17.x(), var17.y() + var9, var17.z()).method9(var11).method16();
         var14.method2(var18.x(), var18.y() + var9, var18.z()).method9(var11).method16();
         var14.method2(var18.x(), var18.y(), var18.z()).method9(var11).method16();
      }

      var14.method17(BufferBuildMode.BATCHED);
      var0.method20();
   }

   public static List<Vector3d> circlePoints(double var0, double var2, double var4, double var6) {
      ArrayList var8 = new ArrayList(66);
      double var9 = Math.PI * 2;
      double var11 = var9 / 64.0;

      for (double var13 = 0.0; var13 < var9; var13 += var11) {
         var8.add(new Vector3d(var0 + var6 * ThreadModuleDump38.method1(-var13), var2, var4 + var6 * ThreadModuleDump38.sin(-var13)));
      }

      var8.add((Vector3d)var8.get(0));
      return var8;
   }

   public static AxisAlignedBBBridge getBlockAABB(Itemcounter6 var0, Vector3i var1) {
      Horsestats20Extension2 var2 = Bridge.method8().method4(var1.x(), var1.y(), var1.z());
      AxisAlignedBBBridge var3 = var0.method4(var2).bridge$getAABB(var0, var2);
      if (var3 == null) {
         var3 = Bridge.method8().method45(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
      }

      return var3.method5(var2).method11(0.01);
   }

   public static void drawBlockOutlineFade(AbstractRenderContext var0, Vector3ic var1, int var2) {
      Vec3Bridge var3 = ThreadModuleDump63.method7().bridge$getEyePosition();
      double var4 = Math.hypot(var1.x() - var3.bridge$xCoord(), var1.z() - var3.bridge$zCoord());
      float var6 = 4.0F;
      float var7 = 4.0F;
      float var8 = (float)ThreadModuleDump67.method2((var4 - var6) / var7, 0.0, 1.0);
      if (var8 != 0.0F) {
         double var9 = 0.05 + 0.02 * Math.sqrt(var4);
         drawBoxWires(
            var0,
            AxisAlignedBBBridge.method2(var1.x() + 0.5 - var9, var1.y() + 1, var1.z() + 0.5 - var9, var1.x() + 0.5 + var9, 255.0, var1.z() + 0.5 + var9),
            ThreadModuleDump23.method26(var2, var8),
            LunarRenderTypes.field53
         );
      }
   }

   public static void drawBeaconBeam(AbstractRenderContext var0, double var1, double var3, double var5, int var7) {
      Itemcounter6Extension var8 = ThreadModuleDump63.method8();
      if (var8 != null) {
         drawBeaconBeamInternal(var0, var1, var8.bridge$getMinBuildHeight(), var3, var5, var7);
      }
   }

   public static void drawBeaconBeamInternal(AbstractRenderContext var0, double var1, double var3, double var5, double var7, int var9) {
      Itemcounter6Extension var10 = ThreadModuleDump63.method8();
      if (var10 != null) {
         Bridge.method5().ifPresent(var0x -> {
            if (var0x.getConfig().hasShaders()) {
               Slayer3 var1x = var0x.getShaders();
               var1x.pushUseProgram(var1x.getProgramBasic());
            }
         });
         com.moonsworth.lunar.client.fishing.Fishing.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::beginBeacon);
         Bridge2_43 var11 = ThreadModuleDump63.method3().bridge$getEntityRenderDispatcher();
         double var12 = var11.bridge$renderPosX();
         double var14 = var11.bridge$renderPosY();
         double var16 = var11.bridge$renderPosZ();
         var0.push();
         double var18 = (float)var1 - var12;
         double var20 = (float)var7 - var16;
         var0.translate((float)var18, (float)(-var14), (float)var20);
         Bridge2_32 var22 = var0.method10(LunarRenderTypes.field49);
         float var23 = (var9 >> 24 & 0xFF) / 255.0F;
         float var24 = (var9 >> 16 & 0xFF) / 255.0F;
         float var25 = (var9 >> 8 & 0xFF) / 255.0F;
         float var26 = (var9 & 0xFF) / 255.0F;
         float var27 = 0.75F;
         float var28 = 0.0F;
         float var29 = (float)ThreadModuleDump67.method2(
            Math.abs(var10.bridge$getMinBuildHeight()) + ThreadModuleDump63.method7().bridge$getPosY() + 50.0, var5, 256.0
         );
         byte var30 = 20;
         byte var31 = 1;
         float var32 = (float) (Math.PI * 2) / var30;
         float var33 = (var28 - var27) / var31;
         float var34 = var29 / var31;
         float var35 = var27;

         for (int var36 = 0; var36 < var31; var36++) {
            var22.method1();

            for (int var37 = 0; var37 < var30; var37++) {
               float var38 = (float)ThreadModuleDump38.sin(var37 * var32);
               float var39 = (float)ThreadModuleDump38.method1(var37 * var32);
               float var40 = (float)ThreadModuleDump38.sin((var37 + 1) * var32);
               float var41 = (float)ThreadModuleDump38.method1((var37 + 1) * var32);
               var22.method2(var38 * (var35 + var33), var3 + var34, var39 * (var35 + var33)).method8(var24, var25, var26, var23).method16();
               var22.method2(var38 * var35, var3, var39 * var35).method8(var24, var25, var26, var23).method16();
               var22.method2(var40 * var35, var3, var41 * var35).method8(var24, var25, var26, var23).method16();
            }

            var22.method17(BufferBuildMode.BATCHED);
            var35 += var33;
            var3 += var34;
         }

         var0.pop();
         var0.method33();
         com.moonsworth.lunar.client.fishing.Fishing.method2(Fishing2Extension.class).ifPresent(Fishing2Extension::endBeacon);
         Bridge.method5().ifPresent(var0x -> {
            if (var0x.getConfig().hasShaders()) {
               Slayer3 var1x = var0x.getShaders();
               var1x.popProgram();
            }
         });
      }
   }

   public static float pushHudTransform(MixinHelper_4 var0, MixinCore9Extension var1) {
      if ((var1 instanceof Nameplate var3 ? var3.method5() : var1) instanceof Nameplate2 var6) {
         var6.markRendered();
      }

      float var7 = getHudScale();
      ThreadModuleDump71 var4 = LcuiScreen.method151();
      var1.method15(new Data2(var4.getScaledWidth() / var7, var4.getScaledHeight() / var7));
      float var5 = var1.getScale() * var7;
      var0.push();
      var0.method40(var5, var5);
      var0.method39(var1.ICRIHRIORRCRCOOCCCHHRIRICCHHII(), var1.RIIIOCHHCIHOIOROOOHRIRICCCCHHC());
      return var5;
   }

   private static float getHudScale() {
      Type var0 = ThreadModuleDump63.method4().method41().method6().method16();
      return var0 != Type.ALL && var0 != Type.MODS ? LcuiScreen.getScale() : 1.0F;
   }

   public static Vector4f layoutTooltip(ContainerSlotPostEvent var0, List<TextComponent> var1, MixinCore9Extension var2) {
      if (var1.isEmpty()) {
         return null;
      }

      float var3 = 0.0F;

      for (TextComponent var5 : var1) {
         float var6 = ThreadModuleDump63.method10().bridge$getStringWidth(var5);
         if (var6 > var3) {
            var3 = var6;
         }
      }

      int var10 = ThreadModuleDump63.method10().method19();
      float var11 = var1.size() * var10;
      var2.method16(var3, var11);
      MixinHelper_4 var12 = var0.method5();
      float var7 = pushHudTransform(var12, var2);

      for (TextComponent var9 : var1) {
         var12.method10(ThreadModuleDump63.method10(), var9, 0, 0, -1, true);
         var12.method39(0.0F, var10);
      }

      var12.pop();
      float var13 = var2.ICRIHRIORRCRCOOCCCHHRIRICCHHII() * var7;
      float var14 = var2.RIIIOCHHCIHOIOROOOHRIRICCCCHHC() * var7;
      return new Vector4f(var13, var13 + var3 * var7, var14, var14 + var11 * var7);
   }

   @Generated
   private Click() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
