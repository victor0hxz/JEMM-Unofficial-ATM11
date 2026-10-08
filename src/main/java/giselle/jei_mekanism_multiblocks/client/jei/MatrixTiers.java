package giselle.jei_mekanism_multiblocks.client.jei;
import java.util.*;
import java.util.function.LongSupplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import mekanism.common.tier.InductionCellTier;
import mekanism.common.tier.InductionProviderTier;
/** Optional addon tiers, reading configured capacities rather than fixed defaults. */
public final class MatrixTiers {
 public record Tier(ItemStack cell,ItemStack provider,LongSupplier capacity,LongSupplier output){}
 public static List<Tier> available(){
  List<Tier> result=new ArrayList<>();String[] names={"basic","advanced","elite","ultimate","absolute","supreme","cosmic","infinite"};
  for(int index=0;index<names.length;index++){
   String namespace=index<4?"mekanism":"mekanism_extras";
   var cell=BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(namespace,names[index]+"_induction_cell"));
   var provider=BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath(namespace,names[index]+"_induction_provider"));
   if(cell==null || cell==Items.AIR || provider==null || provider==Items.AIR)continue;
   int ordinal=index<4?index:index-4;
   LongSupplier capacity=index<4?()->InductionCellTier.values()[ordinal].getMaxEnergy():()->extraValue("ICTier",ordinal,"getMaxEnergy");
   LongSupplier output=index<4?()->InductionProviderTier.values()[ordinal].getOutput():()->extraValue("IPTier",ordinal,"getOutput");
   result.add(new Tier(new ItemStack(cell),new ItemStack(provider),capacity,output));
  }return List.copyOf(result);
 }
 private static long extraValue(String type,int ordinal,String method){
  try{Class<?> clazz=Class.forName("com.jerry.mekextras.common.tier."+type);Object tier=clazz.getEnumConstants()[ordinal];return ((Number)clazz.getMethod(method).invoke(tier)).longValue();}
  catch(ReflectiveOperationException e){throw new IllegalStateException("Cannot read Mekanism Extras matrix tier "+type,e);}
 }
 public static long multiply(long value,int count){return count==0?0:value>Long.MAX_VALUE/count?Long.MAX_VALUE:value*count;}
}
