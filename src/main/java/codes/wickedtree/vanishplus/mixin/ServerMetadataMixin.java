package codes.wickedtree.vanishplus.mixin;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.ServerMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import codes.wickedtree.vanishplus.VanishPlus;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerMetadata.class)
public class ServerMetadataMixin {

    @Shadow
    private ServerMetadata.Players players;

    @Inject(at = @At("HEAD"), method = "getPlayers")
    private void onGetPlayers(CallbackInfoReturnable<ServerMetadata.Players> ci) {
        if (VanishPlus.INSTANCE.isActive()) {

            List<GameProfile> gameProfiles = new ArrayList<>();

            VanishPlus.INSTANCE.getServer().getPlayerManager().getPlayerList().forEach(player -> {
                GameProfile profile = player.getGameProfile();
                if(VanishPlus.INSTANCE.getVanishedPlayers().stream().noneMatch(vanishedPlayer -> vanishedPlayer.getUuid().equals(player.getUuid()))){
                    gameProfiles.add(profile);
                }
            });

            players = new ServerMetadata.Players(players.getPlayerLimit(), VanishPlus.INSTANCE.getFakePlayerCount());
            players.setSample(gameProfiles.toArray(new GameProfile[0]));
        }
    }
}
