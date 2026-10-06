package com.moonsworth.lunar.client.cosmetics.emote;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteModel;
import com.moonsworth.lunar.client.cosmetics.inactive.MolangResourceProvider;
import java.util.Optional;

public class MolangResourceModel extends EmoteModel<EmoteModel> {
   private MolangResourceProvider field8;
   private MolangResourceProvider field9;
   private MolangResourceProvider field10;

   public MolangResourceModel(MolangResourceProvider inactive41, MolangResourceProvider inactive42, Optional<MolangResourceProvider> optional3) {
      this.field8 = inactive41;
      this.field9 = inactive42;
      this.field10 = optional3.orElse(new MolangResourceProvider("lunar:animations/empty_anim.json"));
   }

   public ResourceLocationBridge method1(EmoteModel gui2iterator1, Evaluator evaluator2) {
      return this.field8.method1(evaluator2);
   }

   public ResourceLocationBridge method2(EmoteModel gui2iterator1, Evaluator evaluator2) {
      return this.field9.method1(evaluator2);
   }

   public ResourceLocationBridge method3(EmoteModel gui2iterator1, Evaluator evaluator2) {
      return this.field10.method1(evaluator2);
   }

   public String getModelName() {
      return this.field8.toString();
   }
}
