package com.moonsworth.lunar.client.account;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;
import java.util.UUID;
import com.moonsworth.lunar.client.account.skin.SkinLoadException;

public class GameProfileLookupCallback implements ProfileLookupCallback {
   private GameProfile field1;
   private SkinLoadException field2;

   public void onProfileLookupSucceeded(String var1, UUID var2) {
      this.onProfileLookupSucceeded(new GameProfile(var2, var1));
   }

   public void onProfileLookupSucceeded(GameProfile var1) {
      this.field1 = var1;
   }

   public void onProfileLookupFailed(String var1, Exception var2) {
      this.method1(var2);
   }

   public void onProfileLookupFailed(GameProfile var1, Exception var2) {
      this.method1(var2);
   }

   private void method1(Exception var1) {
      if (var1 instanceof ProfileNotFoundException) {
         this.field2 = new SkinLoadException("No account with that username exists", var1);
      } else {
         this.field2 = new SkinLoadException("An unknown server error occurred", var1);
      }
   }

   public GameProfile method2() {
      if (this.field1 != null) {
         return this.field1;
      } else {
         throw this.field2;
      }
   }
}
