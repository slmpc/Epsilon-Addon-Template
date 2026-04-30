package com.example.epsilonaddon.template.fabric;

import com.example.epsilonaddon.template.EpsilonAddonTemplate;
import com.github.epsilon.addon.EpsilonAddonSetupEvent;
import com.github.epsilon.fabric.addon.FabricEpsilonAddonEntrypoint;
import net.fabricmc.loader.api.FabricLoader;

public final class EpsilonAddonTemplateFabricEntrypoint implements FabricEpsilonAddonEntrypoint {

    @Override
    public void registerAddon(EpsilonAddonSetupEvent event) {
        FabricLoader.getInstance().getModContainer(EpsilonAddonTemplate.MOD_ID)
                .ifPresent(container -> EpsilonAddonTemplate.setVersion(container.getMetadata().getVersion().getFriendlyString()));

        event.registerAddon(EpsilonAddonTemplate.INSTANCE);
    }
}

