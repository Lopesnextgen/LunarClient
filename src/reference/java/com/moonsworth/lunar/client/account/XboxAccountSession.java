package com.moonsworth.lunar.client.account;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.account.AuthUtil;
import com.moonsworth.lunar.client.account.AuthUtil.RefreshAccountResult;
import java.time.Instant;
import java.util.function.Consumer;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.account.AccountType;

public class XboxAccountSession extends AccountSession {
   public XboxAccountSession(String var1) {
      super(var1);
      this.method22(AccountType.XBOX);
   }

   @Override
   public boolean method3() {
      return this.getAccessToken() != null && this.method8() != null
         ? Instant.now().isBefore(this.method8())
         : false;
   }

   @Override
   public long method4() {
      JsonObject var1 = AuthUtil.method3(this.getAccessToken());
      return !var1.has("exp") ? 0L : var1.get("exp").getAsInt() * 1000L;
   }

   @Override
   public int method5() {
      JsonObject var1 = AuthUtil.method3(this.getAccessToken());
      return !var1.has("iat") ? 0 : var1.get("iat").getAsInt();
   }

   @Override
   public void method2(Consumer<RefreshAccountResult> var1) {
      AuthUtil.method2(this, var1);
   }
}
