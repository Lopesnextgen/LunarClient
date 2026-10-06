package com.moonsworth.lunar.client.mod.skyblock.dungeonscorealert;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SoundOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SoundOption.Data;
import com.moonsworth.lunar.client.audio.LunarSoundPlayer;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDungeonBloodNotification extends AbstractFeature {
   private static final String field8 = "DUNGEON_KILL_BLOOD";
   private static final String field9 = "DUNGEON_KILL_BLOOD_TIMER";
   private static final long field10 = 2000L;
   private static final String field11 = "[BOSS] The Watcher: Let's see how you can handle this.";
   private final AlertDisplayListener field12 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);
   private HudTimer field13;
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dungeonKillBloodAlert").method4(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dungeonKillBloodAlertTimer")
         .method4(true))
      .method31();
   private final SoundOption field16 = (SoundOption)((Data)OptionFactory.method13("dungeonKillBloodAlertSound")
         .OIRHORRROCHOIRCRHHORHRCIIRHROO("alertSound"))
      .method31();

   public SkyblockDungeonBloodNotification(SkyblockDungeonScoreAlert skyblockdungeonscorealert1) {
      super(true);
      this.method45(ModTraits.field16, ChildModBinding.method4(false, skyblockdungeonscorealert1));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
      this.handle(EventTick.class, this::method3);
      this.handle(EventWorldChange.class, this::method4);
   }

   public void method1(com.moonsworth.lunar.client.config.option.RootSettingsBuilder.Data data1) {
      data1.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, arg1x -> arg1x.method9(new ClientOption[]{this.field15, this.field16}));
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = TextBridge.getTextContent(data1.OHCICHOROROOORHCRICORHRRCRCCHO()).trim();
      if (text2.equals("[BOSS] The Watcher: Let's see how you can handle this.")) {
         if ((Boolean)this.field14.get()) {
            SkyblockDungeonScoreAlert skyblockdungeonscorealert3 = (SkyblockDungeonScoreAlert)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            this.field12
               .method2(
                  ComparableImpl.method2()
                     .method1("DUNGEON_KILL_BLOOD")
                     .method2(Component.text(skyblockdungeonscorealert3.method1("killBlood", new Object[0]), NamedTextColor.RED))
                     .method3(2000L)
                     .method6()
               );
            LunarSoundPlayer.method2(this.field16, skyblockdungeonscorealert3);
         }

         this.field13 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method4().method5(2000L).method7().method2();
      }
   }

   private void method3(EventTick highlightimpl21) {
      if ((Boolean)this.field14.get() && (Boolean)this.field15.get()) {
         if (this.field13 != null) {
            long number2 = this.field13.get();
            if (number2 <= 0L) {
               this.field13 = null;
               this.field12.method3("DUNGEON_KILL_BLOOD_TIMER");
            } else {
               this.field12
                  .method2(
                     ComparableImpl.method2()
                        .method1("DUNGEON_KILL_BLOOD_TIMER")
                        .method2(Component.text(this.field13.method1(), NamedTextColor.RED))
                        .method3(number2)
                        .method4(Type.LOW)
                        .method6()
                  );
            }
         }
      }
   }

   private void method4(EventWorldChange data31) {
      this.field13 = null;
   }

   public String getId() {
      return "SKYBLOCK_DUNGEON_BLOOD_NOTIFICATION";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
