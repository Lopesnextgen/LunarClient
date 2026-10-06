package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SlayerQuestEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventScoreboardUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.TextComponent;

public class SlayerQuestListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final com.moonsworth.lunar.client.framework.listener.ScoreboardListener field7 = (com.moonsworth.lunar.client.framework.listener.ScoreboardListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.ScoreboardListener.class
   );
   private final com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener field8 = (com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener)this.method3(
      com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener.class
   );
   private final Pattern field9 = Pattern.compile("Spawned by: (.+)");
   private final Pattern field10 = Pattern.compile(
      "^(?<mobTypes>[^ ]+ )?☠ (?<type>Revenant Horror|Atoned Horror|Tarantula Broodfather|Sven Packmaster|Voidgloom Seraph|Bloodfiend|Inferno Demonlord)"
   );
   private String field11 = "";
   private boolean field12 = false;

   public SlayerQuestListener() {
      this.handle(TypedChatMessage.class, this::method1);
      this.handle(EventScoreboardUpdate.class, this::method2);
      this.handle(EventWorldChange.class, this::method3);
      this.handle(EventEntitySpawn.class, this::method4);
   }

   private void method1(TypedChatMessage data1) {
      if (IslandUtils.isOnIsland() && this.field12) {
         String text2 = TextBridge.getTextContent(data1.OHCICHOROROOORHCRICORHRRCRCCHO()).trim();
         if (text2.equals("SLAYER QUEST COMPLETE!") || text2.equals("NICE! SLAYER BOSS SLAIN!")) {
            LunarEventBus.method29()
               .method12(SlayerQuestEvent.Data.class, () -> new SlayerQuestEvent.Data(this.field11, Ref.method3().bridge$getSystemTime()));
            this.field12 = false;
         } else if (text2.equals("SLAYER QUEST FAILED!") || text2.equals("Your Slayer Quest has been cancelled!")) {
            LunarEventBus.method29()
               .method12(SlayerQuestEvent.SlayerQuestFailedEvent.class, () -> new SlayerQuestEvent.SlayerQuestFailedEvent(this.field11, Ref.method3().bridge$getSystemTime()));
            this.field12 = false;
         }
      }
   }

   private void method2(EventScoreboardUpdate highlightimpl21) {
      if (IslandUtils.isOnIsland()) {
         ImmutableList list2 = this.field7.method6();

         for (int index3 = 0; index3 < list2.size(); index3++) {
            String text4 = (String)list2.get(index3);
            if (text4.contains("Slayer Quest")) {
               if (index3 + 1 >= list2.size()) {
                  return;
               }

               this.field11 = ChatFormatting.getTextWithoutFormattingCodes((String)list2.get(index3 + 1));
            }
         }
      }
   }

   private void method3(EventWorldChange data31) {
      if (this.field12) {
         LunarEventBus.method29()
            .method12(SlayerQuestEvent.SlayerQuestFailedEvent.class, () -> new SlayerQuestEvent.SlayerQuestFailedEvent(this.field11, Ref.method3().bridge$getSystemTime()));
         this.field12 = false;
      }
   }

   private void method4(EventEntitySpawn highlightimpl6_21) {
      if (!this.field12) {
         if (IslandUtils.isOnIsland()) {
            if (highlightimpl6_21.field1.bridge$getCustomName() instanceof TextComponent text2) {
               WorldBridgeExtension itemcounter6extension9 = Ref.method8();
               if (itemcounter6extension9 != null) {
                  List list4 = itemcounter6extension9.bridge$getEntities();
                  String text5 = TextBridge.getTextContent(text2);
                  Matcher matcher6 = this.field9.matcher(text5);
                  if (matcher6.find()) {
                     String text7 = matcher6.group(1);
                     String text8 = this.field8.method6();
                     if (text7.equals(text8) || text7.equals(Ref.method4().method31().getName())) {
                        this.method5(highlightimpl6_21.field1, list4, this.field10);
                        return;
                     }
                  }

                  matcher6 = this.field10.matcher(text5);
                  if (matcher6.find()) {
                     this.method5(highlightimpl6_21.field1, list4, this.field9);
                  }
               }
            }
         }
      }
   }

   private void method5(BridgeExtension bridgeextension1, List<BridgeExtension> list2, Pattern pattern3) {
      Iterator iterator4 = list2.iterator();

      while (true) {
         if (!iterator4.hasNext()) {
            return;
         }

         BridgeExtension bridgeextension5 = (BridgeExtension)iterator4.next();
         if (bridgeextension5.bridge$getCustomName() instanceof TextComponent text6
            && bridgeextension5 instanceof EntityArmorStandBridge
            && !(Math.abs(bridgeextension5.bridge$getPosX() - bridgeextension1.bridge$getPosX()) > 0.1)
            && !(Math.abs(bridgeextension5.bridge$getPosY() - bridgeextension1.bridge$getPosY()) > 0.5)
            && !(Math.abs(bridgeextension5.bridge$getPosZ() - bridgeextension1.bridge$getPosZ()) > 0.1)) {
            String text11 = TextBridge.getTextContent(text6);
            Matcher matcher8 = pattern3.matcher(text11);
            if (matcher8.find()) {
               if (pattern3 != this.field9) {
                  break;
               }

               String text9 = matcher8.group(1);
               String text10 = this.field8.method6();
               if (text9.equals(text10) || text9.equals(Ref.method4().method31().getName())) {
                  break;
               }
            }
         }
      }

      LunarEventBus.method29()
         .method12(SlayerQuestEvent.SlayerBossSpawnEvent.class, () -> new SlayerQuestEvent.SlayerBossSpawnEvent(this.field11, Ref.method3().bridge$getSystemTime()));
      this.field12 = true;
   }
}
