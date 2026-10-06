package com.moonsworth.lunar.client.replay.export;

import java.util.List;
import lombok.Generated;

public enum VideoCodec implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   H264("h264", List.of(VideoEncoder.H264_VIDEOTOOLBOX, VideoEncoder.H264_NVENC, VideoEncoder.H264_AMF, VideoEncoder.H264_QSV, VideoEncoder.LIBX264)),
   HEVC("hevc", List.of(VideoEncoder.HEVC_VIDEOTOOLBOX, VideoEncoder.HEVC_NVENC, VideoEncoder.HEVC_AMF, VideoEncoder.HEVC_QSV, VideoEncoder.LIBX265)),
   VP9("vp9", List.of(VideoEncoder.VP9_VIDEOTOOLBOX, VideoEncoder.VP9_NVENC, VideoEncoder.VP9_AMF, VideoEncoder.VP9_QSV, VideoEncoder.VP9_LIBVPX)),
   AV1(
      "av1",
      List.of(
         VideoEncoder.AV1_VIDEOTOOLBOX,
         VideoEncoder.AV1_NVENC,
         VideoEncoder.AV1_AMF,
         VideoEncoder.AV1_QSV,
         VideoEncoder.AV1_LIBSVTAV1,
         VideoEncoder.AV1_LIBAOM
      )
   ),
   WEBP("webp", List.of(VideoEncoder.ANIMATED_WEBP));

   private final String id;
   private final List<VideoEncoder> encoders;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public List<VideoEncoder> getEncoders() {
      return this.encoders;
   }

   @Generated
   VideoCodec(String text3, List<VideoEncoder> list4) {
      this.id = text3;
      this.encoders = list4;
   }
}
