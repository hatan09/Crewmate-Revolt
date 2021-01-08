package tiles;

import gfx.ImgAssets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(ImgAssets.stone, id);
		
	}
	@Override
	public boolean isSolid() {
		return true;
	}

}
