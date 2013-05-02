package pjak.psmelter.enums;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public enum Locations {

	EDGEVILLE(new Area(new Tile[] {
			new Tile(3090, 3500, 0), new Tile(3099, 3500, 0),
			new Tile(3099, 3487, 0), new Tile(3090, 3487, 0) }),  new Area(new Tile[] {
					new Tile(3105, 3504, 0), new Tile(3112, 3504, 0),
					new Tile(3112, 3496, 0), new Tile(3105, 3496, 0) }), 26814),
	AL_KHARID(new Area(new Tile[] {
			new Tile(3267, 3175, 0), new Tile(3274, 3175, 0),
			new Tile(3274, 3160, 0), new Tile(3267, 3156, 0) }), new Area(new Tile(3272, 3192, 0),
					new Tile(3278, 3188, 0)), 76293);
	
	private Area BANK_AREA;
	private Area FURNACE_AREA;
	private int FURNACE_ID;

	
	Locations(final Area BANK_AREA, final Area FURNACE_AREA, final int FURNACE_ID) {
		this.BANK_AREA = BANK_AREA;
		this.FURNACE_AREA = FURNACE_AREA;
		this.FURNACE_ID = FURNACE_ID;
	}
	
	public Area getBankArea() {
		return BANK_AREA;
	}
	
	public Area getFurnaceArea() {
		return FURNACE_AREA;
	}
	
	public int getFurnaceID() {
		return FURNACE_ID;
	}
}
