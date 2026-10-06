package com.moonsworth.lunar.client.network.websocket;

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
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.debug.DebugType;
import com.moonsworth.lunar.client.framework.PacketUtil;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import lombok.Generated;

public final class WebSocketRpcChannel implements RpcChannel, Consumer<WebSocketRpcResponse> {
   private static final RemovalListener<ByteString, WebSocketRpcChannel.Data> field1 = arg0 -> {
      if (arg0.getCause() != RemovalCause.EXPLICIT) {
         LunarLogger.method6(
            "Assets", "Timeout waiting for response to " + ((WebSocketRpcChannel.Data)arg0.getValue()).field1 + " caused by " + arg0.getCause(), new Object[0]
         );
      }
   };
   private final AssetServerClient field2;
   private final AtomicInteger field3;
   private final Cache<ByteString, WebSocketRpcChannel.Data> field4 = CacheBuilder.newBuilder()
      .expireAfterWrite(1L, TimeUnit.MINUTES)
      .removalListener(field1)
      .build();

   public WebSocketRpcChannel(AssetServerClient entityrenderer41) {
      this.field2 = entityrenderer41;
      this.field3 = new AtomicInteger(1);
   }

   public void callMethod(MethodDescriptor methoddescriptor1, RpcController rpccontroller2, Message message3, Message message4, RpcCallback<Message> rpccallback5) {
      if (!this.field2.isOpen()) {
         LunarLogger.method6("Assets", "Tried to make a request without an open socket", new Object[0]);
      } else {
         int number6 = this.field3.getAndIncrement();
         ByteString bytestring7 = ByteString.copyFromUtf8(Integer.toString(number6));
         ServerboundWebSocketMessage serverboundwebsocketmessage8 = ServerboundWebSocketMessage.newBuilder()
            .setRequestId(bytestring7)
            .setService(methoddescriptor1.getService().getFullName())
            .setMethod(methoddescriptor1.getName())
            .setInput(message3.toByteString())
            .build();
         this.field4.put(bytestring7, new WebSocketRpcChannel.Data(serverboundwebsocketmessage8.getService() + "." + serverboundwebsocketmessage8.getMethod(), message4, rpccallback5));
         this.field2.send(serverboundwebsocketmessage8.toByteArray());
         if (Ref.method34(DebugType.ASSET_SERVER)) {
            LunarLogger.method4("Assets", "Request %s for %s.%s", new Object[]{serverboundwebsocketmessage8.getRequestId(), serverboundwebsocketmessage8.getService(), serverboundwebsocketmessage8.getMethod()});
         }
      }
   }

   public void method1(WebSocketRpcResponse websocketrpcresponse1) {
      WebSocketRpcChannel.Data data2 = (WebSocketRpcChannel.Data)this.field4.getIfPresent(websocketrpcresponse1.getRequestId());
      if (data2 == null) {
         LunarLogger.method8("Assets", "Failed to find associated request with ID %s", new Object[]{websocketrpcresponse1.getRequestId()});
      } else {
         this.field4.invalidate(websocketrpcresponse1.getRequestId());

         Message message3;
         try {
            message3 = data2.field2.newBuilderForType().mergeFrom(websocketrpcresponse1.getOutput()).build();
         } catch (InvalidProtocolBufferException invalidprotocolbufferexception5) {
            LunarLogger.method8("Assets", "Failed to decode response", new Object[0]);
            invalidprotocolbufferexception5.printStackTrace();
            return;
         }

         PacketUtil.method2(message3, data2.field3);
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
      public Data(String text1, Message message2, RpcCallback<Message> rpccallback3) {
         this.field1 = text1;
         this.field2 = message2;
         this.field3 = rpccallback3;
      }
   }
}
