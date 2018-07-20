//package thundr.redstonerepository.items.tools;
//
//import cofh.api.energy.IEnergyContainerItem;
//import cofh.lib.util.helpers.EnergyHelper;
//import cofh.lib.util.helpers.StringHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.Items;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemHoe;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.EnumChatFormatting;
//import net.minecraft.world.World;
//import thundr.redstonerepository.RedstoneRepository;
//import thundr.redstonerepository.RedstoneRepository;
//import thundr.redstonerepository.util.Utils;
//
//import java.util.List;
//
//public class ItemPotahoeFluxed extends ItemHoe implements IEnergyContainerItem {
//    //TODO: Revisit this item
//    public static int capacitorCapacity = 16000;
//    public static int send = 80;
//    public static int receive = 0;
//
//    public ItemPotahoeFluxed() {
//        super(ToolMaterial.WOOD);
//        setUnlocalizedName(RedstoneRepository.ID + ".tool.potato.hoe");
//        setTextureName(RedstoneRepository.ID + ":tools/potahoe");
//        setCreativeTab(RedstoneRepository.tabRArm);
//        setMaxDamage(0);
//    }
//
//    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int hitSide, float hitX, float hitY, float hitZ) {
//
//        if (!player.capabilities.isCreativeMode)
//            extractEnergy(stack, send, false);
//
//        return super.onItemUse(stack, player, world, x, y, z, hitSide, hitX, hitY, hitZ);
//    }
//
//    @Override
//    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean held) {
//        if (!world.isRemote) {
//            if (getEnergyStored(stack) <= 0) {
//                ((EntityPlayer) entity).inventory.decrStackSize(slot, 1);
//                ((EntityPlayer) entity).inventory.addItemStackToInventory(new ItemStack(Items.baked_potato, 2));
//
//            }
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public void getSubItems(Item item, CreativeTabs tabs, List list) {
//        list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item), capacitorCapacity));
//    }
//
//    @Override
//    public boolean showDurabilityBar(ItemStack stack) {
//        return true;
//    }
//
//    @Override
//    public double getDurabilityForDisplay(ItemStack stack) {
//        if (stack.stackTagCompound == null)
//            EnergyHelper.setDefaultEnergyTag(stack, 0);
//
//        int currentEnergy = stack.stackTagCompound.getInteger("Energy");
//
//        return 1.0 - ((double) currentEnergy / (double) capacitorCapacity);
//    }
//
//    @SideOnly(Side.CLIENT)
//    @SuppressWarnings("unchecked")
//    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held) {
//
//        if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown())
//            list.add(StringHelper.shiftForDetails());
//
//        if (stack.stackTagCompound == null)
//            EnergyHelper.setDefaultEnergyTag(stack, 0);
//
//        if (StringHelper.isShiftKeyDown()) {
//            list.add(StringHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + capacitorCapacity + " RF");
//            list.add(EnumChatFormatting.GOLD + Utils.localizeFormatted("info.RArm.tooltip.peruse", "" + send));
//        }
//    }
//
//    // Energy Stuff
//
//    @Override
//    public int receiveEnergy(ItemStack stack, int i, boolean simulate) {
//        if (stack.stackTagCompound == null)
//            EnergyHelper.setDefaultEnergyTag(stack, 0);
//
//        int energy = stack.stackTagCompound.getInteger("Energy");
//        int energyReceived = Math.min(i, Math.min(capacitorCapacity - energy, receive));
//
//        if (!simulate) {
//            energy += energyReceived;
//            stack.stackTagCompound.setInteger("Energy", energy);
//        }
//
//        return energyReceived;
//    }
//
//    @Override
//    public int extractEnergy(ItemStack stack, int extract, boolean simulate) {
//        if (stack.stackTagCompound == null)
//            EnergyHelper.setDefaultEnergyTag(stack, 0);
//
//        int energy = stack.stackTagCompound.getInteger("Energy");
//        int energyExtracted = Math.min(extract, Math.min(energy, send));
//
//        if (!simulate) {
//            energy -= energyExtracted;
//            stack.stackTagCompound.setInteger("Energy", energy);
//        }
//
//        return energyExtracted;
//    }
//
//    @Override
//    public int getEnergyStored(ItemStack stack) {
//        if (stack.stackTagCompound == null)
//            EnergyHelper.setDefaultEnergyTag(stack, 0);
//
//        return stack.stackTagCompound.getInteger("Energy");
//    }
//
//    @Override
//    public int getMaxEnergyStored(ItemStack stack) {
//        return capacitorCapacity;
//    }
//}
