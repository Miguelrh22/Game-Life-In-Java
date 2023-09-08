package Main;


public class Game {
    private Map map = new Map(50,40);
    private Clock clock = new Clock();
    
    private boolean active = true;
    
    public void start(){
        actualizar();
    }
    
    private void actualizar(){
        
        while(active){
            cambioDeCiclo(3);   //espera de 3/2 segundos
            map.showMap();
            map.updateMap();    
            comprobar();
        }
        
    }
    
    
    private void comprobar(){
        if(map.getChanges() == false){
            active = false;
            System.out.println("\n\n-------------------------");
            System.out.println("\n\nCycle "+clock.getCycle()+"\t -- STABLE.\nTest Completed\n");
        }
    }
    
    private void cambioDeCiclo(int nCiclos){
        System.out.println("\n\n-------------------------");
        System.out.println("Cycle: "+clock.getCycle()+", | Cells: "+map.getCell());
        System.out.print("\t.");
        clock.startCycle(nCiclos);
        System.out.println("\n\n-------------------------\n");
    }

}