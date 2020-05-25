package com.github.broussk.URCAmod2;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerData implements IExtendedEntityProperties {

    private static final String identifier = "PlayerData";

    //propriétés
    private final EntityPlayer player;
    private boolean virus;

    //constructeur, register
    public PlayerData(EntityPlayer player) {
        this.player = player;
        this.virus = false;
    }

    public static PlayerData get(EntityPlayer player) {
        return (PlayerData) player.getExtendedProperties(identifier);
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(identifier, new PlayerData(player));
    }

    public boolean isServerSide() {
        return this.player instanceof EntityPlayerMP;
    }

    //charge, sauvegarde
    @Override
    public void saveNBTData(NBTTagCompound nbt) {
        nbt.setBoolean("virus", this.getVirus());
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt) {
        this.setVirus(nbt.getBoolean("virus"));
    }

    @Override
    public void init(Entity entity, World world) {
    }

    //getter setter
    public void setVirus(boolean virus) {
        this.virus = virus;
    }

    public boolean getVirus() {
        return this.virus;
    }
}