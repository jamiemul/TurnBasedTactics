package com.zombie.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class Textures {

    public enum UI_TILES {
        selectedTile("ui_tiles/tile_select.png"),
        highlightTile("ui_tiles/tile_high.png"),
        exploredTile("ui_tiles/tile_explored.png"),
        explored("ui_tiles/tile_path.png");
        public final TextureManager.TEXTURE<UI_TILES> texture;

        UI_TILES(String textureName) {
            texture = new TextureManager.TEXTURE(textureName);
        }
    }

    public enum TILE_OBJECTS {
        grassTrunk("tile_objects/grass-trunk.png", true, 3, 1, List.of((TILES.grass))),
        grass1YF("tile_objects/grass-1y-fl.png", true, 0, 5, List.of((TILES.grass))),
        grass9YF("tile_objects/grass-9y-fl.png", true, 0, 5, List.of((TILES.grass))),
        grassBush("tile_objects/grass-bush.png", true, 3, 5, List.of((TILES.grass))),
        grassLeaves("tile_objects/grass-leaves.png", true, 0, 20, List.of((TILES.grass))),
        treeDead("tile_objects/tree-dead.png", false, 0, 1, List.of((TILES.grass))),
        treeOak("tile_objects/tree-oak.png", false, 0, 1, List.of((TILES.grass))),
        tree1("tile_objects/tree1.png", false, 0, 1, List.of((TILES.grass))),
        tree2("tile_objects/tree2.png", false, 0, 1, List.of((TILES.grass)));

        public final TextureManager.TEXTURE<TILE_OBJECTS> texture;
        public final Boolean walkable;
        public final int movementCost;
        public int weighting;
        public final List<TILES> validTiles;

        TILE_OBJECTS(String textureName, Boolean walkable, int movementCost, int weighting, List<TILES> validTiles) {
            this.walkable = walkable;
            this.movementCost = movementCost;
            this.weighting = weighting;
            this.validTiles = validTiles;
            texture = new TextureManager.TEXTURE(textureName);
        }

        public void render(SpriteBatch batch, int screenX, int screenY) {
            batch.draw(TextureManager.getAsset(this.texture.textureName), screenX, screenY, this.texture.width, this.texture.width);
        }
    }

    public enum TILES {
        leftWall("tiles/wall-left.png", true, 0),
        rightWall("tiles/wall-right.png", true, 0),
        floor("tiles/tile_floor.png", true, 0),
        grass("tiles/tile_grass.png", true, 0),
        road("tiles/tile_road.png", true, 0),
        roadLines("tiles/tile_road_lines_l.png", true, 0);

        public final TextureManager.TEXTURE<TILES> texture;
        public final Boolean walkable;
        public final int cost;

        TILES(String textureName, Boolean walkable, int cost) {
            this.walkable = walkable;
            this.cost = cost;
            texture = new TextureManager.TEXTURE(textureName, this);
        }
    }

    public enum UNITS {
        zombie("sprites/zombie.png"),
        human("sprites/directional_sprite.png");
        public final TextureManager.TEXTURE<UNITS> texture;

        UNITS(String textureName) {
            texture = new TextureManager.TEXTURE(textureName, this.getClass());
        }
    }


}
