package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class HypixelPartyTracker extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^Party Finder > ([a-zA-Z_0-9]+) joined the dungeon group! \\([a-zA-Z]+ Level [0-9]+\\)$");
   private static final Pattern field8 = Pattern.compile("^You have joined (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+)'s? party!$");
   private static final Component field9 = Component.text(
      "-----------------------------------------------------", NamedTextColor.BLUE, new TextDecoration[]{TextDecoration.STRIKETHROUGH}
   );
   private static final Pattern field10 = Pattern.compile("(?i)§.");
   private PartyState field11 = null;
   private boolean field12 = false;
   private int field13 = 0;

   public HypixelPartyTracker() {
      this.handle(EventServerChange.class, this::method1);
      this.handle(EventDisconnect.class, this::method2);
      this.method12(EventChatMessage.TypedChatMessage.class, this::method3, Integer.MAX_VALUE);
   }

   @Override
   public void onEnable() {
      if (!ServerBrandWatcher.method8(KeystrokesType.HYPIXEL)) {
         this.field11 = null;
      } else if (!this.field12) {
         this.field12 = true;
         this.field11 = null;
         this.method6();
      }

      this.field13 = 0;
   }

   @Override
   public void onDisable() {
      this.field11 = null;
      this.field12 = false;
      this.field13 = 0;
   }

   @Override
   protected boolean isEnabled() {
      return ServerBrandWatcher.method8(KeystrokesType.HYPIXEL);
   }

   private void method1(EventServerChange highlightimpl101) {
      this.onEnable();
   }

   private void method2(EventDisconnect highlightimpl111) {
      this.onDisable();
   }

   private void method3(EventChatMessage.TypedChatMessage data1) {
      if (this.field13 > 0) {
         if (field9.equals(data1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI())) {
            this.field13--;
         }

         if (this.field13 != 2) {
            data1.setCancelled(true);
         }
      }

      this.field11 = this.method4(data1);
   }

   private PartyState method4(EventChatMessage.TypedChatMessage data1) {
      String text2 = LegacyComponentSerializer.legacySection().serialize(data1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI());
      String text3 = ChatFormatting.getTextWithoutFormattingCodes(data1.CCCCRIIIHIIRHOCIIOICICHHROHHRC());
      if (!text2.startsWith("§cYou are not currently in a party")
         && !text2.startsWith("§eYou left the party.")
         && !text2.startsWith("§You have been kicked from the party")
         && !text2.startsWith("§cThe party was disbanded")
         && !text3.equals("The party was disbanded because all invites expired and the party was empty")
         && !text2.endsWith("§ehas disbanded the party!")) {
         PartyState rewindhandlers34 = this.field11 == null ? new PartyState() : this.field11;
         if (text3.startsWith("Party Leader: ")) {
            String[] items5 = text3.replace("Party Leader: ", "").split(" ");
            String text6 = null;

            for (String text10 : items5) {
               if (!text10.equals("●") && !text10.startsWith("[")) {
                  text6 = text10;
               }
            }

            if (text6 != null) {
               rewindhandlers34.field1.add(text6);
               rewindhandlers34.field2 = text6;
            }
         }

         if (text3.startsWith("Party Leader: ") || text3.startsWith("Party Members: ") || text3.startsWith("Party Moderators: ")) {
            String[] items11 = text3.replace("Party Leader: ", "").replace("Party Members: ", "").replace("Party Moderators: ", "").split(" ");

            for (String text31 : items11) {
               text31 = text31.replace(",", "");
               if (!text31.equals("●") && !text31.startsWith("[")) {
                  rewindhandlers34.field1.add(text31);
               }
            }
         }

         if (text3.startsWith("The party was transferred to ")) {
            String[] items12 = text3.replace("The party was transferred to ", "").split(" by ");
            String[] items15 = items12[0].split(" ");
            rewindhandlers34.field2 = items15[items15.length - 1];
         }

         Matcher matcher13 = field8.matcher(text3);
         if (matcher13.matches()) {
            String text19 = matcher13.group("name");
            rewindhandlers34.field2 = text19;
            rewindhandlers34.field1.clear();
            rewindhandlers34.field1.add(Ref.method7().bridge$getName());
            rewindhandlers34.field1.add(text19);
            return rewindhandlers34;
         }

         if (text2.startsWith("§eYou'll be partying with:")) {
            String[] items18 = text3.replace("You'll be partying with: ", "").split(" ");

            for (String text34 : items18) {
               text34 = text34.replace(",", "");
               if (!text34.equals("●") && !text34.startsWith("[")) {
                  rewindhandlers34.field1.add(text34);
               }
            }

            return rewindhandlers34;
         } else {
            String text16 = null;
            boolean flag21 = false;
            if (text2.startsWith("Party Finder §r§f> ") && text2.contains("§r§ejoined the ")) {
               String[] items24 = text2.split(" ");
               text16 = method5(items24[3]);
               flag21 = true;
            }

            if (text2.endsWith(" §ejoined the party.")) {
               String[] items25 = text2.split(" §ejoined the party.");
               text16 = method5(items25[0]);
               flag21 = true;
            }

            if (text2.endsWith(" §ehas left the party.")) {
               String[] items26 = text2.split(" §ehas left the party.");
               text16 = method5(items26[0]);
               rewindhandlers34.field1.remove(text16);
               flag21 = false;
            }

            if (text2.endsWith(" §ehas been removed from the party.")) {
               String[] items27 = text2.split(" §ehas been removed from the party.");
               text16 = method5(items27[0]);
               rewindhandlers34.field1.remove(text16);
               flag21 = false;
            }

            if (text2.endsWith(" §ewas removed from your party because they disconnected")) {
               String[] items28 = text2.split(" §ewas removed from your party because they disconnected");
               text16 = method5(items28[0]);
               rewindhandlers34.field1.remove(text16);
               flag21 = false;
            }

            if (text16 == null) {
               Matcher matcher29 = field7.matcher(text3);
               if (matcher29.matches()) {
                  text16 = matcher29.group(1);
                  flag21 = true;
                  if (text16.equalsIgnoreCase(Ref.method7().bridge$getName())) {
                     this.method6();
                     return rewindhandlers34;
                  }
               }
            }

            if (text16 != null) {
               text16 = text16.trim();
               if (text16.contains("] ")) {
                  text16 = text16.split(" ")[1];
               }

               if (rewindhandlers34.field2 == null) {
                  if (!flag21) {
                     return rewindhandlers34;
                  }

                  rewindhandlers34.field2 = Ref.method7().bridge$getName();
                  rewindhandlers34.field1.clear();
                  rewindhandlers34.field1.add(Ref.method7().bridge$getName());
               }

               if (flag21) {
                  rewindhandlers34.field1.add(text16);
               } else {
                  rewindhandlers34.field1.remove(text16);
               }
            }

            return rewindhandlers34;
         }
      } else {
         if (this.field11 != null) {
            this.field11.field2 = null;
            this.field11.field1.clear();
         }

         return null;
      }
   }

   private static String method5(String text0) {
      return text0 == null ? null : field10.matcher(text0).replaceAll("");
   }

   private void method6() {
      BackgroundExecutor.method5().schedule(() -> BackgroundExecutor.method18(() -> {
         if (this.field11 != null) {
            this.field11.field1.clear();
         }

         this.field13 = 2;
         Ref.method7().bridge$sendChatMessage("/party list");
      }), 500L, TimeUnit.MILLISECONDS);
   }

   public Optional<PartyState> method7() {
      return !ServerBrandWatcher.method8(KeystrokesType.HYPIXEL) ? Optional.empty() : Optional.ofNullable(this.field11);
   }
}
