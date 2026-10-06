package com.moonsworth.lunar.client.mod.combat.killsounds;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.widget.ScreenLifecycle;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.killsounds.KillSoundSettings;
import com.moonsworth.lunar.client.framework.feature.killsounds.KillSoundType;
import com.moonsworth.lunar.client.event.combat.EventAttack;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileSpawn;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileHit;
import com.moonsworth.lunar.client.event.combat.EventProjectileBase.EventProjectileRemove;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldLoad;
import com.moonsworth.lunar.client.event.mixin.gui.EventTitle;
import com.moonsworth.lunar.client.config.profile.ModProfile;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.audio.LunarSoundPlayer;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.DirectoryWatcher;
import com.moonsworth.lunar.client.util.io.FileExtensionFilter;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public class KillSounds extends AbstractFeature implements ScreenLifecycle {
   private final Map<KillSoundType, KillSoundSettings> field8 = new EnumMap<>(KillSoundType.class);
   private final List<Consumer<List<String>>> field9 = new ArrayList<>();
   @Nullable
   private DirectoryWatcher field10;

   public KillSounds() {
      super(false);
      com.moonsworth.lunar.client.framework.feature.killsounds.KillSoundTracker killsounds1 = new com.moonsworth.lunar.client.framework.feature.killsounds.KillSoundTracker(
         this
      );
      this.handle(EventWorldLoad.class, killsounds1::method1);
      this.handle(EventProjectileSpawn.class, killsounds1::method2);
      this.handle(EventProjectileRemove.class, killsounds1::method4);
      this.handle(EventProjectileHit.class, killsounds1::method3);
      this.handle(TypedChatMessage.class, killsounds1::method6);
      this.handle(EventAttack.class, killsounds1::method5);
      this.handle(EventTitle.class, killsounds1::method7);
   }

   @ConstantName
   public String getId() {
      return "KILL_SOUNDS";
   }

   protected List<Framework7Extension> method9() {
      Builder builder1 = ImmutableList.builder();

      for (KillSoundType killsoundstype5 : KillSoundType.values()) {
         KillSoundSettings killsounds6 = new KillSoundSettings(killsoundstype5);
         this.field8.put(killsoundstype5, killsounds6);
         KillSoundEntry killsoundchildmod7 = KillSoundEntry.method3(this, killsounds6);
         builder1.add(killsoundchildmod7);
         this.field9.add(killsoundchildmod7::method2);
      }

      return builder1.build();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(
         new OptionProvider[]{OptionFactory.method14("openSoundFolder").method5(120.0F).method4(() -> Bridge.method8().method64(this.method14().toURI()))}
      );
   }

   public void load(JsonObject json1) {
      super.load(json1);
      this.method13();
   }

   public void method19() {
      if (this.field10 != null) {
         this.field10.close();
      }

      this.field10 = new DirectoryWatcher(this.method14().toPath(), arg1 -> this.method13());
      this.method13();
   }

   public void onClose() {
      if (this.field10 != null) {
         this.field10.close();
         this.field10 = null;
      }
   }

   private void method13() {
      if (BackgroundExecutor.method18(this::method13)) {
         List list1 = LunarSoundPlayer.method15(this.method14(), FileExtensionFilter.method1(new String[]{"mp3", "wav"}));
         this.field9.forEach(arg1x -> arg1x.accept(list1));
      }
   }

   boolean method5(KillSoundType killsoundstype1, boolean flag2) {
      KillSoundSettings killsounds3 = this.field8.get(killsoundstype1);
      boolean flag4 = killsounds3.isEnabled() || flag2;
      if (flag4 && killsounds3.method2() && killsounds3.getVolume() > 0) {
         try {
            Path path5 = this.method14().toPath().resolve(killsounds3.getFileName());
            float value6 = killsounds3.getVolume() / 100.0F;
            Ref.method3().bridge$getSoundHandler().bridge$playMp3FromURL(path5.toUri().toString(), value6, false, false);
            return true;
         } catch (Exception exception7) {
            LunarLogger.method7("Could not play kill sound!", new Object[]{exception7.getMessage()});
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean method6(KillSoundType killsoundstype1) {
      return this.method5(killsoundstype1, false);
   }

   public File method14() {
      ModProfile horsestats1 = Client.method109().method61().method14();
      File file2 = horsestats1.getFile();
      File file3 = new File(file2, "killsounds");
      if (!file3.exists() && !file3.mkdir()) {
         LunarLogger.method5("Could not create directory for kill sound folder!", new Object[0]);
      }

      return file3;
   }
}
