package com.moonsworth.lunar.client.mod.misc.replaymod;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplaymodRecordingIndicatorChild;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;

public class ReplayMod extends AbstractFeature {
   public static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "replaymod/gui_lunar.png");
   public static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "replaymod/gui_replaymod_lunar.png");
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("lunarUI").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field11 = (ModifierKeybindOption)((Data)OptionFactory.method18("startStop")
         .method5(KeyCode.KEY_P)
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(this))
      .method31();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)((Data)OptionFactory.method18("pauseResume")
         .method5(KeyCode.KEY_O)
         .OOOHICCHHHRHCORIRCRHOCROROIOCR(this))
      .method31();
   private final ReplaymodRecordingIndicatorChild field13 = new ReplaymodRecordingIndicatorChild(this);

   public ReplayMod() {
      super(false);
      this.method19(ModTraits.field18, arg0 -> arg0.method4(new String[]{"replaymod"}));
      this.method19(ModTraits.field3, this.method19("replaymod.title", "replaymod", "replaymod.title"));
      ((SettingIntercept)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field4))
         .method6(
            arg0 -> ExternalLinkRegistry.method2(RecordingExternalLink.class).isPresent() && ((RecordingExternalLink)ExternalLinkRegistry.method2(RecordingExternalLink.class).get()).method1(), true
         );
   }

   public String getId() {
      return "REPLAYMOD";
   }

   public boolean isEnabled() {
      this.updateEnabled();
      return super.isEnabled();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      ((SettingsSectionImpl)lightingextension231.method9(
            new OptionProvider[]{
               ((ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("indicator").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true)).method31())
                  .HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> ((ModEnabledState)this.field13.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field6)).setEnabled(arg1x)),
               OptionFactory.method7("autoPostProcess").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               OptionFactory.method7("showServerIp").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               OptionFactory.method12("camera").HIIIOHRRROCICIOIORRRIRCRCHHIII("replaymod.camera.classic"),
               OptionFactory.method7("autoSync").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               ((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                           "timelineLength"
                        )
                        .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1800))
                     .OCRRICRIORICCCRHIOHORCICIHHICO(1500, 2100))
                  .method31(),
               OptionFactory.method12("interpolator").HIIIOHRRROCICIOIORRRIRCRCHHIII("replaymod.gui.editkeyframe.interpolator.catmull.name"),
               OptionFactory.method7("askForOpenEye"),
               OptionFactory.method7("skipPostRenderGui"),
               OptionFactory.method7("skipPostScreenshotGui"),
               OptionFactory.method7("pathPreview").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               OptionFactory.method12("fullBrightness").HIIIOHRRROCICIOIORRRIRCRCHHIII("gamma"),
               OptionFactory.method12("mainMenuButton").HIIIOHRRROCICIOIORRRIRCRCHHIII("DEFAULT")
            }
         ))
         .method2(() -> true);
      lightingextension231.method1(
         "general",
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new OptionProvider[]{
               this.field10,
               OptionFactory.method7("notifications").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               OptionFactory.method12("recordingPath").HIIIOHRRROCICIOIORRRIRCRCHHIII("./replay_recordings/"),
               OptionFactory.method12("cachePath").HIIIOHRRROCICIOIORRRIRCRCHHIII("./.replay_cache/"),
               OptionFactory.method12("renderPath").HIIIOHRRROCICIOIORRRIRCRCHHIII("./replay_videos/")
            }
         )
      );
      lightingextension231.method1(
         "recording",
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new OptionProvider[]{
               this.field11,
               this.field12,
               OptionFactory.method7("recordSingleplayer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               OptionFactory.method7("recordServer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               OptionFactory.method7("autoStartRecording"),
               OptionFactory.method7("renameDialog").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true)
            }
         )
      );
      lightingextension231.method1(
         "replay", arg0 -> arg0.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method7("showChat").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true)})
      );
      ExternalLinkRegistry.method2(RecordingExternalLink.class).ifPresent(arg1x -> {
         ArrayList list2 = new ArrayList();

         for (SettingsSectionImpl threadmoduledump43extension224 : lightingextension231.method18()) {
            for (ClientOption lightingextension6 : threadmoduledump43extension224.method1()) {
               if (arg1x.method14().containsKey(lightingextension6.getId())) {
                  arg1x.method14().put(lightingextension6.getId(), lightingextension6);
               }

               list2.add(lightingextension6.getId());
            }
         }

         for (Entry entry8 : arg1x.method14().entrySet()) {
            if (!list2.contains(entry8.getKey()) && !((String)entry8.getKey()).equals("legacyMainMenuButton")) {
               lightingextension231.method9(new ClientOption[]{(ClientOption)entry8.getValue()});
            }
         }
      });
      this.field11.method3(() -> ExternalLinkRegistry.method2(RecordingExternalLink.class).ifPresent(arg0 -> {
         if (!arg0.method1()) {
            if (arg0.isRecording()) {
               arg0.stopRecording();
            } else {
               arg0.startRecording();
            }
         }
      }));
      this.field12.method3(() -> ExternalLinkRegistry.method2(RecordingExternalLink.class).ifPresent(arg0 -> {
         if (!arg0.method1()) {
            if (!arg0.isRecording()) {
               return;
            }

            if (arg0.method3()) {
               arg0.method12();
            } else {
               arg0.method11();
            }
         }
      }));
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field13);
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field6})
         .method3(new String[]{"CrushedPixel", "johni0702"})
         .method11(this);
   }

   public boolean method13() {
      return this.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field13)
         && ExternalLinkRegistry.method2(RecordingExternalLink.class).<Boolean>map(RecordingExternalLink::method1).orElse(false);
   }

   @Generated
   public ToggleOption method14() {
      return this.field10;
   }

   @Generated
   public ModifierKeybindOption method15() {
      return this.field11;
   }

   @Generated
   public ModifierKeybindOption method16() {
      return this.field12;
   }
}
