package com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy;
import com.moonsworth.lunar.client.event.mixin.gui.ServerChangeEvent;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final Pattern field7 = Pattern.compile("^Party Finder > ([a-zA-Z_0-9]+) joined the dungeon group! \\([a-zA-Z]+ Level [0-9]+\\)$");
   private static final Pattern field8 = Pattern.compile("^You have joined (?:\\[(?<rank>[^]]+)] )?(?<name>\\w+)'s? party!$");
   private static final Component field9 = Component.text(
      "-----------------------------------------------------", NamedTextColor.BLUE, new TextDecoration[]{TextDecoration.STRIKETHROUGH}
   );
   private static final Pattern field10 = Pattern.compile("(?i)§.");
   private Rewindhandlers3 field11 = null;
   private boolean field12 = false;
   private int field13 = 0;

   public GuiRewindhandlersHandler2() {
      this.handle(ServerChangeEvent.class, this::method1);
      this.handle(DisconnectEvent.class, this::method2);
      this.method11(EventChatMessageLegacy.Data.class, this::method3, Integer.MAX_VALUE);
   }

   @Override
   public void onEnable() {
      if (!Highlight3Iterator.method8(KeystrokesType.HYPIXEL)) {
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
      return Highlight3Iterator.method8(KeystrokesType.HYPIXEL);
   }

   private void method1(ServerChangeEvent var1) {
      this.onEnable();
   }

   private void method2(DisconnectEvent var1) {
      this.onDisable();
   }

   private void method3(EventChatMessageLegacy.Data var1) {
      if (this.field13 > 0) {
         if (field9.equals(var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI())) {
            this.field13--;
         }

         if (this.field13 != 2) {
            var1.setCancelled(true);
         }
      }

      this.field11 = this.method4(var1);
   }

   private Rewindhandlers3 method4(EventChatMessageLegacy.Data var1) {
      String var2 = LegacyComponentSerializer.legacySection().serialize(var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI());
      String var3 = AdventureChatFormatting.getTextWithoutFormattingCodes(var1.CCCCRIIIHIIRHOCIIOICICHHROHHRC());
      if (!var2.startsWith("§cYou are not currently in a party")
         && !var2.startsWith("§eYou left the party.")
         && !var2.startsWith("§You have been kicked from the party")
         && !var2.startsWith("§cThe party was disbanded")
         && !var3.equals("The party was disbanded because all invites expired and the party was empty")
         && !var2.endsWith("§ehas disbanded the party!")) {
         Rewindhandlers3 var4 = this.field11 == null ? new Rewindhandlers3() : this.field11;
         if (var3.startsWith("Party Leader: ")) {
            String[] var5 = var3.replace("Party Leader: ", "").split(" ");
            String var6 = null;

            for (String var10 : var5) {
               if (!var10.equals("●") && !var10.startsWith("[")) {
                  var6 = var10;
               }
            }

            if (var6 != null) {
               var4.field1.add(var6);
               var4.field2 = var6;
            }
         }

         if (var3.startsWith("Party Leader: ") || var3.startsWith("Party Members: ") || var3.startsWith("Party Moderators: ")) {
            String[] var11 = var3.replace("Party Leader: ", "").replace("Party Members: ", "").replace("Party Moderators: ", "").split(" ");

            for (String var31 : var11) {
               var31 = var31.replace(",", "");
               if (!var31.equals("●") && !var31.startsWith("[")) {
                  var4.field1.add(var31);
               }
            }
         }

         if (var3.startsWith("The party was transferred to ")) {
            String[] var12 = var3.replace("The party was transferred to ", "").split(" by ");
            String[] var15 = var12[0].split(" ");
            var4.field2 = var15[var15.length - 1];
         }

         Matcher var13 = field8.matcher(var3);
         if (var13.matches()) {
            String var19 = var13.group("name");
            var4.field2 = var19;
            var4.field1.clear();
            var4.field1.add(ThreadModuleDump63.method7().bridge$getName());
            var4.field1.add(var19);
            return var4;
         }

         if (var2.startsWith("§eYou'll be partying with:")) {
            String[] var18 = var3.replace("You'll be partying with: ", "").split(" ");

            for (String var34 : var18) {
               var34 = var34.replace(",", "");
               if (!var34.equals("●") && !var34.startsWith("[")) {
                  var4.field1.add(var34);
               }
            }

            return var4;
         } else {
            String var16 = null;
            boolean var21 = false;
            if (var2.startsWith("Party Finder §r§f> ") && var2.contains("§r§ejoined the ")) {
               String[] var24 = var2.split(" ");
               var16 = method5(var24[3]);
               var21 = true;
            }

            if (var2.endsWith(" §ejoined the party.")) {
               String[] var25 = var2.split(" §ejoined the party.");
               var16 = method5(var25[0]);
               var21 = true;
            }

            if (var2.endsWith(" §ehas left the party.")) {
               String[] var26 = var2.split(" §ehas left the party.");
               var16 = method5(var26[0]);
               var4.field1.remove(var16);
               var21 = false;
            }

            if (var2.endsWith(" §ehas been removed from the party.")) {
               String[] var27 = var2.split(" §ehas been removed from the party.");
               var16 = method5(var27[0]);
               var4.field1.remove(var16);
               var21 = false;
            }

            if (var2.endsWith(" §ewas removed from your party because they disconnected")) {
               String[] var28 = var2.split(" §ewas removed from your party because they disconnected");
               var16 = method5(var28[0]);
               var4.field1.remove(var16);
               var21 = false;
            }

            if (var16 == null) {
               Matcher var29 = field7.matcher(var3);
               if (var29.matches()) {
                  var16 = var29.group(1);
                  var21 = true;
                  if (var16.equalsIgnoreCase(ThreadModuleDump63.method7().bridge$getName())) {
                     this.method6();
                     return var4;
                  }
               }
            }

            if (var16 != null) {
               var16 = var16.trim();
               if (var16.contains("] ")) {
                  var16 = var16.split(" ")[1];
               }

               if (var4.field2 == null) {
                  if (!var21) {
                     return var4;
                  }

                  var4.field2 = ThreadModuleDump63.method7().bridge$getName();
                  var4.field1.clear();
                  var4.field1.add(ThreadModuleDump63.method7().bridge$getName());
               }

               if (var21) {
                  var4.field1.add(var16);
               } else {
                  var4.field1.remove(var16);
               }
            }

            return var4;
         }
      } else {
         if (this.field11 != null) {
            this.field11.field2 = null;
            this.field11.field1.clear();
         }

         return null;
      }
   }

   private static String method5(String var0) {
      return var0 == null ? null : field10.matcher(var0).replaceAll("");
   }

   private void method6() {
      ThreadModuleDump37.method5().schedule(() -> ThreadModuleDump37.method18(() -> {
         if (this.field11 != null) {
            this.field11.field1.clear();
         }

         this.field13 = 2;
         ThreadModuleDump63.method7().bridge$sendChatMessage("/party list");
      }), 500L, TimeUnit.MILLISECONDS);
   }

   public Optional<Rewindhandlers3> method7() {
      return !Highlight3Iterator.method8(KeystrokesType.HYPIXEL) ? Optional.empty() : Optional.ofNullable(this.field11);
   }
}
