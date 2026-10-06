package com.moonsworth.lunar.network;

import com.moonsworth.lunar.network.mixin.ApiClient;
import com.moonsworth.lunar.network.mixin.ApiCallback;
import com.moonsworth.lunar.network.mixin.ApiResponse;
import com.moonsworth.lunar.network.mixin.Configuration;
import com.moonsworth.lunar.network.mixin.ApiException;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.Call;

public class GameEventApi {
   private ApiClient field1;
   private int field2;
   private String field3;

   public GameEventApi() {
      this(Configuration.method1());
   }

   public GameEventApi(ApiClient mixinhelper1) {
      this.field1 = mixinhelper1;
   }

   public ApiClient method1() {
      return this.field1;
   }

   public void method2(ApiClient mixinhelper1) {
      this.field1 = mixinhelper1;
   }

   public int method3() {
      return this.field2;
   }

   public void method4(int value) {
      this.field2 = value;
   }

   public String method5() {
      return this.field3;
   }

   public void method6(String text) {
      this.field3 = text;
   }

   public Call method7(GameEventBatchPostRequest mixinhelper111, ApiCallback mixinhelper32) {
      String text3 = null;
      String[] items4 = new String[0];
      if (this.field3 != null) {
         text3 = this.field3;
      } else if (items4.length > 0) {
         text3 = items4[this.field2];
      } else {
         text3 = null;
      }

      GameEventBatchPostRequest mixinhelper115 = mixinhelper111;
      String text6 = "/game/event/batch";
      ArrayList list7 = new ArrayList();
      ArrayList list8 = new ArrayList();
      HashMap map9 = new HashMap();
      HashMap map10 = new HashMap();
      HashMap map11 = new HashMap();
      String[] items12 = new String[0];
      String text13 = this.field1.method52(items12);
      if (text13 != null) {
         map9.put("Accept", text13);
      }

      String[] items14 = new String[]{"application/json"};
      String text15 = this.field1.method53(items14);
      if (text15 != null) {
         map9.put("Content-Type", text15);
      }

      String[] items16 = new String[0];
      return this.field1.method63(text3, text6, "POST", list7, list8, mixinhelper115, map9, map10, map11, items16, mixinhelper32);
   }

   private Call method8(GameEventBatchPostRequest mixinhelper111, ApiCallback mixinhelper32) {
      if (mixinhelper111 == null) {
         throw new ApiException("Missing the required parameter 'gameEventBatchPostRequest' when calling gameEventBatchPost(Async)");
      } else {
         return this.method7(mixinhelper111, mixinhelper32);
      }
   }

   public void method9(GameEventBatchPostRequest mixinhelper111) {
      this.method10(mixinhelper111);
   }

   public ApiResponse<Void> method10(GameEventBatchPostRequest mixinhelper111) {
      Call call2 = this.method8(mixinhelper111, null);
      return this.field1.method58(call2);
   }

   public Call method11(GameEventBatchPostRequest mixinhelper111, ApiCallback<Void> mixinhelper32) {
      Call call3 = this.method8(mixinhelper111, mixinhelper32);
      this.field1.method60(call3, mixinhelper32);
      return call3;
   }
}
