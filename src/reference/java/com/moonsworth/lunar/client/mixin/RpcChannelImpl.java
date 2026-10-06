package com.moonsworth.lunar.client.mixin;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;
import com.google.protobuf.Descriptors.MethodDescriptor;
import com.lunarclient.websocket.protocol.v1.ServerboundWebSocketMessage;
import com.lunarclient.websocket.protocol.v1.WebSocketRpcResponse;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.debug.Gui2Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump13;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import lombok.Generated;

public final class RpcChannelImpl implements RpcChannel, Consumer<WebSocketRpcResponse> {
   private static final RemovalListener<ByteString, RpcChannelImpl.Data> field1 = var0 -> {
      if (var0.getCause() != RemovalCause.EXPLICIT) {
         Slayer.method6("Assets", "Timeout waiting for response to " + ((RpcChannelImpl.Data)var0.getValue()).field1 + " caused by " + var0.getCause());
      }
   };
   private final EntityRenderer4 field2;
   private final AtomicInteger field3;
   private final Cache<ByteString, RpcChannelImpl.Data> field4 = CacheBuilder.newBuilder()
      .expireAfterWrite(1L, TimeUnit.MINUTES)
      .removalListener(field1)
      .build();

   public RpcChannelImpl(EntityRenderer4 var1) {
      this.field2 = var1;
      this.field3 = new AtomicInteger(1);
   }

   public void callMethod(MethodDescriptor var1, RpcController var2, Message var3, Message var4, RpcCallback<Message> var5) {
      if (!this.field2.isOpen()) {
         Slayer.method6("Assets", "Tried to make a request without an open socket");
      } else {
         int var6 = this.field3.getAndIncrement();
         ByteString var7 = ByteString.copyFromUtf8(Integer.toString(var6));
         ServerboundWebSocketMessage var8 = ServerboundWebSocketMessage.newBuilder()
            .setRequestId(var7)
            .setService(var1.getService().getFullName())
            .setMethod(var1.getName())
            .setInput(var3.toByteString())
            .build();
         this.field4.put(var7, new RpcChannelImpl.Data(var8.getService() + "." + var8.getMethod(), var4, var5));
         this.field2.send(var8.toByteArray());
         if (ThreadModuleDump63.method34(Gui2Extension.ASSET_SERVER)) {
            Slayer.method4("Assets", "Request %s for %s.%s", var8.getRequestId(), var8.getService(), var8.getMethod());
         }
      }
   }

   public void method1(WebSocketRpcResponse var1) {
      RpcChannelImpl.Data var2 = (RpcChannelImpl.Data)this.field4.getIfPresent(var1.getRequestId());
      if (var2 == null) {
         Slayer.method8("Assets", "Failed to find associated request with ID %s", var1.getRequestId());
      } else {
         this.field4.invalidate(var1.getRequestId());

         Message var3;
         try {
            var3 = var2.field2.newBuilderForType().mergeFrom(var1.getOutput()).build();
         } catch (InvalidProtocolBufferException var5) {
            Slayer.method8("Assets", "Failed to decode response");
            var5.printStackTrace();
            return;
         }

         ThreadModuleDump13.runCallbackOnMainThread(var3, var2.field3);
      }
   }

   public void method2() {
      this.field4.invalidateAll();
   }

   private static final class Data {
      private String field1;
      private Message field2;
      private RpcCallback<Message> field3;

      @Generated
      public Data(String var1, Message var2, RpcCallback<Message> var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }
}
