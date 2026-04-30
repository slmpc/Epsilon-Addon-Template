package com.example.epsilonaddon.template;

import com.github.epsilon.addon.EpsilonAddon;
import com.github.epsilon.settings.impl.BoolSetting;
import com.github.epsilon.settings.impl.StringSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Minimal shared addon example used by both Fabric and NeoForge.
 */
public final class EpsilonAddonTemplate extends EpsilonAddon {

    public static final String MOD_ID = "epsilon_addon_template";
    public static final String MOD_NAME = "Epsilon Addon Template";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final EpsilonAddonTemplate INSTANCE = new EpsilonAddonTemplate();

    private static String version = "dev";

    private final BoolSetting exampleToggle = boolSetting("Example Toggle", true);
    private final StringSetting greeting = stringSetting("Greeting", "Hello from the template!");

    private EpsilonAddonTemplate() {
        super(MOD_ID);
    }

    public static void setVersion(String value) {
        if (value != null && !value.isBlank()) {
            version = value;
        }
    }

    @Override
    public String getDisplayName() {
        return MOD_NAME;
    }

    @Override
    public String getDescription() {
        return "A minimal multiloader addon template for OpenEpsilon.";
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public List<String> getAuthors() {
        return List.of("Your Name");
    }

    @Override
    public void onSetup() {
        LOGGER.info(
                "Loaded {} v{} (exampleToggle={}, greeting=\"{}\"). Replace this class with your own addon logic.",
                getDisplayName(),
                getVersion(),
                exampleToggle.getValue(),
                greeting.getValue()
        );

        // Example:
        // registerModule(YourModule.INSTANCE);
    }
}

