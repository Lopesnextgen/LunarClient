package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump77;
import com.moonsworth.lunar.client.util.colorsaturation.Colorsaturation2;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader;
import mchorse.emoticons.skin_n_bones.api.bobj.BOBJLoader.BOBJData;

public class JitModelResource extends JitResource<Colorsaturation2> {
   public JitModelResource(JitAssetKey var1) {
      super(var1, JitResource.Data6.method1());
   }

   @Override
   protected CompletableFuture<Colorsaturation2> method8() {
      CompletableFuture var1 = CompletableFuture.supplyAsync(ThreadModuleDump77.supplier(this::method4), ThreadModuleDump37.method6());
      CompletableFuture var2 = var1.thenApplyAsync(Colorsaturation2::new, ThreadModuleDump37.method8());
      var2.thenAccept(var1x -> this.method4(var1x.method3()));
      return var2;
   }

   private BOBJData method4() {
      IResourceBridge var1 = ThreadModuleDump63.method3().bridge$getResourceManager().bridge$getResource(this.field3.method3());
      if (var1 == null) {
         throw new RuntimeException("Unable to find resource file: " + this.field3.method3());
      }

      InputStream var2 = var1.bridge$getInputStream();
      return BOBJLoader.readData(var2);
   }

   @Override
   public void cleanUp() {
      Colorsaturation2 var1 = this.value;
      if (var1 != null) {
         var1.free();
         this.value = null;
      }
   }
}
