package com.moonsworth.lunar.client.waypoints;

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
import com.lunarclient.gameipc.protocol.v1.IPCRpcResponse;
import com.lunarclient.gameipc.protocol.v1.LauncherboundIPCMessage;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.debug.Gui2Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump13;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.Generated;

public final class RpcChannelImpl implements RpcChannel, Consumer<IPCRpcResponse> {
   private static final RemovalListener<ByteString, RpcChannelImpl.Data2> field1 = var0 -> {
      if (var0.getCause() != RemovalCause.EXPLICIT) {
         Slayer.method6("IPC", "Timeout waiting for response to " + ((RpcChannelImpl.Data2)var0.getValue()).field1 + " caused by " + var0.getCause());
      }
   };
   private final WebSocketClientIterator field2;
   private final Cache<ByteString, RpcChannelImpl.Data2> field3 = CacheBuilder.newBuilder()
      .expireAfterWrite(10L, TimeUnit.MINUTES)
      .removalListener(field1)
      .build();

   public RpcChannelImpl(WebSocketClientIterator var1) {
      this.field2 = var1;
   }

   public void callMethod(MethodDescriptor var1, RpcController var2, Message var3, Message var4, RpcCallback<Message> var5) {
      if (!this.field2.isOpen()) {
         if (ThreadModuleDump63.method34(Gui2Extension.IPC)) {
            Slayer.method6("IPC", "Tried to make request to service (" + var1.getService().getFullName() + ") without an open socket");
         }
      } else {
         ByteString var6 = ByteString.copyFromUtf8(UUID.randomUUID().toString());
         LauncherboundIPCMessage var7 = LauncherboundIPCMessage.newBuilder()
            .setRequestId(var6)
            .setService(var1.getService().getFullName())
            .setMethod(var1.getName())
            .setFullMethod(var1.getFullName())
            .setInputName(var1.getInputType().getName())
            .setInputFullName(var1.getInputType().getFullName())
            .setInput(var3.toByteString())
            .build();
         this.field3.put(var6, new RpcChannelImpl.Data2(var7.getService() + "." + var7.getMethod(), var4, var5));
         this.field2.send(var7.toByteArray());
         if (ThreadModuleDump63.method34(Gui2Extension.IPC)) {
            Slayer.method4("IPC", "Request %s for %s.%s", var7.getRequestId(), var7.getService(), var7.getMethod());
         }
      }
   }

   public void method1(IPCRpcResponse var1) {
      RpcChannelImpl.Data2 var2 = (RpcChannelImpl.Data2)this.field3.getIfPresent(var1.getRequestId());
      if (var2 == null) {
         Slayer.method8("IPC", "Failed to find associated request with ID %s", var1.getRequestId());
      } else {
         this.field3.invalidate(var1.getRequestId());

         Message var3;
         try {
            var3 = var2.field2.newBuilderForType().mergeFrom(var1.getOutput()).build();
         } catch (InvalidProtocolBufferException var5) {
            Slayer.method8("IPC", "Failed to decode response");
            var5.printStackTrace();
            return;
         }

         ThreadModuleDump13.runCallbackOnMainThread(var3, var2.field3);
      }
   }

   public void method2() {
      this.field3.invalidateAll();
   }

   private static final class Data2 {
      private String field1;
      private Message field2;
      private RpcCallback<Message> field3;

      @Generated
      public Data2(String var1, Message var2, RpcCallback<Message> var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }
   }
}
