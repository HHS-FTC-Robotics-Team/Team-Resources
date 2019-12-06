package org.firstinspires.ftc.teamcode;


public class Niranjan {
    
    private String message;
    
    public int x; 
    public int y;
    public int speed;
    public Boolean cute;
    
    public Niranjan() {
        x = 0;
        y = 0;
        speed = 1000000;
        cute = true;
    }
    
    public String speak()  {
        return message;
    }
    
    public void update() {
        x = x+1;
    }
    // todo: write your code here
}
