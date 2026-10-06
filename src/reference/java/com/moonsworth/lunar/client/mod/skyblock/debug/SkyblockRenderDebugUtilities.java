package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RouteRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.ParticleTrail;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.NameplateComponent;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.util.math.LineSegment;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.joml.Vector3d;
import org.joml.Vector3i;
import toxi.geom.Line3D;
import toxi.geom.Vec3D;

public class SkyblockRenderDebugUtilities extends AbstractFeature {
   private static final int field8 = 3;
   private static final int field9 = 8;
   private static final float field10 = 2.5F;
   private static final int field11 = 5;
   private static final int field12 = 7;
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugTestBoxMethods").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugTestLineMethods").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugTestCircleMethods").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugTestGuiContainerInfo")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("debugHighlightMobs").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
      .method31();
   private final IntegerOption field18 = (IntegerOption)((Data)((Data)OptionFactory.method4("debugMobHighlightRadius")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(32))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 256))
      .method31();
   private final ColorOption field19 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "debugMobHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140915968))
      .method31();
   private final ColorOption field20 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "debugBoxAtColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711681))
      .method15()
      .method31();
   private Vector3d field21 = new Vector3d(9973.0, 77.0, 9845.0);
   private int field22;

   public SkyblockRenderDebugUtilities(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method16(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.method16(ModTraits.field1, HudVisibilityWrapper.method4(new NameplateComponent(this)));
      this.handle(HudRenderLegacyEvent.class, this::method4);
      this.handle(EventRenderContainerSlotPost.class, this::method11);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventTick.class, this::method10);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_RENDER_DEBUG_UTILITIES";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "debugRenderTests", arg1x -> arg1x.method9(new ClientOption[]{this.field13, this.field14, this.field15, this.field16})
      );
      lightingextension231.method1(
         "debugMobHighlights", arg1x -> arg1x.method9(new ClientOption[]{this.field17, this.field18, this.field19})
      );
      lightingextension231.method1(
         "debugOriginControls",
         arg1x -> arg1x.method9(
            new ClientOption[]{OptionFactory.method14("debugSetLineStartToPlayer").method4(this::method13).method31(), this.field20}
         )
      );
   }

   private void method13() {
      this.field21 = Ref.method7().bridge$getPosition();
   }

   private void method4(HudRenderLegacyEvent highlightimpl21) {
      if (this.isEnabled()) {
         AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
         int number3 = (int)Math.floor(this.field21.x());
         int number4 = (int)Math.floor(this.field21.y());
         int number5 = (int)Math.floor(this.field21.z());
         if ((Boolean)this.field13.get()) {
            this.method5(bridgeextension_92, number3, number4, number5);
         }

         if ((Boolean)this.field14.get()) {
            this.method6(bridgeextension_92, number3, number4, number5 + 8);
         }

         if ((Boolean)this.field15.get()) {
            this.method7(bridgeextension_92, number3, number4, number5 + 16);
         }

         if ((Boolean)this.field17.get()) {
            this.method12(bridgeextension_92, highlightimpl21.method5());
         }
      }
   }

   private void method5(AbstractRenderContext bridgeextension_91, int number2, int number3, int number4) {
      Vector3i vector3i5 = new Vector3i(number2, number3, number4);
      this.method8(bridgeextension_91, number2, number3, number4, 0, "drawBoxAtCoordinate");
      this.method8(bridgeextension_91, number2, number3, number4, 1, "drawFancyBox");
      this.method8(bridgeextension_91, number2, number3, number4, 2, "drawFilledBox");
      this.method8(bridgeextension_91, number2, number3, number4, 3, "highlightBlockAt");
      this.method8(bridgeextension_91, number2, number3, number4, 4, "getAABB");
      this.method8(bridgeextension_91, number2, number3, number4, 5, "drawBoxAt");
      this.method8(bridgeextension_91, number2, number3, number4, 6, "highlightBlockFace");
      this.method13(bridgeextension_91, () -> {
         WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_91, vector3i5, 1140915968, false);
         Vector3i vector3i3x = new Vector3i(vector3i5).add(3, 0, 0);
         AxisAlignedBBBridge horsestats124x = AxisAlignedBBBridge.method2(vector3i3x.x(), vector3i3x.y(), vector3i3x.z(), vector3i3x.x() + 1, vector3i3x.y() + 1, vector3i3x.z() + 1).method11(0.01F);
         WorldRenderUtils.drawFancyBox(bridgeextension_91, horsestats124x, 1157562623, true);
         Vector3i vector3i5x = new Vector3i(vector3i5).add(6, 0, 0);
         AxisAlignedBBBridge horsestats126x = AxisAlignedBBBridge.method2(vector3i5x.x(), vector3i5x.y(), vector3i5x.z(), vector3i5x.x() + 1, vector3i5x.y() + 1, vector3i5x.z() + 1);
         WorldRenderUtils.drawFilledBox(bridgeextension_91, horsestats126x, 1140850943, false);
         Vector3i vector3i7 = new Vector3i(vector3i5).add(9, 0, 0);
         WorldRenderUtils.highlightBlockAt(bridgeextension_91, vector3i7, 1157627648);
         WorldBridgeExtension itemcounter6extension8 = Ref.method8();
         if (itemcounter6extension8 != null) {
            Vector3i vector3i9 = new Vector3i(vector3i5).add(12, 0, 0);
            AxisAlignedBBBridge horsestats1210 = WorldRenderUtils.getAABB(itemcounter6extension8, vector3i9);
            WorldRenderUtils.drawFancyBox(bridgeextension_91, horsestats1210, 1157627903, false, 1.5F);
         }

         Vector3i vector3i11 = new Vector3i(vector3i5).add(18, 0, 0);
         WorldRenderUtils.highlightBlockFace(bridgeextension_91, vector3i11, this.field20, HorsestatsType_2.SOUTH);
      });
      Vector3i vector3i6 = new Vector3i(vector3i5).add(15, 0, 0);
      WorldRenderUtils.drawFancyBox1(bridgeextension_91, vector3i6, this.field20, false, true);
   }

   private void method6(AbstractRenderContext bridgeextension_91, int number2, int number3, int number4) {
      float value5 = number2 + 0.5F;
      float value6 = number3 + 0.5F;
      float value7 = number4 + 0.5F;
      this.method8(bridgeextension_91, number2, number3, number4, 0, "renderDebugLine");
      this.method8(bridgeextension_91, number2, number3, number4, 1, "renderLines");
      this.method8(bridgeextension_91, number2, number3, number4, 2, "renderLinesJoml");
      this.method8(bridgeextension_91, number2, number3, number4, 3, "renderLine");
      this.method8(bridgeextension_91, number2, number3, number4, 4, "renderLines (thick)");
      this.method8(bridgeextension_91, number2, number3, number4, 5, "drawLine");
      this.method8(bridgeextension_91, number2, number3, number4, 6, "drawLineFromCamera");
      this.method8(bridgeextension_91, number2, number3, number4, 7, "ParticleSpawnHelper.drawLine");
      this.method13(bridgeextension_91, () -> {
         WorldRenderUtils.renderDebugLine(bridgeextension_91, value5, value6, value7, value5, value6 + 3.0F, value7 + 2.0F, -65536);
         float value4x = value5 + 3.0F;
         ArrayList list5x = new ArrayList();
         list5x.add(new Line3D(new Vec3D(value4x, value6, value7), new Vec3D(value4x + 1.0F, value6 + 3.0F, value7)));
         list5x.add(new Line3D(new Vec3D(value4x, value6, value7), new Vec3D(value4x - 1.0F, value6 + 3.0F, value7)));
         WorldRenderUtils.renderLines(bridgeextension_91, list5x, -16711936);
         float value6x = value5 + 6.0F;
         HashSet set7x = new HashSet();
         set7x.add(new LineSegment(new Vector3d(value6x, value6, value7), new Vector3d(value6x, value6 + 3.0F, value7 + 2.0F)));
         WorldRenderUtils.renderLinesJoml(bridgeextension_91, set7x, -16711681);
         float value8x = value5 + 9.0F;
         WorldRenderUtils.renderLine(bridgeextension_91, value8x, value6, value7, value8x, value6 + 3.0F, value7 + 2.0F, -256, 2.0F, true);
         float value9 = value5 + 12.0F;
         HashSet set10 = new HashSet();
         set10.add(new Line3D(new Vec3D(value9, value6, value7), new Vec3D(value9, value6 + 3.0F, value7 + 2.0F)));
         WorldRenderUtils.renderThickLines(bridgeextension_91, set10, -30720, 2.5F, true);
      });
      float value8 = value5 + 15.0F;
      WorldRenderUtils.drawFancyBox1(bridgeextension_91, value8, value6, value7, value8, value6 + 4.0F, value7 + 2.0F, this.field20.method13(), 2.0F, false, true);
      WorldRenderUtils.drawFancyBox3(new Vec3D(value5 + 18.0F, value6 + 1.0F, value7 + 1.0F), bridgeextension_91, -65281, 2.0F);
   }

   private void method7(AbstractRenderContext bridgeextension_91, int number2, int number3, int number4) {
      double value5 = number3 + 0.1;
      double value7 = number4 + 0.5;
      this.method9(bridgeextension_91, number2 + 2.0, number3 + 2.5F, number4 + 0.5, "draw3DCircle");
      this.method9(bridgeextension_91, number2 + 2.0, number3 + 2.5F - 0.35, number4 + 0.5, "drawFilled3DCircle");
      this.method8(bridgeextension_91, number2, number3, number4, 2, "draw3DCylinder");
      this.method8(bridgeextension_91, number2, number3, number4, 3, "drawBeaconBeam");
      this.method8(bridgeextension_91, number2, number3, number4, 4, "fadingBoxBeam");
      this.method13(bridgeextension_91, () -> {
         double value8 = number2 + 2.0;
         WorldRenderUtils.draw3DCircle(bridgeextension_91, value8, value5, value7, 1.5, 2.0F, -16776961);
         WorldRenderUtils.drawFilled3DCircle(bridgeextension_91, value8, value5, value7, 1.0, 1140850943);
         double value10 = number2 + 6 + 0.5;
         WorldRenderUtils.drawFancyBox4(bridgeextension_91, value10, value5, value7, 1.0, 3.0, 1157597184, false);
         WorldRenderUtils.drawFancyBox7(bridgeextension_91, new Vector3i(number2 + 12, number3, number4), -2013200385);
      });
      double value9 = number2 + 9 + 0.5;
      WorldRenderUtils.drawFancyBox8(bridgeextension_91, value9, 256.0, value7, -1996553985);
      bridgeextension_91.method12();
   }

   private void method8(AbstractRenderContext bridgeextension_91, int number2, int number3, int number4, int number5, String text6) {
      this.method9(bridgeextension_91, number2 + number5 * 3 + 0.5, number3 + 2.5F, number4 + 0.5, text6);
   }

   private void method9(AbstractRenderContext bridgeextension_91, double value2, double value4, double value6, String text8) {
      this.method13(bridgeextension_91, () -> WorldRenderUtils.drawString(bridgeextension_91, text8, value2, value4, value6, -1, true));
   }

   private void method10(com.moonsworth.lunar.client.event.mixin.fishing.EventTick highlightimpl21) {
      if (this.isEnabled() && (Boolean)this.field14.get()) {
         if (++this.field22 >= 5) {
            this.field22 = 0;
            int number2 = (int)Math.floor(this.field21.x());
            int number3 = (int)Math.floor(this.field21.y());
            int number4 = (int)Math.floor(this.field21.z()) + 8;
            int[] items5 = new int[]{number2 + 21, number3, number4};
            int[] items6 = new int[]{number2 + 21, number3 + 3, number4 + 2};
            ParticleTrail.method1(items5, items6, RouteRenderer.field1, ParticleType.FLAME);
         }
      }
   }

   private void method11(EventRenderContainerSlotPost data31) {
      if (this.isEnabled() && (Boolean)this.field16.get()) {
         List list2 = List.of(Component.text("renderContainerInfo"), Component.text("line 2"), Component.text("line 3"));
         WorldRenderUtils.drawFilledBox2(data31, list2, (MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1));
      }
   }

   private void method12(AbstractRenderContext bridgeextension_91, float value2) {
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      if (itemcounter6extension3 != null && bridge5extension_54 != null) {
         int number5 = (Integer)this.field18.get();
         double value6 = number5 > 0 ? (double)number5 * number5 : -1.0;
         ArrayList list8 = new ArrayList();

         for (BridgeExtension bridgeextension10 : itemcounter6extension3.bridge$getEntities()) {
            if (bridgeextension10 instanceof EntityLivingBridge && !(bridgeextension10 instanceof Bridge6_10) && (!(value6 >= 0.0) || !(bridgeextension10.method13(bridge5extension_54) > value6))) {
               list8.add(bridgeextension10.method11(value2));
            }
         }

         this.method13(
            bridgeextension_91,
            () -> {
               DrawBufferBridge bridge2_323x = bridgeextension_91.method10(LunarRenderTypes.field15);
               bridge2_323x.method1();

               for (AxisAlignedBBBridge horsestats125x : list8) {
                  WorldRenderUtils.fillBox(
                     bridge2_323x,
                     horsestats125x.bridge$getMinX(),
                     horsestats125x.bridge$getMinY(),
                     horsestats125x.bridge$getMinZ(),
                     horsestats125x.bridge$getMaxX(),
                     horsestats125x.bridge$getMaxY(),
                     horsestats125x.bridge$getMaxZ(),
                     this.field19.method14(0.0F)
                  );
               }

               bridge2_323x.method17(BufferMode.BATCHED);
               BufferBuilderBridge bridge_287 = bridgeextension_91.method11((Float)Ref.method4().method40().method82().method19().get());

               for (AxisAlignedBBBridge horsestats126x : list8) {
                  WorldRenderUtils.drawBoxOutline(
                     bridge_287,
                     horsestats126x.bridge$getMinX(),
                     horsestats126x.bridge$getMinY(),
                     horsestats126x.bridge$getMinZ(),
                     horsestats126x.bridge$getMaxX(),
                     horsestats126x.bridge$getMaxY(),
                     horsestats126x.bridge$getMaxZ(),
                     this.field19.method14(0.0F) & 16777215 | 0xFF000000
                  );
               }

               bridge_287.end();
            }
         );
      }
   }

   private void method13(AbstractRenderContext bridgeextension_91, Runnable runnable2) {
      EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
      bridgeextension_91.push();
      bridgeextension_91.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
      runnable2.run();
      bridgeextension_91.pop();
   }
}
