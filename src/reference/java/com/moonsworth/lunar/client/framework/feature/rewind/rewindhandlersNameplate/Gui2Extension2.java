package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate;

import java.util.List;
import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   H264("h264", List.of(Gui2Extension3.H264_VIDEOTOOLBOX, Gui2Extension3.H264_NVENC, Gui2Extension3.H264_AMF, Gui2Extension3.H264_QSV, Gui2Extension3.LIBX264)),
   HEVC("hevc", List.of(Gui2Extension3.HEVC_VIDEOTOOLBOX, Gui2Extension3.HEVC_NVENC, Gui2Extension3.HEVC_AMF, Gui2Extension3.HEVC_QSV, Gui2Extension3.LIBX265)),
   VP9("vp9", List.of(Gui2Extension3.VP9_VIDEOTOOLBOX, Gui2Extension3.VP9_NVENC, Gui2Extension3.VP9_AMF, Gui2Extension3.VP9_QSV, Gui2Extension3.VP9_LIBVPX)),
   AV1(
      "av1",
      List.of(
         Gui2Extension3.AV1_VIDEOTOOLBOX,
         Gui2Extension3.AV1_NVENC,
         Gui2Extension3.AV1_AMF,
         Gui2Extension3.AV1_QSV,
         Gui2Extension3.AV1_LIBSVTAV1,
         Gui2Extension3.AV1_LIBAOM
      )
   ),
   WEBP("webp", List.of(Gui2Extension3.ANIMATED_WEBP));

   private final String id;
   private final List<Gui2Extension3> encoders;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public List<Gui2Extension3> getEncoders() {
      return this.encoders;
   }

   @Generated
   Gui2Extension2(String text, List<Gui2Extension3> list) {
      this.id = text;
      this.encoders = list;
   }
}
