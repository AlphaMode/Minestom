package net.minestom.server.entity.metadata.other;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Metadata;
import net.minestom.server.entity.metadata.EntityMeta;
import net.minestom.server.entity.metadata.ObjectDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WitherSkullMeta extends EntityMeta implements ObjectDataProvider {

    private Entity shooter;

    public WitherSkullMeta(@NotNull Entity entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public boolean isInvulnerable() {
        return super.metadata.getIndex((byte) 7, false);
    }

    public void setInvulnerable(boolean value) {
        super.metadata.setIndex((byte) 7, Metadata.Boolean(value));
    }

    @Nullable
    public Entity getShooter() {
        return shooter;
    }

    public void setShooter(@Nullable Entity shooter) {
        this.shooter = shooter;
    }

    @Override
    public int getObjectData() {
        return this.shooter == null ? 0 : this.shooter.getEntityId();
    }

    @Override
    public boolean requiresVelocityPacketAtSpawn() {
        return true;
    }

}
