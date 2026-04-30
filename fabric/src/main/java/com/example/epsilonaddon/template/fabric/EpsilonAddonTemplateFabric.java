package com.example.epsilonaddon.template.fabric;

import com.example.epsilonaddon.template.EpsilonAddonTemplate;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class EpsilonAddonTemplateFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricLoader.getInstance().getModContainer(EpsilonAddonTemplate.MOD_ID)
                .ifPresent(container -> EpsilonAddonTemplate.setVersion(container.getMetadata().getVersion().getFriendlyString()));

        EpsilonAddonTemplate.LOGGER.info("Initialized Fabric addon bootstrap for {}.", EpsilonAddonTemplate.MOD_NAME);
    }
}

