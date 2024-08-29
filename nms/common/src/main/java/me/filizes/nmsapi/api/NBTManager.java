package me.filizes.nmsapi.api;

import org.bukkit.inventory.ItemStack;

public interface NBTManager {

    public ItemStack setNbtTag(ItemStack itemStack, String key, int value);
    public ItemStack setNbtTag(ItemStack itemStack, String key, String value);
    public ItemStack setNbtTag(ItemStack itemStack, String key, double value);
    public ItemStack setNbtTag(ItemStack itemStack, String key, long value);
    public boolean containsNbtTag(ItemStack itemStack, String key);
    public int getIntNbtTag(ItemStack itemStack, String key);
    public String getStringNbtTag(ItemStack itemStack, String key);
    public double getDoubleNbtTag(ItemStack itemStack, String key);
    public long getLongNbtTag(ItemStack itemStack, String key);

}
