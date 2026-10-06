package com.moonsworth.lunar.client.network.friend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.conversation.v1.ConversationMessage;
import com.lunarclient.websocket.conversation.v1.ConversationReference;
import com.lunarclient.websocket.conversation.v1.ConversationReference.TargetCase;
import com.lunarclient.websocket.conversation.v1.ConversationSender.SenderCase;
import com.lunarclient.websocket.friend.v1.RemoveFriendRequest;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.ItemSetHandler;
import com.moonsworth.lunar.client.driver.core.gui.Annotation;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.mixin.EntityRenderer5;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.client.util.ThreadModuleDump69;
import it.unimi.dsi.fastutil.Pair;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class FriendRequestManager extends ItemSetHandler<Memory> implements Gui, JsonProviderLegacy {
   private final GuiIterator field2 = new GuiIterator();
   private final Map<Memory, List<EntityRenderer5>> field3 = new ConcurrentHashMap<>();
   private final ConcurrentLinkedQueue<Pair<UUID, EntityRenderer5>> field4 = new ConcurrentLinkedQueue<>();

   @Override
   protected Set<Memory> method3() {
      return new HashSet<>();
   }

   public Memory method2(UUID var1) {
      for (Memory var3 : this.method13()) {
         if (var3.method10().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public boolean method3(UUID var1) {
      for (Memory var3 : this.method13()) {
         if (var3.method10().equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public void method4(Memory var1) {
      this.method13().add(var1);
      this.method9();
   }

   public void method5(UUID var1) {
      this.method13().removeIf(var1x -> var1x.method10().equals(var1));
      ThreadModuleDump63.method5()
         .ifPresent(
            var1x -> var1x.method93().removeFriend(null, RemoveFriendRequest.newBuilder().setFriendUuid(ThreadModuleDump66.method3(var1)).build(), var0x -> {})
         );
      this.method9();
   }

   public int method6(UUID var1) {
      AtomicInteger var2 = new AtomicInteger(0);
      Memory var3 = this.method2(var1);
      if (var3 != null && this.field3.containsKey(var3)) {
         int var4 = 0;

         for (EntityRenderer5 var6 : this.field3.get(var3)) {
            if (!var6.method6()) {
               var4++;
            }
         }

         var2.addAndGet(var4);
      }

      return var2.get();
   }

   public void method7(ConversationReference var1, ConversationMessage var2) {
      if (var1.getTargetCase() == TargetCase.FRIEND_UUID) {
         UUID var3 = ThreadModuleDump66.method1(var1.getFriendUuid());
         Memory var4 = this.method2(var3);
         if (var4 != null) {
            if (var2.getSender().getSenderCase() == SenderCase.PLAYER) {
               UUID var5 = ThreadModuleDump66.method1(var2.getSender().getPlayer().getUuid());
               Memory var6;
               if (var5.equals(Client.method109().method31().method10())) {
                  var6 = Client.method109().method31();
               } else {
                  var6 = this.method2(var5);
               }

               if (var6 != null) {
                  EntityRenderer5 var7 = new EntityRenderer5(var6, var2.getContents().getPlainText());
                  this.field3.computeIfAbsent(var4, var0 -> new CopyOnWriteArrayList<>()).add(0, var7);
                  this.field4.add(Pair.of(var3, var7));
                  if (var6 == Client.method109().method31()) {
                     var7.method2();
                  } else {
                     Client.method109().method69().method12(var6, var2.getContents().getPlainText());
                  }

                  this.method9();
               }
            }
         }
      }
   }

   public List<EntityRenderer5> method8(Memory var1) {
      return this.field3.get(var1);
   }

   public void clear() {
      this.method13().clear();
      this.method9();
   }

   public synchronized void method9() {
      this.method14();
   }

   public void method10(AbstractRenderContext var1, float var2, float var3, Memory var4) {
      this.method11(var1, var2, var3, var4.method10(), var4.method5(), 1.0F);
   }

   public void method11(AbstractRenderContext var1, float var2, float var3, UUID var4, int var5, float var6) {
      this.method12(var1, var2, var3, var4, 6, var5, var6);
   }

   public void method12(AbstractRenderContext var1, float var2, float var3, UUID var4, int var5, int var6, float var7) {
      LcuiScreen.method38(var1, ThreadModuleDump69.getHeadTexture(var4), var5, var2 + 1.0F, var3 + 1.0F, ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var7));
      LcuiScreen.method57(var1, var2 + 1.0F, var3 + 1.0F, var5 * 2, var5 * 2, 4.0F, var6);
   }

   public void method13(MixinHelper_4 var1, float var2, float var3, UUID var4, int var5, int var6, float var7) {
      LcuiScreen.method94(var1, var2, var3, var5 * 2 + 2, var5 * 2 + 2, var6);
      LcuiScreen.method39(var1, ThreadModuleDump69.getHeadTexture(var4), var5, var2 + 1.0F, var3 + 1.0F, ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var7));
   }

   private void method14() {
      this.field2.method2("friends", this.method13());
      JsonArray var1 = new JsonArray();
      ThreadModuleDump63.method4().method51().method13().forEach(var2 -> var1.add(this.method15(var2.provide(), true)));
      ThreadModuleDump63.method4().method51().method7().forEach(var2 -> var1.add(this.method15(var2.provide(), false)));
      this.field2.method3("friendRequests", var1);
   }

   private JsonElement method15(JsonElement var1, boolean var2) {
      var1.getAsJsonObject().addProperty("inboundRequest", var2);
      return var1;
   }

   public JsonElement provide() {
      JsonArray var1 = new JsonArray();
      this.field3.forEach((var1x, var2) -> {
         for (EntityRenderer5 var4 : var2) {
            var1.add(var4.provide());
         }
      });
      this.field3.clear();
      return var1;
   }

   @Nullable
   public JsonElement method128() {
      Pair var1 = this.field4.poll();
      if (var1 != null) {
         JsonArray var2 = new JsonArray();
         JsonObject var3 = ((EntityRenderer5)var1.second()).provide().getAsJsonObject();
         var3.addProperty("to", ((UUID)var1.first()).toString());
         var2.add(var3);
         ((EntityRenderer5)var1.second()).method2();
         return var2;
      } else {
         return null;
      }
   }

   @Annotation("friends")
   @Generated
   public GuiIterator method17() {
      return this.field2;
   }
}
