package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ChatLineBridge;
import net.minecraft.client.gui.ChatLine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChatLine.class)
public abstract class ChatLineMixin implements ChatLineBridge {
   @Unique
   private int impl$id;
   @Unique
   private boolean impl$isAddedByApollo;

   public ChatLineMixin() {
   }

   public int bridge$getLunarID() {
      return this.impl$id;
   }

   public void bridge$setLunarID(int value) {
      this.impl$id = value;
   }

   public boolean bridge$isAddedByApollo() {
      return this.impl$isAddedByApollo;
   }

   public void bridge$setAddedByApollo(boolean flag) {
      this.impl$isAddedByApollo = flag;
   }
}
