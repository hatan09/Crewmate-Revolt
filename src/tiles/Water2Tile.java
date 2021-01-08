package tiles;

import gfx.ImgAssets;

public class Water2Tile extends Tile{
		public Water2Tile(int id) {
			super(ImgAssets.water2, id);
			
		}
		
		@Override
		public boolean isSolid() {
			return true;
		}

	}


