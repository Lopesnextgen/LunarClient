package com.moonsworth.lunar.network.mixin;

import java.net.URI;
import java.util.List;
import java.util.Map;
import okhttp3.Credentials;

public class MixinHelper103 implements MixinHelper10 {
   private String username;
   private String password;

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String var1) {
      this.username = var1;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }

   @Override
   public void method1(List<MixinHelper8> var1, Map<String, String> map, Map<String, String> map2, String text, String text2, URI uRI) {
      if (this.username != null || this.password != null) {
         map.put("Authorization", Credentials.basic(this.username == null ? "" : this.username, this.password == null ? "" : this.password));
      }
   }
}
