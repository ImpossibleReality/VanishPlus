package codes.wickedtree.vanish.mixin;

import codes.wickedtree.vanish.mixinterface.EntityIDProvider;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityS2CPacket.class)
public class EntityS2CPacketMixin implements EntityIDProvider {
    @Shadow
    protected int id;

    @Override
    public int getIdOnServer() {
        return id;
    }
}
