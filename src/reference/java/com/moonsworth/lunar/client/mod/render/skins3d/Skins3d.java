package com.moonsworth.lunar.client.mod.render.skins3d;

import com.lunarclient.apollo.module.limb.BodyPart;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.LayerRendererBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.cosmetics.skin.HatLayerRenderer;
import com.moonsworth.lunar.client.cosmetics.skin.BodyOverlayLayerRenderer;
import com.moonsworth.lunar.client.cosmetics.skin.SkinLayerCache;
import com.moonsworth.lunar.client.event.player.EventPlayerRemove;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderModel.EventRenderBipedModel;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Set;
import java.util.function.Consumer;
import lombok.Generated;
import org.joml.Vector3d;

public class Skins3d extends AbstractFeature {
   private static final float field8 = 1.08F;
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableHat")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableJacket")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableLeftSleeve")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableRightSleeve")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableLeftPants")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableRightPants")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field15 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("baseVoxelSize")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.15F))
         .method8(1.001F, 1.4F))
      .method31();
   private final FloatOption field16 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("bodyVoxelWidthSize")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.05F))
         .method8(1.001F, 1.4F))
      .method31();
   private final FloatOption field17 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("headVoxelSize")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.18F))
         .method8(1.001F, 1.25F))
      .method31();
   private final FloatOption field18 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "firstPersonVoxelSize"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.1F))
         .method8(1.001F, 1.3F))
      .method31();
   private final IntegerOption field19 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
               "renderDistanceLod"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(14))
         .method7(5, 40))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableSkulls")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("enableSkullsItems")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field22 = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2("skullVoxelSize")
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.1F))
         .method8(1.001F, 1.2F))
      .method31();
   private final SkinLayerCache field23 = new SkinLayerCache();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showOthers")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public Skins3d() {
      super(false);
      this.handle(EventServerJoin.class, this.field23::method1);
      this.handle(EventPlayerRemove.class, this.field23::method2);
      this.handle(EventRenderBipedModel.class, this::method8);
   }

   public String getId() {
      return "3D_SKINS";
   }

   public static Skins3d method13() {
      return Client.method109().method40().method25();
   }

   public boolean shouldRender(EntityPlayerBridge bridgeextension2221) {
      Skins3d skins3d2 = method13();
      if (!skins3d2.isEnabled()) {
         return false;
      }

      if (!(Boolean)skins3d2.method35().get()) {
         Bridge5Extension_5 bridge5extension_53 = Ref.method3().bridge$getPlayer();
         if (bridge5extension_53 != null && !bridgeextension2221.bridge$getUniqueID().equals(bridge5extension_53.bridge$getUniqueID())) {
            return false;
         }
      }

      if (bridgeextension2221.bridge$isSkinTextureUploaded() && !bridgeextension2221.bridge$isInvisible() && (Boolean)skins3d2.method15().get()) {
         return Ref.method7() != null && bridgeextension2221.bridge$distanceToCameraSq() > skins3d2.method14() && !bridgeextension2221.bridge$isDummySelf()
            ? false
            : !bridgeextension2221.bridge$isEmoting();
      } else {
         return false;
      }
   }

   public boolean method2(EntityPlayerBridge bridgeextension2221) {
      return Ref.method4().method53().method15(bridgeextension2221.bridge$getUniqueID(), CosmeticCategoryType.SUITS) != null;
   }

   public float method3(FloatOption lightingextension4721, boolean flag2) {
      return flag2 ? Math.min((Float)lightingextension4721.get(), 1.08F) : (Float)lightingextension4721.get();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method1(
         "performanceOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field19, this.field24})
      );
      lightingextension231.method1(
         "playerModelOptions",
         arg1x -> arg1x.method9(
            new ClientOption[]{
               this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, this.field16, this.field17, this.field18
            }
         )
      );
      lightingextension231.method1(
         "skullModelOptions", arg1x -> arg1x.method9(new ClientOption[]{this.field20, this.field21, this.field22})
      );
      if (Ref.MC_VERSION >= 8) {
         this.field20.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> {
            TurboEngineManager fogiterator_31x = Ref.method4().method89();
            if (fogiterator_31x != null && fogiterator_31x.method6()) {
               fogiterator_31x.method10(BatchEntityType.ENTITIES);
            }
         });
         this.field21.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg0 -> {
            TurboEngineManager fogiterator_31x = Ref.method4().method89();
            if (fogiterator_31x != null) {
               if (fogiterator_31x.method3()) {
                  fogiterator_31x.method10(BatchEntityType.ENTITIES);
               }

               if (fogiterator_31x.method6()) {
                  fogiterator_31x.method10(BatchEntityType.ENTITIES);
               }
            }
         });
         this.field22.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> {
            if ((Boolean)this.field20.get() || (Boolean)this.field21.get()) {
               TurboEngineManager fogiterator_32 = Ref.method4().method89();
               if (fogiterator_32 != null) {
                  if (fogiterator_32.method3()) {
                     fogiterator_32.method10(BatchEntityType.ENTITIES);
                  }

                  if (fogiterator_32.method6()) {
                     fogiterator_32.method10(BatchEntityType.ENTITIES);
                  }
               }
            }
         });
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method3(new String[]{"tr7zw"}).method11(this);
   }

   public int method14() {
      int number1 = (Integer)this.field19.get();
      return number1 * number1;
   }

   public void method7(Consumer<LayerRendererBridge> consumer1) {
      consumer1.accept(HatLayerRenderer.field1);
      consumer1.accept(BodyOverlayLayerRenderer.field1);
   }

   private void method8(EventRenderBipedModel data131) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method3().bridge$getPlayer();
      if (bridge5extension_52 != null) {
         if (!(Boolean)this.field24.get() && !bridge5extension_52.bridge$getUniqueID().equals(data131.CCOOOHHHROORHRHORHCOIIHOHCRCRC().bridge$getUniqueID())) {
            return;
         }

         EntityPlayerBridge bridgeextension2223 = data131.CCOOOHHHROORHRHORHCOIIHOHCRCRC();
         Vector3d vector3d4 = new Vector3d(bridge5extension_52.bridge$getPosX(), bridge5extension_52.bridge$getPosY(), bridge5extension_52.bridge$getPosZ());
         Vector3d vector3d5 = new Vector3d(bridgeextension2223.bridge$getPosX(), bridgeextension2223.bridge$getPosY(), bridgeextension2223.bridge$getPosZ());
         if (vector3d5.distanceSquared(vector3d4) > this.method14()) {
            return;
         }
      }

      Set set6 = data131.method5();
      this.method9(BodyPart.HEAD, this.field9, set6);
      this.method9(BodyPart.TORSO, this.field10, set6);
      this.method9(BodyPart.LEFT_ARM, this.field11, set6);
      this.method9(BodyPart.RIGHT_ARM, this.field12, set6);
      this.method9(BodyPart.LEFT_LEG, this.field13, set6);
      this.method9(BodyPart.RIGHT_LEG, this.field14, set6);
   }

   private void method9(BodyPart bodypart1, ToggleOption lightingextension4432, Set<BodyPart> set3) {
      if ((Boolean)lightingextension4432.get()) {
         set3.add(bodypart1);
      }
   }

   @Generated
   public ToggleOption method15() {
      return this.field9;
   }

   @Generated
   public ToggleOption method16() {
      return this.field10;
   }

   @Generated
   public ToggleOption method17() {
      return this.field11;
   }

   @Generated
   public ToggleOption method19() {
      return this.field12;
   }

   @Generated
   public ToggleOption method21() {
      return this.field13;
   }

   @Generated
   public ToggleOption method22() {
      return this.field14;
   }

   @Generated
   public FloatOption method23() {
      return this.field15;
   }

   @Generated
   public FloatOption method24() {
      return this.field16;
   }

   @Generated
   public FloatOption method25() {
      return this.field17;
   }

   @Generated
   public FloatOption method26() {
      return this.field18;
   }

   @Generated
   public IntegerOption method27() {
      return this.field19;
   }

   @Generated
   public ToggleOption method28() {
      return this.field20;
   }

   @Generated
   public ToggleOption method29() {
      return this.field21;
   }

   @Generated
   public FloatOption method30() {
      return this.field22;
   }

   @Generated
   public SkinLayerCache method34() {
      return this.field23;
   }

   @Generated
   public ToggleOption method35() {
      return this.field24;
   }
}
