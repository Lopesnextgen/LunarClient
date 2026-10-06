package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BipedModelBridge;
import com.moonsworth.lunar.client.audio.music.StyngrSong;
import mchorse.emoticons.common.EmoteAPI;
import org.apache.commons.lang3.text.WordUtils;
import com.moonsworth.lunar.client.cosmetics.emote.Emote;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteGift;

public class EmoteImpl extends Emote {
   private final String field8;
   private boolean started = false;
   private boolean field9 = false;

   public EmoteImpl(int var1, EmoteGift var2, String var3, String text, StyngrSong chest) {
      super(var1, var2, WordUtils.capitalizeFully(var3.replace("_", " ")), text, null, chest);
      this.field8 = var3;
   }

   @Override
   public String method2() {
      return this.field8;
   }

   public void method2(Bridge6_10 var1, int var2) {
      if (!this.started) {
         EmoteAPI.setEmoteClient(this.method2(), var1, var2);
         this.started = true;
         this.field9 = false;
      }
   }

   @Override
   public void method3(Bridge6_10 var1) {
      super.method3(var1);
      this.field9 = true;
      this.started = false;
   }

   @Override
   public boolean method3() {
      return this.field9;
   }

   @Override
   public void method1(Bridge5_11 var1, BipedModelBridge var2, float var3) {
   }
}
