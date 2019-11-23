class Nav extends LinearOpMode {
  
  String state = "rest";
  
  Drive d = null;
  double x = 0;
  double y = 0;
  double dx = 0;
  double dy = 0;
  double travelled = 0;
  
  private BNO055IMU imu = null;
  private Orientation lastAngles = new Orientation();
  private double globalAngle, power = 0.30, correction;
  Collector collect = null;
  
  Find f = new Find();
  
  public Nav(Drive drive, BNO055IMU acc, Collector c) {
  
      d = drive;
      imu = acc;
      collect = c;
      
      
  }
 
  
  public void turn(double degrees){
      if (getAngle() > 86) {
          d.setPower(0,0,0);
      } else {
          //set the power for the turn
      }
  
  }
  
  public void drive(){
    double h =   Math.sqrt((x-dx)*(x-dx)- (y-dy)*(y-dy));
  
    if (travelled < h){
    d.setPower(1,1,1);
    
    } else {
    
    d.setPower(0,0,0);
    state = "rest";
    
    }
    
  }
  
  public void seek(){
    
    if(f.countSkystones() > 0) {
      double angle = f.getSkystoneAngle();
      if(angle == 0) {
        turn(angle);
      } else {
        //go forward
      }
    }
    
    if(f.getDistance() < /*something*/) {
      collect.in();
    } else {
      collect.out();
    }
    
    if(f.getBlock()) {
      state = "rest";
    }
    
    
    
    
    
  }
  private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }
    
    public void runOpMode() {
      
    }
}