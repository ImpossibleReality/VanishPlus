package codes.wickedtree.vanishplus.mixin;

import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codes.wickedtree.vanishplus.mixinterface.EntityIDProvider;

@Mixin(EntityS2CPacket.class)
public class EntityS2CPacketMixin implements EntityIDProvider {
    @Shadow
    protected int id;

    @Override
    public int getIdOnServer() {
        return id;
    }
}
