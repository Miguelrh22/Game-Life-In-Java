package Main;

import java.util.Random;


public class Map {
    private final int WIDTH;
    private final int HEIGHT;

    private int[][] map;
    
    private final Random rn = new Random();
    
    private final int STEPS = rn.nextInt(200,800);
    private boolean hasChanges = false;
    private int nCells = 0;
    
    public Map(int WIDTH, int HEIGHT) {
        this.WIDTH = Math.max(32, Math.min(WIDTH, 100));
        this.HEIGHT = Math.max(32, Math.min(HEIGHT, 100));
        createMap();
    }
    
    private void createMap(){
        map = new int[WIDTH][HEIGHT];
        fillRandomMap();
        
    }
    private void fillRandomMap(){
        int sx = WIDTH/2;
        int sy = HEIGHT/2;
        
        int cx = sx;
        int cy = sy;
        
        int dir = 0;
        int xdir;
        int ydir;
        
        
        int steps = STEPS;
        int csteps = 10;
        
        while(steps > 0){
            if(map[cx][cy] == 0){
                map[cx][cy] = 1;
                steps--;
                csteps--;
            }
            
            if(csteps == 0){
                csteps = rn.nextInt(5, 20);
                cx = sx;
                cy = sy;
            }
            
            if(rn.nextInt(0,2) == 1){
                dir = rn.nextInt(0,4)*90;
            }
            
            xdir = (int) Math.cos(dir*Math.PI/180);
            ydir = (int) Math.sin(dir*Math.PI/180);
            
            cx += xdir;
            cy += ydir;
            
            if(cx < 2 || cx >= WIDTH-2) cx += -xdir*3; 
            if(cy < 2 || cy >= HEIGHT-2) cy += -ydir*3; 
            
        }
    }

    public void showMap(){
        nCells = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if(map[j][i] == 0){
                    System.out.print(" ");
                } else {
                    nCells++;
                    System.out.print("#");
                }
            }
            System.out.print("\n");
        }
    }
    
    public boolean getChanges(){
        return hasChanges;
    }
    public int getCell(){
        return nCells;
    }
    
    public void updateMap(){
        hasChanges = false;
        int[][] auxMap = new int[WIDTH][HEIGHT];
        
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                auxMap[i][j] = map[i][j];
            }
        }
        
        
        for (int i = 1; i < WIDTH-1; i++) {
            for (int j = 1; j < HEIGHT-1; j++) {
                
                int collideCells = map[i][j-1] + map[i][j+1] + map[i+1][j] + map[i-1][j];
                int nearCells = collideCells + map[i-1][j-1] + map[i-1][j+1] + map[i+1][j-1] + map[i+1][j+1];
                
                
                if(map[i][j] == 1){
                    //cell die
                    if(nearCells != 2 && nearCells != 3){
                        auxMap[i][j] = 0; 
                        hasChanges = true;
                    }
                } else {
                    //cell create
                    if(nearCells == 3){
                        auxMap[i][j] = 1; 
                        hasChanges = true;
                    }
                }
                
                
            }
        }
        
        map = auxMap;
    }

}