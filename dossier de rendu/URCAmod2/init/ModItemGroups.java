public static class ModItemGroup extends ItemGroup {

    private final Supplier<ItemStack> iconSupplier;

    public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
        super(name);
        this.iconSupplier = iconSupplier;
    }

    @Override
    public ItemStack createIcon() {
        return iconSupplier.get();
    }

    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(Urcamod2.MODID, () -> new ItemStack(ModItems.EXAMPLE_ITEM));

    @ObjectHolder(Urcamod2.MODID)//forge va regarder tout les champs de la classe et allouer la valeur
    public class ModItems {
        public static final Item EXAMPLE_ITEM = null;
    }
    
}
