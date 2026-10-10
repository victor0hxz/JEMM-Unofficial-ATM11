package giselle.jei_mekanism_multiblocks.client.jei.category;

/** Layer guide matching Extras' NaquadahReactorValidator, viewed from above. */
public final class NaquadahLayout {
    private static final int[][] GRID = {
        {0,0,0,1,1,1,0,0,0},{0,1,1,2,2,2,1,1,0},{0,1,2,2,2,2,2,1,0},
        {1,2,2,2,2,2,2,2,1},{1,2,2,2,2,2,2,2,1},{1,2,2,2,2,2,2,2,1},
        {0,1,2,2,2,2,2,1,0},{0,1,1,2,2,2,1,1,0},{0,0,0,1,1,1,0,0,0}
    };
    private NaquadahLayout() {}
    public static char block(int x, int y, int z) {
        boolean wall = ((x == 0 || x == 8) && GRID[y][z] != 0)
            || ((y == 0 || y == 8) && GRID[x][z] != 0)
            || ((z == 0 || z == 8) && GRID[x][y] != 0);
        if (!wall) return '.';
        if (x == 4 && y == 8 && z == 4) return 'R';
        if (x == 4 && y == 4 && z == 0) return 'L';
        if ((x == 3 || x == 5) && y == 4 && z == 8) return 'P';
        return 'C';
    }
}
