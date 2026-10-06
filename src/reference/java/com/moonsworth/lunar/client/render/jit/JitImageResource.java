package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.client.inactive.Inactive3_2;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class JitImageResource extends JitResourceBase<Gui2Handler> {
   public JitImageResource(JitAssetKey var1) {
      super(var1, JitResource.Data6.method1());
   }

   protected Gui2Handler method7() {
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      IResourceBridge var2 = var1.bridge$getResource(this.field3.method3());
      if (var2 == null) {
         throw new RuntimeException("Unable to find resource file: " + this.field3);
      }

      String var3 = IOUtils.toString(var2.bridge$getInputStream(), StandardCharsets.UTF_8);
      Gui2Handler var4 = Inactive3_2.method1(var3);
      var4.method20(this.field3.method3());
      return var4;
   }
}
