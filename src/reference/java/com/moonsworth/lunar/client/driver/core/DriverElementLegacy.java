package com.moonsworth.lunar.client.driver.core;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.webosr.handler.FileSystem;
import com.moonsworth.webosr.handler.FileSystem.Payload;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class DriverElementLegacy {
   private final HttpServer field1;
   private final FileSystem field2;

   public DriverElementLegacy(FileSystem var1) {
      try {
         this.field1 = HttpServer.create(new InetSocketAddress(8222), 0);
         this.field2 = var1;
         this.field1.createContext("/", new DriverElementLegacy.Data3());
         this.field1.setExecutor(null);
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public void start() {
      this.field1.start();
      Slayer.method4("GameUI Asset", "Started on port " + this.field1.getAddress().getPort());
   }

   class Data3 implements HttpHandler {
      @Override
      public void handle(HttpExchange var1) {
         try {
            String var2 = var1.getRequestURI().getPath();
            if (var2 != null && !var2.isEmpty() && !var2.equals("/")) {
               if (!var2.startsWith("file:")) {
                  var2 = "file://" + var2;
               }

               if (DriverElementLegacy.this.field2.doesResourceExist(var2)) {
                  Payload var3 = DriverElementLegacy.this.field2.onRequest(var2);
                  if (var3.buffer() != null && var3.length() > 0) {
                     var1.getResponseHeaders().set("Content-Type", var3.mimeType());
                     var1.sendResponseHeaders(var3.statusCode(), var3.length());
                     ByteBuffer var4 = var3.buffer();
                     var4.rewind();
                     byte[] var5 = new byte[var4.remaining()];
                     var4.get(var5);

                     try (OutputStream var6 = var1.getResponseBody()) {
                        var6.write(var5);
                     }
                  } else {
                     var1.sendResponseHeaders(404, -1L);
                  }

                  return;
               } else {
                  Slayer.method8("GameUI Asset", "Could not find: " + var2);
                  var1.sendResponseHeaders(404, -1L);
                  return;
               }
            }

            var1.sendResponseHeaders(404, -1L);
         } catch (Exception var16) {
            Slayer.method8("GameUI Asset", "Failed to proxy request: " + var16.getMessage());
            var1.sendResponseHeaders(500, -1L);
            return;
         } finally {
            var1.close();
         }
      }
   }
}
