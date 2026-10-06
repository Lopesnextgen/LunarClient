package com.moonsworth.lunar.client.mod.render.crosshair;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.render.texture.FramebufferCaptureTask;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairPattern;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairPresets;
import com.moonsworth.lunar.client.framework.feature.crosshair.CrosshairType;
import com.moonsworth.lunar.client.event.ResultEvent.Outcome;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderCrosshair;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.driver.bridge.JsonSection;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.UUID;
import lombok.Generated;

public class Crosshair extends AbstractFeature {
   public final CrosshairStyle field8 = CrosshairStyle.method27(this, true, CrosshairType.NORMAL);
   public final CrosshairStyle field9 = CrosshairStyle.method27(this, false, CrosshairType.FRIENDLY);
   public final CrosshairStyle field10 = CrosshairStyle.method27(this, false, CrosshairType.ENEMY);
   public final CrosshairElytra field11 = CrosshairElytra.method13(this, false);
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showInF5")
         .method4(true))
      .method31();

   public Crosshair() {
      super(false);
      this.handle(EventRenderHudBase.class, this::method6);
      this.handle(EventRenderCrosshair.class, this::method5);
   }

   public String getId() {
      return "CROSSHAIR";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field3}).method11(this);
   }

   protected boolean method24(String text1) {
      return text1.equals("CROSSHAIR_ELYTRA");
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field8, this.field9, this.field10, this.field11);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field12});
   }

   private void method5(EventRenderCrosshair highlightimpl221) {
      highlightimpl221.method2(Outcome.DENY);
   }

   public void method6(EventRenderHudBase highlightimpl1) {
      if (!FramebufferCaptureTask.method6()) {
         if (!Ref.method4().method40().method85().method17(arg0 -> !arg0.method53().method14())) {
            if (!Ref.method4().method40().method85().method19()) {
               GuiScreenBridge bridge5extension62 = this.mc.bridge$getCurrentScreen();
               if (bridge5extension62 != null && !(bridge5extension62 instanceof Bridge5Extension612) && bridge5extension62 instanceof Bridge5Extension62) {
                  return;
               }

               if (Ref.method4().method40().method64().method13()) {
                  return;
               }
            }

            if (this.mc.bridge$getGameSettings().bridge$getThirdPersonView() == 0 || (Boolean)this.field12.get()) {
               CrosshairType crosshairtype15 = CrosshairType.NORMAL;
               boolean flag3 = this.field10.isEnabled();
               boolean flag4 = this.field9.isEnabled();
               boolean flag5 = this.field11.isEnabled();
               if (flag3 || flag4) {
                  BridgeExtension bridgeextension6 = (BridgeExtension)this.mc.bridge$getPointedEntity().orElse(null);
                  if (bridgeextension6 instanceof Bridge6_10) {
                     UUID uuid8 = bridgeextension6.bridge$getUniqueID();
                     boolean flag9 = this.field4.method60().method3().containsKey(uuid8);
                     if (flag4 && flag9) {
                        crosshairtype15 = CrosshairType.FRIENDLY;
                     } else if (flag3 && !flag9 && !bridgeextension6.bridge$isInvisibleTo(Ref.method7())) {
                        crosshairtype15 = CrosshairType.ENEMY;
                     }
                  } else if (bridgeextension6 instanceof EntityLivingBridge bridgeextension2_57) {
                     boolean flag18 = bridgeextension2_57.bridge$isHostile();
                     if (flag3 && flag18) {
                        crosshairtype15 = CrosshairType.ENEMY;
                     } else if (flag4 && !flag18) {
                        crosshairtype15 = CrosshairType.FRIENDLY;
                     }
                  }
               }

               List list16 = ((ModChildren)this.method7(ModTraits.field5)).getChildren();
               CrosshairStyle crosshairchildmod17 = null;
               CrosshairElytra crosshairelytra19 = null;

               for (int index20 = 0; index20 < list16.size(); index20++) {
                  CrosshairStyle crosshairchildmod10 = (CrosshairStyle)list16.get(index20);
                  if (flag5 && crosshairchildmod10 instanceof CrosshairElytra crosshairelytra11) {
                     crosshairelytra19 = crosshairelytra11;
                     if (crosshairchildmod17 != null) {
                        break;
                     }
                  }

                  if (crosshairchildmod17 == null && crosshairchildmod10.method29() == crosshairtype15) {
                     crosshairchildmod17 = crosshairchildmod10;
                     if (!flag5 || crosshairelytra19 != null) {
                        break;
                     }
                  }
               }

               if (crosshairchildmod17 != null) {
                  if (crosshairtype15 != CrosshairType.NORMAL && !crosshairchildmod17.isEnabled()) {
                     crosshairchildmod17 = this.field8;
                  }

                  if (crosshairchildmod17.isEnabled()) {
                     double value21 = highlightimpl1.method3().method10();
                     double value23 = highlightimpl1.method3().method11();
                     int number13 = (int)Math.round(value21 / 2.0);
                     int number14 = (int)Math.round(value23 / 2.0);
                     crosshairchildmod17.method15(highlightimpl1.method2(), new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2(number13, number14), false);
                  }

                  if (crosshairelytra19 != null) {
                     double value22 = highlightimpl1.method3().method10();
                     double value24 = highlightimpl1.method3().method11();
                     int number25 = (int)Math.round(value22 / 2.0);
                     int number26 = (int)Math.round(value24 / 2.0);
                     crosshairelytra19.method15(highlightimpl1.method2(), new com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2(number25, number26), false);
                  }
               }
            }
         }
      }
   }

   @JsonSection("settings")
   public JsonElement method31() {
      JsonArray array1 = (JsonArray)super.method31();
      JsonArray array2 = new JsonArray();

      for (String text6 : CrosshairPresets.field16) {
         array2.add(text6);
      }

      JsonArray array7 = new JsonArray();

      for (CrosshairPattern crosshair210 : CrosshairPresets.field17) {
         array7.add(crosshair210.method4());
      }

      JsonObject json9 = new JsonObject();
      json9.add("crosshair_presets", array2);
      json9.add("crosshair_banned", array7);
      array1.add(json9);
      return array1;
   }

   @Generated
   public ToggleOption method13() {
      return this.field12;
   }
}
