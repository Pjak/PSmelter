package pjak.psmelter.enums;

import org.powerbot.game.api.methods.tab.Inventory;

public enum Bars
{
    IRON(440,-1,false, 2351, new int[]{28}, 10, "Iron"),
    STEEL(440,453,true, 2353, new int[]{9,18}, 18, "Steel"),
    BRONZE(438,436,true, 2349, new int[]{14,14}, 2, "Bronze"),
    MITHRIL(447,453,true, 2359, new int[]{5,20}, 30, "Mithril"),
    ADAMANT(449,453,true, 2361, new int[]{4,24}, 34, "Adamant"),
    RUNITE(451,453,true, 2363, new int[]{3,24}, 58, "Rune"),
    GOLD(444,-1,false, 2357, new int[]{28}, 22, "Gold"),
    SILVER(442,-1,false, 2351, new int[]{28}, 14, "Silver");

    private final int primaryOreID;//primary ore id
    private final int secondaryOreID;//secondary ore id can be coal or other secondary
    private final boolean hasSecondary;//returns if uses secondary ingredient/ore
	private final int barID;
	private final int[] withdrawAmount;
	private final String name;
	private final int widgChild;
    
    Bars(final int primaryOreID, final int secondaryOreID, final boolean hasSecondary, final int barID, final int[] withdrawAmount, final int widgChild, final String name){
    	this.primaryOreID = primaryOreID;
        this.secondaryOreID = secondaryOreID;
        this.hasSecondary = hasSecondary;
        this.barID = barID;
        this.withdrawAmount = withdrawAmount;
        this.name = name;
        this.widgChild = widgChild;
    }
    
    public int getPrimaryOreID() {
    	return primaryOreID;
    	}
    
    public int getSecondaryOreID() {
    	return secondaryOreID;
    }
    
    public boolean HasSecondary() {
    	return hasSecondary;
    	}
    
    public int getBarID(){
    	return barID;
    	}
    
    public int[] getWithdrawAmount() {
    	return withdrawAmount;
    }
    
    public String getName(){
        return name;
    }
    
    public int getWidgChild() {
    	return widgChild;
    }
    
    	public boolean hasMinimum(){
            if(HasSecondary())
                return Inventory.contains(getPrimaryOreID()) && Inventory.contains(getSecondaryOreID());
            else
                return Inventory.contains(getPrimaryOreID());
        }
}