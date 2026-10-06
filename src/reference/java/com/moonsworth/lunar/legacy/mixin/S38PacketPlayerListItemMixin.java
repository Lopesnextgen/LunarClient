package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.PlayerListEntryEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.SPacketPlayerListItem.Action;
import net.minecraft.network.play.server.SPacketPlayerListItem.AddPlayerData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(S38PacketPlayerListItem.class)
public abstract class S38PacketPlayerListItemMixin {
   @Shadow
   public Action action$v1_8;
   @Final
   @Shadow
   public List<AddPlayerData> players;
   @Shadow
   public String field_149126_a$v1_7;

   @Annotation2(min = 1)
   @Inject(method = "readPacketData", at = @At("TAIL"))
   private void lunar$addPlayerData$v1_8(PacketBuffer var1, CallbackInfo var2) {
      ArrayList var3 = Lists.newArrayList();

      for (AddPlayerData var5 : this.players) {
         GameProfile var6 = var5.profile;
         if (this.action$v1_8 == Action.ADD_PLAYER) {
            ThreadModuleDump63.method3()
               .bridge$submit(() -> ClientEventBus.method29().method12(PlayerListEntryEvent.PlayerListAddEvent.class, () -> new PlayerListEntryEvent.PlayerListAddEvent(var5.profile)));
         } else if (this.action$v1_8 == Action.REMOVE_PLAYER) {
            ThreadModuleDump63.method3()
               .bridge$submit(() -> ClientEventBus.method29().method12(PlayerListEntryEvent.PlayerListRemoveEvent.class, () -> new PlayerListEntryEvent.PlayerListRemoveEvent(var5.profile)));
         } else {
            var6 = new GameProfile(var5.profile.getId(), null);
         }

         S38PacketPlayerListItem var7 = (S38PacketPlayerListItem)this;
         AddPlayerData var8;
         if (ThreadModuleDump63.MC_VERSION >= 5) {
            Objects.requireNonNull(var7);
            var8 = new AddPlayerData(var7, var6, var5.ping, var5.gamemode$v1_12, var5.teamNameSPT);
         } else {
            Objects.requireNonNull(var7);
            var8 = new AddPlayerData(var7, var6, var5.ping, var5.gamemode, var5.teamNameSPT);
         }

         var3.add(var8);
      }

      this.players.clear();
      this.players.addAll(var3);
   }

   @Annotation2(max = 0)
   @Inject(method = "readPacketData", at = @At("TAIL"))
   private void impl$addPlayerData$v1_7(PacketBuffer var1, CallbackInfo var2) {
      this.field_149126_a$v1_7 = ThreadModuleDump63.method4().method40().method41().method9(this.field_149126_a$v1_7);
   }
}
