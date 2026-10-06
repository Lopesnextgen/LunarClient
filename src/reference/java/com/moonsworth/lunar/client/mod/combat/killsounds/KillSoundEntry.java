package com.moonsworth.lunar.client.mod.combat.killsounds;

import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.killsounds.KillSoundSettings;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.DropdownOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.DropdownOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.ArrayList;
import java.util.List;

public class KillSoundEntry extends AbstractFeature {
   private final KillSoundSettings field8;
   private final DropdownOption<String> field9 = (DropdownOption<String>)((Data)OptionFactory.method24("soundFileName", "")
         .method4(new ArrayList())
         .method20(Codec.STRING))
      .method31();

   private KillSoundEntry(KillSounds killsounds1, KillSoundSettings killsounds2) {
      super(false);
      this.field8 = killsounds2;
      this.method3(ModTraits.field16, ChildModBinding.method3(killsounds1));
   }

   @ConstantName
   public String getId() {
      throw new IllegalStateException("KillSoundEntry must be created using KillSoundEntry.create()!");
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      IntegerOption lightingextension4222 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "volume"
               )
               .method4(100))
            .OCRRICRIORICCCRHIOHORCICIHHICO(0, 100))
         .method31();
      ((SettingsSectionImpl)lightingextension231.method9(
            new OptionProvider[]{
               lightingextension4222,
               this.field9,
               OptionFactory.method14("previewSoundButton")
                  .method5(120.0F)
                  .method4(
                     () -> ((KillSounds)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1()).method5(this.field8.method3(), true)
                  )
            }
         ))
         .method2(() -> this.field9.method7().isEmpty());
      lightingextension4222.CICORRHIOIIOORRRICCORIOIOCIHII(this.field8::setVolume);
      this.field9.CICORRHIOIIOORRRICCORIOIOCIHII(this.field8::setFileName);
   }

   void method2(List<String> list1) {
      this.field9.method7().clear();
      this.field9.method7().addAll(list1);
      if (list1.isEmpty()) {
         this.field9.method11("", true);
      } else if (!list1.contains(this.field9.getValue())) {
         this.field9.method11((String)list1.get(0), true);
      }
   }

   public static KillSoundEntry method3(KillSounds killsounds0, KillSoundSettings killsounds1) {
      String text2 = killsounds1.method3().name();
      final String text3 = "KILL_SOUND_" + text2 + "_CHILD";
      KillSoundEntry killsoundchildmod4 = new KillSoundEntry(killsounds0, killsounds1) {
         @ConstantName
         @Override
         public String getId() {
            return text3;
         }
      };
      killsounds1.method1(killsoundchildmod4);
      return killsoundchildmod4;
   }
}
