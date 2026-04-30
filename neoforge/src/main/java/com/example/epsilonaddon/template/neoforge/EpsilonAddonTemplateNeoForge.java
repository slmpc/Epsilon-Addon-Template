package com.example.epsilonaddon.template.neoforge;

import com.example.epsilonaddon.template.EpsilonAddonTemplate;
import com.github.epsilon.neoforge.addon.EpsilonAddonSetupEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = EpsilonAddonTemplate.MOD_ID, dist = Dist.CLIENT)
public final class EpsilonAddonTemplateNeoForge {

    public EpsilonAddonTemplateNeoForge(ModContainer container) {
        EpsilonAddonTemplate.setVersion(container.getModInfo().getVersion().toString());
        NeoForge.EVENT_BUS.addListener(this::onAddonSetup);
        EpsilonAddonTemplate.LOGGER.info("Initialized NeoForge addon bootstrap for {}.", EpsilonAddonTemplate.MOD_NAME);
    }

    private void onAddonSetup(EpsilonAddonSetupEvent event) {
        event.registerAddon(EpsilonAddonTemplate.INSTANCE);
    }
}


