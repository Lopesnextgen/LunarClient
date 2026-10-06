package com.moonsworth.lunar.client.mod.render.nametag;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityLivingStateBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityLabel.EventRenderEntityLabelLines;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig.Builder;
import net.kyori.adventure.text.format.TextColor;

public class Nametag extends AbstractFeature {
   private final FloatOption nametagBackgroundOpacity = (FloatOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method2(
               "nametagBackgroundOpacity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .method8(0.0F, 1.0F))
      .method31();
   private final ModifierKeybindOption toggleNametagsKeybind = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
               "toggleNametagsKeybind"
            )
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption togglePlayerNametagsKeybind = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
               "togglePlayerNametagsKeybind"
            )
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ToggleOption nametag = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("nametag")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption nametagShadow = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("nametagShadow")
      .method31();
   private final ToggleOption toggleNametags = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("toggleNametags")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption hideNametagsInF1 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideNametagsInF1")
      .method31();
   private final ToggleOption replaceOwnNametagColor = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("replaceOwnNametagColor")
      .method31();
   private final ColorOption ownNametagColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "ownNametagColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method16()
      .method31();
   private final ToggleOption hideNametagF5Passengers = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("hideNametagF5Passengers")
      .method31();
   private boolean allNametagsHidden = false;
   private boolean playerNametagsHidden = false;
   private Pattern ownNamePattern = null;
   private final Consumer<Builder> ownNameReplacer = arg1 -> {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         String text3 = bridge5extension_52.bridge$getName();
         if (this.ownNamePattern == null) {
            this.ownNamePattern = Pattern.compile(text3, 16);
         }

         arg1.match(this.ownNamePattern).replacement(arg1x -> arg1x.color(TextColor.color(this.ownNametagColor.method14(0.0F))));
      }
   };

   public Nametag() {
      super(true);
      this.method2(
         EventRenderNameTag.class,
         arg1 -> {
            if (!this.method3(arg1.method2())) {
               arg1.setCancelled(true);
               arg1.setLines(new ArrayList());
            } else if ((Boolean)this.replaceOwnNametagColor.get()) {
               if (Ref.method7() == null
                  || !(arg1.method2() instanceof EntityPlayerBridge bridgeextension2222)
                  || !bridgeextension2222.bridge$getUniqueID().equals(Ref.method7().bridge$getUniqueID())) {
                  return;
               }

               Component component4 = arg1.method3();
               if (component4 == null) {
                  return;
               }

               arg1.method1(component4.replaceText(this.ownNameReplacer));
            }
         },
         Integer.MAX_VALUE
      );
      if (Ref.MC_VERSION >= 16) {
         this.method2(EventRenderEntityLabelLines.class, arg1 -> {
            EntityPlayerBridge bridgeextension2222 = arg1.method2();
            if (bridgeextension2222 != null) {
               if (!this.method3(bridgeextension2222)) {
                  arg1.setCancelled(true);
               } else {
                  if (bridgeextension2222 == Ref.method7()) {
                     String text3 = bridgeextension2222.bridge$getName();

                     for (int index4 = 0; index4 < arg1.method3(); index4++) {
                        if (arg1.method4(index4).contains(text3)) {
                           arg1.method3(index4, arg1.method2(index4).replaceText(this.ownNameReplacer));
                        }
                     }
                  }
               }
            }
         }, Integer.MAX_VALUE);
      }

      this.method3(com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect.class, () -> this.ownNamePattern = null);
   }

   public String getId() {
      return "NAMETAG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new ClientOption[]{this.nametagShadow, this.nametag, this.toggleNametags, this.toggleNametagsKeybind, this.togglePlayerNametagsKeybind, this.hideNametagsInF1, this.nametagBackgroundOpacity}
      );
      ((SettingsSectionImpl)((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.hideNametagF5Passengers}))
            .method6(16))
         .method2(() -> {
            Bridge5Extension_5 bridge5extension_50 = Ref.method7();
            if (bridge5extension_50 == null) {
               return true;
            }

            List list1x = Ref.method7().bridge$getPassengers();

            for (int index2 = 0; index2 < list1x.size(); index2++) {
               if (((BridgeExtension)list1x.get(index2)).bridge$isTextDisplay()) {
                  return false;
               }
            }

            return true;
         });
      lightingextension231.method7(this.replaceOwnNametagColor, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.ownNametagColor}));
      this.toggleNametagsKeybind.method3(() -> {
         if (Ref.method8() != null) {
            this.allNametagsHidden = !this.allNametagsHidden;
            if ((Boolean)this.toggleNametags.get()) {
               Ref.method4().method69().method3("All nametags are " + (!this.allNametagsHidden ? "now" : "no longer") + " enabled.");
            }
         }
      });
      this.togglePlayerNametagsKeybind.method3(() -> {
         if (Ref.method8() != null) {
            this.playerNametagsHidden = !this.playerNametagsHidden;
            if ((Boolean)this.toggleNametags.get()) {
               Ref.method4().method69().method3("Player nametags are " + (!this.playerNametagsHidden ? "now" : "no longer") + " enabled.");
            }
         }
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field4}).method11(this);
   }

   public boolean method3(EntityLivingStateBridge bridgeextension2_21) {
      if (!this.isEnabled()) {
         return true;
      } else if ((Boolean)this.hideNametagsInF1.get() && Ref.method3().bridge$getGameSettings().bridge$isHideGui()) {
         return false;
      } else {
         return this.allNametagsHidden ? false : !this.playerNametagsHidden || !(bridgeextension2_21 instanceof EntityPlayerBridge);
      }
   }

   public boolean shouldRenderPlayerNametag(BridgeExtension bridgeextension1) {
      if ((!this.isEnabled() || !(Boolean)this.nametag.get())
         && !Ref.method4().method40().method85().method17(arg0 -> arg0.method45().method19() || !arg0.method45().method15().isFixedToPlayer())) {
         return false;
      }

      if (bridgeextension1.bridge$isInvisible()) {
         return false;
      }

      if (bridgeextension1 != Ref.method3().bridge$getPlayer()) {
         return false;
      }

      List list2 = bridgeextension1.bridge$getPassengers();
      return list2 != null && !list2.isEmpty() ? false : !Ref.method3().bridge$getPlayer().OIHOORHOCRCOCIORRHOROOOORROIIH();
   }

   public float getBackgroundOpacity() {
      if (!this.isEnabled()) {
         return Bridge.getMinecraftVersion().method19() ? 1.0F : 0.25F;
      } else {
         return (Float)this.nametagBackgroundOpacity.get() * (Bridge.getMinecraftVersion().method19() ? 1.0F : 0.25F);
      }
   }

   @Generated
   public FloatOption getNametagBackgroundOpacity() {
      return this.nametagBackgroundOpacity;
   }

   @Generated
   public ModifierKeybindOption getToggleNametagsKeybind() {
      return this.toggleNametagsKeybind;
   }

   @Generated
   public ModifierKeybindOption getTogglePlayerNametagsKeybind() {
      return this.togglePlayerNametagsKeybind;
   }

   @Generated
   public ToggleOption getNametag() {
      return this.nametag;
   }

   @Generated
   public ToggleOption getNametagShadow() {
      return this.nametagShadow;
   }

   @Generated
   public ToggleOption getToggleNametags() {
      return this.toggleNametags;
   }

   @Generated
   public ToggleOption getHideNametagsInF1() {
      return this.hideNametagsInF1;
   }

   @Generated
   public ToggleOption getReplaceOwnNametagColor() {
      return this.replaceOwnNametagColor;
   }

   @Generated
   public ColorOption getOwnNametagColor() {
      return this.ownNametagColor;
   }

   @Generated
   public boolean isAllNametagsHidden() {
      return this.allNametagsHidden;
   }

   @Generated
   public boolean isPlayerNametagsHidden() {
      return this.playerNametagsHidden;
   }

   @Generated
   public Pattern getOwnNamePattern() {
      return this.ownNamePattern;
   }

   @Generated
   public Consumer<Builder> getOwnNameReplacer() {
      return this.ownNameReplacer;
   }

   @Generated
   public ToggleOption getHideNametagF5Passengers() {
      return this.hideNametagF5Passengers;
   }
}
