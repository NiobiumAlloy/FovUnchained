package io.github.niobiumalloy.fovunchained.config;

public class Config {
    public static Config INSTANCE = new Config();

    public boolean customFovEnabled = true;
    public int customFov = 130;

    public boolean customSwingSpeedEnabled = true;
    public float swingSpeedMultiplier = 2.0f;
}
