package codes.wickedtree.vanishplus;

import net.fabricmc.api.ModInitializer;

public class VanishPlusInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        VanishPlus.INSTANCE.init();
    }
}
