package com.moonsworth.lunar.client.network.ipc;

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
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.debug.DebugType;
import com.moonsworth.lunar.client.framework.PacketUtil;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.Generated;

public final class RpcChannelImpl implements RpcChannel, Consumer<IPCRpcResponse> {
   private static final RemovalListener<ByteString, RpcChannelImpl.PendingRequest> field1 = arg0 -> {
      if (arg0.getCause() != RemovalCause.EXPLICIT) {
         LunarLogger.method6(
            "IPC", "Timeout waiting for response to " + ((RpcChannelImpl.PendingRequest)arg0.getValue()).field1 + " caused by " + arg0.getCause(), new Object[0]
         );
      }
   };
   private final WebSocketClientIterator field2;
   private final Cache<ByteString, RpcChannelImpl.PendingRequest> field3 = CacheBuilder.newBuilder()
      .expireAfterWrite(10L, TimeUnit.MINUTES)
      .removalListener(field1)
      .build();

   public RpcChannelImpl(WebSocketClientIterator websocketclientiterator1) {
      this.field2 = websocketclientiterator1;
   }

   public void callMethod(MethodDescriptor methoddescriptor1, RpcController rpccontroller2, Message message3, Message message4, RpcCallback<Message> rpccallback5) {
      if (!this.field2.isOpen()) {
         if (Ref.method34(DebugType.IPC)) {
            LunarLogger.method6("IPC", "Tried to make request to service (" + methoddescriptor1.getService().getFullName() + ") without an open socket", new Object[0]);
         }
      } else {
         ByteString bytestring6 = ByteString.copyFromUtf8(UUID.randomUUID().toString());
         LauncherboundIPCMessage launcherboundipcmessage7 = LauncherboundIPCMessage.newBuilder()
            .setRequestId(bytestring6)
            .setService(methoddescriptor1.getService().getFullName())
            .setMethod(methoddescriptor1.getName())
            .setFullMethod(methoddescriptor1.getFullName())
            .setInputName(methoddescriptor1.getInputType().getName())
            .setInputFullName(methoddescriptor1.getInputType().getFullName())
            .setInput(message3.toByteString())
            .build();
         this.field3.put(bytestring6, new RpcChannelImpl.PendingRequest(launcherboundipcmessage7.getService() + "." + launcherboundipcmessage7.getMethod(), message4, rpccallback5));
         this.field2.send(launcherboundipcmessage7.toByteArray());
         if (Ref.method34(DebugType.IPC)) {
            LunarLogger.method4("IPC", "Request %s for %s.%s", new Object[]{launcherboundipcmessage7.getRequestId(), launcherboundipcmessage7.getService(), launcherboundipcmessage7.getMethod()});
         }
      }
   }

   public void method1(IPCRpcResponse ipcrpcresponse1) {
      RpcChannelImpl.PendingRequest data22 = (RpcChannelImpl.PendingRequest)this.field3.getIfPresent(ipcrpcresponse1.getRequestId());
      if (data22 == null) {
         LunarLogger.method8("IPC", "Failed to find associated request with ID %s", new Object[]{ipcrpcresponse1.getRequestId()});
      } else {
         this.field3.invalidate(ipcrpcresponse1.getRequestId());

         Message message3;
         try {
            message3 = data22.field2.newBuilderForType().mergeFrom(ipcrpcresponse1.getOutput()).build();
         } catch (InvalidProtocolBufferException invalidprotocolbufferexception5) {
            LunarLogger.method8("IPC", "Failed to decode response", new Object[0]);
            invalidprotocolbufferexception5.printStackTrace();
            return;
         }

         PacketUtil.method2(message3, data22.field3);
      }
   }

   public void method2() {
      this.field3.invalidateAll();
   }

   private static final class PendingRequest {
      private String field1;
      private Message field2;
      private RpcCallback<Message> field3;

      @Generated
      public PendingRequest(String text1, Message message2, RpcCallback<Message> rpccallback3) {
         this.field1 = text1;
         this.field2 = message2;
         this.field3 = rpccallback3;
      }
   }
}
