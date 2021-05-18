package codes.wickedtree.vanishplus.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import codes.wickedtree.vanishplus.VanishPlus;
import codes.wickedtree.vanishplus.commands.VanishCommand;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(at = @At("TAIL"), method = "onPlayerConnect")
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        VanishPlus.INSTANCE.onPlayerConnect(player);
        VanishCommand.sendFakePlayerListEntry(player);
    }
}
