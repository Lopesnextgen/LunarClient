package com.moonsworth.lunar.network.mixin;

import java.net.URI;
import java.util.List;
import java.util.Map;
import okhttp3.Credentials;

public class HttpBasicAuth implements Authentication {
   private String username;
   private String password;

   public HttpBasicAuth() {
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String text1) {
      this.username = text1;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String text1) {
      this.password = text1;
   }

   @Override
   public void method1(List<Pair> list, Map<String, String> map, Map<String, String> map2, String text, String text2, URI uri6) {
      if (this.username != null || this.password != null) {
         map.put("Authorization", Credentials.basic(this.username == null ? "" : this.username, this.password == null ? "" : this.password));
      }
   }
}
