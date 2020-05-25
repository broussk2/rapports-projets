package com.github.broussk.URCAmod2;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent

@EventBusSubscriber(modid = Urcamod2.MODID, bus = EventBusSubscriber.Bus.MOD) //indique à forge que la classe contient des méthodes qui doivent être subscribed pour gérer les events
public final class ModEventSubscriber {

    @SubscribeEvent //indique à forge que la methode veut subscribe un event
    public static void onRegisterItems(RegistryEvent.Register<Item> event) { //le param indique que la methode doit être appelé quand le mod doit enregistrer l'item
        event.getRegistry().registerAll(
                setup(new Item(new Item.Properties()), "example_item")
        );
    }

    //Les methodes pour définir le nom des entrés
    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(Urcamod2.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }


    //ajout d'un block
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "example_ore") //crée un simple block de pierre
        );
    }

    @SubscribeEvent
    public void eventVirus(AttackEntityEvent event)
    {
        if(player.PlayerData.getVirus()==false && getTarget().PlayerData.getVirus() == true) {
            System.out.println("You are coroned");
            this.player.setVirus(true);
            this.player.addPotionEffect(curse);
        }
        else if(player.PlayerData.getVirus()==true && getTarget().PlayerData.getVirus() == false){
            System.out.println("target coroned");
            this.target.setVirus(true);
            this.target.addPotionEffect(curse);
        }
    }

    @SubscribeEvent
    public void onBiomeDecoration(DecorateBiomeEvent.Decorate event)
    {
        World world = event.getWorld();
        Biome biome = world.getBiomeGenForCoords(event.getPos());
        Random rand = event.getRand();

        if ((biome == Biomes.PLAINS || biome == Biomes.ICE_PLAINS || biome == Biomes.MUTATED_PLAINS || biome == Biomes.EXTREME_HILLS_WITH_TREES || biome == Biomes.MUTATED_EXTREME_HILLS_WITH_TREES) && event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE) {
            if (rand.nextDouble() > 0.1) return;
            int x = rand.nextInt(16) + 8;
            int y = rand.nextInt(16) + 8;

            TintedTreeGenerator gen = new TintedTreeGenerator();

            gen.generateTree(rand, world, world.getHeight(event.getPos().add(x, 0, y)));

            event.setResult(Event.Result.DENY);
        }

        if (event.type == DecorateBiomeEvent.Decorate.EventType.FLOWERS && (event.getResult() == Event.Result.ALLOW || event.getResult() == Event.Result.DEFAULT))
        {
            for (int i = 0; i < Settings.FLOWER_QUANTITY; i++)
            {
                int x = event.chunkX + event.rand.nextInt(16) + 8;
                int y = event.world.getTopSolidOrLiquidBlock(x, z);
                int z = event.chunkZ + event.rand.nextInt(16) + 8;

                Block randomPlant = ModBlocks.plants[event.rand.nextInt(ModBlocks.plants.length)];

                if (event.world.isAirBlock(x, y, z) && (!event.world.provider.hasNoSky || y < 255) && randomPlant.canBlockStay(event.world, x, y, z))
                {
                    event.world.setBlock(x, y, z, randomPlant, 0, 2);
                }
            }
        }

        if (Configs.get(event.getWorld()).decorationIsDisabled(event.getType()))
        {
            event.setResult(Event.Result.DENY);
        }
    }
}
