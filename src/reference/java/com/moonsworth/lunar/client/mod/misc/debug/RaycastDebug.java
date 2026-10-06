package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge8_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.bridge.world.BiomeBridge;
import com.moonsworth.lunar.bridge.world.mixin.ChunkBridge;
import com.moonsworth.lunar.client.cosmetics.SprayPlacement;
import com.moonsworth.lunar.client.cosmetics.SprayPlacementTracker;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.DoubleOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import com.moonsworth.lunar.client.util.raytrace.CosmeticRaycastContext;
import com.moonsworth.lunar.client.util.raytrace.BlockRaycastContext;
import com.moonsworth.lunar.client.util.raytrace.EntityRaycastContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import org.joml.Matrix4f;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector3f;

public class RaycastDebug extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)OptionFactory.method7("testDynamicBlockRaycast").method31();
   private final EnumOption<com.moonsworth.lunar.client.util.raytrace.Raycaster.Type> field9 = (EnumOption<com.moonsworth.lunar.client.util.raytrace.Raycaster.Type>)OptionFactory.method10(
         "raycastMode", com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.BLOCK
      )
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hitYourself").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<RaycastDebug.Type> field11 = (EnumOption<RaycastDebug.Type>)OptionFactory.method10(
         "blockRaycastType", RaycastDebug.Type.BLOCK
      )
      .method31();
   private final DoubleOption field12 = (DoubleOption)((Data)((Data)((Data)OptionFactory.method1("distance").OIRHOOIICOCIOOHICRRRICORIHHIHC(50.0))
            .method8(0.0, 1000.0))
         .method6(1))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderRay").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("alwaysShowRay").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderBox").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("renderText").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("showAll").method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("accurateMode").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("hitEntitiesWithoutBoundingBox").method31();
   private Ray<Itemcounter6, Vec3iBridge, MissResult, BlockRaycastContext> field20;

   public RaycastDebug() {
      super(false);
      this.handle(HudRenderLegacyEvent.class, this::method1);
   }

   public String getId() {
      return "RAYCAST_DEBUG";
   }

   private void method1(HudRenderLegacyEvent highlightimpl21) {
      AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
      EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
      if (bridge2_433 != null && (!Bridge.getMinecraftVersion().method19() || !bridge2_433.bridge$getCamera().isEmpty())) {
         Bridge5Extension_5 bridge5extension_54 = this.mc.bridge$getPlayer();
         if ((Boolean)this.field8.get()) {
            if (this.field20 == null) {
               com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data215 = Ray.method9(Raycaster.field3)
                  .method14((arg0, arg1x) -> !arg1x.bridge$isAir());
               if ((Boolean)this.field17.get()) {
                  data215.method17((arg3x, arg4x) -> this.method4(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
               }

               com.moonsworth.lunar.client.util.raytrace.Ray.Data data16 = data215.method12(bridge5extension_54, (Double)this.field12.get());
               this.field20 = data16.method18();
            }

            Horsestats_3 horsestats_317 = this.field20.method8(Ref.method8());
            this.method2(bridge2_433, this.field20, horsestats_317, bridgeextension_92);
         } else {
            com.moonsworth.lunar.client.util.raytrace.Raycaster.Type type5 = (com.moonsworth.lunar.client.util.raytrace.Raycaster.Type)this.field9.get();
            boolean flag6 = type5 == com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.COSMETIC && (Boolean)this.field10.get();

            com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data27 = switch (type5) {
               case BLOCK -> {
                  switch ((RaycastDebug.Type)this.field11.get()) {
                     case BLOCK:
                        com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data223 = Ray.method9(Raycaster.field3)
                           .method14((arg0, arg1x) -> !arg1x.bridge$isAir());
                        if ((Boolean)this.field17.get()) {
                           data223.method17((arg3x, arg4x) -> this.method4(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
                        }

                        yield yield23;
                     case BIOME:
                        BiomeBridge itemcounter_322 = bridge5extension_54.method6();
                        com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data29 = Ray.method9(Raycaster.field8)
                           .method14((arg1x, arg2x) -> arg2x != itemcounter_322);
                        if ((Boolean)this.field17.get()) {
                           data29.method17((arg3x, arg4x) -> this.method8(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
                        }

                        yield yield9;
                     case BLOCK_LIGHT:
                        com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data221 = Ray.method9(Raycaster.field6).method14((arg0, arg1x) -> arg1x >= 15);
                        if ((Boolean)this.field17.get()) {
                           data221.method17((arg3x, arg4x) -> this.method9(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
                        }

                        yield yield21;
                     case SKY_LIGHT:
                        com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data220 = Ray.method9(Raycaster.field7).method14((arg0, arg1x) -> arg1x >= 15);
                        if ((Boolean)this.field17.get()) {
                           data220.method17((arg3x, arg4x) -> this.method9(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
                        }

                        yield yield20;
                     default:
                        yield Ray.method9(Raycaster.field3);
                  }
               }
               case ENTITY -> {
                  com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data219 = Ray.method9(Raycaster.field9)
                     .method1(new EntityRaycastContext((Boolean)this.field19.get()))
                     .method14((arg0, arg1x) -> Ref.method7() != arg1x);
                  if ((Boolean)this.field17.get()) {
                     data219.method17((arg3x, arg4x) -> this.method6(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
                  }

                  yield yield19;
               }
               case COSMETIC -> flag6
                  ? Ray.method9(Raycaster.field11).method1(new CosmeticRaycastContext((Boolean)this.field18.get())).method14((arg0, arg1x) -> true)
                  : Ray.method9(Raycaster.field9).method14((arg0, arg1x) -> arg1x instanceof Bridge6_10 && Ref.method7() != arg1x);
               case SPRAY -> {
                  com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data218 = Ray.method9(Raycaster.field12).method14((arg0, arg1x) -> true);
                  if ((Boolean)this.field17.get()) {
                     data218.method17((arg3x, arg4x) -> this.method5(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
                  }

                  yield yield18;
               }
               case CHUNK -> {
                  com.moonsworth.lunar.client.util.raytrace.Ray.RayBuilder data28 = Ray.method9(Raycaster.field10).method14((arg1x, arg2x) -> {
                     Vector2d vector2d3x = new Vector2d(bridge5extension_54.bridge$getPosX(), bridge5extension_54.bridge$getPosZ());
                     return new Vector2d((arg2x.bridge$getX() << 4) + 8, (arg2x.bridge$getZ() << 4) + 8).distance(vector2d3x) >= 96.0;
                  });
                  if ((Boolean)this.field17.get()) {
                     data28.method17((arg3x, arg4x) -> this.method7(bridgeextension_92, bridge2_433, arg3x, this.method3(arg4x)));
                  }

                  yield yield8;
               }
               default -> Ray.method9(Raycaster.field3);
            };
            if (flag6) {
               Ref.method13()
                  .bridge$getCamera()
                  .ifPresentOrElse(
                     arg2x -> {
                        Vec3Bridge horsestats153x = this.method14(arg2x.bridge$getPitch(), arg2x.bridge$getYaw());
                        Vec3Bridge horsestats154x = Vec3Bridge.method2(arg2x.bridge$getPosX(), arg2x.bridge$getPosY(), arg2x.bridge$getPosZ());
                        byte number5x = 4;
                        data27.method2(
                           Vec3Bridge.method2(
                              horsestats154x.bridge$xCoord() + horsestats153x.bridge$xCoord() * number5x,
                              horsestats154x.bridge$yCoord() + horsestats153x.bridge$yCoord() * number5x,
                              horsestats154x.bridge$zCoord() + horsestats153x.bridge$zCoord() * number5x
                           ),
                           horsestats154x
                        );
                     },
                     () -> {
                        Vec3Bridge horsestats153x = this.method14((float)bridge5extension_54.bridge$getRotationPitch(), (float)bridge5extension_54.bridge$getRotationYaw());
                        Vec3Bridge horsestats154x = Vec3Bridge.method2(bridge5extension_54.bridge$getPosX(), bridge5extension_54.bridge$getPosY(), bridge5extension_54.bridge$getPosZ());
                        byte number5x = 4;
                        data27.method2(
                           Vec3Bridge.method2(
                              horsestats154x.bridge$xCoord() + horsestats153x.bridge$xCoord() * number5x,
                              horsestats154x.bridge$yCoord() + horsestats153x.bridge$yCoord() * number5x,
                              horsestats154x.bridge$zCoord() + horsestats153x.bridge$zCoord() * number5x
                           ),
                           horsestats154x
                        );
                     }
                  );
            } else {
               data27.method8(bridge5extension_54, (Double)this.field12.get(), highlightimpl21.method5());
            }

            Ray sextension24 = data27.method18();
            Horsestats_3 horsestats_325;
            if (flag6) {
               horsestats_325 = sextension24.method8(bridge5extension_54);
            } else if (type5 == com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.SPRAY) {
               ArrayList list10 = new ArrayList();

               for (List list12 : Ref.method4().method46().method43().values()) {
                  for (SprayPlacementTracker clickhandler214 : list12) {
                     list10.add(clickhandler214);
                  }
               }

               horsestats_325 = sextension24.method8(list10);
            } else {
               horsestats_325 = sextension24.method8(Ref.method8());
            }

            this.method2(bridge2_433, sextension24, horsestats_325, bridgeextension_92);
         }
      }
   }

   private void method2(EntityRenderDispatcherBridge bridge2_431, Ray sextension2, Horsestats_3<?, ?> horsestats_33, AbstractRenderContext bridgeextension_94) {
      boolean flag5 = horsestats_33.method1() != com.moonsworth.lunar.bridge.horsestats.Horsestats.Type.MISS;
      Vector3d vector3d6 = new Vector3d(sextension2.method4().bridge$xCoord(), sextension2.method4().bridge$yCoord(), sextension2.method4().bridge$zCoord());
      if (flag5) {
         if (horsestats_33 instanceof MissResult horsestatshandler7) {
            this.method4(bridgeextension_94, bridge2_431, horsestatshandler7, vector3d6);
         } else if (horsestats_33 instanceof com.moonsworth.lunar.bridge.horsestats.EntityHitResult.Data data8) {
            BridgeExtension bridgeextension14 = data8.method5();
            if (this.field9.get() == com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.COSMETIC) {
               if (bridgeextension14 instanceof Bridge5_11 bridge5_1115
                  && Ray.method9(Raycaster.field11)
                     .method8(this.mc.bridge$getPlayer(), (Double)this.field12.get(), 0.0F)
                     .method14((arg0, arg1x) -> true)
                     .method18()
                     .method8(bridge5_1115) instanceof com.moonsworth.lunar.bridge.horsestats.CosmeticHitResult.Data data17) {
                  this.method10(bridgeextension_94, bridge2_431, data17, vector3d6);
               }
            } else {
               this.method6(bridgeextension_94, bridge2_431, data8, vector3d6);
            }
         } else if (horsestats_33 instanceof com.moonsworth.lunar.bridge.horsestats.ChunkHitResult.Data data9) {
            this.method7(bridgeextension_94, bridge2_431, data9, vector3d6);
         } else if (horsestats_33 instanceof com.moonsworth.lunar.bridge.horsestats.BiomeHitResult.Data data10) {
            this.method8(bridgeextension_94, bridge2_431, data10, vector3d6);
         } else if (horsestats_33 instanceof com.moonsworth.lunar.bridge.horsestats.LightHitResult.Data data11) {
            this.method9(bridgeextension_94, bridge2_431, data11, vector3d6);
         } else if (horsestats_33 instanceof com.moonsworth.lunar.bridge.horsestats.CosmeticHitResult.Data data12) {
            this.method10(bridgeextension_94, bridge2_431, data12, vector3d6);
         } else if (horsestats_33 instanceof com.moonsworth.lunar.bridge.horsestats.SprayHitResult.Data data13) {
            this.method5(bridgeextension_94, bridge2_431, data13, vector3d6);
         }
      }

      if ((Boolean)this.field13.get() && (flag5 || (Boolean)this.field14.get()) && this.mc.bridge$getGameSettings().bridge$getThirdPersonView() != 0) {
         bridgeextension_94.push();
         bridgeextension_94.translate(-bridge2_431.bridge$renderPosX(), -bridge2_431.bridge$renderPosY(), -bridge2_431.bridge$renderPosZ());
         BufferBuilderBridge bridge_2818 = bridgeextension_94.method11(1.0F);
         if (!flag5) {
            bridge_2818.method2(1.0F, 0.0F, 0.0F, 1.0F);
         }

         bridge_2818.method3(vector3d6.x, vector3d6.y, vector3d6.z, sextension2.method5().bridge$xCoord(), sextension2.method5().bridge$yCoord(), sextension2.method5().bridge$zCoord());
         bridge_2818.end();
         bridgeextension_94.pop();
      }
   }

   private Vector3d method3(Ray sextension1) {
      Vec3Bridge horsestats152 = sextension1.method4();
      return new Vector3d(horsestats152.bridge$xCoord(), horsestats152.bridge$yCoord(), horsestats152.bridge$zCoord());
   }

   private void method4(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, MissResult horsestatshandler3, Vector3d vector3d4) {
      Vec3Bridge horsestats155 = horsestatshandler3.method5();
      Vec3iBridge horsestats206 = horsestatshandler3.method6();
      if ((Boolean)this.field16.get()) {
         this.method11(
            bridgeextension_91,
            bridge2_432,
            vector3d4,
            new Vector3d(horsestats155.bridge$xCoord(), horsestats155.bridge$yCoord(), horsestats155.bridge$zCoord()),
            Ref.method8().RHIRRICCRHHHIIHHIHHOHRCHIOORCC(horsestats206).bridge$getName()
         );
      }

      if ((Boolean)this.field15.get()) {
         this.method12(
            bridgeextension_91,
            bridge2_432,
            horsestats206.bridge$getX(),
            horsestats206.bridge$getY(),
            horsestats206.bridge$getZ(),
            horsestats206.bridge$getX() + 1,
            horsestats206.bridge$getY() + 1,
            horsestats206.bridge$getZ() + 1,
            -16711936
         );
      }
   }

   private void method5(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, com.moonsworth.lunar.bridge.horsestats.SprayHitResult.Data data3, Vector3d vector3d4) {
      SprayPlacement click5 = (SprayPlacement)data3.method7();
      Vector3f vector3f6 = click5.method2();
      if ((Boolean)this.field16.get()) {
         this.method11(bridgeextension_91, bridge2_432, vector3d4, new Vector3d(vector3f6), Component.text(click5.method1().getName()));
      }

      if ((Boolean)this.field15.get()) {
         this.method12(bridgeextension_91, bridge2_432, vector3f6.x - 0.005, vector3f6.y - 0.005, vector3f6.z - 0.005, vector3f6.x + 0.005, vector3f6.y + 0.005, vector3f6.z + 0.005, -65536);
      }
   }

   private void method6(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, com.moonsworth.lunar.bridge.horsestats.EntityHitResult.Data data3, Vector3d vector3d4) {
      BridgeExtension bridgeextension5 = data3.method5();
      Vec3Bridge horsestats156 = bridgeextension5.bridge$getEyePosition();
      if ((Boolean)this.field16.get()) {
         this.method11(bridgeextension_91, bridge2_432, vector3d4, new Vector3d(horsestats156.bridge$xCoord(), horsestats156.bridge$yCoord(), horsestats156.bridge$zCoord()), bridgeextension5.bridge$getTypeName());
      }

      if ((Boolean)this.field15.get()) {
         AxisAlignedBBBridge horsestats127 = bridgeextension5.bridge$getBoundingBox();
         this.method12(
            bridgeextension_91,
            bridge2_432,
            horsestats127.bridge$getMinX(),
            horsestats127.bridge$getMinY(),
            horsestats127.bridge$getMinZ(),
            horsestats127.bridge$getMaxX(),
            horsestats127.bridge$getMaxY(),
            horsestats127.bridge$getMaxZ(),
            -16711936
         );
      }
   }

   private void method7(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, com.moonsworth.lunar.bridge.horsestats.ChunkHitResult.Data data3, Vector3d vector3d4) {
      ChunkBridge itemcounter25 = data3.method7();
      if ((Boolean)this.field16.get()) {
         this.method11(
            bridgeextension_91,
            bridge2_432,
            vector3d4,
            new Vector3d(itemcounter25.bridge$getX() << 4, Ref.method7().bridge$getPosY(), itemcounter25.bridge$getZ() << 4),
            Component.text().content("(" + itemcounter25.bridge$getX() + ", " + itemcounter25.bridge$getZ() + ")").build()
         );
      }

      if ((Boolean)this.field15.get()) {
         int number6 = itemcounter25.bridge$getX() << 4;
         int number7 = itemcounter25.bridge$getZ() << 4;
         this.method12(
            bridgeextension_91,
            bridge2_432,
            number6,
            Ref.method8().bridge$getMinBuildHeight(),
            number7,
            number6 + 16,
            Ref.method8().bridge$getMaxBuildHeight(),
            number7 + 16,
            -16711936
         );
      }
   }

   private void method8(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, com.moonsworth.lunar.bridge.horsestats.BiomeHitResult.Data data3, Vector3d vector3d4) {
      BiomeBridge itemcounter_35 = data3.method8();
      Vec3iBridge horsestats206 = data3.method7();
      if ((Boolean)this.field16.get()) {
         this.method11(
            bridgeextension_91,
            bridge2_432,
            vector3d4,
            new Vector3d(horsestats206.bridge$getX(), horsestats206.bridge$getY(), horsestats206.bridge$getZ()),
            Component.text().content(itemcounter_35.bridge$getBiomeName()).build()
         );
      }

      if ((Boolean)this.field15.get()) {
         this.method12(
            bridgeextension_91,
            bridge2_432,
            horsestats206.bridge$getX(),
            horsestats206.bridge$getY(),
            horsestats206.bridge$getZ(),
            horsestats206.bridge$getX() + 1,
            horsestats206.bridge$getY() + 1,
            horsestats206.bridge$getZ() + 1,
            -16711936
         );
      }
   }

   private void method9(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, com.moonsworth.lunar.bridge.horsestats.LightHitResult.Data data3, Vector3d vector3d4) {
      Vec3iBridge horsestats205 = data3.method7();
      if ((Boolean)this.field16.get()) {
         this.method11(
            bridgeextension_91, bridge2_432, vector3d4, new Vector3d(horsestats205.bridge$getX(), horsestats205.bridge$getY(), horsestats205.bridge$getZ()), Component.text().content(data3.method9() + "").build()
         );
      }

      if ((Boolean)this.field15.get()) {
         this.method12(
            bridgeextension_91,
            bridge2_432,
            horsestats205.bridge$getX(),
            horsestats205.bridge$getY(),
            horsestats205.bridge$getZ(),
            horsestats205.bridge$getX() + 1,
            horsestats205.bridge$getY() + 1,
            horsestats205.bridge$getZ() + 1,
            -16711936
         );
      }
   }

   private void method10(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, com.moonsworth.lunar.bridge.horsestats.CosmeticHitResult.Data data3, Vector3d vector3d4) {
      OwnedCosmetic gui2handler5 = (OwnedCosmetic)data3.method7();
      Vector3d vector3d6 = data3.method9();
      Matrix4f matrix4f7 = data3.method10();
      AxisAlignedBBBridge horsestats128 = data3.method8();
      if ((Boolean)this.field16.get()) {
         this.method11(bridgeextension_91, bridge2_432, vector3d4, vector3d6, Component.text().content(gui2handler5.getName()).build());
      }

      if ((Boolean)this.field15.get()) {
         this.method12(bridgeextension_91, bridge2_432, vector3d6.x - 0.005, vector3d6.y - 0.005, vector3d6.z - 0.005, vector3d6.x + 0.005, vector3d6.y + 0.005, vector3d6.z + 0.005, -65536);
         float value9 = 0.01F;
         Vector3f vector3f10 = new Vector3f((float)horsestats128.bridge$getMinX() - 0.01F, (float)horsestats128.bridge$getMinY() - 0.01F, (float)horsestats128.bridge$getMinZ() - 0.01F);
         Vector3f vector3f11 = new Vector3f((float)horsestats128.bridge$getMaxX() + 0.01F, (float)horsestats128.bridge$getMaxY() + 0.01F, (float)horsestats128.bridge$getMaxZ() + 0.01F);
         matrix4f7.transformPosition(vector3f10);
         matrix4f7.transformPosition(vector3f11);
         this.method12(bridgeextension_91, bridge2_432, vector3f10.x(), vector3f10.y(), vector3f10.z(), vector3f11.x(), vector3f11.y(), vector3f11.z(), -16711936);
      }
   }

   private void method11(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, Vector3d vector3d3, Vector3d vector3d4, Component component5) {
      bridgeextension_91.push();
      double value6 = bridge2_432.bridge$renderPosX();
      double value8 = bridge2_432.bridge$renderPosY();
      double value10 = bridge2_432.bridge$renderPosZ();
      double value12 = vector3d4.x - value6;
      double value14 = vector3d4.y - value8;
      double value16 = vector3d4.z - value10;
      Vector3d vector3d18 = this.method13(vector3d3, vector3d4, 0.9);
      Vector3d vector3d19 = new Vector3d(vector3d18.x - value6, vector3d18.y - value8 + 1.0, vector3d18.z - value10);
      Bridge8_5.method3(bridgeextension_91, vector3d19);
      float value20 = 0.016666668F * (float)(1.0 + Math.sqrt(value12 * value12 + value14 * value14 + value16 * value16) * 0.15F);
      bridgeextension_91.scale(-value20, -value20, value20);
      bridgeextension_91.method12();
      Ref.method10().method11(bridgeextension_91, component5, Ref.method10().method18(component5, 0.0F), -10.0F, -1, true);
      bridgeextension_91.method13();
      bridgeextension_91.pop();
   }

   private void method12(AbstractRenderContext bridgeextension_91, EntityRenderDispatcherBridge bridge2_432, double value3, double value5, double value7, double value9, double value11, double value13, int number15) {
      bridgeextension_91.push();
      bridgeextension_91.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
      BufferBuilderBridge bridge_2816 = bridgeextension_91.method11(1.0F);
      bridge_2816.method1(number15);
      bridge_2816.method3(value3, value5, value7, value9, value5, value7);
      bridge_2816.method3(value9, value5, value7, value9, value5, value13);
      bridge_2816.method3(value9, value5, value13, value3, value5, value13);
      bridge_2816.method3(value3, value5, value7, value3, value5, value13);
      bridge_2816.method3(value3, value11, value7, value9, value11, value7);
      bridge_2816.method3(value9, value11, value7, value9, value11, value13);
      bridge_2816.method3(value9, value11, value13, value3, value11, value13);
      bridge_2816.method3(value3, value11, value7, value3, value11, value13);
      bridge_2816.method3(value3, value5, value7, value3, value11, value7);
      bridge_2816.method3(value9, value5, value7, value9, value11, value7);
      bridge_2816.method3(value3, value5, value13, value3, value11, value13);
      bridge_2816.method3(value9, value5, value13, value9, value11, value13);
      bridge_2816.end();
      bridgeextension_91.pop();
   }

   private Vector3d method13(Vector3d vector3d1, Vector3d vector3d2, double value3) {
      Vector3d vector3d5 = new Vector3d(vector3d2).sub(vector3d1);
      double value6 = vector3d5.length();
      vector3d5.normalize();
      double value8 = value6 * value3;
      return new Vector3d(vector3d1.x + value8 * vector3d5.x, vector3d1.y + value8 * vector3d5.y, vector3d1.z + value8 * vector3d5.z);
   }

   public Vec3Bridge method14(float value1, float value2) {
      float value3 = value1 * (float) (Math.PI / 180.0);
      float value4 = -value2 * (float) (Math.PI / 180.0);
      float value5 = (float)Math.cos(value4);
      float value6 = (float)Math.sin(value4);
      float value7 = (float)Math.cos(value3);
      float value8 = (float)Math.sin(value3);
      return Vec3Bridge.method2(value6 * value7, -value8, value5 * value7);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field9})).method2(() -> (Boolean)this.field8.get());
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field11}))
         .method2(() -> (Boolean)this.field8.get() || this.field9.get() != com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.BLOCK);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field12}))
         .method2(() -> this.field9.get() == com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.COSMETIC && (Boolean)this.field10.get());
      lightingextension231.method7(this.field13, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field14}));
      lightingextension231.method9(new ClientOption[]{this.field15, this.field16});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field17}))
         .method2(() -> this.field9.get() == com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.COSMETIC);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field10, this.field18}))
         .method2(() -> this.field9.get() != com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.COSMETIC);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field19}))
         .method2(() -> this.field9.get() != com.moonsworth.lunar.client.util.raytrace.Raycaster.Type.ENTITY);
      this.field8.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field20 = null);
      this.field17.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field20 = null);
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.field20 = null);
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method4(() -> "Raycast Debug Mod")
         .method1(new ModCategory[]{ModCategory.field6, ModCategory.field7})
         .method11(this);
   }

   private enum Type implements OptionEnumValue {
      BLOCK,
      BLOCK_LIGHT,
      SKY_LIGHT,
      BIOME;

      Type() {
      }

      public String id() {
         return this.name().toLowerCase(Locale.ROOT);
      }
   }
}
