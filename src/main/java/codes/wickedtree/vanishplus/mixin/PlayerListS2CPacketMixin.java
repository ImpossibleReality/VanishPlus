package codes.wickedtree.vanishplus.mixin;

import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codes.wickedtree.vanishplus.mixinterface.IPlayerListS2CPacket;

import java.util.List;

@Mixin(PlayerListS2CPacket.class)
public class PlayerListS2CPacketMixin implements IPlayerListS2CPacket {
    @Shadow
    @Final
    private List<PlayerListS2CPacket.Entry> entries;

    @Shadow private PlayerListS2CPacket.Action action;

    @Override
    public List<PlayerListS2CPacket.Entry> getEntriesOnServer() {
        return entries;
    }

    @Override
    public PlayerListS2CPacket.Action getActionOnServer() {
        return action;
    }
}
