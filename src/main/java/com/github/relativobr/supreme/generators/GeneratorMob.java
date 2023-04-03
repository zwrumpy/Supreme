package com.github.relativobr.supreme.generators;

import static com.github.relativobr.supreme.util.ItemUtil.getValueGeneratorsWithLimit;

import com.github.relativobr.supreme.Supreme;
import com.github.relativobr.supreme.resource.SupremeComponents;
import com.github.relativobr.supreme.util.ItemGroups;
import com.github.relativobr.supreme.util.SupremeItemStack;
import com.github.relativobr.supreme.util.UtilEnergy;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.AbstractEnergyProvider;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class GeneratorMob extends AbstractEnergyProvider {

  public static final int BASIC_GENERATOR_MOB_ENERGY = 25;
  public static final int BASIC_GENERATOR_MOB_BUFFER = 1000;
  public static final int MEDIUM_GENERATOR_MOB_ENERGY = 50;
  public static final int MEDIUM_GENERATOR_MOB_BUFFER = 4000;
  public static final int ADVANCED_GENERATOR_MOB_ENERGY = 100;
  public static final int ADVANCED_GENERATOR_MOB_BUFFER = 16000;

  public static final SlimefunItemStack GENERATOR_MOB_BASIC = new SupremeItemStack("SUPREME_GENERATOR_MOB_BASIC",
      Material.COMPOSTER, false,
      LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR),
      UtilEnergy.energyBuffer(getValueGeneratorsWithLimit(GeneratorMob.BASIC_GENERATOR_MOB_BUFFER)),
      UtilEnergy.energyPowerPerSecond(getValueGeneratorsWithLimit(GeneratorMob.BASIC_GENERATOR_MOB_ENERGY)));
  public static final ItemStack[] RECIPE_GENERATOR_MOB_BASIC = new ItemStack[]{SlimefunItems.SMALL_CAPACITOR,
      SlimefunItems.SILICON, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ELECTRIC_MOTOR,
      SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.ALUMINUM_INGOT,
      SlimefunItems.SMALL_CAPACITOR};

  public static final SlimefunItemStack GENERATOR_MOB_MEDIUM = new SupremeItemStack("SUPREME_GENERATOR_MOB_MEDIUM",
      Material.COMPOSTER, false,
      LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR),
      UtilEnergy.energyBuffer(getValueGeneratorsWithLimit(GeneratorMob.MEDIUM_GENERATOR_MOB_BUFFER)),
      UtilEnergy.energyPowerPerSecond(getValueGeneratorsWithLimit(GeneratorMob.MEDIUM_GENERATOR_MOB_ENERGY)));
  public static final ItemStack[] RECIPE_GENERATOR_MOB_MEDIUM = new ItemStack[]{GeneratorMob.GENERATOR_MOB_BASIC,
      SlimefunItems.FERROSILICON, GeneratorMob.GENERATOR_MOB_BASIC, SlimefunItems.REINFORCED_PLATE,
      SupremeComponents.SYNTHETIC_RUBY, SlimefunItems.REINFORCED_PLATE, GeneratorMob.GENERATOR_MOB_BASIC,
      SlimefunItems.ALUMINUM_BRONZE_INGOT, GeneratorMob.GENERATOR_MOB_BASIC};

  public static final SlimefunItemStack GENERATOR_MOB_ADVANCED = new SupremeItemStack("SUPREME_GENERATOR_MOB_ADVANCED",
      Material.COMPOSTER, false,
      LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR),
      UtilEnergy.energyBuffer(getValueGeneratorsWithLimit(GeneratorMob.ADVANCED_GENERATOR_MOB_BUFFER)),
      UtilEnergy.energyPowerPerSecond(getValueGeneratorsWithLimit(GeneratorMob.ADVANCED_GENERATOR_MOB_ENERGY)));
  public static final ItemStack[] RECIPE_GENERATOR_MOB_ADVANCED = new ItemStack[]{GeneratorMob.GENERATOR_MOB_MEDIUM,
      SlimefunItems.CARBONADO, GeneratorMob.GENERATOR_MOB_MEDIUM, SlimefunItems.HEATING_COIL, SlimefunItems.PLUTONIUM,
      SlimefunItems.HEATING_COIL, GeneratorMob.GENERATOR_MOB_MEDIUM, SupremeComponents.INDUCTIVE_MACHINE,
      GeneratorMob.GENERATOR_MOB_MEDIUM};

  private int energy;
  private int buffer;
  private int mobRange = 1;

  public GeneratorMob(SlimefunItemStack item, ItemStack[] recipe) {
    super(ItemGroups.ELECTRIC_CATEGORY, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
  }

  @ParametersAreNonnullByDefault
  private boolean isAnimalNearby(Location l) {
    for (Entity e : getEntitiesAroundPoint(l, mobRange)) {
      if (e == null) continue;
      if (!(e.getType() == EntityType.COW || e.getType() == EntityType.SHEEP || e.getType() == EntityType.PIG)) continue;
      return true;
    }
    return false;
  }

  public static List<Entity> getEntitiesAroundPoint(Location location, double radius) {
    List<Entity> entities = new ArrayList<Entity>();
    World world = location.getWorld();

    int smallX = (int) Math.floor((location.getX() - radius) / 16.0D);
    int bigX = (int) Math.floor((location.getX() + radius) / 16.0D);
    int smallZ = (int) Math.floor((location.getZ() - radius) / 16.0D);
    int bigZ = (int) Math.floor((location.getZ() + radius) / 16.0D);

    for (int x = smallX; x <= bigX; x++)
      for (int z = smallZ; z <= bigZ; z++)
        if (world.isChunkLoaded(x, z))
          entities.addAll(Arrays.asList(world.getChunkAt(x, z).getEntities()));

    Iterator<Entity> entityIterator = entities.iterator();
    while (entityIterator.hasNext()) {
      if (entityIterator.next().getLocation().distanceSquared(location) > radius * radius) {
        entityIterator.remove();
      }
    }
    return entities;
  }

  @ParametersAreNonnullByDefault
  private boolean isValidAnimal(Entity n) {
    return n instanceof Cow || n instanceof Sheep || n instanceof Pig;
  }

  public final GeneratorMob setEnergy(int value) {
    this.energy = value;
    return this;
  }

  public final GeneratorMob setBuffer(int value) {
    this.buffer = value;
    return this;
  }

  public final GeneratorMob setMobRange(int value) {
    this.mobRange = value;
    return this;
  }

  @Nonnull
  @Override
  public ItemStack getProgressBar() {
    return new ItemStack(Material.OBSERVER);
  }

  @Override
  public int getEnergyProduction() {
    return energy;
  }

  @Override
  protected void registerDefaultFuelTypes() {
  }

  @Override
  public int getGeneratedOutput(Location l, Config config) {
    if (l != null) {
      return isAnimalNearby(l) ? getEnergyProduction() : 0;
    }
    return 0;
  }

  @Override
  public int getCapacity() {
    return this.buffer;
  }

  @Override
  public int[] getInputSlots() {
    return new int[0];
  }

  @Override
  public int[] getOutputSlots() {
    return new int[0];
  }
}
