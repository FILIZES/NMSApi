package me.filizes.nmsapi.v1_12_R1;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTManager implements me.filizes.nmsapi.api.NBTManager {

    @Override
    public ItemStack setNbtTag(ItemStack itemStack, String key, int value) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag.setInt(key, value);
        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Override
    public ItemStack setNbtTag(ItemStack itemStack, String key, String value) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag.setString(key, value);
        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Override
    public ItemStack setNbtTag(ItemStack itemStack, String key, double value) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag.setDouble(key, value);
        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Override
    public ItemStack setNbtTag(ItemStack itemStack, String key, long value) {
        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();
        tag.setLong(key, value);
        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    @Override
    public boolean containsNbtTag(ItemStack itemStack, String key) {

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();

        return tag.hasKey(key);
    }

    @Override
    public int getIntNbtTag(ItemStack itemStack, String key) {

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();

        return tag.getInt(key);
    }

    @Override
    public String getStringNbtTag(ItemStack itemStack, String key) {

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();

        return tag.getString(key);
    }

    @Override
    public long getLongNbtTag(ItemStack itemStack, String key) {

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();

        return tag.getLong(key);
    }

    @Override
    public double getDoubleNbtTag(ItemStack itemStack, String key) {

        net.minecraft.server.v1_12_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        if (tag == null) tag = new NBTTagCompound();

        return tag.getDouble(key);
    }
}
