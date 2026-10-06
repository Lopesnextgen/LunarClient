package com.moonsworth.lunar.client.mod.misc.soundchanger;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.soundchanger.Soundchanger;
import com.moonsworth.lunar.client.framework.feature.soundchanger.SoundChangerEntry;
import com.moonsworth.lunar.client.framework.feature.soundchanger.Soundchanger.Data;
import com.moonsworth.lunar.client.event.mixin.holograms.EventOptionsReloadBase.EventOptionsReload;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Generated;

public class SoundChanger extends AbstractFeature {
   private boolean loaded = false;
   private JsonObject field8 = new JsonObject();
   private Map<ResourceLocationBridge, SoundChangerEntry> field9;

   public SoundChanger() {
      super(false);
      this.handle(EventOptionsReload.class, arg1 -> ((OptionContainer)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field14)).method2().forEach(arg0 -> {
         if (arg0.get() instanceof Integer number1x) {
            arg0.method11(number1x, true);
         }
      }));
   }

   public String getId() {
      return "SOUND_CHANGER";
   }

   private void method13() {
      this.field9 = new HashMap<>();
      this.mc.bridge$getSoundHandler().bridge$getAllRegisteredSounds().forEach(arg1x -> this.field9.put(arg1x, new SoundChangerEntry(arg1x, 100)));
      Soundchanger soundchanger1 = Bridge.method8().getMinecraftVersion().method20() ? Soundchanger.method1() : Soundchanger.method2();

      for (SoundChangerEntry soundchanger23 : this.field9.values()) {
         if (soundchanger1.method7().containsKey(soundchanger23.getLocation())) {
            soundchanger23.setPrettyName((String)soundchanger1.method7().get(soundchanger23.getLocation()));
         }
      }

      this.method2(soundchanger1.method8().values());
   }

   private void method2(Collection<Data> list1) {
      for (Data data3 : list1) {
         this.field9
            .values()
            .stream()
            .filter(arg1x -> arg1x.method3().length > data3.getIndex() && arg1x.method3()[data3.getIndex()].equals(data3.method1()))
            .forEach(arg1x -> arg1x.method3()[data3.getIndex()] = data3.getPrettyName());
         this.method2(data3.method3().values());
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      this.method13();
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("openSoundFile").method4(() -> BrowserUtils.method10(this.method15()))});
      lightingextension231.method1(this.method14());
   }

   private List<ClientOption<?>> method14() {
      HashMap map1 = new HashMap();

      for (SoundChangerEntry soundchanger23 : this.field9.values()) {
         if (soundchanger23.method3().length != 0) {
            com.moonsworth.lunar.client.config.option.CategoryOption.Data data4 = map1.computeIfAbsent(
               soundchanger23.method3()[0],
               arg0 -> (com.moonsworth.lunar.client.config.option.CategoryOption.Data)new com.moonsworth.lunar.client.config.option.CategoryOption.Data(arg0)
                  .OHICCHCORCORRRHCHRCCIROCCHCCRC()
            );
            IntegerOption lightingextension4225 = this.method5(soundchanger23);
            com.moonsworth.lunar.client.config.option.CategoryOption.Data data6 = data4;
            String text7 = soundchanger23.method3()[0];

            for (int index8 = 1; index8 < soundchanger23.method3().length; index8++) {
               text7 = text7 + "." + soundchanger23.method3()[index8];
               data6 = data6.method1(soundchanger23.method3()[index8], text7, soundchanger23.method3()[index8], new ClientOption[0]);
            }

            data6.method3(new OptionProvider[]{lightingextension4225});
            data6.method11();
         }
      }

      return map1.values().stream().<ClientOption<?>>map(OptionProvider::method1).collect(Collectors.toList());
   }

   private IntegerOption method5(SoundChangerEntry soundchanger21) {
      IntegerOption lightingextension4222 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                        soundchanger21.getLocation().toString()
                     )
                     .ROICHOCCIOCHCIHOIHIHICCORIROCC(soundchanger21.getPrettyName()))
                  .method4(soundchanger21.getVolume()))
               .OCRRICRIORICCCRHIOHORCICIHHICO(0, 100))
            .OHICCHCORCORRRHCHRCCIROCCHCCRC())
         .method31();
      lightingextension4222.CICORRHIOIIOORRRICCORIOIOCIHII(soundchanger21::setVolume);
      return lightingextension4222;
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }

   public File method15() {
      return new File(LunarConstants.field25 + File.separator + Ref.method4().method61().method14().getName(), this.method16());
   }

   private String method16() {
      return "sound_settings.txt";
   }

   public void load(JsonObject json1) {
      super.load(json1);
      File file2 = this.method15();
      if (!file2.exists()) {
         try {
            file2.createNewFile();
            Files.writeString(file2.toPath(), "{}");
         } catch (IOException exception6) {
            exception6.printStackTrace();
            return;
         }
      }

      try {
         String text3 = Files.readString(file2.toPath());
         this.field8 = (JsonObject)LunarConstants.field22.fromJson(text3, JsonObject.class);
         ConfigMigrator.method3(this, this.field8);
         OptionContainer framework54 = (OptionContainer)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field14);
         framework54.method4(arg1x -> {
            try {
               arg1x.load(this.field8);
            } catch (Exception exception3x) {
               exception3x.printStackTrace();
            }

            return false;
         });
      } catch (IOException exception5) {
         CrashReporter.method5(exception5, "Loading SoundMod");
      }

      this.loaded = true;
   }

   public void method1(JsonObject json1) {
      if (this.loaded) {
         OptionContainer framework52 = (OptionContainer)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field14);
         LinkedHashSet set3 = new LinkedHashSet(framework52.method1());
         framework52.method1().clear();
         super.method1(json1);
         framework52.method1().addAll(set3);
         JsonObject json4 = new JsonObject();
         json4.addProperty("version", ConfigMigrator.field2);
         HashSet set5 = new HashSet();
         framework52.method4(arg2x -> {
            set5.add(arg2x.getId());
            arg2x.method1(json4);
            return false;
         });
         this.field8.keySet().forEach(arg3x -> {
            if (!set5.contains(arg3x) && !json4.has(arg3x)) {
               json4.add(arg3x, this.field8.get(arg3x));
            }
         });

         try {
            Files.writeString(this.method15().toPath(), LunarConstants.field23.toJson(json4));
         } catch (IOException exception7) {
            CrashReporter.method5(exception7, "Saving SoundMod");
         }
      }
   }

   @Generated
   public Map<ResourceLocationBridge, SoundChangerEntry> method17() {
      return this.field9;
   }
}
