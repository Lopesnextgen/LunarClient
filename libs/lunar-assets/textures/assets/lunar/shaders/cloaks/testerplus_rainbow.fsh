LUNAR-SHADER-DEFINITION-MARKER

// https://github.com/hughsk/glsl-hsv2rgb/blob/master/index.glsl
vec3 hsv2rgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

float remapX(float x) {
    return clamp(10.0/9.5 - 0.1 * abs(x - 11.5), 0.0, 1.0);
}

vec3 _sample(vec2 xy) {
    float hue = mod(cos(TimeSeconds * 0.25) * 0.5 + xy.x + 0.1 * cos(TimeSeconds * 0.25 * 20.0 + xy.y * 20.0), 1.0);

    return hsv2rgb(vec3(hue, 1.0, 1.0));
}

void main() {
    float y = max(uv.y, 1.0/16.0);
    float x = remapX(uv.x * 22.0);

    if (uv.x > 0.5 && uv.y < 1.0/17.0) {
        x = remapX((uv.x - 0.45) * 22.0);
        y = 1.0;
    }

    OUT_COLOR = vec4(_sample(vec2(x, y)), 1.0);
}
