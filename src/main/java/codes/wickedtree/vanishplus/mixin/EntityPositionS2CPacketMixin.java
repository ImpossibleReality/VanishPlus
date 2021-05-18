package codes.wickedtree.vanishplus.mixin;

import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codes.wickedtree.vanishplus.mixinterface.EntityIDProvider;

@Mixin(EntityPositionS2CPacket.class)
public class EntityPositionS2CPacketMixin implements EntityIDProvider {
    @Shadow
    private int id;

    @Override
    public int getIdOnServer() {
        return id;
    }
}
