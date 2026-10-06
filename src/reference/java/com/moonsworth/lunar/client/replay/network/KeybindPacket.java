package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.recording.KeybindAction;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class KeybindPacket extends ReplayPacket {
   private KeybindAction field1;
   private boolean state;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.method9(KeybindAction.class);
      this.state = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method10(this.field1);
      bytebufloader1.writeBoolean(this.state);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      nameplate41.method15().put(this.field1, this.state);
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      return new KeybindPacket(this.field1, !this.state);
   }

   @Generated
   public KeybindPacket() {
   }

   @Generated
   public KeybindPacket(KeybindAction keybindAction, boolean flag) {
      this.field1 = keybindAction;
      this.state = flag;
   }
}
