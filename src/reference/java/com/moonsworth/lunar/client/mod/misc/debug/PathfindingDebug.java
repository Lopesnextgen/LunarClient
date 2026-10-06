package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.render.turbo.PathFollower;
import com.moonsworth.lunar.client.render.turbo.TurboPathFollower;
import com.moonsworth.lunar.client.render.turbo.TurboPath;
import com.moonsworth.lunar.client.render.turbo.PathEntity;
import com.moonsworth.lunar.client.render.turbo.PathNode;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.util.text.TextUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.jspecify.annotations.Nullable;

public class PathfindingDebug extends AbstractFeature {
   private static final Component field8 = Component.text("closed", NamedTextColor.DARK_RED);
   private static final Component field9 = Component.text("open", NamedTextColor.GREEN);
   private final SimpleKeybindOption field10 = (SimpleKeybindOption)OptionFactory.method17("setPoints").method31();
   private final IntegerOption field11 = (IntegerOption)((Data)((Data)OptionFactory.method4("followRange").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(16))
         .method7(1, 5000))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showNodes").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showTarget").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showNodeMap").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<PathfindingDebug.Type> field15 = (EnumOption<PathfindingDebug.Type>)OptionFactory.method10(
         "nodeInfo", PathfindingDebug.Type.TYPE
      )
      .method31();
   private @Nullable TurboPathFollower field16;
   private @Nullable Vector3i field17;
   private @Nullable Vector3i field18;

   public PathfindingDebug() {
      super(false);
      this.handle(HudRenderLegacyEvent.class, this::method6);
      this.handle(EventDisconnect.class, this::method4);
   }

   public String getId() {
      return "PATHFINDING_DEBUG";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method3(new String[]{"FX"}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field10, this.field11, this.field12, this.field13});
      lightingextension231.method7(this.field14, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15}));
      this.field11.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
         if (Ref.method8() != null && this.field16 != null) {
            Vec3iBridge horsestats202 = this.field16.method33();
            if (horsestats202 != null) {
               this.field16 = new PathFollower(this.method3(this.field17), Ref.method8(), arg1x);
               TurboPath holograms_53 = this.field16.method8(horsestats202, 1, (Integer)this.field11.get());
               if (!this.field16.method14(holograms_53)) {
                  this.field16 = null;
                  this.field17 = null;
                  this.field18 = null;
               }
            }
         }
      });
      this.field10.method3(() -> {
         if (Ref.method8() != null) {
            if (this.field17 == null && Ref.method7() != null) {
               this.method5(arg1xx -> this.field17 = arg1xx);
            } else if (this.field18 == null && this.field17 != null) {
               this.method5(arg1xx -> this.field18 = arg1xx);
               if (this.field18 != null) {
                  this.field16 = new PathFollower(this.method3(this.field17), Ref.method8(), (Integer)this.field11.get());
                  Horsestats20Extension2 horsestats20extension21x = Bridge.method8().method4(this.field18.x, this.field18.y, this.field18.z);
                  TurboPath holograms_52 = this.field16.method8(horsestats20extension21x, 1, (Integer)this.field11.get());
                  if (!this.field16.method14(holograms_52)) {
                     this.field16 = null;
                     this.field17 = null;
                     this.field18 = null;
                  }
               }
            } else {
               this.field16 = null;
               this.field17 = null;
               this.field18 = null;
            }
         }
      });
   }

   public PathEntity method3(final Vector3i vector3i1) {
      return new PathEntity() {
         public double method2() {
            return 1.0;
         }

         public float method3() {
            return 1.0F;
         }

         public float method4() {
            return 2.0F;
         }

         public boolean isOnGround() {
            return true;
         }

         public boolean isInWater() {
            return false;
         }

         public boolean method6() {
            return false;
         }

         public double bridge$getPosX() {
            return vector3i1.x + 0.5;
         }

         public double bridge$getPosY() {
            return vector3i1.y;
         }

         public double bridge$getPosZ() {
            return vector3i1.z + 0.5;
         }

         public @Nullable Horsestats20Extension2 method8() {
            return Bridge.method8().method4(vector3i1.x, vector3i1.y, vector3i1.z);
         }

         public float bridge$getWidth() {
            return 0.99F;
         }

         public float bridge$getHeight() {
            return 0.99F;
         }

         public Random method9() {
            return new Random();
         }

         public void method10(Vector3d vector3d1x) {
         }
      };
   }

   public void method4(EventDisconnect highlightimpl111) {
      this.field16 = null;
      this.field17 = null;
      this.field18 = null;
   }

   private void method5(Consumer<Vector3i> consumer1) {
      ((MissResult)Ray.method9(Raycaster.field3)
            .method8(Ref.method7(), 255.0, Ref.method3().bridge$getTimer().method1())
            .method14((arg0, arg1x) -> arg1x.bridge$hasCollision(Ref.method8(), arg0))
            .method18()
            .method8(Ref.method8()))
         .method5(arg1x -> {
            Vector3i vector3i2 = arg1x.method6().bridge$toJoml();
            vector3i2 = vector3i2.add(0, 1, 0);
            Bridge3_23 bridge3_233 = Ref.method8().ICRHORIIHOHROHOHOCOOHOOCOORRHO(vector3i2);
            if (bridge3_233 != null && bridge3_233.bridge$isAir()) {
               consumer1.accept(vector3i2);
            }
         });
   }

   public void method6(HudRenderLegacyEvent highlightimpl21) {
      EntityRenderDispatcherBridge bridge2_432 = Ref.method3().bridge$getEntityRenderDispatcher();
      AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
      if (this.field16 == null) {
         if (this.field17 != null) {
            bridgeextension_93.push();
            bridgeextension_93.translate(
               this.field17.x() - bridge2_432.bridge$renderPosX(), this.field17.y() - bridge2_432.bridge$renderPosY(), this.field17.z() - bridge2_432.bridge$renderPosZ()
            );
            BufferBuilderBridge bridge_2813 = bridgeextension_93.method12(1.0F, false);
            WorldRenderUtils.drawBoxOutline(bridge_2813, AxisAlignedBBBridge.method1(), -65536);
            bridge_2813.end();
            bridgeextension_93.pop();
         }
      } else {
         if ((Boolean)this.field13.get()) {
            Vec3iBridge horsestats204 = this.field16.method33();
            if (horsestats204 != null) {
               AxisAlignedBBBridge horsestats125 = AxisAlignedBBBridge.method2(0.0, 0.0, 0.0, 0.1, 0.1, 0.1);
               bridgeextension_93.push();
               bridgeextension_93.translate(
                  horsestats204.bridge$getX() + 0.45 - bridge2_432.bridge$renderPosX(),
                  horsestats204.bridge$getY() - bridge2_432.bridge$renderPosY(),
                  horsestats204.bridge$getZ() + 0.45 - bridge2_432.bridge$renderPosZ()
               );
               BufferBuilderBridge bridge_286 = bridgeextension_93.method12(1.5F, false);
               WorldRenderUtils.drawBoxOutline(bridge_286, horsestats125, -16711936);
               bridge_286.end();
               bridgeextension_93.pop();
            }
         }

         if ((Boolean)this.field12.get()) {
            TurboPath holograms_511 = this.field16.method30();
            if (holograms_511 != null) {
               AxisAlignedBBBridge horsestats1214 = AxisAlignedBBBridge.method2(0.1, 0.0, 0.1, 0.9, 0.2, 0.9);
               int number16 = holograms_511.method8();

               for (int index7 = 0; index7 < number16; index7++) {
                  PathNode holograms78 = holograms_511.method5(index7);
                  bridgeextension_93.push();
                  bridgeextension_93.translate(holograms78.x - bridge2_432.bridge$renderPosX(), holograms78.y - bridge2_432.bridge$viewerPosY(), holograms78.z - bridge2_432.bridge$renderPosZ());
                  BufferBuilderBridge bridge_289 = bridgeextension_93.method12(1.5F, false);
                  if (index7 == number16 - 1) {
                     WorldRenderUtils.drawBoxOutline(bridge_289, AxisAlignedBBBridge.method1(), -16711681);
                  } else {
                     WorldRenderUtils.drawBoxOutline(bridge_289, horsestats1214, -65281);
                  }

                  bridge_289.end();
                  bridgeextension_93.pop();
               }
            }
         }

         if ((Boolean)this.field14.get()) {
            AxisAlignedBBBridge horsestats1212 = AxisAlignedBBBridge.method2(0.2, 0.0, 0.2, 0.8, 0.1, 0.8);

            for (ObjectIterator objectiterator15 = this.field16.method32().method9().int2ObjectEntrySet().iterator(); objectiterator15.hasNext(); bridgeextension_93.pop()) {
               Entry entry17 = (Entry)objectiterator15.next();
               PathNode holograms718 = (PathNode)entry17.getValue();
               bridgeextension_93.push();
               bridgeextension_93.translate(holograms718.x - bridge2_432.bridge$renderPosX(), holograms718.y - bridge2_432.bridge$viewerPosY(), holograms718.z - bridge2_432.bridge$renderPosZ());
               BufferBuilderBridge bridge_2819 = bridgeextension_93.method12(1.0F, false);
               WorldRenderUtils.drawBoxOutline(bridge_2819, horsestats1212, -1442840321);
               bridge_2819.end();
               PathfindingDebug.Type type20 = (PathfindingDebug.Type)this.field15.get();
               if (type20 == PathfindingDebug.Type.CAME_FROM) {
                  PathNode holograms710 = holograms718.field5;
                  if (holograms710 != null) {
                     bridgeextension_93.pop();
                     bridgeextension_93.push();
                     bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$viewerPosY(), -bridge2_432.bridge$renderPosZ());
                     WorldRenderUtils.renderDebugLine(bridgeextension_93, holograms718.x + 0.5, holograms718.y + 0.5, holograms718.z + 0.5, holograms710.x + 0.5, holograms710.y + 0.5, holograms710.z + 0.5, -65536);
                  }
               } else {
                  WorldRenderUtils.drawComponent(bridgeextension_93, type20.dataProvider.apply(holograms718), 0.5, 0.5, 0.5, true, 0.8F);
               }
            }
         }
      }
   }

   private enum Type implements OptionEnumValue {
      TYPE(arg0 -> Component.text(arg0.field8.name())),
      HEURISTIC_TO_NEXT(arg0 -> Component.text(arg0.field3)),
      HEURISTIC_TO_TARGET(arg0 -> Component.text(arg0.field4)),
      CLOSED(arg0 -> arg0.closed ? PathfindingDebug.field8 : PathfindingDebug.field9),
      MALUS(arg0 -> Component.text(arg0.field7)),
      PATH_DISTANCE(arg0 -> Component.text(arg0.field2)),
      WALKED_DISTANCE(arg0 -> Component.text(arg0.field6)),
      CAME_FROM(arg0 -> Component.empty());

      private final Function<PathNode, Component> dataProvider;

      public String id() {
         return TextUtils.toCamelCase(this.name().toLowerCase(), false);
      }

      @Override
      public String toString() {
         return this.id();
      }

      @Generated
      Type(Function<PathNode, Component> function3) {
         this.dataProvider = function3;
      }

      @Generated
      public Function<PathNode, Component> getDataProvider() {
         return this.dataProvider;
      }
   }
}
